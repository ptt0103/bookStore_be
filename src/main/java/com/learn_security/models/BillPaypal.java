package com.learn_security.models;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bill_paypal")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BillPaypal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "paypal_order_id")
    private String paypalOrderId;
    @Column(name = "paypal_order_status")
    private String paypalOrderStatus;

    private Double total;
    @ManyToOne(targetEntity = Order.class, fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    Order order;

    @CreatedBy
    private String email;

    @CreatedDate
    private Date createAt;
}
