package org.unlogged.springwebfluxdemo.repository.flow1;

import org.unlogged.springwebfluxdemo.model.Staff;
import org.unlogged.springwebfluxdemo.model.StaffDTO;
import org.unlogged.springwebfluxdemo.model.StaffSaveRequest;
import org.unlogged.springwebfluxdemo.model.UniversityProfile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RXjavaSqlRepoCE extends BaseRepository {
    public Flux<Integer> getAllStaffNames();

    public Mono<StaffDTO> findStaffById(int id);

    Mono<Boolean> saveStaff(StaffSaveRequest request);

    public Mono<Boolean> updateStaffNameForId(StaffSaveRequest request);

    public Mono<Boolean> deleteStaff(int id);

    public Mono<UniversityProfile> getUniversityProfile(String universityId);
    public Flux<StaffDTO> getStaffForUniversity(String universityId);
}
