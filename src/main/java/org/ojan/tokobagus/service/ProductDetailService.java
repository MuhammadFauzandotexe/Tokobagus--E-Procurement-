package org.ojan.tokobagus.service;

import org.ojan.tokobagus.entity.ProductDetail;
import org.ojan.tokobagus.model.request.ProductDetailRequest;
import org.ojan.tokobagus.model.response.CommonResponse;
import org.ojan.tokobagus.model.response.ProductDetailResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductDetailService {
    CommonResponse<ProductDetailResponse> create(ProductDetailRequest productDetailRequest, String vendorId);
    CommonResponse<ProductDetailResponse> findByid(String id);
    CommonResponse<List<ProductDetail>> getAll(Pageable pageable);
    CommonResponse<ProductDetailResponse> updateById(ProductDetail request);

}
