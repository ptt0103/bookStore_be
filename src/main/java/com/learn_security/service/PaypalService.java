package com.learn_security.service;


import com.learn_security.dto.CheckoutResponse;
import com.learn_security.dto.PaymentDto;
import com.learn_security.models.BillPaypal;
import com.learn_security.models.Order;
import com.learn_security.models.User;
import com.learn_security.repository.BillPaypalRepository;
import com.learn_security.repository.OrderRepository;
import com.learn_security.repository.UserRepository;
import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class PaypalService {
    private final APIContext apiContext;

    private final OrderRepository orderRepository;

    private final BillPaypalRepository billPaypalRepository;

    private final UserRepository userRepository;

    public Payment createPayment(PaymentDto paymentDto) throws PayPalRESTException {
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(String.format("%.2f",paymentDto.getTotal()));

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod("PAYPAL");

        Payment payment = new Payment();
        payment.setIntent("AUTHORIZE");
        payment.setPayer(payer);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(paymentDto.getCancelUrl());
        redirectUrls.setReturnUrl(paymentDto.getReturnUrl());

        payment.setRedirectUrls(redirectUrls);

        Payment created = payment.create(apiContext);
        for (Long i: paymentDto.getOrderIds()) {
            com.learn_security.models.Order order = orderRepository.findById(i).get();
            BillPaypal bill = BillPaypal.builder()
                    .order(order)
                    .paypalOrderId(created.getId())
                    .paypalOrderStatus(created.getState())
                    .email(order.getUserEmail())
                    .createAt(new Date())
                    .total(paymentDto.getTotal())
                    .build();
            billPaypalRepository.save(bill);
        }

        return created;
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        List<Order> orders = billPaypalRepository.getOrderByPaypalOrderId(paymentId);
        for (Order order : orders){
            order.setStatus(1);
            orderRepository.save(order);
        }
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecution);
    }

    public List<CheckoutResponse> checkoutList(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<BillPaypal> billPaypals = billPaypalRepository.getAllByEmail(authentication.getName());
        List<CheckoutResponse> checkoutResponses = new ArrayList<>();
        for (BillPaypal billPaypal : billPaypals
             ) {
            CheckoutResponse response = CheckoutResponse.builder()
                    .total(billPaypal.getTotal())
                    .name(billPaypal.getEmail())
                    .paymentId(billPaypal.getPaypalOrderId())
                    .paymentStatus(billPaypal.getPaypalOrderStatus())
                    .createAt(simpleDateFormat.format(billPaypal.getCreateAt()))
                    .build();
            checkoutResponses.add(response);
        }
        return checkoutResponses;
    }
    public void update(Payment payment){
        List<BillPaypal> billPaypals = billPaypalRepository.getAllByPaypalOrderId(payment.getId());
        for (BillPaypal bill : billPaypals
             ) {
            bill.setPaypalOrderStatus(payment.getState());
            billPaypalRepository.save(bill);
        }
    }

    public List<Order> getBillOrder(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return billPaypalRepository.getBillByEmail(authentication.getName());
    }
    public List<Order> getBillOrder(String orderId){

        return billPaypalRepository.getOrderByPaypalOrderId(orderId);
    }
}
