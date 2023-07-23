package org.ojan.tokobagus.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ProductDetailResponse {
    private String id;
    private String name;
    private String description;
    private Long price;
    private Long stock;
}
