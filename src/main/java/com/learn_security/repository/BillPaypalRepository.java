package com.learn_security.repository;

import com.learn_security.models.BillPaypal;
import com.learn_security.models.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillPaypalRepository extends JpaRepository<BillPaypal, Long> {
    @Query("select b from BillPaypal b where b.email =:email and b.paypalOrderStatus ='approved' group by b.paypalOrderId order by b.createAt DESC ")
    List<BillPaypal> getAllByEmail(String email);
    @Query("select b.order from BillPaypal b where b.email =:email order by b.createAt DESC")
    List<Order> getBillByEmail(@Param("email") String email);

    @Query("select b.order from BillPaypal b where b.paypalOrderId =:paypalOrderId order by b.createAt DESC")
    List<Order> getOrderByPaypalOrderId(@Param("paypalOrderId") String paypalOrderId);

    @Query("select b from BillPaypal b where b.paypalOrderId =:paypalOrderId order by b.createAt DESC")
    List<BillPaypal> getAllByPaypalOrderId(@Param("paypalOrderId") String paypalOrderId);
}
