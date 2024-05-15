package org.unlogged.springwebfluxdemo.controller.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.unlogged.springwebfluxdemo.model.ecommerce.SellerDto;
import org.unlogged.springwebfluxdemo.service.ecommerce.SellerService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/ecommerce/seller")
public class SellerController {

    private final SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("/all")
    public Flux<SellerDto> getSellers() {
        return sellerService.getAllSellers();
    }

    @PostMapping
    public Mono<SellerDto> saveSeller(@RequestBody Mono<SellerDto> sellerDtoMono) {
        return sellerService.saveSeller(sellerDtoMono);
    }

    @DeleteMapping("/delete/{sellerId}")
    public Mono<Void> deleteSeller(@PathVariable String sellerId) {
        return sellerService.deleteSeller(sellerId);
    }
}