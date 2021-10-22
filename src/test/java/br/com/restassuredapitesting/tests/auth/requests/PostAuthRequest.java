package br.com.restassuredapitesting.tests.auth.requests;

import br.com.restassuredapitesting.tests.auth.requests.payload.AuthPayloads;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PostAuthRequest {

    @Step("Retorna o token")
    public Response tokenReturs() {

        AuthPayloads authPayloads = new AuthPayloads();

        return given()
                .header("Content-Type", "application/json")
                .when()
                .body(authPayloads.jsonAuthLogin().toString())
                .post("auth");
    }

    @Step("Busca o token")
    public String getToken() {
        return "token=" + this.tokenReturs()
                .then()
                .statusCode(200)
                .extract()
                .path("token");
    }
}