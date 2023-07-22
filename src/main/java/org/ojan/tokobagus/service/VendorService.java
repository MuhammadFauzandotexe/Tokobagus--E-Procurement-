package org.ojan.tokobagus.service;

import org.ojan.tokobagus.entity.Vendor;
import org.ojan.tokobagus.model.request.VendorRequest;
import org.ojan.tokobagus.model.response.CommonResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VendorService {
    CommonResponse<Vendor> create(VendorRequest vendorRequest);
    CommonResponse<Vendor> findById(String id);
    CommonResponse<List<Vendor>> findAll(Pageable pageable);
    CommonResponse<Vendor> updateById(Vendor vendor);
}
