package org.ojan.tokobagus.repository;

import org.ojan.tokobagus.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
}
