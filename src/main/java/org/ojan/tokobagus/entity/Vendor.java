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
@Table(name = "m_vendor")
public class Vendor extends BaseEntity{
    @Id
    @GenericGenerator(strategy = "uuid2", name = "system-uuid")
    @GeneratedValue(generator = "system-uuid")
    private String id;
    private String name;
    private String mobilePhone;
    @ManyToMany
    @JoinTable(name = "product_vendor",
            joinColumns = { @JoinColumn(name = "vendor_id",referencedColumnName = "id") //TODO ini adalah id user
            },inverseJoinColumns = {@JoinColumn(name = "product_detail_id",referencedColumnName = "id")}//TODO reference ke id role
    )
    private List<ProductDetail> productDetails;
}
