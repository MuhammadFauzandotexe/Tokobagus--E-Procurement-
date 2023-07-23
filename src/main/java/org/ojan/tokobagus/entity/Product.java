package org.ojan.tokobagus.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "m_product")
public class Product extends BaseEntity {
    @Id
    @GenericGenerator(strategy = "uuid2", name = "system-uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(updatable = false)
    private String id;
    private String name;
    private String description;
    @OneToOne()
    @JoinColumn(name = "category_id",updatable = false)
    private ProductCategory productCategory;
    @Column(updatable = false)
    @OneToMany(mappedBy = "product")
    private List<ProductDetail> productDetails;
}

