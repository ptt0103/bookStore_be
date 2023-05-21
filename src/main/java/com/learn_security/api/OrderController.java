package com.learn_security.api;

import com.learn_security.dto.ApiResponse;
import com.learn_security.dto.OrderDto;
import com.learn_security.dto.UpdateOrderDto;
import com.learn_security.models.Order;
import com.learn_security.repository.BillPaypalRepository;
import com.learn_security.service.OrderService;
import com.learn_security.service.PaypalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/order")
public class OrderController {
    private final OrderService orderService;
    private final PaypalService paypalService;

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrderInCart(){
        return ResponseEntity.ok().body(orderService.getAllOrder(0));
    }

    @GetMapping("/purchased")
    public ResponseEntity<List<Order>> getAllOrderPurchased(){
        return ResponseEntity.ok().body(paypalService.getBillOrder());
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addNewOrder(@RequestBody OrderDto orderDto){
        return new ResponseEntity<>(orderService.addOrder(orderDto), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateOrder(@RequestBody UpdateOrderDto updateOrderDto){
        return new ResponseEntity<>(orderService.updateOrder(updateOrderDto), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse> deleteOrder(Long id){
        return new ResponseEntity<>(orderService.deleteOrder(id), HttpStatus.OK);
    }
}
