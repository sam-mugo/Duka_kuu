package backend.duka_kuu.domain;

import java.time.OffsetDateTime;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Product {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false, columnDefinition = "text")
    private String productDescription;

    @Column(nullable = false, columnDefinition = "text")
    private String productImage;

    @Column(nullable = false)
    private Double productPrice;

    @Column
    private Integer productQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subcategory_id", nullable = false)
    private Subcategory subcategory;

//    @OneToOne
//    @MapsId
//    @JoinColumn(name = "id", nullable = true)
//    @OneToOne(mappedBy = "product")
//    @PrimaryKeyJoinColumn
//    private OrderLineItem orderItems;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "inventory_id", nullable = false)
//    private Inventory inventory;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
