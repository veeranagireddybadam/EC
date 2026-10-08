//package ec.edu.model;
//
//public class BookingMovieBites {
//
//}

package ec.edu.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class BookingMovieBites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "booking_ticket_id")
    private BookingTicket bookingTicket;

    @ManyToOne
    @JoinColumn(name = "bite_id")
    private MovieBite movieBite;

    private int quantity;
}

