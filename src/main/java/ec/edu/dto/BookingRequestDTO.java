package ec.edu.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class BookingRequestDTO {
    private int userId;
    private int movieSetupId;
    private double totalPrice;
    private List<Integer> seatNumberAllocationIds;


}
