package org.unlogged.springwebfluxdemo.controller.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.unlogged.springwebfluxdemo.model.ecommerce.PlatformsDto;
import org.unlogged.springwebfluxdemo.model.ecommerce.SellerDto;
import org.unlogged.springwebfluxdemo.service.ecommerce.PlatformService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/ecommerce/platform")
public class PlatformController {

    @Autowired
    PlatformService platformService;

    @GetMapping("/all")
    public Flux<PlatformsDto> getPlatforms() {
        return platformService.getAllPlatforms();
    }

    @GetMapping("/{platformId}")
    public Mono<PlatformsDto> getPlatformById(@PathVariable String platformId) {
        return platformService.getPlatform(platformId);
    }

    @PostMapping
    public Mono<PlatformsDto> savePlatform(@RequestBody Mono<PlatformsDto> platformsDtoMono) {
        return platformService.savePlatform(platformsDtoMono);
    }

    @DeleteMapping("/delete/{platformId}")
    public Mono<Void> deletePlatform(@PathVariable String platformId) {
        return platformService.deletePlatform(platformId);
    }
}
