package org.ojan.tokobagus.service.impl;

import lombok.AllArgsConstructor;
import org.ojan.tokobagus.entity.Vendor;
import org.ojan.tokobagus.model.request.VendorRequest;
import org.ojan.tokobagus.model.response.CommonResponse;
import org.ojan.tokobagus.repository.VendorRepository;
import org.ojan.tokobagus.service.VendorService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VendorServiceImpl implements VendorService {
    private final VendorRepository vendorRepository;
    @Override
    public CommonResponse<Vendor> create(VendorRequest vendorRequest) {
        try {
            Vendor vendor = vendorRepository.save(new Vendor().toBuilder()
                .mobilePhone(vendorRequest.getMobilePhone())
                .name(vendorRequest.getName())
                .build());
        return CommonResponse.<Vendor>builder()
                .message(" Successfully Register")
                .data(vendor)
                .statusCode(HttpStatus.OK.value())
                .build();}
        catch (DataIntegrityViolationException ex){
             throw new RuntimeException("name or mobile phone already exist");
        }
    }

    @Override
    public CommonResponse<Vendor> findById(String id) {
        Optional<Vendor> vendor = Optional.ofNullable(vendorRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Vendor Not Found");
        }));
        return CommonResponse.<Vendor>builder()
                .message("Success")
                .statusCode(HttpStatus.OK.value())
                .data(vendor.get())
                .build() ;
    }

    @Override
    public CommonResponse<List<Vendor>> findAll(Pageable pageable) {
        try {
            Page<Vendor> vendorsPage = vendorRepository.findAll(pageable);
            List<Vendor> vendors = vendorsPage.getContent();
            return CommonResponse.<List<Vendor>>builder()
                    .statusCode(HttpStatus.OK.value())
                    .message("Success")
                    .data(vendorsPage.getContent())
                    .build();
        }catch (Exception e){
            throw new RuntimeException("Resource Not Found");
        }
    }

    @Override
    public CommonResponse<Vendor> updateById(Vendor vendor) {
            Optional<Vendor> vendorUpdated = Optional.ofNullable(vendorRepository.findById(vendor.getId()).orElseThrow(() -> {
                throw new RuntimeException("Resource Not Found");
            }));
            if (!vendorUpdated.isPresent()){
                throw new RuntimeException("Resource Not Found");
            }
            vendorUpdated.get().setName(vendor.getName());
            vendorUpdated.get().setMobilePhone(vendor.getMobilePhone());
            vendorRepository.save(vendorUpdated.get());
        return CommonResponse.<Vendor>builder()
                .message("Successfully Update")
                .statusCode(HttpStatus.OK.value())
                .build();
    }
}
