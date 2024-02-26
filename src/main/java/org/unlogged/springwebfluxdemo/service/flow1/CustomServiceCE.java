package org.unlogged.springwebfluxdemo.service.flow1;

import org.unlogged.springwebfluxdemo.model.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomServiceCE {

    Flux<Integer> getAllStaffNames();

    Flux<Coffee> getCoffeeList();

    Mono<StaffDTO> getStaffById(int id);

    Mono<Boolean> addStaff(StaffSaveRequest request);

    Mono<Boolean> updateStaff(StaffSaveRequest request);

    Mono<Boolean> deleteStaff(int id);

    Mono<UniversityProfileDTO> getUniversityProfile(String universityId);

    public Mono<UniversityFoodInfo> getFoodProfileForUniversity(String universityId);

    public Mono<UniversityProfileV2> getUniversityV2(String universityId);
}
