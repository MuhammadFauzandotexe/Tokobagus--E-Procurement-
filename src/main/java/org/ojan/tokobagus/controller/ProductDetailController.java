package org.ojan.tokobagus.controller;

import lombok.AllArgsConstructor;
import org.ojan.tokobagus.entity.ProductDetail;
import org.ojan.tokobagus.entity.Vendor;
import org.ojan.tokobagus.model.request.ProductDetailRequest;
import org.ojan.tokobagus.model.response.CommonResponse;
import org.ojan.tokobagus.model.response.ProductDetailResponse;
import org.ojan.tokobagus.service.ProductDetailService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ProductDetailController {
    private final ProductDetailService productDetailService;

    @PostMapping("/product-detail")
    public ResponseEntity<CommonResponse<ProductDetailResponse>> create(@RequestBody ProductDetailRequest productDetailRequest,String vendorId){
        CommonResponse<ProductDetailResponse> productDetailCommonResponse = productDetailService.create(productDetailRequest, vendorId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productDetailCommonResponse);
    }
    @GetMapping("product-details")
    public ResponseEntity<CommonResponse<List<ProductDetail>>> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page, size);
        CommonResponse<List<ProductDetail>> all = productDetailService.getAll(pageable);
        return ResponseEntity.status(HttpStatus.OK.value()).body(
                productDetailService.getAll(pageable));
    }

    @GetMapping("product-detail/{id}")
    public ResponseEntity<CommonResponse<ProductDetailResponse>> findById(@PathVariable("id") String id){
        CommonResponse<ProductDetailResponse> byid = productDetailService.findByid(id);
        return ResponseEntity.status(HttpStatus.OK.value()).body(productDetailService.findByid(id));
    }

    @PutMapping("product-detail/{id}")
    public ResponseEntity<CommonResponse<ProductDetailResponse>> updateById(@RequestBody ProductDetail productDetail){
        return ResponseEntity.status(HttpStatus.OK.value())
                .body(productDetailService.updateById(productDetail));

    }
}
