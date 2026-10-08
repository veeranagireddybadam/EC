package ec.edu.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "booking_ticket")
public class BookingTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingTicketId;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "booking_status")
    private String bookingStatus;

    @Column(name = "number_of_tickets")
    private int numberofTickets;

    @Temporal(TemporalType.DATE)
    private Date bookingDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movieSetupId")
    private MovieSetup movieSetup;
    
    @OneToMany(mappedBy = "bookingTicket", cascade = CascadeType.ALL)
    private List<BookingMovieBites> bookingMovieBites;

}
