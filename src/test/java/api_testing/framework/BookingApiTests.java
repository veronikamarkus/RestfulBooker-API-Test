package api_testing.framework;

import api_testing.AuthApi;
import api_testing.BookingApi;
import api_testing.request.Booking;
import api_testing.request.Credentials;
import api_testing.response.BookingResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static api_testing.TestConfig.*;
import static org.hamcrest.Matchers.notNullValue;


public class BookingApiTests {

    private static String token;

    @Test
    public void getBookingsShouldReturn200(){
        BookingApi.getBookings().then().statusCode(200);
    }

    @Test
    public void getBookingByIdShouldReturn200(){
        int id = 7;
        BookingApi.getBookingById(id).then().log().all().statusCode(200);
    }

    @Test
    public void postBookingShouldReturn200() throws IOException {
        Booking bookingPayload = Booking.getPayloadFromFile("json/jira_53.json");

        BookingApi.postBooking(bookingPayload)
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void postBookingResponseShouldContainId(){
        Booking bookingPayload = Booking.getFullPayload();

        BookingApi.postBooking(bookingPayload)
                .then()
                .body("bookingid", notNullValue());
    }

    @Test
    public void postBookingResponseShouldContainBooking(){
        Booking bookingPayload = Booking.getFullPayload();

        BookingApi.postBooking(bookingPayload)
                .then()
                .body("booking", notNullValue());
    }

    @Test
    public void postAuthWithCorrectCredentialsShouldReturn200(){
        Credentials credentials = getCredentials(ADMIN_USERNAME, ADMIN_PASSWORD);

        Response response = AuthApi.postAuth(credentials);

        Assertions.assertThat(response.statusCode()).isEqualTo(200);
    }

    @Test
    public void postAuthWithIncorrectCredentialsShouldReturnBadCredentials(){
        Credentials credentials = getCredentials(INCORRECT_USERNAME, INCORRECT_PASSWORD);

        Response response = AuthApi.postAuth(credentials);

        Assertions.assertThat(response
                .getBody()
                .jsonPath()
                .getString("reason"))
                .isEqualTo("Bad credentials");
    }

    @Test
    public void postAuthReturnsToken(){
        Credentials credentials = getCredentials(ADMIN_USERNAME, ADMIN_PASSWORD);

        Response response = AuthApi.postAuth(credentials);

        Assertions.assertThat(response
                        .getBody()
                        .jsonPath()
                        .getString("token"))
                        .isNotNull();
    }

    @Test
    public void postBookingWithIncorrectAcceptHeaderReturns418(){
        Booking bookingPayload = Booking.getFullPayload();

         Response response = BookingApi.postBooking(bookingPayload, ContentType.JSON, ContentType.TEXT);

        Assertions.assertThat(response.statusCode()).isEqualTo(418);
    }

    @Test
    public void putBookingShouldReturn201(){
        String token = getToken();

        Booking booking = Booking.getFullPayload();
        BookingResponse bookingResponse = BookingApi.postBooking(booking).as(BookingResponse.class);

        int bookingId = bookingResponse.getBookingid();

        Response putBookingResponse = BookingApi.putBooking(booking, bookingId, token);

        Assertions.assertThat(putBookingResponse.statusCode()).isEqualTo(200);
    }

    @Test
    public void deleteBookingShouldReturn201(){
        String token = getToken();

        Booking booking = Booking.getFullPayload();
        BookingResponse bookingResponse = BookingApi.postBooking(booking).as(BookingResponse.class);

        int bookingId = bookingResponse.getBookingid();

        Response deleteBookingResponse = BookingApi.deleteBooking(bookingId, token);

        Assertions.assertThat(deleteBookingResponse.statusCode()).isEqualTo(201);
    }

    private String getToken() {
        if (token == null) {
            token = AuthApi.getToken();
        }
        return token;
    }

    private Credentials getCredentials(String username, String password){
        Credentials credentials = new Credentials();
        credentials.setUsername(username);
        credentials.setPassword(password);
        return credentials;
    }
}
