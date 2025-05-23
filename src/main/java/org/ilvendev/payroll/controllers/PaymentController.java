package org.ilvendev.payroll.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ilvendev.enums.PaymentStatus;
import org.ilvendev.payroll.domain.Payment;
import org.ilvendev.payroll.dto.PaymentRequest;
import org.ilvendev.payroll.dto.PaymentResponseBasic;
import org.ilvendev.payroll.dto.PaymentResponseDetail;
import org.ilvendev.payroll.services.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
@AllArgsConstructor
@Slf4j
@Validated
public class PaymentController {
    PaymentService paymentService;

    @PostMapping("/request")
    public ResponseEntity<Integer> createPaymentRequest(@Valid @RequestBody PaymentRequest request) {
        log.debug("Received create payment request");

        Payment createdPayment = paymentService.createPaymentRequest(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdPayment.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .build();
    }


    // TODO: Make this work correctly in the service
    @PostMapping("/{id}/pay")
    public ResponseEntity<String> makePayment(@Valid @PathVariable("id") Integer paymentId) {
        log.debug("Received make payment request");

        paymentService.makePayment(paymentId);

        return ResponseEntity.ok("Payment paid");
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDetail> getPaymentById(@Valid @PathVariable("id") Integer paymentId) {
        log.debug("Received get request for payment with ID {}", paymentId);

        PaymentResponseDetail fetchedPayment = paymentService.getDetailPaymentInfo(paymentId);

        return ResponseEntity.ok(fetchedPayment);
    }

    @GetMapping("/get")
    public ResponseEntity<List<PaymentResponseBasic>> getByParams(@Valid @RequestParam(required = false) LocalDate startDate,
                                                                  @Valid @RequestParam(required = false) LocalDate endDate,
                                                                  @Valid @RequestParam(required = false) String bankAccountNumber,
                                                                  @Valid @RequestParam(required = false) PaymentStatus status,
                                                                  @Valid @RequestParam(required = false) LocalDate dueDate) {
        log.debug("Received get all payments with params: " +
                "Start date {}, " +
                "End date {}, " +
                "Account number {}, " +
                "Payments status {}, " +
                "DueDate {}", startDate, endDate, bankAccountNumber, status, dueDate);

        List<PaymentResponseBasic> fetchedPayments;

        if (startDate != null && endDate != null){
            fetchedPayments = paymentService.getAllPaymentsBetween(startDate, endDate);
        } else if (bankAccountNumber != null) {
            fetchedPayments = paymentService.getPaymentsForAccountNumber(bankAccountNumber);
        } else if (status != null) {
            fetchedPayments = paymentService.getPaymentsByStatus(status);
        } else if (dueDate != null) {
            fetchedPayments = paymentService.getPaymentsByDueDate(dueDate);
        } else {
            fetchedPayments = paymentService.getAllPayments();
        }

        return ResponseEntity.ok(fetchedPayments);
    }
}
