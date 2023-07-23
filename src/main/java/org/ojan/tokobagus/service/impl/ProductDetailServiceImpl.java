package org.ojan.tokobagus.service.impl;

import lombok.AllArgsConstructor;
import org.ojan.tokobagus.entity.Product;
import org.ojan.tokobagus.entity.ProductCategory;
import org.ojan.tokobagus.entity.ProductDetail;
import org.ojan.tokobagus.entity.Vendor;
import org.ojan.tokobagus.model.request.ProductDetailRequest;
import org.ojan.tokobagus.model.response.CommonResponse;
import org.ojan.tokobagus.model.response.ProductDetailResponse;
import org.ojan.tokobagus.repository.ProductDetailRepository;
import org.ojan.tokobagus.repository.VendorRepository;
import org.ojan.tokobagus.service.ProductCategoryService;
import org.ojan.tokobagus.service.ProductDetailService;
import org.ojan.tokobagus.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductDetailServiceImpl implements ProductDetailService {
    private final ProductDetailRepository productDetailRepository;
    private final ProductCategoryService productCategoryService;
    private final ProductService productService;
    private final EntityManager entityManager;
    private final VendorRepository vendorRepository;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CommonResponse<ProductDetailResponse> create(ProductDetailRequest productDetailRequest, String vendorId) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Optional<Vendor> vendor = Optional.ofNullable(vendorRepository.findById(vendorId)
                    .orElseThrow(() -> new RuntimeException("Vendor Nor Found")));
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
            vendor.get().setProductDetails(List.of(productDetail));
            return CommonResponse.<ProductDetailResponse>builder()
                    .message("Success create product")
                    .data(ProductDetailResponse.builder()
                            .id(productDetail.getId())
                            .name(productDetail.getProduct().getName())
                            .description(productDetail.getProduct().getDescription())
                            .price(productDetail.getPrice())
                            .stock(productDetail.getStock())
                            .build()
                    )
                    .statusCode(HttpStatus.OK.value()).build();
        }catch (Exception e){
            transaction.rollback();
            throw new RuntimeException("Error when save product");
        }
    }

    @Override
    public CommonResponse<ProductDetailResponse> findByid(String id) {
        ProductDetail productDetail = productDetailRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Resource Not Found : /products/{}" + id));
        ProductDetail productDetail1 = new ProductDetail();
        return CommonResponse.<ProductDetailResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success")
                .data(ProductDetailResponse.builder()
                    .id(productDetail.getId())
                    .name(productDetail.getProduct().getName())
                    .description(productDetail.getProduct().getDescription())
                    .price(productDetail.getPrice())
                    .stock(productDetail.getStock())
                    .build())
                .build();
    }

    @Override
    public CommonResponse<List<ProductDetail>> getAll(Pageable pageable) {
        try {
            Page<ProductDetail> productDetails = productDetailRepository.findAll(pageable);
            List<ProductDetail> content = productDetails.getContent();
            return CommonResponse.<List<ProductDetail>>builder()
                    .statusCode(HttpStatus.OK.value())
                    .message("Success")
                    .data(content)
                    .build();
        }catch (Exception e){
            throw new RuntimeException("");
        }
    }

    @Override
    public CommonResponse<ProductDetailResponse> updateById(ProductDetail request) {
        ProductDetail productDetail = productDetailRepository
                .findById(request.getId()).orElseThrow(()->new RuntimeException("Resource Not Found"));
        productDetail.setStock(request.getStock());
        productDetail.setPrice(request.getPrice());
        productDetailRepository.save(productDetail);
        return CommonResponse.<ProductDetailResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Success")
                .data(ProductDetailResponse.builder()
                        .id(productDetail.getId())
                        .name(productDetail.getProduct().getName())
                        .description(productDetail.getProduct().getDescription())
                        .price(productDetail.getPrice())
                        .stock(productDetail.getStock())
                        .build()).build();
    }
}
