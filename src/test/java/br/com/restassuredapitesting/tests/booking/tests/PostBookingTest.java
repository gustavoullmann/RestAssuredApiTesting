package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.EndToEnd;
import br.com.restassuredapitesting.tests.booking.payloads.BookingPayloads;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.containsString;

@Feature("Feature - Criação de Reservas")
public class PostBookingTest extends BaseTest {

    PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Criar uma Reserva válida")
    public void assurePostCreateValidBooking() {

        JSONObject validBookingPayload = BookingPayloads.validBookingPayload1();
        String firstname = BookingPayloads.validBookingPayload1().getString("firstname");

        postBookingRequest.postCreateBooking(validBookingPayload)
                .then()
                .statusCode(200)
                .body("booking.firstname", containsString(firstname));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, EndToEnd.class})
    @DisplayName("Criar uma Reserva inválido")
    public void avoidPostCreateInvalidBooking() {

        JSONObject invalidBookingPayload = BookingPayloads.invalidBookingPayload();

        postBookingRequest.postCreateBooking(invalidBookingPayload)
                .then()
                .statusCode(500);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Criar duas Reservas em sequência")
    public void assurePostCreateSequentialBookings() {

        JSONObject validBookingPayload1 = BookingPayloads.validBookingPayload1();
        JSONObject validBookingPayload2 = BookingPayloads.validBookingPayload2();
        String firstname1 = BookingPayloads.validBookingPayload1().getString("firstname");
        String firstname2 = BookingPayloads.validBookingPayload2().getString("firstname");

        postBookingRequest.postCreateBooking(validBookingPayload1)
                .then()
                .statusCode(200)
                .body("booking.firstname", containsString(firstname1));

        postBookingRequest.postCreateBooking(validBookingPayload2)
                .then()
                .statusCode(200)
                .body("booking.firstname", containsString(firstname2));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, EndToEnd.class})
    @DisplayName("Criar uma Reserva com informações extras no payload")
    public void assurePostCreateValidBookingWithExtraAttributes() {

        JSONObject invalidBookingPayloadExtraParams = BookingPayloads.invalidBookingPayloadExtraParams();
        String firstname = BookingPayloads.invalidBookingPayloadExtraParams().getString("firstname");

        postBookingRequest.postCreateBooking(invalidBookingPayloadExtraParams)
                .then()
                .log().all()
                .statusCode(200)
                .body("booking.firstname", containsString(firstname));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, EndToEnd.class})
    @DisplayName("Criar uma Reserva com atributo 'Header Accept' inválido")
    public void avoidPostCreateBookingWithInvalidAcceptHeader() {

        JSONObject validBookingPayload = BookingPayloads.validBookingPayload1();

        postBookingRequest.postCreateBookingWithInvalidAcceptHeader(validBookingPayload)
                .then()
                .statusCode(418);
    }
}