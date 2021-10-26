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
        String nome = BookingPayloads.validBookingPayload1().getString("firstname");

        postBookingRequest.postCreateBooking(BookingPayloads.validBookingPayload1())
                .then()
                .statusCode(200)
                .body("booking.firstname", containsString(nome));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, EndToEnd.class})
    @DisplayName("Criar uma Reserva inválido")
    public void avoidPostCreateInvalidBooking() {

        postBookingRequest.postCreateBooking(BookingPayloads.invalidBookingPayload())
                .then()
                .statusCode(500);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Criar duas Reservas em sequência")
    public void assurePostCreateSequentialBookings() {
        String nome1 = BookingPayloads.validBookingPayload1().getString("firstname");
        String nome2 = BookingPayloads.validBookingPayload2().getString("firstname");

        postBookingRequest.postCreateBooking(BookingPayloads.validBookingPayload1())
                .then()
                .statusCode(200)
                .body("booking.firstname", containsString(nome1));

        postBookingRequest.postCreateBooking(BookingPayloads.validBookingPayload2())
                .then()
                .statusCode(200)
                .body("booking.firstname", containsString(nome2));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, EndToEnd.class})
    @DisplayName("Criar uma Reserva com informações extras no payload")
    public void assurePostCreateValidBookingWithExtraAttributes() {

        String nome = BookingPayloads.invalidBookingPayloadExtraParams().getString("firstname");

        postBookingRequest.postCreateBooking(BookingPayloads.invalidBookingPayloadExtraParams())
                .then()
                .log().all()
                .statusCode(200)
                .body("booking.firstname", containsString(nome));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, EndToEnd.class})
    @DisplayName("Criar uma Reserva com atributo 'Header Accept' inválido")
    public void avoidPostCreateBookingWithInvalidAcceptHeader() {

        postBookingRequest.postCreateBookingWithInvalidAcceptHeader(BookingPayloads.validBookingPayload1())
                .then()
                .statusCode(418);
    }
}