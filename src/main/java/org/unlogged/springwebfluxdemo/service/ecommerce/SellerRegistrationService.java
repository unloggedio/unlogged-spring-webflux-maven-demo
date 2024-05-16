package org.unlogged.springwebfluxdemo.service.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unlogged.springwebfluxdemo.entity.Platform;
import org.unlogged.springwebfluxdemo.entity.Seller;
import org.unlogged.springwebfluxdemo.model.ecommerce.SellerDto;
import org.unlogged.springwebfluxdemo.repository.ecommerce.PlatformRepository;
import org.unlogged.springwebfluxdemo.repository.ecommerce.SellerRepository;
import org.unlogged.springwebfluxdemo.utils.SellerUtil;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Service
public class SellerRegistrationService {

    private final PlatformRepository platformRepository;
    private final SellerRepository sellerRepository;
    private final SellerUtil sellerUtil;

    @Autowired
    public SellerRegistrationService(PlatformRepository platformRepository, SellerRepository sellerRepository, SellerUtil sellerUtil) {
        this.platformRepository = platformRepository;
        this.sellerRepository = sellerRepository;
        this.sellerUtil = sellerUtil;
    }

    public Mono<SellerDto> registerSellerOnPlatform(String sellerId, String platformId) {
        return Mono.zip(sellerRepository.findById(sellerId), platformRepository.findById(platformId))
                .flatMap(tuple -> {
                    Seller seller = tuple.getT1();
                    Platform platform = tuple.getT2();

                    if (seller.getPlatformIds() == null) {
                        seller.setPlatformIds(new ArrayList<>());
                    }
                    seller.getPlatformIds().add(platformId);

                    platform.getListedSellerIds().add(sellerId);
                    return Mono.zip(sellerRepository.save(seller), platformRepository.save(platform));
                })
                .map(tuple -> {
                    Seller savedSeller = tuple.getT1();
                    return sellerUtil.sellerEntityToSellerDto(savedSeller);
                });
    }
}
