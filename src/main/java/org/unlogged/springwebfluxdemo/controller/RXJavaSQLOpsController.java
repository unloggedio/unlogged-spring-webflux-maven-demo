package org.unlogged.springwebfluxdemo.controller;

import com.github.davidmoten.rx.jdbc.ConnectionProvider;
import com.github.davidmoten.rx.jdbc.ConnectionProviderFromUrl;
import com.github.davidmoten.rx.jdbc.Database;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.unlogged.springwebfluxdemo.model.Staff;
import org.unlogged.springwebfluxdemo.model.StaffDTO;
import org.unlogged.springwebfluxdemo.model.StaffSaveRequest;
import reactor.core.publisher.Mono;
import rx.Observable;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rxjdbc")
public class RXJavaSQLOpsController {
    public static ConnectionProvider connectionProvider
            = new ConnectionProviderFromUrl(
            "jdbc:mysql://localhost:3306/unlogged_demo", "root", "");
    private Database db = Database.from(connectionProvider);

    @RequestMapping("/all")
    public List<String> getData1() {
        List<String> names = db.select(
                        "select id from STAFF where id < ?")
                .parameter(100)
                .getAs(String.class)
                .toList()
                .toBlocking()
                .single();

        return names;
    }

    @RequestMapping("/get")
    public StaffDTO findStaffById(@RequestParam int id) {
        Staff staffresult = db.select("select id, name from STAFF where id=" + id)
                .autoMap(Staff.class)
                .toBlocking()
                .single();
        return new StaffDTO(staffresult);
    }

    public Integer saveStaff(StaffSaveRequest request) {
        Observable<Integer> obs = db.update("insert into STAFF(id, name) " +
                        "VALUES(" + request.getId() + ", '" + request.getName() + "')")
                .count();
        return obs.toBlocking().single();
    }
}
