package org.unlogged.springwebfluxdemo.repository.flow1;

import com.github.davidmoten.rx.jdbc.ConnectionProvider;
import com.github.davidmoten.rx.jdbc.ConnectionProviderFromUrl;
import com.github.davidmoten.rx.jdbc.Database;
import org.springframework.stereotype.Repository;
import org.unlogged.springwebfluxdemo.model.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rx.Observable;

import java.util.List;

@Repository
public class RXjavaSqlRepoImpl implements RxJavaSqlRepo {

    public static ConnectionProvider connectionProvider
            = new ConnectionProviderFromUrl(
            "jdbc:mysql://localhost:3306/unlogged_demo", "root", "");
    private Database db = Database.from(connectionProvider);

    @Override
    public Flux<String> getAllStaffNames() {
        List<String> names = db.select(
                        "select id from STAFF where id < ?")
                .parameter(10)
                .getAs(String.class)
                .toList()
                .toBlocking()
                .single();
        return Flux.just(names.toArray(new String[names.size()]));
    }

    @Override
    public Mono<StaffDTO> findStaffById(int id) {
        Staff staffresult = db.select("select id, name from STAFF where id=" + id)
                .autoMap(Staff.class)
                .toBlocking()
                .single();
        return Mono.just(new StaffDTO(staffresult));
    }

    @Override
    public Flux<StaffDTO> getStaffForUniversity(String universityId) {
        Staff staffresult = db.select("select s.id, s.name from STAFF s, UNIVERSITY_STAFF us where us.university_id='" + universityId + "' and s.id=us.staff_id")
                .autoMap(Staff.class)
                .toBlocking()
                .single();
        return Flux.just(new StaffDTO(staffresult));
    }

    @Override
    public Mono<Boolean> saveStaff(StaffSaveRequest request) {
        Observable<Integer> observable = db.update("insert into STAFF(id, name) VALUES(" + request.getId() + ", '" + request.getName() + "')").count();
        return Mono.just(observable.toBlocking().single() == 1);
    }

    @Override
    public Mono<Boolean> updateStaffNameForId(StaffSaveRequest request) {
        Observable<Integer> observable = db.update("update STAFF set " +
                "name='" + request.getName() + "' where id=" + request.getId() + "").count();
        return Mono.just(observable.toBlocking().single() == 1);
    }

    @Override
    public Mono<Boolean> deleteStaff(int id) {
        Observable<Boolean> transactionStart = db.beginTransaction();
        Observable<Integer> observable = db.update("delete from STAFF where id=" + id)
                .dependsOn(transactionStart)
                .count();
        return Mono.just(observable.toBlocking().single() == 1);
    }

    @Override
    public Mono<UniversityProfile> getUniversityProfile(String universityId) {
        University university = db.select("select id, name, address from UNIVERSITY where id=" + universityId)
                .autoMap(University.class)
                .toBlocking()
                .single();
        return Mono.just(new UniversityProfile(university));
    }
}
