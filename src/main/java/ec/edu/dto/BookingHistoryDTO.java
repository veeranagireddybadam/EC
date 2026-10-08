package ec.edu.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingHistoryDTO {
	private int bookingId;          // 1st
	private String userName;         // 2nd
	private String movieName;        // 3rd
	private Date showDate;           // 4th
	private String showTime;         // 5th
	private String seats;            // 6th
	private double amount;           // 7th
	private String screenName;       // 8th
	private String transactionId;	//9 th
	private String snacks; 

}
