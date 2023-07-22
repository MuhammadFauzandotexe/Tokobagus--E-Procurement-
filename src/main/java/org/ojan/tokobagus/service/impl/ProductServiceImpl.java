package org.ojan.tokobagus.service.impl;

import lombok.AllArgsConstructor;
import org.ojan.tokobagus.entity.Product;
import org.ojan.tokobagus.repository.ProductRepository;
import org.ojan.tokobagus.service.ProductService;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.saveAndFlush(product);
    }
}
