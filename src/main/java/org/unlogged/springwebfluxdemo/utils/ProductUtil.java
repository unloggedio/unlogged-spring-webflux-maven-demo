package org.unlogged.springwebfluxdemo.utils;

import org.springframework.beans.BeanUtils;
import org.unlogged.springwebfluxdemo.entity.Product;
import org.unlogged.springwebfluxdemo.model.ecommerce.ProductDto;

public class ProductUtil {

    public static ProductDto productEntityToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

    public static Product productDtoToProductEntity(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }
}
