package org.unlogged.springwebfluxdemo.service.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unlogged.springwebfluxdemo.model.ecommerce.SellerDto;
import org.unlogged.springwebfluxdemo.repository.ecommerce.SellerRepository;
import org.unlogged.springwebfluxdemo.utils.CommonEcommUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SellerService {

    private final SellerRepository repository;
    private final CommonEcommUtil commonEcommUtil;

    @Autowired
    public SellerService(SellerRepository repository, CommonEcommUtil commonEcommUtil) {
        this.repository = repository;
        this.commonEcommUtil = commonEcommUtil;
    }

    public Flux<SellerDto> getAllSellers() {
        return repository.findAll().map(commonEcommUtil::sellerEntityToSellerDto);
    }

    public Mono<SellerDto> getSeller(String sellerId) {
        return repository.findById(sellerId).map(commonEcommUtil::sellerEntityToSellerDto);
    }

    public Mono<SellerDto> saveSeller(Mono<SellerDto> sellerDtoMono) {
        return sellerDtoMono.map(commonEcommUtil::sellerDtoToSellerEntity)
                .flatMap(repository::insert)
                .map(commonEcommUtil::sellerEntityToSellerDto);
    }

    public Mono<SellerDto> updateSeller(Mono<SellerDto> sellerDtoMono, String sellerId) {
        return repository.findById(sellerId)
                .flatMap(seller -> sellerDtoMono.map(commonEcommUtil::sellerDtoToSellerEntity)
                        .doOnNext(e-> e.setId(sellerId)))
                .flatMap(repository::save)
                .map(commonEcommUtil::sellerEntityToSellerDto);
    }

    public Mono<Void> deleteSeller(String sellerId) {
        return repository.deleteById(sellerId);
    }
}
