package ec.edu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.edu.dto.BookingHistoryDTO;
import ec.edu.dto.BookingRequestDTO;
import ec.edu.dto.BookingTicketDTO;
import ec.edu.model.BookingMovieBites;
import ec.edu.model.BookingTicket;
import ec.edu.model.BookingTicketNumber;
import ec.edu.model.MovieSetup;
import ec.edu.model.SeatNumberAllocation;
import ec.edu.model.User;
import ec.edu.repository.BookingMovieBitesRepository;
import ec.edu.repository.BookingTicketNumberRepository;
import ec.edu.repository.BookingTicketRepository;
import ec.edu.repository.MovieSetupRepository;
import ec.edu.repository.SeatNumberAllocationRepository;
import ec.edu.repository.UserRepository;

@Service
public class BookingTicketServiceImp implements BookingTicketService {
	
	@Autowired
	private BookingTicketRepository bookingticketRepository;
	
	@Autowired
	private UserRepository userRepository;
	

    @Autowired
    private BookingMovieBitesRepository bookingMovieBitesRepository;
	
	@Autowired
	 private MovieSetupRepository movieSetupRepository;
	
	@Autowired
    private BookingTicketNumberRepository bookingTicketNumberRepository;

    @Autowired
    private SeatNumberAllocationRepository seatAllocationRepo;

    public BookingTicket createBookingTicket(BookingTicketDTO bookingTicketDTO) {
        BookingTicket bookingTicket = new BookingTicket();
        bookingTicket.setTotalPrice(bookingTicketDTO.getTotalPrice());
        bookingTicket.setBookingStatus(bookingTicketDTO.getBookingStatus());
        bookingTicket.setNumberofTickets(bookingTicketDTO.getNumberofTickets());
        bookingTicket.setBookingDate(new Date());

        User user = userRepository.findById(bookingTicketDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        bookingTicket.setUser(user);

        MovieSetup movieSetup = movieSetupRepository.findById(bookingTicketDTO.getMovieSetupId()).orElseThrow(() -> new RuntimeException("Movie Setup not found"));
        bookingTicket.setMovieSetup(movieSetup);

        return bookingticketRepository.save(bookingTicket);
    }



    @Override
    public List<BookingTicketDTO> fetchallbookingticketdetails() {
        List<BookingTicketDTO> bookingTicketDTOs = new ArrayList<>();
        List<BookingTicket> bookingTickets = bookingticketRepository.findAll();

        for (BookingTicket bookingTicket : bookingTickets) {
            BookingTicketDTO bookingTicketDTO = new BookingTicketDTO();
            bookingTicketDTO.setBookingTicketId(bookingTicket.getBookingTicketId());
            bookingTicketDTO.setTotalPrice(bookingTicket.getTotalPrice());
            bookingTicketDTO.setBookingStatus(bookingTicket.getBookingStatus());
            bookingTicketDTO.setNumberofTickets(bookingTicket.getNumberofTickets());
            bookingTicketDTO.setBookingDate(bookingTicket.getBookingDate());

            if (bookingTicket.getUser() != null) {
                bookingTicketDTO.setUserId(bookingTicket.getUser().getUserId());
            }

            if (bookingTicket.getMovieSetup() != null) {
                bookingTicketDTO.setMovieSetupId(bookingTicket.getMovieSetup().getMovieSetupId());
            }

            bookingTicketDTOs.add(bookingTicketDTO);
        }

        return bookingTicketDTOs;
    }


    @Override
    public BookingTicketDTO fetchonebookingticket(int bookingTicketId) {
        BookingTicket bookingTicket = bookingticketRepository.findById(bookingTicketId).orElse(null);
        if (bookingTicket == null) {
            return null;  // or throw custom exception if preferred
        }

        BookingTicketDTO bookingTicketDTO = new BookingTicketDTO();
        bookingTicketDTO.setBookingTicketId(bookingTicket.getBookingTicketId());
        bookingTicketDTO.setTotalPrice(bookingTicket.getTotalPrice());
        bookingTicketDTO.setBookingStatus(bookingTicket.getBookingStatus());
        bookingTicketDTO.setNumberofTickets(bookingTicket.getNumberofTickets());
        bookingTicketDTO.setBookingDate(bookingTicket.getBookingDate());

        if (bookingTicket.getUser() != null) {
            bookingTicketDTO.setUserId(bookingTicket.getUser().getUserId());
        }

        if (bookingTicket.getMovieSetup() != null) {
            bookingTicketDTO.setMovieSetupId(bookingTicket.getMovieSetup().getMovieSetupId());
        }

        return bookingTicketDTO;
    }


    @Override
    public BookingTicket updateBookingTicket(int bookingTicketId, BookingTicketDTO bookingTicketDTO) {
        BookingTicket bookingTicket = bookingticketRepository.findById(bookingTicketId)
            .orElseThrow(() -> new RuntimeException("Booking ticket not found with id: " + bookingTicketId));

        bookingTicket.setTotalPrice(bookingTicketDTO.getTotalPrice());
        bookingTicket.setBookingStatus(bookingTicketDTO.getBookingStatus());
        bookingTicket.setNumberofTickets(bookingTicketDTO.getNumberofTickets());
        bookingTicket.setBookingDate(bookingTicketDTO.getBookingDate());

        if (bookingTicketDTO.getUserId() != 0) {
            User user = userRepository.findById(bookingTicketDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + bookingTicketDTO.getUserId()));
            bookingTicket.setUser(user);
        } else {
            bookingTicket.setUser(null);  // or keep old user if you prefer
        }

        if (bookingTicketDTO.getMovieSetupId() != 0) {
            MovieSetup movieSetup = movieSetupRepository.findById(bookingTicketDTO.getMovieSetupId())
                .orElseThrow(() -> new RuntimeException("Movie setup not found with id: " + bookingTicketDTO.getMovieSetupId()));
            bookingTicket.setMovieSetup(movieSetup);
        } else {
            bookingTicket.setMovieSetup(null);  // or keep old movieSetup if you prefer
        }

        return bookingticketRepository.save(bookingTicket);
    }


	@Override
	public void deleteBookingTicket(int bookingTicketId) {
		// TODO Auto-generated method stub
		bookingticketRepository.deleteById(bookingTicketId);
		
	}



	@Override
	public void bookSeats(BookingRequestDTO bookingRequest) {
		// TODO Auto-generated method stub
		
	}



//	@Override
//	public Object getAllBookingHistories() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
//	@Autowired
//	private BookingTicketNumberRepository bookingTicketNumberRepository;
//
//	@Override
//	public void saveBookingTicketNumber(BookingTicketNumber btn) {
//	    bookingTicketNumberRepository.save(btn);
//	}

//	public void bookSeats(BookingRequestDTO bookingRequest) {
//	    User user = userRepository.findById(bookingRequest.getUserId())
//	        .orElseThrow(() -> new RuntimeException("User not found"));
//
//	    MovieSetup movieSetup = movieSetupRepository.findById(bookingRequest.getMovieSetupId())
//	        .orElseThrow(() -> new RuntimeException("MovieSetup not found"));
//
//	    // Create booking ticket
//	    BookingTicket bookingTicket = new BookingTicket();
//	    bookingTicket.setUser(user);
//	    bookingTicket.setMovieSetup(movieSetup);
//	    bookingTicket.setTotalPrice(bookingRequest.getTotalPrice());
//	    bookingTicket.setNumberofTickets(bookingRequest.getSeatNumberAllocationIds().size());
//	    bookingTicket.setBookingStatus("CONFIRMED");
//	    bookingTicket.setBookingDate(new Date());
//
//	    bookingTicket = bookingticketRepository.save(bookingTicket);
//
//	    // Create BookingTicketNumber
//	    BookingTicketNumber bookingTicketNumber = new BookingTicketNumber();
//	    bookingTicketNumber.setBookingticket(bookingTicket);
//
//	    // You can optionally set seat setup here if you want:
//	    // bookingTicketNumber.setSeatsetup(someSeatSetup);
//
//	    List<SeatNumberAllocation> seatAllocations = new ArrayList<>();
//	    for (Integer seatAllocId : bookingRequest.getSeatNumberAllocationIds()) {
//	        SeatNumberAllocation seatAllocation = seatAllocationRepo.findById(seatAllocId)
//	            .orElseThrow(() -> new RuntimeException("Seat allocation not found with id " + seatAllocId));
//
//	        // Link seat allocation to BookingTicketNumber
//	        seatAllocation.setBookingTicketNumber(bookingTicketNumber);
//	        seatAllocations.add(seatAllocation);
//	    }
//
//	    // Save BookingTicketNumber
//	    bookingTicketNumber.setSeatnumberallocation(seatAllocations);
//	    bookingTicketNumberRepository.save(bookingTicketNumber);
//
//	    // Save updated SeatNumberAllocations with their BookingTicketNumber linked
//	    seatAllocationRepo.saveAll(seatAllocations);
//	}
//
	@Override
    public List<BookingHistoryDTO> getAllBookingHistories() {
        List<Object[]> results = bookingticketRepository.fetchBookingHistory();
        List<BookingHistoryDTO> bookings = new ArrayList<>();

        for (Object[] row : results) {
            int bookingTicketId = row[0] != null ? ((Number) row[0]).intValue() : null;
            String transactionId = (String) row[1];
            String userName = (String) row[2];
            String movieName = (String) row[3];
            String screenName = (String) row[4];
            Date showDate = (Date) row[5];
            String showTime = (String) row[6];
            String seats = (String) row[7];
            Double amount = row[8] != null ? ((Number) row[8]).doubleValue() : null;

            // Fetch snack names + quantities for this booking
            List<BookingMovieBites> bites = bookingMovieBitesRepository.findByBookingTicketBookingTicketId(bookingTicketId);
            String biteNames = bites.stream()
                    .map(b -> b.getMovieBite().getBiteName() + " (" + b.getQuantity() + ")")
                    .collect(Collectors.joining(", "));

            bookings.add(new BookingHistoryDTO(
                    bookingTicketId,
                    userName,
                    movieName,
                    showDate,
                    showTime,
                    seats,
                    amount,
                    screenName,
                    transactionId,
                    biteNames
            ));
        }

        return bookings;
    }





	
	

	
	

	
}
