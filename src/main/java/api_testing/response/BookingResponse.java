package api_testing.response;

import api_testing.request.Booking;
import lombok.Data;

@Data
public class BookingResponse {
    private int bookingid;
    private Booking booking;

}
