package api_testing.request;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonAutoDetect
public class Booking {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;

    public static Booking getFullPayload() {
        Faker faker = new Faker();

        BookingDates bookingDates = new BookingDates();

        bookingDates.setCheckin(faker
                .date()
                .between(new GregorianCalendar(2024, Calendar.JUNE, 15).getTime(),
                        new GregorianCalendar(2024, Calendar.JUNE, 30).getTime()));
        bookingDates.setCheckout(faker
                .date()
                .between(new GregorianCalendar(2024, Calendar.JULY, 15).getTime(),
                        new GregorianCalendar(2024, Calendar.JULY, 30).getTime()));

        return new BookingBuilder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .totalprice(faker.number().numberBetween(50, 500))
                .depositpaid(faker.bool().bool())
                .bookingdates(bookingDates)
                .additionalneeds(faker.cat().breed())
                .build();
    }

    public static Booking getPayloadFromFile(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream resource = Booking.class.getClassLoader().getResourceAsStream(filePath);
        return objectMapper.readValue(resource, Booking.class);
    }

}
