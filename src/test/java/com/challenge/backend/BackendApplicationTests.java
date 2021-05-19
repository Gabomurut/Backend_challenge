package com.challenge.backend;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.challenge.backend.Data.PostRepository;
import com.challenge.backend.Data.PostsOnly;
import com.challenge.backend.Model.Post;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
class BackendApplicationTests {

	@Test
	void contextLoads() {
	}

    @MockBean
    private PostRepository mockRepository;
	
 
    @Before
    public void init() throws MalformedURLException {

		URL prueba = new URL("http://www.prueba.com");
		LocalDate date = LocalDate.now();
       
        PostsOnly post1 = (PostsOnly) new Post(1, "Titulo", "Contenido", prueba, "Categoria", date, 1);
        PostsOnly post2 = (PostsOnly) new Post(2, "Titulo", "Contenido", prueba, "Categoria", date, 1);

		
		
		@SuppressWarnings("unchecked")
        List<PostsOnly> posts = new ArrayList();
        posts.add(post1);
        posts.add(post2);

		Mockito.when(mockRepository.findById(1).get()).thenReturn((Post) post1);
        Mockito.when(mockRepository.findPostsByTitleAndCategoryOrderByCreationDate("","")).thenReturn(posts);

    }

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


