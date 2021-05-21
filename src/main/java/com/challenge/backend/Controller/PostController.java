package com.challenge.backend.Controller;

import java.util.List;
import java.util.NoSuchElementException;

import com.challenge.backend.Data.CategoryRepository;
import com.challenge.backend.Data.PostRepository;
import com.challenge.backend.Data.PostsOnly;
import com.challenge.backend.Model.Category;
import com.challenge.backend.Model.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;

@RestController
public class PostController {

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private CategoryRepository categoryRepository;

  List<Post> posts;

  /*
   * POST /posts Deberá guardar un nuevo post según los datos recibidos en la
   * petición. OK
   */

  @PostMapping("/posts")

  Post newPost(@ModelAttribute Post newPost) {

    if (newPost.getImage().matches("(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png)")) {

      postRepository.save(newPost);
      return newPost;

    } else {

      throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Verifcar URL de Imagen ");

    }

  }

  /*
   * GET /posts/:id Deberá buscar un post cuyo id sea el especificado en el
   * parámetro :id. Si existe, devolver sus detalles, caso contrario devolver un
   * mensaje de error.
   ** 
   * OK
   **/

  @GetMapping("/posts/{id}")
  Post post(@PathVariable int id) {
    try {
      return postRepository.findById(id).get();
    } catch (NoSuchElementException exc) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encuentra el post " + id, exc);
    }
  }

  /*
   * GET /posts Deberá mostrar un listado de posts, ordenados por fecha de
   * creación, en forma descendente. Este listado deberá mostrar solamente los
   * campos ID, título, imagen, categoría y fecha de creación. Se deberá poder
   * filtrar por título y/o categoría. /posts?title=TITULO
   * /posts?category=CATEGORIA /posts?titulo=TITULO&category=CATEGORY
   ** 
   * OK **
   */

  @GetMapping("/posts")

  List<PostsOnly> posts(String title, String category) {

    return postRepository.findPostsByTitleAndCategoryOrderByCreationDate(title, category);

  }

  /*
   * DELETE /posts/:id Deberá eliminar el post con el id especificado en el
   * parámetro :id. En el caso de que no exista, devolver un mensaje de error.
   ** 
   * OK
   */

  @DeleteMapping("/posts/{id}")
  void deletePost(@PathVariable int id) {

    try {
      postRepository.deleteById(id);
    } catch (NoSuchElementException exc) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encuentra el post " + id, exc);
    }
  }

  /*
   * PATCH /posts/:id Deberá actualizar el post con el id especificado en el
   * parámetro :id, y actualizar sus datos según el cuerpo de la petición. En el
   * caso de que no exista, devolver un mensaje de error. OK**
   */

  @PatchMapping("/posts/{id}")
  void patchPost(@PathVariable int id, Post updatePost) {
    try {
      Post postToPatch = postRepository.findById(id).get();
      updatePost.setId(id);
      if (updatePost.getTitle() == null) {
        updatePost.setTitle(postToPatch.getTitle());
      }
      if (updatePost.getContent() == null) {
        updatePost.setContent(postToPatch.getContent());
      }
      if (updatePost.getCategory() == null) {
        updatePost.setCategory(postToPatch.getCategory());
      }
      if (updatePost.getCreationDate() == null) {
        updatePost.setCreationDate(postToPatch.getCreationDate());
      }
      if (updatePost.getImage() == null) {
        updatePost.setImage(postToPatch.getImage());
      }
      if (updatePost.getUserId() == 0) {
        updatePost.setUserId(postToPatch.getUserId());
      }

      postRepository.save(updatePost);
    } catch (NoSuchElementException exc) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encuentra el post " + id, exc);
    }
  }

  @PostMapping("/category")

  Category newCategory(@ModelAttribute Category newCategory) {

          categoryRepository.save(newCategory);

          return newCategory;

}

}