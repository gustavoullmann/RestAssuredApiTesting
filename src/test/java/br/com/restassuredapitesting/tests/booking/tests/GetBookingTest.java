package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.EndToEnd;
import br.com.restassuredapitesting.suites.SchemaTests;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.utils.Utils;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.greaterThan;

@Feature("Feature - Consultas de Reservas")
public class GetBookingTest extends BaseTest {

    GetBookingRequest getBookingRequest = new GetBookingRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar Id's de reservas")
    public void validaListagemDeIdsDasReservas() {

        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SchemaTests.class})
    @DisplayName("Garantir o schema de retorno da listagem de reservas")
    public void validaSchemaDaListagemDeReservas() {

        getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking","bookingsIdList"))));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SchemaTests.class})
    @DisplayName("Garantir o schema de retorno de uma reserva")
    public void validaSchemaDeUmaReserva() {

        getBookingRequest.bookingReturnFirstId()
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking","booking"))));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Garantir o retorno de uma reserva específica")
    public void validaRetornoDeReservaEspecifica() {

        getBookingRequest.bookingReturnFirstId()
                .then()
                .statusCode(200);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar Id's de reservas filtrados pelo nome")
    public void validaListagemDeIdsDasReservasPeloNome() {

        Response booking = getBookingRequest.bookingReturnFirstId();

        String firstName = booking.then().extract().path("firstname");

        getBookingRequest.bookingReturnIdsByFilter("firstname", firstName,
                                                   "", null,
                                                   "", null)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar Id's de reservas filtrados pelo sobrenome")
    public void validaListagemDeIdsDasReservasPeloSobrenome() {

        Response booking = getBookingRequest.bookingReturnFirstId();

        String lastName = booking.then().extract().path("lastname");

        getBookingRequest.bookingReturnIdsByFilter("lastname", lastName,
                                                   "", null,
                                                   "", null)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar Id's de reservas filtrados pela data checkin")
    public void validaListagemDeIdsDasReservasPeloCheckin() {

        Response booking = getBookingRequest.bookingReturnFirstId();

        String checkIn = booking.then().extract().path("bookingdates.checkin");

        getBookingRequest.bookingReturnIdsByFilter("bookingdates.checkin", checkIn,
                                                   "", null,
                                                   "", null)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar Id's de reservas filtrados pela data checkout")
    public void validaListagemDeIdsDasReservasPeloCheckout() {

        Response booking = getBookingRequest.bookingReturnFirstId();

        String checkout = booking.then().extract().path("bookingdates.checkout");

        getBookingRequest.bookingReturnIdsByFilter("bookingdates.checkout", checkout,
                                                   "", null,
                                                   "", null)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar Id's de reservas filtrados pelas datas checkin e checkout")
    public void validaListagemDeIdsDasReservasPeloCheckinAndCheckout() {

        Response booking = getBookingRequest.bookingReturnFirstId();

        String checkIn = booking.then().extract().path("bookingdates.checkin");
        String checkOut = booking.then().extract().path("bookingdates.checkout");

        getBookingRequest.bookingReturnIdsByFilter("bookingdates.checkin", checkIn,
                                                   "bookingdates.checkout", checkOut,
                                                   "", null)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar Id's de reservas filtrados")
    public void validaListagemDeIdsDasReservasByFilter() {

        Response booking = getBookingRequest.bookingReturnFirstId();

        String firstName = booking.then().extract().path("firstname" );
        String checkIn = booking.then().extract().path("bookingdates.checkin");
        String checkOut = booking.then().extract().path("bookingdates.checkout");

        getBookingRequest.bookingReturnIdsByFilter("firstname", firstName,
                                                   "bookingdates.checkin", checkIn,
                                                   "bookingdates.checkout", checkOut)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, EndToEnd.class})
    @DisplayName("Retornar Status Code = 500 eo executar filtro mal formatado")
    public void validaRetorno500DeFiltroIncorretoNasReservas() {

        Response booking = getBookingRequest.bookingReturnFirstId();

        String firstName = booking.then().extract().path("firstname" );
        String checkIn = booking.then().extract().path("bookingdates.checkin");
        String checkOut = booking.then().extract().path("bookingdates.checkout");

        getBookingRequest.bookingReturnIdsByFilter("teste", firstName,
                        "", null,
                        "", null)
                .then()
                .statusCode(500);
    }
}