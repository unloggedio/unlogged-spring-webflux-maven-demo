package org.unlogged.springwebfluxdemo.helper;

import com.github.davidmoten.rx.jdbc.ConnectionProvider;
import com.github.davidmoten.rx.jdbc.ConnectionProviderFromUrl;
import com.github.davidmoten.rx.jdbc.Database;
import com.github.davidmoten.rx.jdbc.exceptions.SQLRuntimeException;
import rx.Observable;

import java.util.NoSuchElementException;

public class SqlDbConnectionHelper {

    public static Database getDB() {
        ConnectionProvider dbPathConnectionProvider
                = new ConnectionProviderFromUrl(
                "jdbc:mysql://localhost:3306/", "root", "root_password");
        Database dbPathCheck = Database.from(dbPathConnectionProvider);
        return createDBifNeeded(dbPathCheck);
    }


    public static Database createDBifNeeded(Database pathCheck) {
        System.out.println("Post Construct creation");
        if (shouldCreateNewDB(pathCheck)) {
            System.out.println("Creating new DB");
            return createTablesAndSeed(pathCheck);
        } else {
            System.out.println("DB exists, no need to seed or create.");
           return Database.from(new ConnectionProviderFromUrl(
                    "jdbc:mysql://localhost:3306/udemo2", "root", "root_password"));
        }
    }

    public static boolean shouldCreateNewDB(Database dbPathCheck) {
        try {
            String dbname = dbPathCheck.select("SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = 'udemo2'")
                    .getAs(String.class)
                    .toBlocking()
                    .single();
            if (dbname == null || dbname.isEmpty()) {
                return true;
            }
            return false;
        } catch (SQLRuntimeException e) {
            //no db exists with this name, create it
        } catch (NoSuchElementException noSuchElementException) {
            //db doesn't exist
        }
        return true;
    }

    public static Database createTablesAndSeed(Database pathCheck) {
        Observable<Integer> createDB = pathCheck.update(
                        "CREATE DATABASE IF NOT EXISTS udemo2")
                .count();
        createDB.toBlocking().single();

        Database db = Database.from(new ConnectionProviderFromUrl(
                "jdbc:mysql://localhost:3306/udemo2", "root", "root_password"));

        Observable<Integer> createUniversity = db.update(
                        "CREATE TABLE IF NOT EXISTS UNIVERSITY("
                                + "id int primary key AUTO_INCREMENT, name varchar(255), address varchar(255))")
                .dependsOn(createDB)
                .count();
        createUniversity.toBlocking().single();
        Observable<Integer> createStaff = db.update(
                        "CREATE TABLE IF NOT EXISTS STAFF("
                                + "id int primary key AUTO_INCREMENT, name varchar(255))")
                .dependsOn(createDB)
                .dependsOn(createUniversity)
                .count();
        createStaff.toBlocking().single();
        Observable<Integer> createUniversityStaffBinding = db.update(
                        "CREATE TABLE IF NOT EXISTS UNIVERSITY_STAFF("
                                + "staff_id int , university_id int," +
                                "FOREIGN KEY(university_id) REFERENCES UNIVERSITY(id)," +
                                "FOREIGN KEY(staff_id) REFERENCES STAFF(id))")
                .dependsOn(createStaff)
                .dependsOn(createUniversity)
                .count();
        createUniversityStaffBinding.toBlocking().single();
        Observable<Integer> staff1Insert = db.update("insert into STAFF(name) " +
                        "VALUES('staff1')")
                .dependsOn(createUniversityStaffBinding)
                .count();
        Observable<Integer> staff2Insert = db.update("insert into STAFF(name) " +
                        "VALUES('staff2')")
                .dependsOn(createUniversityStaffBinding)
                .count();
        staff1Insert.toBlocking().single();
        staff2Insert.toBlocking().single();

        Observable<Integer> university1Insert = db.update("insert into UNIVERSITY(name,address) " +
                        "VALUES('university1','place1')")
                .dependsOn(createUniversityStaffBinding)
                .count();
        Observable<Integer> university2Insert = db.update("insert into UNIVERSITY(name,address) " +
                        "VALUES('university2','place2')")
                .dependsOn(createUniversityStaffBinding)
                .count();

        university1Insert.toBlocking().single();
        university2Insert.toBlocking().single();

        Observable<Integer> binding1 = db.update("insert into UNIVERSITY_STAFF(staff_id,university_id) " +
                        "VALUES(1,1)")
                .dependsOn(university1Insert)
                .dependsOn(staff1Insert)
                .count();
        Observable<Integer> binding2 = db.update("insert into UNIVERSITY_STAFF(staff_id,university_id) " +
                        "VALUES(2,2)")
                .dependsOn(university2Insert)
                .dependsOn(staff2Insert)
                .count();
        binding1.toBlocking().single();
        binding2.toBlocking().single();
        return db;
    }
}
