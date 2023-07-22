package org.ojan.tokobagus.controller;

import lombok.AllArgsConstructor;
import org.ojan.tokobagus.entity.Vendor;
import org.ojan.tokobagus.model.request.VendorRequest;
import org.ojan.tokobagus.model.response.CommonResponse;
import org.ojan.tokobagus.service.VendorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class VendorController {
    private final VendorService vendorService;
    @PostMapping("/vendor")
    public ResponseEntity<CommonResponse<Vendor>> create(@RequestBody  VendorRequest vendorRequest){
        CommonResponse<Vendor> vendorCommonResponse = vendorService.create(vendorRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vendorCommonResponse);
    }
    @GetMapping("/vendors")
    public ResponseEntity<CommonResponse<List<Vendor>>> findAllVendors(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK.value())
                .body(vendorService.findAll(pageable));
    }
    @GetMapping("/vendor/{id}")
    public ResponseEntity<CommonResponse<Vendor>> findById(@PathVariable("id") String id){
        return ResponseEntity.status(HttpStatus.OK.value())
                .body(vendorService.findById(id));
    }

}
