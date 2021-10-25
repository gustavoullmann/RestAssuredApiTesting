package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {

    public int returnBookingIdsListFirstId() {
        return returnBookingIdsList()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");
    }

    @Step("Retorna lista com Id's das Reservas cadastradas")
    public Response returnBookingIdsList() {
        return given()
                .when()
                .get("booking");
    }

    @Step("Retorna Reserva do primeiro Id cadastrado") //TODO: remover esse método e usar o returnSpecificBookingWithId em seu lugar
    public Response bookingReturnFirstId() {
        int firstId = returnBookingIdsListFirstId();

        return given()
                .when()
                .get("booking/" + firstId);
    }

    @Step("Retorna uma reserva específica cadastrada usando Id")
    public Response returnSpecificBookingWithId(int id) {
        return given()
                .when()
                .get("booking/" + id);
    }

    @Step("Retorna lista com Id's das Reservas cadastradas usando filtros")
    public Response returnBookingIdsListWithFilters(String filter1, String value1,
                                                    String filter2, String value2,
                                                    String filter3, String value3) {

        return given()
                .queryParams(filter1, value1, filter2, value2, filter3, value3)
                .when()
                .get("booking");
    }
}