package br.com.restassuredapitesting.tests.auth.requests;

import br.com.restassuredapitesting.tests.auth.requests.payload.AuthPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostAuthRequest {

    @Step("Autentica login e retorna Response (com token)")
    public Response authAndCreateTokenResponse() {

        AuthPayloads authPayloads = new AuthPayloads();

        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(authPayloads.jsonAuthLogin().toString())
                .post("auth");
    }

    @Step("Extrai o token de uma Response para uma String")
    public String authCreateTokenResponseToString() {
        return "token=" + this.authAndCreateTokenResponse()
                .then()
                .statusCode(200)
                .extract()
                .path("token");
    }
}