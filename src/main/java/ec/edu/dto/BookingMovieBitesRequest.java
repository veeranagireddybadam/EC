package ec.edu.dto;

import lombok.Data;
import java.util.List;

@Data
public class BookingMovieBitesRequest {
    private int bookingTicketId;
    private List<BookingMovieBiteDTO> snacks;
}
