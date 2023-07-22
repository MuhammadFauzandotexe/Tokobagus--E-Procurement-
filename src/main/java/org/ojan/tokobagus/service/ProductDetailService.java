package org.ojan.tokobagus.service;

import org.ojan.tokobagus.entity.ProductDetail;
import org.ojan.tokobagus.model.request.ProductDetailRequest;

public interface ProductDetailService {
    ProductDetail create(ProductDetailRequest productDetailRequest);
}
