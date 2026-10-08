package ec.edu.service;

import java.util.List;

import ec.edu.dto.PaymentDTO;
import ec.edu.model.Payment;

public interface PaymentService {

	Payment createpayment(PaymentDTO paymentDTO);

	List<PaymentDTO> fetchallpayments();

	PaymentDTO getOnePayment(int paymentId);

	String savePayment(PaymentDTO dto);

}