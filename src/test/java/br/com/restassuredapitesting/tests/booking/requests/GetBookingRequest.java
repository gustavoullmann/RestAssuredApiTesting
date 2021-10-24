package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {

    public int returnFirtsId() {
        return bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");
    }

    @Step("Retorna os Ids da listagem de Reservas")
    public Response bookingReturnIds() {
        return given()
                .when()
                .get("booking");
    }

    @Step("Retorna a primeira reserva cadastrada")
    public Response bookingReturnFirstId() {
        int primeiroId = returnFirtsId();

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

    @Step("Retorna lista Id' com filtros")
    public Response bookingReturnIdsByFilter(String filter1, String value1,
                                             String filter2, String value2,
                                             String filter3, String value3) {

        return given()
                .queryParams(filter1, value1,
                            filter2, value2,
                            filter3, value3)
                .when()
                .get("booking");
    }
}







