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
    @DisplayName("Listar Id's de Reservas válidas")
    public void assureGetBookingIdsList() {

        getBookingRequest.getBookingIdsList()
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SchemaTests.class})
    @DisplayName("Garantir o Schema da lista de Id's de Reservas válidas")
    public void assureGetBookingIdsListSchema() {

        getBookingRequest.getBookingIdsList()
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking","bookingsIdList"))));
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, SchemaTests.class})
    @DisplayName("Garantir o Schema das informações de uma Reserva válida")
    public void assureGetBookingSchema() {

        int firstId = getBookingRequest.returnBookingIdsListFirstId();

        getBookingRequest.getSpecificBookingWithId(firstId)
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(new File(Utils.getSchemaBasePath("booking","booking"))));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Garantir o retorno de uma Reserva específica")
    public void assureGetSpecificBookingWithId() {

        int firstId = getBookingRequest.returnBookingIdsListFirstId();

        getBookingRequest.getSpecificBookingWithId(firstId)
                .then()
                .statusCode(200);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar Id's de reservas filtradas pelo nome")
    public void assureGetBookingIdsListNameFilter() {

        int firstId = getBookingRequest.returnBookingIdsListFirstId();
        Response booking = getBookingRequest.getSpecificBookingWithId(firstId);
        String firstName = booking.then().extract().path("firstname");

        getBookingRequest.getBookingIdsListWithFilters("firstname", firstName,
                                                   "", null,
                                                   "", null)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar Id's de Reservas válidas filtradas pelo sobrenome")
    public void assureGetBookingIdsListLastnameFilter() {

        int firstId = getBookingRequest.returnBookingIdsListFirstId();
        Response booking = getBookingRequest.getSpecificBookingWithId(firstId);
        String lastName = booking.then().extract().path("lastname");

        getBookingRequest.getBookingIdsListWithFilters("lastname", lastName,
                                                   "", null,
                                                   "", null)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar Id's de Reservas válidas filtradas pela data de checkin")
    public void assureGetBookingIdsListCheckinFilter() {

        int firstId = getBookingRequest.returnBookingIdsListFirstId();
        Response booking = getBookingRequest.getSpecificBookingWithId(firstId);
        String checkIn = booking.then().extract().path("bookingdates.checkin");

        getBookingRequest.getBookingIdsListWithFilters("bookingdates.checkin", checkIn,
                                                   "", null,
                                                   "", null)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar Id's de Reservas válidas filtradas pela data de checkout")
    public void assureGetBookingIdsListCheckoutFilter() {

        int firstId = getBookingRequest.returnBookingIdsListFirstId();
        Response booking = getBookingRequest.getSpecificBookingWithId(firstId);
        String checkout = booking.then().extract().path("bookingdates.checkout");

        getBookingRequest.getBookingIdsListWithFilters("bookingdates.checkout", checkout,
                                                   "", null,
                                                   "", null)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar Id's de Reservas válidas filtradas pelas datas de checkin e de checkout")
    public void assureGetBookingIdsListCheckinCheckoutFilter() {

        int firstId = getBookingRequest.returnBookingIdsListFirstId();
        Response booking = getBookingRequest.getSpecificBookingWithId(firstId);
        String checkIn = booking.then().extract().path("bookingdates.checkin");
        String checkOut = booking.then().extract().path("bookingdates.checkout");

        getBookingRequest.getBookingIdsListWithFilters("bookingdates.checkin", checkIn,
                                                   "bookingdates.checkout", checkOut,
                                                   "", null)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Listar Id's de Reservas válidas filtradas por nome e datas de checkin e de checkout")
    public void assureGetBookingIdsListNameCheckinCheckoutFilter() {

        int firstId = getBookingRequest.returnBookingIdsListFirstId();
        Response booking = getBookingRequest.getSpecificBookingWithId(firstId);
        String firstName = booking.then().extract().path("firstname" );
        String checkIn = booking.then().extract().path("bookingdates.checkin");
        String checkOut = booking.then().extract().path("bookingdates.checkout");

        getBookingRequest.getBookingIdsListWithFilters("firstname", firstName,
                                                   "bookingdates.checkin", checkIn,
                                                   "bookingdates.checkout", checkOut)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, EndToEnd.class})
    @DisplayName("Retornar Status Code = 500 eo executar filtro de Reservas mal formatado")
    public void assureGet500StatusCodeUsingBadFilter() {

        int firstId = getBookingRequest.returnBookingIdsListFirstId();
        Response booking = getBookingRequest.getSpecificBookingWithId(firstId);
        String firstName = booking.then().extract().path("firstname" );

        getBookingRequest.getBookingIdsListWithFilters("teste", firstName,
                        "", null,
                        "", null)
                .then()
                .statusCode(500);
    }
}