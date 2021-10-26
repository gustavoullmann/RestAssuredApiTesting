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

        getBookingRequest.returnBookingIdsList()
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SchemaTests.class})
    @DisplayName("Garantir o schema de retorno da listagem de reservas")
    public void validaSchemaDaListagemDeReservas() {

        getBookingRequest.returnBookingIdsList()
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking","bookingsIdList"))));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SchemaTests.class})
    @DisplayName("Garantir o schema de retorno de uma reserva")
    public void validaSchemaDeUmaReserva() {

        int firstId = getBookingRequest.returnBookingIdsListFirstId();

        getBookingRequest.returnSpecificBookingWithId(firstId)
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking","booking"))));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Garantir o retorno de uma reserva específica")
    public void validaRetornoDeReservaEspecifica() {

        int firstId = getBookingRequest.returnBookingIdsListFirstId();

        getBookingRequest.returnSpecificBookingWithId(firstId)
                .then()
                .statusCode(200);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar Id's de reservas filtrados pelo nome")
    public void validaListagemDeIdsDasReservasPeloNome() {

        int firstId = getBookingRequest.returnBookingIdsListFirstId();
        Response booking = getBookingRequest.returnSpecificBookingWithId(firstId);
        String firstName = booking.then().extract().path("firstname");

        getBookingRequest.returnBookingIdsListWithFilters("firstname", firstName,
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

        int firstId = getBookingRequest.returnBookingIdsListFirstId();
        Response booking = getBookingRequest.returnSpecificBookingWithId(firstId);
        String lastName = booking.then().extract().path("lastname");

        getBookingRequest.returnBookingIdsListWithFilters("lastname", lastName,
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

        int firstId = getBookingRequest.returnBookingIdsListFirstId();
        Response booking = getBookingRequest.returnSpecificBookingWithId(firstId);
        String checkIn = booking.then().extract().path("bookingdates.checkin");

        getBookingRequest.returnBookingIdsListWithFilters("bookingdates.checkin", checkIn,
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

        int firstId = getBookingRequest.returnBookingIdsListFirstId();
        Response booking = getBookingRequest.returnSpecificBookingWithId(firstId);
        String checkout = booking.then().extract().path("bookingdates.checkout");

        getBookingRequest.returnBookingIdsListWithFilters("bookingdates.checkout", checkout,
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

        int firstId = getBookingRequest.returnBookingIdsListFirstId();
        Response booking = getBookingRequest.returnSpecificBookingWithId(firstId);
        String checkIn = booking.then().extract().path("bookingdates.checkin");
        String checkOut = booking.then().extract().path("bookingdates.checkout");

        getBookingRequest.returnBookingIdsListWithFilters("bookingdates.checkin", checkIn,
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

        int firstId = getBookingRequest.returnBookingIdsListFirstId();
        Response booking = getBookingRequest.returnSpecificBookingWithId(firstId);
        String firstName = booking.then().extract().path("firstname" );
        String checkIn = booking.then().extract().path("bookingdates.checkin");
        String checkOut = booking.then().extract().path("bookingdates.checkout");

        getBookingRequest.returnBookingIdsListWithFilters("firstname", firstName,
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

        int firstId = getBookingRequest.returnBookingIdsListFirstId();
        Response booking = getBookingRequest.returnSpecificBookingWithId(firstId);
        String firstName = booking.then().extract().path("firstname" );

        getBookingRequest.returnBookingIdsListWithFilters("teste", firstName,
                        "", null,
                        "", null)
                .then()
                .statusCode(500);
    }
}