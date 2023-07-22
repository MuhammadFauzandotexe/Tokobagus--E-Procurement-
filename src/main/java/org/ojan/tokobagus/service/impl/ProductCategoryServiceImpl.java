package org.ojan.tokobagus.service.impl;

import lombok.AllArgsConstructor;
import org.ojan.tokobagus.entity.ProductCategory;
import org.ojan.tokobagus.repository.ProductCategoryRepository;
import org.ojan.tokobagus.service.ProductCategoryService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;
    @Override
    public ProductCategory create(ProductCategory productCategory) {
        return productCategoryRepository.saveAndFlush(productCategory);
    }
}
