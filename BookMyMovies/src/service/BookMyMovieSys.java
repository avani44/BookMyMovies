package service;

import java.sql.*;
import java.util.List;
import java.util.Scanner;
import config.DataBaseConfig;

public class BookMyMovieSys {

    Scanner sc = new Scanner(System.in);

    // Display movies
    public void displayMovies() {
        String sql = "SELECT * FROM movies";

        try (Connection con = DataBaseConfig.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("------------- Available Movies -------------");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("movie_id") + ". " +
                                rs.getString("title") +
                                " (" + rs.getString("genre") + ")"
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Display theaters in a city
    public void displayTheaters(String city) {
        String sql = "SELECT * FROM theaters WHERE city = ?";

        try (Connection con = DataBaseConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, city);
            ResultSet rs = stmt.executeQuery();

            System.out.println("Theaters in " + city + ":");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("theater_id") + ". " +
                                rs.getString("name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Display shows
    public void displayShows(int movieId, int theaterId) {
        String sql = "SELECT * FROM shows WHERE movie_id = ? AND theater_id = ?";

        try (Connection con = DataBaseConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, movieId);
            stmt.setInt(2, theaterId);

            ResultSet rs = stmt.executeQuery();
            System.out.println("------------- Available Shows -------------");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("show_id") + ". " +
                                rs.getString("timing") +
                                " | Seats Available: " +
                                rs.getInt("available_seats")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Book ticket
    public void bookTicket(int userId, int showId, List<String> selectedSeats) {

        String checkSeatSQL =
                "SELECT is_booked FROM seats WHERE seat_number = ? AND show_id = ?";
        String updateSeatSQL =
                "UPDATE seats SET is_booked = TRUE WHERE seat_number = ? AND show_id = ?";
        String bookingSQL =
                "INSERT INTO bookings (user_id, show_id, seats_booked, total_price) VALUES (?,?,?,?)";

        try (Connection con = DataBaseConfig.getConnection()) {

            con.setAutoCommit(false);

            // Check seat availability
            for (String seat : selectedSeats) {
                try (PreparedStatement stmt = con.prepareStatement(checkSeatSQL)) {
                    stmt.setString(1, seat);
                    stmt.setInt(2, showId);

                    ResultSet rs = stmt.executeQuery();
                    if (rs.next() && rs.getBoolean("is_booked")) {
                        System.out.println("Seat " + seat + " is already booked.");
                        con.rollback();
                        return;
                    }
                }
            }

            // Book seats
            for (String seat : selectedSeats) {
                try (PreparedStatement stmt = con.prepareStatement(updateSeatSQL)) {
                    stmt.setString(1, seat);
                    stmt.setInt(2, showId);
                    stmt.executeUpdate();
                }
            }

            double seatPrice = 200.0;
            double totalPrice = selectedSeats.size() * seatPrice;

            // Insert booking
            try (PreparedStatement stmt = con.prepareStatement(bookingSQL)) {
                stmt.setInt(1, userId);
                stmt.setInt(2, showId);
                stmt.setString(3, String.join(",", selectedSeats));
                stmt.setDouble(4, totalPrice);
                stmt.executeUpdate();
            }

            con.commit();
            System.out.println(
                    "✅ Booking Successful | Seats: " + selectedSeats +
                            " | Total Price: ₹" + totalPrice
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
