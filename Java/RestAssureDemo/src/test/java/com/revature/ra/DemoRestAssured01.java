package com.revature.ra;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

public class DemoRestAssured01
{
    @BeforeAll
    static void setUp()
    {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
    }

    @AfterAll
    static void tearDown()
    {
        RestAssured.reset();
    }

    @Test
    public void firstRequestDemo()
    {
        given()
                .log().all()
                .when()
                .get("/posts/1")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void testWithMoreDetails()
    {
        given()
                .log().parameters()
                .queryParam("userId", 1)
                .when()
                .get("/posts")  //post?userId=1
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("size()", greaterThan(0));
    }

    //Write a method to test a URL user/1. Test for name = "Leanne Graham", email @,
    // address city = "Gwenborough" and latitude < 0
    @Test
    public void testUserDetails()
    {
        given()
                .log().parameters()
                .when()
                .get("/users/1")
                .then()
                .log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("name", equalTo("Leanne Graham"))
                .body("email", containsString("@"))
                .body("address.city", equalTo("Gwenborough"))
                .body("address.geo.lng.toDouble()", greaterThan(0.0));
    }
}
