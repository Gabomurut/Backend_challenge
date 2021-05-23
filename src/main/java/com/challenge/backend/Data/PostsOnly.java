package com.challenge.backend.Data;

import java.time.LocalDate;

import com.challenge.backend.Model.Category;


public interface PostsOnly {

    int getId();
    String getTitle();
    Category getCategory();
    String getImage();
    LocalDate getCreationDate();


}
