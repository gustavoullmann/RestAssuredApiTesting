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

    @Step("Retorna lista Id' filtrados pelo nome")
    public Response bookingReturnIdsByName() {
        String firstname = bookingReturnFirstId().then().extract().path("firstname" );

        return given()
                .when()
                .get("booking?firstname=" + firstname);
    }

    @Step("Retorna lista Id' filtrados pelo sobrenome")
    public Response bookingReturnIdsByLastName() {
        String lastName = bookingReturnFirstId().then().extract().path("lastname" );

        return given()
                .when()
                .get("booking?lastname=" + lastName);
    }

    @Step("Retorna lista Id' filtrados pela data checkin")
    public Response bookingReturnIdsByCheckin() {
        String checkin = bookingReturnFirstId().then().extract().path("bookingdates.checkin");

        return given()
                .when()
                .get("booking?checkin=" + checkin);
    }

    @Step("Retorna lista Id' filtrados pela data checkout")
    public Response bookingReturnIdsByCheckout() {
        String checkout = bookingReturnFirstId().then().extract().path("bookingdates.checkout");

        return given()
                .when()
                .get("booking?checkout=" + checkout);
    }

    @Step("Retorna lista Id' filtrados pelas datas checkin e checkout")
    public Response bookingReturnIdsByCheckinAndCheckout() {
        String checkin = bookingReturnFirstId().then().extract().path("bookingdates.checkin");
        String checkout = bookingReturnFirstId().then().extract().path("bookingdates.checkout");

        return given()
                .when()
                .get("booking?checkin=" + checkin + "&checkout=" + checkout);
    }

    @Step("Retorna lista Id' filtrados pelo nome e datas checkin e checkout")
    public Response bookingReturnIdsByNameAndCheckinAndCheckout() {
        String firstname = bookingReturnFirstId().then().extract().path("firstname" );
        String checkin = bookingReturnFirstId().then().extract().path("bookingdates.checkin");
        String checkout = bookingReturnFirstId().then().extract().path("bookingdates.checkout");

        return given()
                .when()
                .get("booking?firstname=" + firstname +
                        "&firstnamecheckin=" + checkin +
                        "&checkout=" + checkout);
    }
}