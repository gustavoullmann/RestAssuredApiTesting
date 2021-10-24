package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.booking.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PutBookingRequest {

    BookingPayloads bookingPayloads = new BookingPayloads();

    @Step("Atualiza uma Reserva específica com o parâmetro token")
    public Response updateBookingToken(int id, String token) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", token)
                .when()
                .body(bookingPayloads.payloadValidBookingToken().toString())
                .put("booking/" + id);
    }

    @Step("Cria uma reserva")
    public Response createBooking() {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(bookingPayloads.payloadCreateValidBooking().toString())
                .post("booking/");
    }

    @Step("Atualiza uma Reserva específica com o parâmetro Authorization")
    public Response updateBookingAuthorization(int id) {
        return given()
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(bookingPayloads.payloadValidBookingAuthorization().toString())
                .put("booking/" + id);
    }
}