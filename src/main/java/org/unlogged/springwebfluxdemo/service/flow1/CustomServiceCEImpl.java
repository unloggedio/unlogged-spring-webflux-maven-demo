package org.unlogged.springwebfluxdemo.service.flow1;

import org.unlogged.springwebfluxdemo.model.*;
import org.unlogged.springwebfluxdemo.repository.RedisCoffeeInteractionRepo;
import org.unlogged.springwebfluxdemo.repository.flow1.RxJavaSqlRepo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class CustomServiceCEImpl extends BaseServiceAbstract<RedisCoffeeInteractionRepo, RxJavaSqlRepo> implements CustomServiceCE {

    private RedisCoffeeInteractionRepo repo;
    private RxJavaSqlRepo sqlRepo;

    public CustomServiceCEImpl(RedisCoffeeInteractionRepo repo, RxJavaSqlRepo sqlRepo) {

        this.repo = repo;
        this.sqlRepo = sqlRepo;
    }

    @Override
    public Flux<String> getAllStaffNames() {
        return sqlRepo.getAllStaffNames();
    }

    @Override
    public Flux<Coffee> getCoffeeList() {
        return repo.all();
    }

    @Override
    public Mono<StaffDTO> getStaffById(int id) {
        return sqlRepo.findStaffById(id);
    }

    @Override
    public Mono<Boolean> addStaff(StaffSaveRequest request) {
        return sqlRepo.saveStaff(request);
    }

    @Override
    public Mono<Boolean> updateStaff(StaffSaveRequest request) {
        return sqlRepo.updateStaffNameForId(request);
    }

    @Override
    public Mono<Boolean> deleteStaff(int id) {
        return sqlRepo.deleteStaff(id);
    }

    @Override
    public Mono<UniversityProfileDTO> getUniversityProfile(String universityId) {
        Mono<UniversityProfile> universityProfileMono = sqlRepo.getUniversityProfile(universityId);
        Flux<StaffDTO> staffForselectUniversity = sqlRepo.getStaffForUniversity(universityId);
        return Mono.zip(universityProfileMono, staffForselectUniversity.collectList(), (profile, stafflist) ->
                new UniversityProfileDTO(profile, stafflist));
    }

    @Override
    public Mono<UniversityFoodInfo> getFoodProfileForUniversity(String universityId) {
        Mono<UniversityProfileDTO> universityProfileMono = getUniversityProfile(universityId);
        Flux<Coffee> coffeeFlux = getCoffeeList();
        Mono<UniversityFoodInfo> foodInfoMono = Mono.just(new UniversityFoodInfo())
                .map(element -> {
                    element.setUniversityId(Objects.requireNonNull(universityProfileMono.block()).getId());
                    element.setUniversityName(Objects.requireNonNull(universityProfileMono.block()).getName());
                    element.setBeveragesAvailable(coffeeFlux.collectList().block());
                    return element;
                });
//        Mono<UniversityFoodInfo> foodInfoMono = Mono
//                .zip(universityProfileMono, coffeeFlux.collectList(), (profile, beverages) -> {
//                    UniversityFoodInfo foodProfile = new UniversityFoodInfo();
//                    foodProfile.setUniversityId(profile.getId());
//                    foodProfile.setUniversityName(profile.getName());
//                    foodProfile.setBeveragesAvailable(beverages);
//                    return foodProfile;
//                });
        return foodInfoMono;
    }
}
