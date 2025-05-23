package org.ilvendev.payroll.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ilvendev.enums.PaymentStatus;
import org.ilvendev.exceptions.resource_exceptions.ResourceNotFoundException;
import org.ilvendev.payroll.domain.Payment;
import org.ilvendev.payroll.dto.PaymentRequest;
import org.ilvendev.payroll.dto.PaymentResponseBasic;
import org.ilvendev.payroll.dto.PaymentResponseDetail;
import org.ilvendev.payroll.mappers.PaymentMapper;
import org.ilvendev.payroll.repositories.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    // create payment request (basically status PENDING and no paymentDate)

    // make payment (basically connect to some outside payment API or something and then when finished set status to Finished and paymentDate to currentDate)

    // find by id
    // find by account number
    // find by status
    // find by due date

    @Transactional
    public Payment createPaymentRequest(PaymentRequest request){
        log.debug("Creating payment request");
        Payment requestedPayment = paymentMapper.toEntity(request);

        Payment savedPayment = paymentRepository.save(requestedPayment);

        log.info("Successfully created payment request");
        return savedPayment;
    }

    // Need to find a way to get the info when the payment actually went through, then we can change it to paid
    // Need to connect some payment API like payU to this
    @Transactional
    public void makePayment(Integer id){
        log.debug("Making requested payment for payment with ID {}", id);
        Payment requestedPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment", id.toString()));

        // here goes paying with payU or something

        requestedPayment.setStatus(PaymentStatus.PAID);
        requestedPayment.setPaymentDate(LocalDate.now());

        paymentRepository.save(requestedPayment);

        log.info("Payment sent");
    }

    // Might be useless, as this will be sending 1000s of payments at some point
    // public List<PaymentResponseBasic> getAllPayments;

    public PaymentResponseDetail getDetailPaymentInfo(Integer id){
        log.debug("Fetching detailed information for payment with ID {}", id);

        Payment fetchedPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Payment", id.toString()));

        log.info("Successfully got detailed information for payment with ID {}", id);

        return paymentMapper.toDetailResponse(fetchedPayment);
    }

    public List<PaymentResponseBasic> getAllPaymentsBetween(LocalDate startDate, LocalDate endDate){
        log.debug("Fetching all payments between {} and {}", startDate, endDate);

        List<Payment> fetchedPayments = paymentRepository.findByDueDateBetween(startDate, endDate);

        log.info("Successfully got all payments between {} and {}", startDate, endDate);
        return paymentMapper.toBasicResponseList(fetchedPayments);
    }

    public List<PaymentResponseBasic> getPaymentsForAccountNumber(String bankAccountNumber){
        log.debug("Fetching payments for account number {}", bankAccountNumber);

        List<Payment> fetchedPayments = paymentRepository.findByBankAccountNumber(bankAccountNumber);

        log.info("Successfully got all payments for account {}", bankAccountNumber);

        return paymentMapper.toBasicResponseList(fetchedPayments);
    }

    public List<PaymentResponseBasic> getPaymentsByStatus(PaymentStatus status){
        log.debug("Fetching payments with status {}", status);

        List<Payment> fetchedPayments = paymentRepository.findByStatus(status);

        log.info("Successfully got all payments with status {}", status);

        return paymentMapper.toBasicResponseList(fetchedPayments);
    }

    public List<PaymentResponseBasic> getPaymentsByDueDate(LocalDate dueDate){
        log.debug("Fetching payments with due date");

        List<Payment> fetchedPayments = paymentRepository.findByDueDate(dueDate);

        log.info("Successfully got all payments due by {}", dueDate);

        return paymentMapper.toBasicResponseList(fetchedPayments);
    }

    public List<PaymentResponseBasic> getAllPayments(){
        log.debug("Fetching all payments");

        List<Payment> fetchedPayments = paymentRepository.findAll();

        log.info("Successfully got all payments");

        return paymentMapper.toBasicResponseList(fetchedPayments);
    }

}
