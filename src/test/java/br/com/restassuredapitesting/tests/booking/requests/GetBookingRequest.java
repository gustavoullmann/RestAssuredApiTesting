package br.com.restassuredapitesting.tests.booking.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GetBookingRequest {

    @Step("Retorna lista de Id's das Reservas cadastradas")
    public Response getBookingIdsList() {
        return given()
                .when()
                .get("booking");
    }

    @Step("Retorna uma reserva específica cadastrada usando Id")
    public Response getSpecificBookingWithId(int id) {
        return given()
                .when()
                .get("booking/" + id);
    }

    @Step("Retorna lista de Id's das Reservas cadastradas usando filtros")
    public Response getBookingIdsListWithFilters(String filter1, String value1,
                                                 String filter2, String value2,
                                                 String filter3, String value3) {

        return given()
                .queryParams(filter1, value1, filter2, value2, filter3, value3)
                .when()
                .get("booking");
    }
}