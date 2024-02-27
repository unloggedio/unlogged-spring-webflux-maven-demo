package org.unlogged.springwebfluxdemo.controller.flow1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.unlogged.springwebfluxdemo.model.*;
import org.unlogged.springwebfluxdemo.service.flow1.CustomService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CustomControllerCE extends BaseController<CustomService> {

    private CustomService customService;

    CustomControllerCE(CustomService customService) {
        this.customService = customService;
    }

    @RequestMapping("/staff")
    public ResponseEntity<Flux<Integer>> getAllStaffNames() {
        return ResponseEntity.ok(customService.getAllStaffNames());
    }

    @RequestMapping("/coffee")
    public ResponseEntity<Flux<Coffee>> getCoffeeList() {
        return ResponseEntity.ok(customService.getCoffeeList());
    }

    @RequestMapping("/staff/get")
    public ResponseEntity<Mono<StaffDTO>> getStaffById(@RequestParam int id) {
        return ResponseEntity.ok(customService.getStaffById(id));
    }

    @RequestMapping("/staff/save")
    public ResponseEntity<Mono<Boolean>> saveStaff(@RequestParam StaffSaveRequest saveRequest) {
        return ResponseEntity.ok(customService.addStaff(saveRequest));
    }

    @RequestMapping("/staff/update")
    public ResponseEntity<Mono<Boolean>> updateStaff(@RequestParam StaffSaveRequest updateRequest) {
        return ResponseEntity.ok(customService.updateStaff(updateRequest));
    }

    @RequestMapping("/staff/delete")
    public ResponseEntity<Mono<Boolean>> delete(int id) {
        return ResponseEntity.ok(customService.deleteStaff(id));
    }

    @RequestMapping("/university/staff")
    public ResponseEntity<Mono<UniversityProfileDTO>> getListofStaffForUniversity(@RequestParam int id) {
        return ResponseEntity.ok(customService.getUniversityProfile(id));
    }

    @RequestMapping("/university/food")
    public ResponseEntity<Mono<UniversityFoodInfo>> getFoodInfoProfileForUniversity(@RequestParam int id) {
        return ResponseEntity.ok(customService.getFoodProfileForUniversity(id));
    }

    public Mono<UniversityFoodInfo> getFoodInfoProfileForUniversityNoEP(int id) {
        return customService.getFoodProfileForUniversity(id);
    }

    public Mono<UniversityProfileV2> getUniversityV2Mono(int universityId) {
        return customService.getUniversityV2(universityId);
    }

    //This will Timeout
    @RequestMapping("/university/mix")
    public Mono<UniversityProfileV2> getUniversityMixed(@RequestParam int universityId) {
        return customService.getMixResponse(universityId);
    }

    @RequestMapping("/university/v2/profile")
    public ResponseEntity<Mono<UniversityProfileV2>> getUniversityV2(@RequestParam int universityId) {
        return ResponseEntity.ok(customService.getUniversityV2(universityId));
    }
}
