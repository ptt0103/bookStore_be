package com.learn_security.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantity;
    private Integer status;

    @CreatedBy
    String userEmail;

    @ManyToOne(targetEntity = Book.class, fetch = FetchType.EAGER)
    @PrimaryKeyJoinColumn
    Book book;
}
