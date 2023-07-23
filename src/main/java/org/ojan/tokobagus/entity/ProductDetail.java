package org.ojan.tokobagus.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "m_product_details")
public class ProductDetail extends BaseEntity {
    @Id
    @GenericGenerator(strategy = "uuid2", name = "system-uuid")
    @GeneratedValue(generator = "system-uuid")
    @Column(updatable = false)
    private String id;
    private Long price;
    private Long stock;
    @ManyToOne
    @JoinColumn(name = "product_id",updatable = false)
    private Product product;
}
