package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class PostBookingRequest {

    @Step("Cria uma reserva com payload válido")
    public Response postCreateBooking(JSONObject payload) {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(payload.toString())
                .post("booking/");
    }

    @Step("Cria uma reserva com header Accept inválido")
    public Response postCreateBookingWithWrongAcceptHeader(JSONObject payload) {
        return given()
                .header("Accept", "-")
                .header("Content-Type", "application/json")
                .when()
                .body(payload.toString())
                .post("booking/");
    }
}