package ec.edu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.dto.PaymentDTO;
import ec.edu.model.Payment;
import ec.edu.service.PaymentService;

@CrossOrigin("*")
@RestController
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
//	@PostMapping("/API/savepayment")
//	public ResponseEntity<Payment>createpayment(@RequestBody PaymentDTO paymentDTO){
//		Payment savedpayment =paymentService.createpayment(paymentDTO);
//		return new ResponseEntity<>(savedpayment,HttpStatus.CREATED);
//	}
	
	
//    @PostMapping("/save")
//    public String savePayment(@RequestBody PaymentDTO dto) {
//        paymentService.savePayment(dto);
//        return "Payment saved successfully";
//    }
	
	  @PostMapping("/savePayment")
	    public ResponseEntity<String> savePayment(@RequestBody PaymentDTO dto) {
	        String transactionId = paymentService .savePayment(dto);
	        return ResponseEntity.ok(transactionId);
	    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/fetchallpayments")
	public ResponseEntity<List<PaymentDTO>>fetchallpayments(){
		List<PaymentDTO> paymentDTOs = paymentService.fetchallpayments();
		return new ResponseEntity<>(paymentDTOs, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/fetchbyonepayment/{paymentId}")
	public ResponseEntity<PaymentDTO> getOnePayment(@PathVariable int paymentId) {
	    PaymentDTO paymentDTO = paymentService.getOnePayment(paymentId);
	    return new ResponseEntity<>(paymentDTO, HttpStatus.OK);
	}


//	  @DeleteMapping("/deleteBite/{biteId}")
//	  
//	  public ResponseEntity<String> deleteBite(@PathVariable int biteId){
//		  paymentService. deleteBite(biteId);
//			  return new ResponseEntity<>("Deleted bite with Id" +biteId,HttpStatus.OK);
//		  }
	
	
	
	
	
}
