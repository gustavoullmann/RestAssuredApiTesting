package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.EndToEnd;
import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.payloads.BookingPayloads;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.PutBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;

@Feature("Feature - Atualização de Reservas")
public class PutBookingTest extends BaseTest {

    PutBookingRequest putBookingRequest = new PutBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Alterar uma reserva somente utilizando um token")
    public void validarAlteracaoDeUmaReservaUtilizandoToken() {
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        putBookingRequest.updateBookingToken(primeiroId, postAuthRequest.authCreateTokenResponseToString())
                .then()
                .statusCode(200)
                .body("size()",greaterThan(0));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Alterar uma reserva somente utilizando uma autorização")
    public void validarAlteracaoDeUmaReservaUtilizandoAuthorization() {

        int primeiroId = getBookingRequest.returnFirtsId();
        String nome = BookingPayloads.validBookingPayload1().getString("firstname");

        putBookingRequest.updateBookingAuthorization(primeiroId)
                .then()
                .statusCode(200)
                .body("firstname", containsString(nome));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, EndToEnd.class})
    @DisplayName("Alterar uma reserva sem enviar um token")
    public void validarAlteracaoDeUmaReservaUSemToken() {
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        putBookingRequest.updateBookingToken(primeiroId, "")
                .then()
                .statusCode(403);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, EndToEnd.class})
    @DisplayName("Alterar uma reserva com token adulterado")
    public void validarAlteracaoDeUmaReservaComTokenAdulterado() {
        int primeiroId = getBookingRequest.bookingReturnIds()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        putBookingRequest.updateBookingToken(primeiroId, postAuthRequest.authCreateTokenResponseToString() + "FakeToken")
                .then()
                .statusCode(403);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, EndToEnd.class})
    @DisplayName("Alterar uma reserva inexistente com token")
    public void validarAlteracaoDeUmaReservaInexistenteComToken() {

        putBookingRequest.updateBookingToken(-1, postAuthRequest.authCreateTokenResponseToString())
                .then()
                .statusCode(405);
    }
}