package com.iwa.iwaid.consultoriomedico.restcontrollers;

import com.iwa.iwaid.consultoriomedico.dto.OrderDTO;
import com.iwa.iwaid.consultoriomedico.services.PaypalService;
import com.paypal.api.payments.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.paypal.api.payments.Links;
import com.paypal.base.rest.PayPalRESTException;

@RestController
@RequestMapping(path = "/iwaid/payments/")
@RequiredArgsConstructor
public class PaypalController {
    private final PaypalService service;

    public static final String SUCCESS_URL = "success";
    public static final String CANCEL_URL = "cancel";

    @PostMapping("/pay")
    public String payment(@ModelAttribute("order") OrderDTO order) {
        try {
            Payment payment = service.createPayment(order.getAmount(), order.getCurrency(), order.getMethod(),
                    order.getIntent(), order.getDescription(), "http://localhost:8081/" + CANCEL_URL,
                    "http://localhost:8081/" + SUCCESS_URL);
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    return "redirect:"+link.getHref();
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return "redirect:/iwaid/payments/pay";
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "success";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/iwaid/payments/pay";
    }
}
