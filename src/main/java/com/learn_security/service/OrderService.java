package com.learn_security.service;

import com.learn_security.dto.ApiResponse;
import com.learn_security.dto.OrderDto;
import com.learn_security.dto.UpdateOrderDto;
import com.learn_security.models.Book;
import com.learn_security.models.Order;
import com.learn_security.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    private final BookService bookService;

    public List<Order> getAllOrder(Integer status) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return orderRepository.getOrdersByUserEmailAndStatus(authentication.getName(), status);
    }

    public ApiResponse addOrder(OrderDto orderDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Book book = bookService.getBookById(orderDto.getBookId());
        Order order = Order.builder()
                .quantity(orderDto.getQuantity())
                .status(0)
                .book(book)
                .userEmail(authentication.getName())
                .build();
        orderRepository.save(order);
        return ApiResponse.builder()
                .message("Add success")
                .build();
    }

    public ApiResponse updateOrder(UpdateOrderDto updateOrderDto){
        Order order = orderRepository.findById(updateOrderDto.getId()).get();
        order.setQuantity(updateOrderDto.getQuantity());
        orderRepository.save(order);
        return ApiResponse.builder()
                .message("update success")
                .build();
    }

    public ApiResponse deleteOrder(Long id){
        orderRepository.deleteById(id);
        return ApiResponse.builder()
                .message("delete success")
                .build();
    }
}
