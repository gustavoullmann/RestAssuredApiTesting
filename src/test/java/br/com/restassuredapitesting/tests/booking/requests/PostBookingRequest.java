package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.booking.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class PostBookingRequest {

    BookingPayloads bookingPayloads = new BookingPayloads();

    @Step("Cria uma reserva")
    public Response createBooking(JSONObject payload) {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(payload.toString())
                .post("booking/");
    }

    @Step("Cria uma reserva com payload incorreto")
    public Response createInvalidBooking() {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(bookingPayloads.payloadCreateInvalidBooking().toString())
                .post("booking/");
    }

    @Step("Cria uma reserva com header Accept inválido")
    public Response createBookingInvalidAccept(JSONObject payload) {
        return given()
                .header("Accept", "-")
                .header("Content-Type", "application/json")
                .when()
                .body(payload.toString())
                .post("booking/");
    }
}
