package org.unlogged.springwebfluxdemo.service.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unlogged.springwebfluxdemo.model.ecommerce.SellerDto;
import org.unlogged.springwebfluxdemo.repository.ecommerce.SellerRepository;
import org.unlogged.springwebfluxdemo.utils.SellerUtil;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SellerService {

    private final SellerRepository repository;
    private final SellerUtil SellerUtil;

    @Autowired
    public SellerService(SellerRepository repository, SellerUtil SellerUtil) {
        this.repository = repository;
        this.SellerUtil = SellerUtil;
    }

    public Flux<SellerDto> getAllSellers() {
        return repository.findAll().map(SellerUtil::sellerEntityToSellerDto);
    }

    public Mono<SellerDto> getSeller(String sellerId) {
        return repository.findById(sellerId).map(SellerUtil::sellerEntityToSellerDto);
    }

    public Mono<SellerDto> saveSeller(Mono<SellerDto> sellerDtoMono) {
        return sellerDtoMono.map(SellerUtil::sellerDtoToSellerEntity)
                .flatMap(repository::insert)
                .map(SellerUtil::sellerEntityToSellerDto);
    }

    public Mono<SellerDto> updateSeller(Mono<SellerDto> sellerDtoMono, String sellerId) {
        return repository.findById(sellerId)
                .flatMap(seller -> sellerDtoMono.map(SellerUtil::sellerDtoToSellerEntity)
                        .doOnNext(e-> e.setId(sellerId)))
                .flatMap(repository::save)
                .map(SellerUtil::sellerEntityToSellerDto);
    }

    public Mono<Void> deleteSeller(String sellerId) {
        return repository.deleteById(sellerId);
    }

    public Mono<Void> deleteAllSellers() {
        return repository.deleteAll();
    }
}
