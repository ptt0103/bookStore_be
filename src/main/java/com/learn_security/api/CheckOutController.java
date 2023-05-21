package com.learn_security.api;

import com.learn_security.dto.ApiResponse;
import com.learn_security.dto.CheckoutResponse;
import com.learn_security.dto.PaymentDto;
import com.learn_security.models.Order;
import com.learn_security.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checkout")
@RequiredArgsConstructor
public class CheckOutController {
    private final PaypalService paypalService;

    @PostMapping("/paypal")
    public ResponseEntity<List<Links>> payment(@RequestBody PaymentDto paymentDto){
        try {
            Payment payment = paypalService.createPayment(paymentDto);
            System.out.println(payment.toJSON());
            return new ResponseEntity<>(payment.getLinks(), HttpStatus.CREATED);
        } catch (PayPalRESTException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/paypal/success")
    public ResponseEntity<ApiResponse> paymentSuccess(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
        try{
            Payment payment = paypalService.executePayment(paymentId, payerId);

            paypalService.update(payment);
            if(payment.getState().equals("approved")){
                return new ResponseEntity<>(ApiResponse.builder().message("purchase success").build(), HttpStatus.OK);
            }
        } catch (PayPalRESTException e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>(ApiResponse.builder().message("something wrong").build(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CheckoutResponse>> getAllBill(){
        return new ResponseEntity<>(paypalService.checkoutList(), HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<List<Order>> getAllOrderById(@PathVariable String orderId){
        return new ResponseEntity<>(paypalService.getBillOrder(orderId), HttpStatus.OK);
    }

}
