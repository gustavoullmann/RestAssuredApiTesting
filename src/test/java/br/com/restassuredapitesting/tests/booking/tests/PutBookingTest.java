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
    @DisplayName("Alterar uma Reserva válida usando um token válido")
    public void assurePutUpdateValidBookingWithValidToken() {

        int firstId = getBookingRequest.returnBookingIdsListFirstId();
        String firstname = BookingPayloads.validBookingPayload1().getString("firstname");
        String token = postAuthRequest.authCreateTokenResponseToString();

        putBookingRequest.putUpdateBookingWithValidToken(firstId, token)
                .then()
                .statusCode(200)
                .body("size()",greaterThan(0),
                        "firstname", containsString(firstname));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Alterar uma Reserva válida usando uma autorização válida")
    public void assurePutUpdateValidBookingWithValidAuthorization() {

        int firstId = getBookingRequest.returnBookingIdsListFirstId();
        String authorization = "Basic YWRtaW46cGFzc3dvcmQxMjM=";
        String firstname = BookingPayloads.validBookingPayload2().getString("firstname");

        putBookingRequest.putUpdateBookingWithValidAuthorization(firstId, authorization)
                .then()
                .statusCode(200)
                .body("size()",greaterThan(0),
                        "firstname", containsString(firstname));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, EndToEnd.class})
    @DisplayName("Alterar uma Reserva válida usando um token vazio")
    public void avoidPutUpdateValidBookingWithVoidToken() {

        int firstId = getBookingRequest.returnBookingIdsListFirstId();

        putBookingRequest.putUpdateBookingWithValidToken(firstId, "")
                .then()
                .statusCode(403);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, EndToEnd.class})
    @DisplayName("Alterar uma Reserva válida usando um token inválido")
    public void avoidPutUpdateValidBookingWithInvalidToken() {

        int firstId = getBookingRequest.returnBookingIdsListFirstId();
        String fakeToken = postAuthRequest.authCreateTokenResponseToString() + "FakeToken";

        putBookingRequest.putUpdateBookingWithValidToken(firstId, fakeToken )
                .then()
                .statusCode(403);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, EndToEnd.class})
    @DisplayName("Alterar uma Reserva inválida usando um token válido")
    public void avoidPutUpdateInvalidBookingWithValidToken() {

        String token = postAuthRequest.authCreateTokenResponseToString();

        putBookingRequest.putUpdateBookingWithValidToken(-1, token)
                .then()
                .statusCode(405);
    }
}