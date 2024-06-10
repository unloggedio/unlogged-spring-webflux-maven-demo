//package org.unlogged.springwebfluxdemo.controller;
//
//import com.github.davidmoten.rx.jdbc.ConnectionProvider;
//import com.github.davidmoten.rx.jdbc.ConnectionProviderFromUrl;
//import com.github.davidmoten.rx.jdbc.Database;
//import com.github.davidmoten.rx.jdbc.exceptions.SQLRuntimeException;
//import jakarta.annotation.PostConstruct;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.unlogged.springwebfluxdemo.model.Staff;
//import org.unlogged.springwebfluxdemo.model.StaffDTO;
//import org.unlogged.springwebfluxdemo.model.StaffSaveRequest;
//import reactor.core.publisher.Mono;
//import rx.Observable;
//
//import java.util.List;
//import java.util.NoSuchElementException;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/rxjdbc")
//public class RXJavaSQLOpsController {
//
//    public static ConnectionProvider dbPathConnectionProvider
//            = new ConnectionProviderFromUrl(
//            "jdbc:mysql://localhost:3306/", "root", "kartikey");
//    private Database db = null;
//    private Database dbPathCheck = Database.from(dbPathConnectionProvider);
//
//    @PostConstruct
//    public void postConstruct() {
//        System.out.println("Post Construct creation");
//        if (shouldCreateNewDB()) {
//            System.out.println("Creating new DB");
//            createTablesAndSeed();
//        } else {
//            System.out.println("DB exists, no need to seed or create.");
//            db = Database.from(new ConnectionProviderFromUrl(
//                    "jdbc:mysql://localhost:3306/udemo", "root", "kartikey"));
//        }
//    }
//
//    public boolean shouldCreateNewDB() {
//        try {
//            String dbname = dbPathCheck.select("SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'udemo'")
//                    .getAs(String.class)
//                    .toBlocking()
//                    .single();
//            if (dbname == null || dbname.isEmpty()) {
//                return true;
//            }
//            return false;
//        } catch (SQLRuntimeException e) {
//            //no db exists with this name, create it
//        } catch (NoSuchElementException noSuchElementException) {
//            //db doesn't exist
//        }
//        return true;
//    }
//
//    public void createTablesAndSeed() {
//        Observable<Integer> createDB = dbPathCheck.update(
//                        "CREATE DATABASE IF NOT EXISTS udemo")
//                .count();
//        createDB.toBlocking().single();
//
//        db = Database.from(new ConnectionProviderFromUrl(
//                "jdbc:mysql://localhost:3306/udemo", "root", "kartikey"));
//
//        Observable<Integer> createUniversity = db.update(
//                        "CREATE TABLE IF NOT EXISTS UNIVERSITY("
//                                + "id int primary key AUTO_INCREMENT, name varchar(255), address varchar(255))")
//                .dependsOn(createDB)
//                .count();
//        createUniversity.toBlocking().single();
//        Observable<Integer> createStaff = db.update(
//                        "CREATE TABLE IF NOT EXISTS STAFF("
//                                + "id int primary key AUTO_INCREMENT, name varchar(255))")
//                .dependsOn(createDB)
//                .dependsOn(createUniversity)
//                .count();
//        createStaff.toBlocking().single();
//        Observable<Integer> createUniversityStaffBinding = db.update(
//                        "CREATE TABLE IF NOT EXISTS UNIVERSITY_STAFF("
//                                + "staff_id int , university_id int," +
//                                "FOREIGN KEY(university_id) REFERENCES UNIVERSITY(id)," +
//                                "FOREIGN KEY(staff_id) REFERENCES STAFF(id))")
//                .dependsOn(createStaff)
//                .dependsOn(createUniversity)
//                .count();
//        createUniversityStaffBinding.toBlocking().single();
//        Observable<Integer> staff1Insert = db.update("insert into STAFF(name) " +
//                        "VALUES('staff1')")
//                .dependsOn(createUniversityStaffBinding)
//                .count();
//        Observable<Integer> staff2Insert = db.update("insert into STAFF(name) " +
//                        "VALUES('staff2')")
//                .dependsOn(createUniversityStaffBinding)
//                .count();
//        staff1Insert.toBlocking().single();
//        staff2Insert.toBlocking().single();
//
//        Observable<Integer> university1Insert = db.update("insert into UNIVERSITY(name,address) " +
//                        "VALUES('university1','place1')")
//                .dependsOn(createUniversityStaffBinding)
//                .count();
//        Observable<Integer> university2Insert = db.update("insert into UNIVERSITY(name,address) " +
//                        "VALUES('university2','place2')")
//                .dependsOn(createUniversityStaffBinding)
//                .count();
//
//        university1Insert.toBlocking().single();
//        university2Insert.toBlocking().single();
//
//        Observable<Integer> binding1 = db.update("insert into UNIVERSITY_STAFF(staff_id,university_id) " +
//                        "VALUES(1,1)")
//                .dependsOn(university1Insert)
//                .dependsOn(staff1Insert)
//                .count();
//        Observable<Integer> binding2 = db.update("insert into UNIVERSITY_STAFF(staff_id,university_id) " +
//                        "VALUES(2,2)")
//                .dependsOn(university2Insert)
//                .dependsOn(staff2Insert)
//                .count();
//        binding1.toBlocking().single();
//        binding2.toBlocking().single();
//    }
//
//    @RequestMapping("/all")
//    public List<String> getData1() {
//        List<String> names = db.select(
//                        "select id from STAFF where id < ?")
//                .parameter(100)
//                .getAs(String.class)
//                .toList()
//                .toBlocking()
//                .single();
//
//        return names;
//    }
//
//    @RequestMapping("/get")
//    public StaffDTO findStaffById(@RequestParam int id) {
//        Staff staffresult = db.select("select id, name from STAFF where id=" + id)
//                .autoMap(Staff.class)
//                .toBlocking()
//                .single();
//        return new StaffDTO(staffresult);
//    }
//
//    public Integer saveStaff(StaffSaveRequest request) {
//        Observable<Integer> obs = db.update("insert into STAFF(id, name) " +
//                        "VALUES(" + request.getId() + ", '" + request.getName() + "')")
//                .count();
//        return obs.toBlocking().single();
//    }
//}
