package com.revature.ra;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class DemoRestCRUD
{
    static RequestSpecification requestSpec;
    static ResponseSpecification responseSpec;
    private int createdPostId; // or String if your API returns string IDs

    @BeforeAll
    static void setUp()
    {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        requestSpec = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
                .addHeader("Accept", "RestAssuredDemo")
            .build();

        responseSpec = new ResponseSpecBuilder()
            .expectContentType(ContentType.JSON)
            .expectResponseTime(lessThan(2000L))
            .build();

    }

    @AfterAll
    static void tearDown()
    {
        RestAssured.reset();
    }

    @Test
    public void getPOst()
    {
        given()
            .spec(requestSpec)
                .when()
                .get("/posts/1")
                .then()
                .spec(responseSpec)
                .statusCode(200);
    }

    @Test
    @Order(1)
    @DisplayName("CREATE - POST new post")
    void create_post_returnsCreatedResource()
    {
        // Request body as JSON string
        String requestBody = """
            {
                "title": "Test Post from REST Assured",
                "body": "This post was created during our demo",
                "userId": 1
            }
            """;

        Response response = given()
                .spec(requestSpec)
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)  // Created
                .body("title", equalTo("Test Post from REST Assured"))
                .body("body", containsString("demo"))
                .body("userId", equalTo(1))
                .body("id", notNullValue())
                .extract()
                .response();

        // Store ID for later tests
        createdPostId = response.jsonPath().getInt("id");
        System.out.println("Created post with ID: " + createdPostId);
    }

    @ParameterizedTest(name = "GET /posts/{0} returns 200")
    @ValueSource(ints = {1, 2, 3, 4, 5})
    @DisplayName("GET multiple posts by ID")
    void getPost_variousIds_return200(int postId) {
        given()
                .when()
                .get("/posts/" + postId)
                .then()
                .statusCode(200)
                .body("id", equalTo(postId));
    }

    @ParameterizedTest(name = "User {0} has name {1}")
    @CsvSource({
            "1, Leanne Graham",
            "2, Ervin Howell",
            "3, Clementine Bauch",
            "4, Patricia Lebsack",
            "5, Chelsey Dietrich"
    })
    @DisplayName("Validate user names")
    void getUser_validateName_matchesExpected(int userId, String expectedName)
    {
        given()
                .when()
                .get("/users/" + userId)
                .then()
                .statusCode(200)
                .body("name", equalTo(expectedName));
    }

    @ParameterizedTest(name = "User {0} has name {1}")
    @CsvFileSource(resources = "/user_names.csv", numLinesToSkip = 1)
    @DisplayName("Validate user names")
    void getUserCSVFileSource_validateName_matchesExpected(int userId, String expectedName)
    {
        given()
                .when()
                .get("/users/" + userId)
                .then()
                .statusCode(200)
                .body("name", equalTo(expectedName));
    }

    @Test
    @DisplayName("Extract and assert with JUnit")
    void extractAndAssert_withJUnit()
    {
        var response = given()
                .when()
                .get("/users")
                .then()
                .extract()
                .response();

        // JUnit assertions
        int statusCode = response.statusCode();
        Assertions.assertEquals(200, statusCode, "Status should be 200");

        int userCount = response.jsonPath().getList("$").size();
        Assertions.assertEquals(10, userCount, "Should have 10 users");

        String firstUserName = response.jsonPath().getString("[0].name");
        Assertions.assertNotNull(firstUserName, "First user should have a name");
        Assertions.assertFalse(firstUserName.isEmpty(), "Name should not be empty");
    }

}
