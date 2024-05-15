package org.unlogged.springwebfluxdemo.service.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unlogged.springwebfluxdemo.model.ecommerce.PlatformsDto;
import org.unlogged.springwebfluxdemo.repository.ecommerce.PlatformRepository;
import org.unlogged.springwebfluxdemo.utils.CommonEcommUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlatformService {

    private final PlatformRepository repository;
    private final CommonEcommUtil commonEcommUtil;

    @Autowired
    public PlatformService(PlatformRepository repository, CommonEcommUtil commonEcommUtil) {
        this.repository = repository;
        this.commonEcommUtil = commonEcommUtil;
    }

    public Flux<PlatformsDto> getAllPlatforms() {
        return repository.findAll().map(commonEcommUtil::platformEntityToPlatformDto);
    }

    public Mono<PlatformsDto> getPlatform(String platformId) {
        return repository.findById(platformId).map(commonEcommUtil::platformEntityToPlatformDto);
    }

    public Mono<PlatformsDto> savePlatform(Mono<PlatformsDto> platformsDtoMono) {
        return platformsDtoMono.map(commonEcommUtil::platformDtoToPlatformEntity)
                .flatMap(repository::insert)
                .map(commonEcommUtil::platformEntityToPlatformDto);
    }

    public Mono<PlatformsDto> updatePlatform(Mono<PlatformsDto> platformsDtoMono, String platformId) {
        return repository.findById(platformId)
                .flatMap(platform -> platformsDtoMono.map(commonEcommUtil::platformDtoToPlatformEntity)
                        .doOnNext(e-> e.setId(platformId)))
                .flatMap(repository::save)
                .map(commonEcommUtil::platformEntityToPlatformDto);
    }

    public Mono<Void> deletePlatform(String platformId) {
        return repository.deleteById(platformId);
    }

}
