package api_testing;

import api_testing.request.Booking;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class BookingApi extends BaseApi {
    public static final String API_URL = BASE_API_URL + "/booking/";

    public static Response getBookings(){
        return given().get(API_URL);
    }

    public static Response getBookingById(int bookingId){
        return getBookingById(bookingId, JSON);
    }

    public static Response getBookingById(int bookingId, ContentType contentType){
        return given()
                .accept(contentType.toString())
                .when()
                .get(API_URL + bookingId);
    }

    public static Response postBooking(Booking booking){
        return postBooking(booking, JSON, JSON);
    }

    public static Response postBooking(Booking booking, ContentType contentType, ContentType acceptType){
        return given()
                .contentType(contentType.toString())
                .accept(acceptType.toString())
                .body(booking)
                .post(API_URL);
    }

    public static Response putBooking(Booking booking, int bookingId, String token){
        return putBooking(booking, bookingId, JSON, JSON, token);
    }

    public static Response putBooking(Booking booking, int bookingId, ContentType contentType, ContentType acceptType, String token){
        return given()
                .contentType(contentType.toString())
                .accept(acceptType.toString())
                .header("Cookie", "token=" + token)
                .body(booking)
                .put(API_URL + bookingId);
    }

    public static Response deleteBooking(int bookingId, String token){
        return deleteBooking(bookingId, token, JSON, JSON);
    }

    public static Response deleteBooking(int bookingId, String token, ContentType contentType, ContentType acceptType){
        return given()
                .contentType(contentType.toString())
                .accept(acceptType.toString())
                .header("Cookie", "token=" + token)
                .log().uri().and().log().headers()
                .delete(API_URL + bookingId);
    }
}
