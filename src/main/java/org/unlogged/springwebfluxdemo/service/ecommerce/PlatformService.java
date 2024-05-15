package org.unlogged.springwebfluxdemo.service.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unlogged.springwebfluxdemo.model.ecommerce.PlatformsDto;
import org.unlogged.springwebfluxdemo.repository.ecommerce.PlatformRepository;
import org.unlogged.springwebfluxdemo.utils.PlatformUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlatformService {

    @Autowired
    private PlatformRepository repository;

    public Flux<PlatformsDto> getAllPlatforms() {
        return repository.findAll().map(PlatformUtil::platformEntityToPlatformDto);
    }

    public Mono<PlatformsDto> getPlatform(String platformId) {
        return repository.findById(platformId).map(PlatformUtil::platformEntityToPlatformDto);
    }

    public Mono<PlatformsDto> savePlatform(Mono<PlatformsDto> platformsDtoMono) {
        return platformsDtoMono.map(PlatformUtil::platformDtoToPlatformEntity)
                .flatMap(repository::insert)
                .map(PlatformUtil::platformEntityToPlatformDto);
    }

    public Mono<PlatformsDto> updatePlatform(Mono<PlatformsDto> platformsDtoMono, String platformId) {
        return repository.findById(platformId)
                .flatMap(platform -> platformsDtoMono.map(PlatformUtil::platformDtoToPlatformEntity)
                        .doOnNext(e-> e.setId(platformId)))
                .flatMap(repository::save)
                .map(PlatformUtil::platformEntityToPlatformDto);
    }

    public Mono<Void> deletePlatform(String platformId) {
        return repository.deleteById(platformId);
    }

}
