package br.com.restassuredapitesting.tests.healthcheck.tests;

import br.com.restassuredapitesting.base.BaseTest;
import br.com.restassuredapitesting.suites.AllTests;
import br.com.restassuredapitesting.suites.HealthCheck;
import br.com.restassuredapitesting.tests.healthcheck.requests.HealthCheckRequest;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Feature("Feature - Verificação de status de API")
public class GetHealthCheckTest extends BaseTest {

    HealthCheckRequest healthCheckRequest = new HealthCheckRequest();

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Category({AllTests.class, HealthCheck.class})
    @DisplayName("Verifica se a API está online")
    public void verificaApiOnline() {

        healthCheckRequest.healthCheckApiReturn()
                .then()
                .statusCode(201);
    }
}