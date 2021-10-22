package br.com.restassuredapitesting.tests.ping.requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class HealthCheckRequest {

    @Step("Retorna API online")
    public Response healthCheckApiReturn() {
        return given()
                .header("Content-Type", "application/json")
                .when()
                .get("ping");
    }
}
