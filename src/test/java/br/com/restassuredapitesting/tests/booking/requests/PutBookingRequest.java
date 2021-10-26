package br.com.restassuredapitesting.tests.booking.requests;

import br.com.restassuredapitesting.tests.booking.payloads.BookingPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PutBookingRequest {

    BookingPayloads bookingPayloads = new BookingPayloads();

    @Step("Atualiza uma Reserva específica usando um token válido")
    public Response putUpdateBookingWithValidToken(int id, String token) {
        return given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", token)
                .when()
                .body(bookingPayloads.validBookingPayload1().toString())
                .put("booking/" + id);
    }

    @Step("Atualiza uma Reserva específica usando uma Authorization válida")
    public Response putUpdateBookingWithValidAuthorization(int id) {
        return given()
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=") //TODO: tentar passar essa autorização para PostAuthRequest
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(bookingPayloads.validBookingPayload2().toString())
                .put("booking/" + id);
    }
}