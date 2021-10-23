package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {

    @Step("Retorna os Ids da listagem de Reservas")
    public Response bookingReturnIds() {
        return given()
                .when()
                .get("booking");
    }

    @Step("Retorna a primeira reserva cadastrada")
    public Response bookingReturnFirstId() {
        int primeiroId = bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        return given()
                .when()
                .get("booking/" + primeiroId);
    }

    @Step("Retorna uma reserva específica cadastrada")
    public Response bookingReturn(int id) {
        return given()
                .when()
                .get("booking/" + id);
    }
}