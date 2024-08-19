package ar.lamansys.messages.infrastructure.output.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "cart")
@Entity
public class Cart {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "app_user_id", nullable = false)
    private String appUserId;

    @Column(name = "total_price", nullable = false)
    private Integer totalPrice;

    @Column(name = "is_finalized", nullable = false)
    private Boolean isFinalized = false;

    @Column(name="seller_id", nullable = false)
    private String sellerId;

    public Cart(Integer id) {
        this.id = id;
    }

}
