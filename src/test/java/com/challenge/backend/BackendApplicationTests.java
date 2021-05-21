package com.challenge.backend;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import com.challenge.backend.Data.PostRepository;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
class BackendApplicationTests {

	@Test
	void contextLoads() {
	}

    @MockBean
    private PostRepository mockRepository;


    @Test
    public void testFindPostById() {
 
        given().contentType("application/json")
                .when().get("/posts/1")
                .then()
                .statusCode(200);

    }

    @Test
    public void testFindPost() {

		given().contentType("application/json")
                .when().get("/posts")
                .then()
                .statusCode(200).body("size()", is(2));

    }

}


