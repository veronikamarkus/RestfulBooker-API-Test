package api_testing;

import api_testing.request.Credentials;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static api_testing.TestConfig.ADMIN_PASSWORD;
import static api_testing.TestConfig.ADMIN_USERNAME;
import static io.restassured.RestAssured.given;

public class AuthApi extends BaseApi{
    private static final String API_URL = BASE_API_URL + "/auth";

    public static String getToken(){
        Credentials credentials = new Credentials();
        credentials.setUsername(ADMIN_USERNAME);
        credentials.setPassword(ADMIN_PASSWORD);

        Response response = postAuth(credentials);

        return response.getBody().jsonPath().getString("token");
    }

    public static Response postAuth(Credentials credentials){

        return given()
                .log().uri()
                .header("Content-Type", ContentType.JSON)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log().headers()
                .body(credentials)
                .log().body()
                .when()
                .post(API_URL)
                .then()
                .log().body()
                .and()
                .extract()
                .response();
    }
}
