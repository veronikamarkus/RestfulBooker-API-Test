package api_testing;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.core.IsNull.notNullValue;

class AppTest {

    public static final String API_URL = "https://restful-booker.herokuapp.com/booking/";

    @Test
    public void givenAcceptType_whenGetAllBooking_thenShouldReturnHttpStatus200() {
        given()
                .accept(JSON.toString())
                .when()
                .get(API_URL)
                .then()
                .statusCode(SC_OK);
    }

    @Test
    public void givenAcceptType_whenGetBookingId_thenShouldReturnHttpStatus200() {
        int id = 7;

        given()
                .accept(JSON.toString())
                .when()
                .get(API_URL + id)
                .then()
                .log().all()
                .statusCode(SC_OK);
    }

    @Test
    public void givenAcceptType_whenPostBooking_thenShouldReturnHttpStatus200() {
        String payload = "{\n" +
                "    \"firstname\": \"John\",\n" +
                "    \"lastname\": \"Doe\",\n" +
                "    \"totalprice\": 100,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2025-04-08\",\n" +
                "        \"checkout\": \"2025-04-14\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";;

        given()
                .accept(JSON.toString())
                .contentType(JSON)
                .body(payload)
                .when()
                .post(API_URL)
                .then()
                .log().all()
                .statusCode(SC_OK);
    }

    @Test
    public void givenAcceptType_whenPostBooking_thenResponseShouldContainBooking() {
        String payload = "{\n" +
                "    \"firstname\": \"John\",\n" +
                "    \"lastname\": \"Doe\",\n" +
                "    \"totalprice\": 100,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2025-04-08\",\n" +
                "        \"checkout\": \"2025-04-14\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";;

        given()
                .accept(JSON.toString())
                .contentType(JSON)
                .body(payload)
                .when()
                .post(API_URL)
                .then()
                .log().all()
                .statusCode(SC_OK)
                .body("booking", notNullValue());
    }

    @Test
    public void givenAcceptType_whenPostBooking_thenResponseShouldContainId() {
        String payload = "{\n" +
                "    \"firstname\": \"John\",\n" +
                "    \"lastname\": \"Doe\",\n" +
                "    \"totalprice\": 100,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2025-04-08\",\n" +
                "        \"checkout\": \"2025-04-14\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";;

        given()
                .accept(JSON.toString())
                .contentType(JSON)
                .body(payload)
                .when()
                .post(API_URL)
                .then()
                .log().all()
                .statusCode(SC_OK)
                .body("bookingid", notNullValue());
    }
}
