package org.ojan.tokobagus.controller;

import lombok.AllArgsConstructor;
import org.ojan.tokobagus.model.response.CommonResponse;
import org.ojan.tokobagus.service.ProductDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ProductDetailController {
    private final ProductDetailService productDetailService;

    @GetMapping
    public ResponseEntity<CommonResponse<?>> findAll(){
return null;
    }
}
