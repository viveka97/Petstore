package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import static org.hamcrest.Matchers.equalTo;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.allOf;

@QuarkusTest
public class PetDataTest {

    @Test
    public void testPetEndpoint() {
        given()
                .when().get("/data/pets")
                .then()
                .statusCode(200);

    }

    @Test
    public void testPetAddEndpointSuccess(){
        given()
                .header("Content-Type","application/json")
                .body("{\r\n    \"name\":\"Dane\",\r\n  \"age\":10,\r\n   \"type\":\"Bird\"\r\n}")
                .when().post("data/pets/add")
                .then()
                .statusCode(200)
                .body("petId",notNullValue())
                .body("petAge",equalTo(10))
                .body("petName",equalTo("neo"))
                .body("petType",equalTo("Dog"));
    }

    @Test
    public void testPetUpdateEndpoint(){
        given()
                .header("Content-Type","application/json")
                .pathParam("petId",1)
                .body("{\n \"name\":\"Woofy\"\n }")
                .when().put("data/pets/edit/{petId}")
                .then()
                .statusCode(200)
                .body("petId",equalTo(1))
                .body("petAge",notNullValue())
                .body("petName",equalTo("Woofy"))
                .body("petType",notNullValue());
    }

    @Test
    public void testPetDeleteEndpoint(){
        given()
                .header("Content-Type","application/json")
                .pathParam("petId",1)
                .when().delete("data/pets/delete/{petId}")
                .then()
                .statusCode(200)
                .body("successful",equalTo(true));
    }



}