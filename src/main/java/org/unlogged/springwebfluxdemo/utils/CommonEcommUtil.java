package org.unlogged.springwebfluxdemo.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.unlogged.springwebfluxdemo.entity.*;
import org.unlogged.springwebfluxdemo.model.ecommerce.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommonEcommUtil {

    public PlatformsDto platformEntityToPlatformDto(Platform platform) {
        PlatformsDto platformDto = new PlatformsDto();
        platformDto.setId(platform.getId());
        platformDto.setPlatformName(platform.getPlatformName());
        platformDto.setRegisteredCountry(platform.getRegisteredCountry());
        platformDto.setOperatingCategories(platform.getOperatingCategories());
        platformDto.setListedSellers(mapSellers(platform.getListedSellers()));
//        private List<String> operatingCategories;
        return platformDto;
    }

    public Platform platformDtoToPlatformEntity(PlatformsDto platformDto) {
        Platform platform = new Platform();
        platform.setId(platformDto.getId());
        platform.setPlatformName(platformDto.getPlatformName());
        platform.setRegisteredCountry(platformDto.getRegisteredCountry());
        platform.setOperatingCategories(platformDto.getOperatingCategories());
        platform.setListedSellers(mapSellerDtos(platformDto.getListedSellers()));
//      operatingCategories
        return platform;
    }

    public SellerDto sellerEntityToSellerDto(Seller seller) {
        SellerDto sellerDto = new SellerDto();
        sellerDto.setId(seller.getId());
        sellerDto.setName(seller.getName());
        sellerDto.setEmail(seller.getEmail());
        sellerDto.setAge(seller.getAge());
        sellerDto.setProducts(mapProducts(seller.getProducts()));
        sellerDto.setShippingServices(mapShippingServices(seller.getShippingServices()));
        return sellerDto;
    }

    public Seller sellerDtoToSellerEntity(SellerDto sellerDto) {
        Seller seller = new Seller();
        seller.setId(sellerDto.getId());
        seller.setName(sellerDto.getName());
        seller.setEmail(sellerDto.getEmail());
        seller.setAge(sellerDto.getAge());
        seller.setProducts(mapProductDtos(sellerDto.getProducts()));
        seller.setShippingServices(mapShippingServiceDtos(sellerDto.getShippingServices()));
        return seller;
    }

    public ProductDto productEntityToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

    public Product productDtoToProductEntity(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }

    private List<SellerDto> mapSellers(List<Seller> sellers) {
        if(sellers == null) return Collections.emptyList();
        return sellers.stream()
                .map(seller -> {
                    SellerDto sellerDto = new SellerDto();
                    sellerDto.setId(seller.getId());
                    sellerDto.setName(seller.getName());
                    sellerDto.setEmail(seller.getEmail());
                    sellerDto.setAge(seller.getAge());
                    sellerDto.setProducts(mapProducts(seller.getProducts()));
                    sellerDto.setShippingServices(mapShippingServices(seller.getShippingServices()));
                    return sellerDto;
                }).collect(Collectors.toList());
    }
    private List<ShippingServiceDto> mapShippingServices(List<ShippingServices> shippingServices) {
        if (shippingServices == null) {
            return Collections.emptyList();
        }
        return shippingServices.stream()
                .map(shippingService -> {
                    ShippingServiceDto shippingServiceDto = new ShippingServiceDto();
                    shippingServiceDto.setId(shippingService.getId());
                    shippingServiceDto.setServiceProvider(shippingService.getServiceProvider());
                    shippingServiceDto.setOperationPinCodes(shippingService.getOperationPinCodes());
                    shippingServiceDto.setMaxCapacity(shippingService.getMaxCapacity());
                    shippingServiceDto.setShippingCostPerItem(shippingService.getShippingCostPerItem());
                    shippingServiceDto.setRateCards(mapRateCards(shippingService.getRateCards()));
                    return shippingServiceDto;
                })
                .collect(Collectors.toList());
    }

    private List<RateCardDto> mapRateCards(List<RateCard> rateCards) {
        if (rateCards == null) {
            return Collections.emptyList();
        }
        return rateCards.stream()
                .map(rateCard -> {
                    RateCardDto rateCardDto = new RateCardDto();
                    BeanUtils.copyProperties(rateCard, rateCardDto);
                    return rateCardDto;
                })
                .collect(Collectors.toList());
    }

    private List<ProductDto> mapProducts(List<Product> products) {
        if (products == null) {
            return Collections.emptyList();
        }
        return products.stream()
                .map(product -> {
                    ProductDto productDto = new ProductDto();
                    BeanUtils.copyProperties(product, productDto);
                    return productDto;
                })
                .collect(Collectors.toList());
    }

    private List<Product> mapProductDtos(List<ProductDto> productDtos) {
        return productDtos.stream()
                .map(productDto -> {
                    Product product = new Product();
                    BeanUtils.copyProperties(productDto, product);
                    return product;
                })
                .collect(Collectors.toList());
    }

    private List<Seller> mapSellerDtos(List<SellerDto> sellerDtos) {
        return sellerDtos.stream()
                .map(sellerDto -> {
                    Seller seller = new Seller();

                    seller.setId(sellerDto.getId());
                    seller.setName(sellerDto.getName());
                    seller.setEmail(sellerDto.getEmail());
                    seller.setAge(sellerDto.getAge());
                    seller.setProducts(mapProductDtos(sellerDto.getProducts()));
                    seller.setShippingServices(mapShippingServiceDtos(sellerDto.getShippingServices()));
                    return seller;
                }).collect(Collectors.toList());
    }

    private List<ShippingServices> mapShippingServiceDtos(List<ShippingServiceDto> shippingServiceDtos) {
        return shippingServiceDtos.stream()
                .map(shippingServiceDto -> {
                    ShippingServices shippingService = new ShippingServices();
                    shippingService.setId(shippingServiceDto.getId());
                    shippingService.setServiceProvider(shippingServiceDto.getServiceProvider());
                    shippingService.setOperationPinCodes(shippingServiceDto.getOperationPinCodes());
                    shippingService.setMaxCapacity(shippingServiceDto.getMaxCapacity());
                    shippingService.setShippingCostPerItem(shippingServiceDto.getShippingCostPerItem());
                    shippingService.setRateCards(mapRateCardDtos(shippingServiceDto.getRateCards()));
                    return shippingService;
                })
                .collect(Collectors.toList());
    }

    private List<RateCard> mapRateCardDtos(List<RateCardDto> rateCardDtos) {
        return rateCardDtos.stream()
                .map(rateCardDto -> {
                    RateCard rateCard = new RateCard();
                    BeanUtils.copyProperties(rateCardDto, rateCard);
                    return rateCard;
                })
                .collect(Collectors.toList());
    }
}
