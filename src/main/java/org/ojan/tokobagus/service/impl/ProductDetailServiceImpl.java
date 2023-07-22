package org.ojan.tokobagus.service.impl;

import lombok.AllArgsConstructor;
import org.ojan.tokobagus.entity.Product;
import org.ojan.tokobagus.entity.ProductCategory;
import org.ojan.tokobagus.entity.ProductDetail;
import org.ojan.tokobagus.model.request.ProductDetailRequest;
import org.ojan.tokobagus.repository.ProductDetailRepository;
import org.ojan.tokobagus.service.ProductCategoryService;
import org.ojan.tokobagus.service.ProductDetailService;
import org.ojan.tokobagus.service.ProductService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductDetailServiceImpl implements ProductDetailService {
    private final ProductDetailRepository productDetailRepository;
    private final ProductCategoryService productCategoryService;
    private final ProductService productService;
    @Override
    public ProductDetail create(ProductDetailRequest productDetailRequest) {
        ProductCategory productCategory = ProductCategory.builder()
                .categoryName(productDetailRequest.getCategory())
                .build();
        productCategoryService.create(productCategory);
        Product product = Product.builder()
                .description(productDetailRequest.getDescription())
                .name(productDetailRequest.getName())
                .productCategory(productCategory)
        .build();
        productService.create(product);
        ProductDetail productDetail = ProductDetail.builder()
                .product(product)
                .stock(productDetailRequest.getStock())
                .price(productDetailRequest.getPrice())
                .build();
        productDetailRepository.saveAndFlush(productDetail);
        return productDetail;
    }
}
