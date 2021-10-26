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
import io.qameta.allure.Story;
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
    @DisplayName("Excluir uma Reserva válida usando um token válido")
    public void assureDeleteValidBookingWithValidToken() {

        int firstId = getBookingRequest.returnBookingIdsListFirstId();
        String token = postAuthRequest.authCreateTokenResponseToString();

        deleteBookingRequest.deleteBookingWithToken(firstId, token)
                .then()
                .statusCode(201);

        getBookingRequest.getSpecificBookingWithId(firstId)
                .then()
                .statusCode(404);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, EndToEnd.class})
    @DisplayName("Excluir uma reserva inválida utilizando um token válido")
    public void avoidDeleteInvalidBookingWithValidToken() {

        String token = postAuthRequest.authCreateTokenResponseToString();

        deleteBookingRequest.deleteBookingWithToken(-1, token)
                .then()
                .statusCode(405);
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Category({AllTests.class, EndToEnd.class})
    @DisplayName("Excluir uma reserva válida utilizando um token inválido")
    public void avoidDeleteValidBookingWithInvalidToken() {

        int firstId = getBookingRequest.returnBookingIdsListFirstId();

        deleteBookingRequest.deleteBookingWithToken(firstId, "invalidToken")
                .then()
                .statusCode(403);

        getBookingRequest.getSpecificBookingWithId(firstId)
                .then()
                .statusCode(200);
    }
}