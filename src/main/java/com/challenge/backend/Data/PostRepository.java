package com.challenge.backend.Data;

import java.util.List;

import com.challenge.backend.Model.Category;
import com.challenge.backend.Model.Post;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends CrudRepository<Post, Integer> {


        @Query("SELECT p FROM Post p WHERE (:title is null or p.title = :title) and (:category is null" + " or p.category = :category) ORDER BY creationDate DESC")
        List<PostsOnly> findAllByTitleAndCategoryOrderByCreationDate(@Param ("title") String title, @Param ("category") Category category) ;
        

}
