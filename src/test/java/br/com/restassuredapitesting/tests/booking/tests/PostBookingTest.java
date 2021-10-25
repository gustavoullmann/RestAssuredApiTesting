package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.EndToEnd;
import br.com.restassuredapitesting.tests.booking.payloads.BookingPayloads;
import br.com.restassuredapitesting.tests.booking.requests.PostBookingRequest;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.containsString;

public class PostBookingTest extends BaseTest {

    PostBookingRequest postBookingRequest = new PostBookingRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Criar uma reserva")
    public void criarUmaReserva() {
        String nome = BookingPayloads.payloadCreateValidBooking1().getString("firstname");

        postBookingRequest.createBooking(BookingPayloads.payloadCreateValidBooking1())
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
        String nome1 = BookingPayloads.payloadCreateValidBooking1().getString("firstname");
        String nome2 = BookingPayloads.payloadCreateValidBooking2().getString("firstname");

        postBookingRequest.createBooking(BookingPayloads.payloadCreateValidBooking1())
                .then()
                .statusCode(200)
                .body("booking.firstname", containsString(nome1));

        postBookingRequest.createBooking(BookingPayloads.payloadCreateValidBooking2())
                .then()
                .statusCode(200)
                .body("booking.firstname", containsString(nome2));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, EndToEnd.class})
    @DisplayName("Criar uma reserva com payload maior")
    public void criarUmaReservaComPayLoadInvalidoMaior() {

        String nome = BookingPayloads.payloadCreateInvalidBookingMoreParams().getString("firstname");

        postBookingRequest.createBooking(BookingPayloads.payloadCreateInvalidBookingMoreParams())
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

        postBookingRequest.createBookingInvalidAccept(BookingPayloads.payloadCreateValidBooking1())
                .then()
                .statusCode(418);
    }
}