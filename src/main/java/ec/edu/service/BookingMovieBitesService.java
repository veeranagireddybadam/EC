package ec.edu.service;

import ec.edu.dto.BookingMovieBiteDTO;
import ec.edu.model.BookingMovieBites;
import ec.edu.model.BookingTicket;
import ec.edu.model.MovieBite;
import ec.edu.repository.BookingMovieBitesRepository;
import ec.edu.repository.BookingTicketRepository;
import ec.edu.repository.MovieBiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingMovieBitesService {

    @Autowired
    private BookingMovieBitesRepository bookingMovieBitesRepository;

    @Autowired
    private BookingTicketRepository bookingTicketRepository;

    @Autowired
    private MovieBiteRepository movieBiteRepository;

    public void saveBookingMovieBites(int bookingTicketId, List<BookingMovieBiteDTO> snacks) throws Exception {
        BookingTicket bookingTicket = bookingTicketRepository.findById(bookingTicketId)
                .orElseThrow(() -> new Exception("BookingTicket not found with id: " + bookingTicketId));

        for (BookingMovieBiteDTO snackDto : snacks) {
            MovieBite movieBite = movieBiteRepository.findById(snackDto.getBiteId())
                    .orElseThrow(() -> new Exception("MovieBite not found with id: " + snackDto.getBiteId()));

            BookingMovieBites bookingMovieBites = new BookingMovieBites();
            bookingMovieBites.setBookingTicket(bookingTicket);
            bookingMovieBites.setMovieBite(movieBite);
            bookingMovieBites.setQuantity(snackDto.getQuantity());

            bookingMovieBitesRepository.save(bookingMovieBites);
        }
    }
}
