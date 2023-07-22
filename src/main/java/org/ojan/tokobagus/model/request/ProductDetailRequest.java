package org.ojan.tokobagus.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ProductDetailRequest {
    private String vendorId;
    private String name;
    private String description;
    private Long stock;
    private Long price;
    private String category;
}
