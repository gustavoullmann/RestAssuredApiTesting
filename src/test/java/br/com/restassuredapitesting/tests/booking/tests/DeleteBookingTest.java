package br.com.restassuredapitesting.tests.booking.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AcceptanceTests;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.EndToEnd;
import br.com.restassuredapitesting.tests.auth.requests.PostAuthRequest;
import br.com.restassuredapitesting.tests.booking.requests.DeleteBookingRequest;
import br.com.restassuredapitesting.tests.booking.requests.GetBookingRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Feature("Feature - Exclusão de Reservas")
public class DeleteBookingTest extends BaseTest {

    DeleteBookingRequest deleteBookingRequest = new DeleteBookingRequest();
    GetBookingRequest getBookingRequest = new GetBookingRequest();
    PostAuthRequest postAuthRequest = new PostAuthRequest();

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, AcceptanceTests.class})
    @DisplayName("Excluir com sucesso uma reserva válida utilizando um token válido")
    public void deletaUmaReserva() {
        int primeiroId = getBookingRequest.getBookingIdsList()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        deleteBookingRequest.deleteBookingWithToken(primeiroId, postAuthRequest.authCreateTokenResponseToString())
                .then()
                .statusCode(201);
//              .body()??? TODO: revisar

        getBookingRequest.getSpecificBookingWithId(primeiroId)
                .then()
                .statusCode(404);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, EndToEnd.class})
    @DisplayName("Excluir uma reserva inválida utilizando um token válido")
    public void deletaUmaReservaInvalida() {

        deleteBookingRequest.deleteBookingWithToken(-1, postAuthRequest.authCreateTokenResponseToString())
                .then()
                .statusCode(405);
//              .body()??? TODO: revisar
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, EndToEnd.class})
    @DisplayName("Excluir uma reserva válida utilizando um token inválido")
    public void deletaUmaReservaSemToken() {
        int primeiroId = getBookingRequest.getBookingIdsList()
                .then()
                .statusCode(200)
                .extract()
                .path("[0].bookingid");

        deleteBookingRequest.deleteBookingWithToken(primeiroId, "invalidToken")
                .then()
                .statusCode(403);
//              .body()??? TODO: revisar

        getBookingRequest.getSpecificBookingWithId(primeiroId)
                .then()
                .statusCode(200);
    }
}