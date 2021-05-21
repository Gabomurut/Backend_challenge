package com.challenge.backend.Data;

import java.time.LocalDate;

public interface PostsOnly {

    int getId();
    String getTitle();
    String getCategory();
    String getImage();
    LocalDate getCreationDate();


}
