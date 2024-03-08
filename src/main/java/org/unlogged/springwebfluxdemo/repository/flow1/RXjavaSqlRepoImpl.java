package org.unlogged.springwebfluxdemo.repository.flow1;

import com.github.davidmoten.rx.jdbc.ConnectionProvider;
import com.github.davidmoten.rx.jdbc.ConnectionProviderFromUrl;
import com.github.davidmoten.rx.jdbc.Database;
import org.springframework.stereotype.Repository;
import org.unlogged.springwebfluxdemo.helper.SqlDbConnectionHelper;
import org.unlogged.springwebfluxdemo.model.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rx.Observable;

import java.util.List;

@Repository
public class RXjavaSqlRepoImpl implements RxJavaSqlRepo {

    private Database db = null;

    @Override
    public Flux<Integer> getAllStaffNames() {
        if (db == null) {
            db = SqlDbConnectionHelper.getDB();
        }
        List<Integer> ids = db.select(
                        "select id from STAFF where id < ?")
                .parameter(10)
                .getAs(Integer.class)
                .toList()
                .toBlocking()
                .single();
        return Flux.just(ids.toArray(new Integer[ids.size()]));
    }

    @Override
    public Mono<StaffDTO> findStaffById(int id) {
        if (db == null) {
            db = SqlDbConnectionHelper.getDB();
        }
        Staff staffresult = db.select("select id, name from STAFF where id=" + id)
                .autoMap(Staff.class)
                .toBlocking()
                .single();
        return Mono.just(new StaffDTO(staffresult));
    }

    @Override
    public Flux<StaffDTO> getStaffForUniversity(int universityId) {
        if (db == null) {
            db = SqlDbConnectionHelper.getDB();
        }
        Staff staffresult = db.select("select s.id, s.name from STAFF s, UNIVERSITY_STAFF us where us.university_id='" + universityId + "' and s.id=us.staff_id")
                .autoMap(Staff.class)
                .toBlocking()
                .single();
        return Flux.just(new StaffDTO(staffresult));
    }

    @Override
    public Mono<Boolean> saveStaff(StaffSaveRequest request) {
        if (db == null) {
            db = SqlDbConnectionHelper.getDB();
        }
        Observable<Integer> observable = db.update("insert into STAFF(id, name) VALUES(" + request.getId() + ", '" + request.getName() + "')").count();
        return Mono.just(observable.toBlocking().single() == 1);
    }

    @Override
    public Mono<Boolean> updateStaffNameForId(StaffSaveRequest request) {
        if (db == null) {
            db = SqlDbConnectionHelper.getDB();
        }
        Observable<Integer> observable = db.update("update STAFF set " +
                "name='" + request.getName() + "' where id=" + request.getId() + "").count();
        return Mono.just(observable.toBlocking().single() == 1);
    }

    @Override
    public Mono<Boolean> deleteStaff(int id) {
        if (db == null) {
            db = SqlDbConnectionHelper.getDB();
        }
        Observable<Boolean> transactionStart = db.beginTransaction();
        Observable<Integer> observable = db.update("delete from STAFF where id=" + id)
                .dependsOn(transactionStart)
                .count();
        return Mono.just(observable.toBlocking().single() == 1);
    }

    @Override
    public Mono<UniversityProfile> getUniversityProfile(int universityId) {
        if (db == null) {
            db = SqlDbConnectionHelper.getDB();
        }
        University university = db.select("select id, name, address from UNIVERSITY where id=" + universityId)
                .autoMap(University.class)
                .toBlocking()
                .single();
        return Mono.just(new UniversityProfile(university));
    }
}
