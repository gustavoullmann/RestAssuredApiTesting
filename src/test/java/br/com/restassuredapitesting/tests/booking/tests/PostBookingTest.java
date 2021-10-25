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
    @DisplayName("Criar uma reserva")
    public void criarUmaReserva() {
        String nome = BookingPayloads.validBookingPayload1().getString("firstname");

        postBookingRequest.createBooking(BookingPayloads.validBookingPayload1())
                .then()
                .statusCode(200)
                .body("booking.firstname", containsString(nome));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, EndToEnd.class})
    @DisplayName("Criar uma reserva com payload inválido")
    public void criarUmaReservaComPayLoadInvalido() {

        postBookingRequest.createInvalidBooking()
                .then()
                .statusCode(500);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Criar duas reservas em sequencia")
    public void criarDuasReservasSequenciais() {
        String nome1 = BookingPayloads.validBookingPayload1().getString("firstname");
        String nome2 = BookingPayloads.validBookingPayload2().getString("firstname");

        postBookingRequest.createBooking(BookingPayloads.validBookingPayload1())
                .then()
                .statusCode(200)
                .body("booking.firstname", containsString(nome1));

        postBookingRequest.createBooking(BookingPayloads.validBookingPayload2())
                .then()
                .statusCode(200)
                .body("booking.firstname", containsString(nome2));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, EndToEnd.class})
    @DisplayName("Criar uma reserva com payload maior")
    public void criarUmaReservaComPayLoadInvalidoMaior() {

        String nome = BookingPayloads.invalidBookingPayloadExtraParams().getString("firstname");

        postBookingRequest.createBooking(BookingPayloads.invalidBookingPayloadExtraParams())
                .then()
                .log().all()
                .statusCode(200)
                .body("booking.firstname", containsString(nome));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, EndToEnd.class})
    @DisplayName("Criar uma reserva com Header Accept Inválido")
    public void criarUmaReservaHeaderAcceptInvalido() {

        postBookingRequest.createBookingInvalidAccept(BookingPayloads.validBookingPayload1())
                .then()
                .statusCode(418);
    }
}