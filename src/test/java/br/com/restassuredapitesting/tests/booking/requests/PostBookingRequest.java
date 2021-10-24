package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.booking.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostBookingRequest {

    BookingPayloads bookingPayloads = new BookingPayloads();

    @Step("Cria uma reserva")
    public Response createBooking() {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(bookingPayloads.payloadCreateValidBooking().toString())
                .post("booking/");
    }
}
