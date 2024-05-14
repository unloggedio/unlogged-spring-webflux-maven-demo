package org.unlogged.springwebfluxdemo.utils;

import org.springframework.beans.BeanUtils;
import org.unlogged.springwebfluxdemo.entity.Product;
import org.unlogged.springwebfluxdemo.entity.RateCard;
import org.unlogged.springwebfluxdemo.entity.Seller;
import org.unlogged.springwebfluxdemo.entity.ShippingServices;
import org.unlogged.springwebfluxdemo.model.ecommerce.ProductDto;
import org.unlogged.springwebfluxdemo.model.ecommerce.RateCardDto;
import org.unlogged.springwebfluxdemo.model.ecommerce.SellerDto;
import org.unlogged.springwebfluxdemo.model.ecommerce.ShippingServiceDto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SellerUtil {

    public static SellerDto sellerEntityToSellerDto(Seller seller) {
        SellerDto sellerDto = new SellerDto();
        sellerDto.setId(seller.getId());
        sellerDto.setName(seller.getName());
        sellerDto.setEmail(seller.getEmail());
        sellerDto.setAge(seller.getAge());
        sellerDto.setProducts(mapProducts(seller.getProducts()));
        sellerDto.setShippingServices(mapShippingServices(seller.getShippingServices()));
        return sellerDto;
    }

    private static List<ProductDto> mapProducts(List<Product> products) {
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

    private static List<ShippingServiceDto> mapShippingServices(List<ShippingServices> shippingServices) {
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

    private static List<RateCardDto> mapRateCards(List<RateCard> rateCards) {
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

    public static Seller sellerDtoToSellerEntity(SellerDto sellerDto) {
        Seller seller = new Seller();
        seller.setId(sellerDto.getId());
        seller.setName(sellerDto.getName());
        seller.setEmail(sellerDto.getEmail());
        seller.setAge(sellerDto.getAge());
        seller.setProducts(mapProductDtos(sellerDto.getProducts()));
        seller.setShippingServices(mapShippingServiceDtos(sellerDto.getShippingServices()));
        return seller;
    }

    private static List<Product> mapProductDtos(List<ProductDto> productDtos) {
        return productDtos.stream()
                .map(productDto -> {
                    Product product = new Product();
                    BeanUtils.copyProperties(productDto, product);
                    return product;
                })
                .collect(Collectors.toList());
    }

    private static List<ShippingServices> mapShippingServiceDtos(List<ShippingServiceDto> shippingServiceDtos) {
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

    private static List<RateCard> mapRateCardDtos(List<RateCardDto> rateCardDtos) {
        return rateCardDtos.stream()
                .map(rateCardDto -> {
                    RateCard rateCard = new RateCard();
                    BeanUtils.copyProperties(rateCardDto, rateCard);
                    return rateCard;
                })
                .collect(Collectors.toList());
    }

}
