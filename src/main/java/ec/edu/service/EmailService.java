package ec.edu.service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import ec.edu.model.BookingMovieBites;
import ec.edu.model.BookingTicket;
import ec.edu.model.BookingTicketNumber;
import ec.edu.model.Payment;
import ec.edu.repository.BookingMovieBitesRepository;
import ec.edu.repository.BookingTicketNumberRepository;
import ec.edu.repository.BookingTicketRepository;
import ec.edu.repository.PaymentRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    private final BookingTicketRepository bookingTicketRepo;
    private final BookingTicketNumberRepository bookingTicketNumberRepo;
    private final PaymentRepository paymentRepository;
    private final BookingMovieBitesRepository bookingMovieBitesRepo;
    private final JavaMailSender mailSender;

    public EmailService(BookingTicketRepository bookingTicketRepo,
                        BookingTicketNumberRepository bookingTicketNumberRepo,
                        PaymentRepository paymentRepository,
                        BookingMovieBitesRepository bookingMovieBitesRepo,
                        JavaMailSender mailSender) {
        this.bookingTicketRepo = bookingTicketRepo;
        this.bookingTicketNumberRepo = bookingTicketNumberRepo;
        this.paymentRepository = paymentRepository;
        this.bookingMovieBitesRepo = bookingMovieBitesRepo;
        this.mailSender = mailSender;
    }

    public void sendBookingConfirmationEmail(int bookingTicketId) {
        BookingTicket booking = bookingTicketRepo.findById(bookingTicketId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        // Fetch booked seats details
        List<BookingTicketNumber> bookedSeats = bookingTicketNumberRepo.findByBookingticket(booking);

        // Extract seat numbers sorted
        String seats = bookedSeats.stream()
                .map(bn -> bn.getSeatNumberAllocation().getSeatNumber())
                .sorted()
                .collect(Collectors.joining(", "));

        // Fetch payment transaction id
        Payment payment = paymentRepository.findByBookingticket(booking)
                .orElseThrow(() -> new RuntimeException("Payment not found for booking"));

        String transactionId = payment.getTransactionId();

        // Fetch screen name
        String screenName = booking.getMovieSetup().getScreen().getScreenName();

        // Fetch snacks and quantities
        List<BookingMovieBites> bookingBites = bookingMovieBitesRepo.findByBookingTicket(booking);

        String snacks;
        if (bookingBites.isEmpty()) {
            snacks = "None";
        } else {
            snacks = bookingBites.stream()
                .map(b -> b.getMovieBite().getBiteName() + " x" + b.getQuantity())
                .collect(Collectors.joining(", "));
        }

        // Compose email body with snacks info
        String emailBody = buildEmailBody(booking, seats, screenName, transactionId, snacks);

        // Send email
        String to = booking.getUser().getEmail();
        String subject = "🎫 Booking Confirmation - ExpressCinema";

        sendEmail(to, subject, emailBody);
    }

    private String buildEmailBody(BookingTicket booking, String seats, String screenName, String transactionId, String snacks) {
        String formattedShowDate = new SimpleDateFormat("dd MMMM yyyy").format(booking.getMovieSetup().getShowDate());

        return String.format(
                "<html>" +
                        "<body style='font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;'>" +

                        "<div style='max-width: 600px; margin: 0 auto; background-color: #fff; padding: 20px; border-radius: 8px; " +
                        "box-shadow: 0 4px 8px rgba(0,0,0,0.1);'>" +

                        "<h2 style='color: #0A142F; text-align: center;'>🎥 ExpressCinema Booking Confirmation</h2>" +
                        "<p style='font-size: 16px;'>Dear <strong>%s</strong>,</p>" +

                        "<p style='font-size: 15px;'>Thank you for your booking! Here are your ticket details:</p>" +

                        "<div style='border: 1px solid #ddd; border-radius: 6px; padding: 15px; background-color: #f9f9f9; margin-top: 15px;'>"+
                        "<table style='width:100%%; border-collapse: collapse;'>"+
                        "<tr><td style='padding: 8px; font-weight: bold;'>🎬 Movie:</td><td>%s</td></tr>"+
                        "<tr><td style='padding: 8px; font-weight: bold;'>🆔 Booking ID:</td><td>%d</td></tr>"+
                        "<tr><td style='padding: 8px; font-weight: bold;'>📅 Show Date:</td><td>%s</td></tr>"+
                        "<tr><td style='padding: 8px; font-weight: bold;'>⏰ Show Time:</td><td>%s</td></tr>"+
                        "<tr><td style='padding: 8px; font-weight: bold;'>🏟️ Screen:</td><td>%s</td></tr>"+
                        "<tr><td style='padding: 8px; font-weight: bold;'>💺 Seats:</td><td>%s</td></tr>"+
                        "<tr><td style='padding: 8px; font-weight: bold;'>🍿 Snacks:</td><td>%s</td></tr>"+ // Added Snacks row
                        "<tr><td style='padding: 8px; font-weight: bold;'>🎟️ Tickets:</td><td>%d</td></tr>"+
                        "<tr><td style='padding: 8px; font-weight: bold;'>💵 Total Price:</td><td>₹%.2f</td></tr>"+
                        "<tr><td style='padding: 8px; font-weight: bold;'>🔖 Transaction ID:</td><td>%s</td></tr>"+
                        "</table>" +
                        "</div>" +

                        "<p style='font-size: 14px; margin-top: 20px;'>We look forward to seeing you at the show! 🎉</p>" +
                        "<p style='font-weight: bold; color: #0A142F;'>- ExpressCinema Team</p>" +

                        "</div>" +
                        "</body>" +
                        "</html>",
                booking.getUser().getUserName(),
                booking.getMovieSetup().getMovie().getMovieTitle(),
                booking.getBookingTicketId(),
                formattedShowDate,
                booking.getMovieSetup().getShowtime(),
                screenName,
                seats,
                snacks,
                booking.getNumberofTickets(),
                booking.getTotalPrice(),
                transactionId
        );
    }

    private void sendEmail(String to, String subject, String body) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true); // enable HTML rendering

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
