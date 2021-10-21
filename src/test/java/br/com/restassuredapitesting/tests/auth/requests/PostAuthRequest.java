package br.com.restassuredapitesting.tests.auth.requests;

import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class PostAuthRequest {

    public Response tokenReturs() {
        JSONObject payload = new JSONObject();
        payload.put("username","admin");
        payload.put("password", "password123");

        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(payload.toString())
                .post("https://treinamento-api.herokuapp.com/auth");
    }

    public String getToken() {
        return "token=" + this.tokenReturs()
                .then()
                .statusCode(200)
                .extract()
                .path("token");
    }
}