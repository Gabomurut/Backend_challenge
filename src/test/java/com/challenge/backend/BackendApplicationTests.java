package com.challenge.backend;

import com.challenge.backend.Data.CategoryRepository;
import com.challenge.backend.Data.UserRepository;
import com.challenge.backend.Model.Post;
import com.challenge.backend.SecurityConfig.JwtTokenService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import com.challenge.backend.Data.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackendApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@EnableAutoConfiguration(exclude= {JpaRepositoriesAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
class BackendApplicationTests {

    @Autowired
    private  JwtTokenService jwtTokenService;

    @MockBean
    private PostRepository mockRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @Before
    public void before() {

       
    }

    // Test de Find by Id (Post)

    @Test
    public void testFindPostById() {
        Authentication authentication = getAuthentication();
        String token = jwtTokenService.generateToken(authentication);

        Post post = new Post();
        when(mockRepository.findById(2)).thenReturn(Optional.of(post));
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + token);
        given().contentType("application/json").headers(headers).when().get("/posts/2").then().statusCode(200);
    }


     // Test Find by Id not found
    @Test
    public void testFindPostByIdNotFound() {
        Authentication authentication = getAuthentication();
        String token = jwtTokenService.generateToken(authentication);

        Post post = new Post();
        when(mockRepository.findById(1)).thenReturn(Optional.of(post));
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer "+token);
        given().contentType("application/json").headers(headers).when().get("/posts/2").then().statusCode(404);
    }

   
    // Test de Delete by Id (Post)

    @Test
    public void testDeletePostByIdNotFound() {
        Authentication authentication = getAuthentication();
        String token = jwtTokenService.generateToken(authentication);
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + token);

        doThrow(new EmptyResultDataAccessException(1)).when(mockRepository).deleteById(14);

        given().contentType("application/json").headers(headers).when().delete("/posts/14").then().statusCode(404);
    }


    // Test de autenticación. Usuario no autenticado intenta GET/posts/1
    @Test
    public void testFindPostByIdNotAuthenticated() {

        Post post = new Post();
        when(mockRepository.findById(1)).thenReturn(Optional.of(post));
        given().contentType("application/json").when().get("/posts/1").then().statusCode(401);
    }

    private Authentication getAuthentication() {
        Authentication authentication = new Authentication() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                GrantedAuthority grantedAuthority = new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return "ROLE_USER";
                    }
                };
                Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                grantedAuthorities.add(grantedAuthority);
                return grantedAuthorities;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                UserDetails userDetails = new UserDetails() {
                    @Override
                    public Collection<? extends GrantedAuthority> getAuthorities() {
                        return null;
                    }

                    @Override
                    public String getPassword() {
                        return null;
                    }

                    @Override
                    public String getUsername() {
                        return "fakeUser";
                    }

                    @Override
                    public boolean isAccountNonExpired() {
                        return false;
                    }

                    @Override
                    public boolean isAccountNonLocked() {
                        return false;
                    }

                    @Override
                    public boolean isCredentialsNonExpired() {
                        return false;
                    }

                    @Override
                    public boolean isEnabled() {
                        return true;
                    }
                };
                return userDetails;
            }

            @Override
            public boolean isAuthenticated() {
                return true;
            }

            @Override
            public void setAuthenticated(boolean b) throws IllegalArgumentException {

            }
        };
        return authentication;
    }



}
