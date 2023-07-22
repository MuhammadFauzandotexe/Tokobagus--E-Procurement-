package org.ojan.tokobagus.model.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@JsonNaming(PropertyNamingStrategies.LowerCaseStrategy.class)
public class VendorRequest {
    private String name;
    private String mobilePhone;
}
