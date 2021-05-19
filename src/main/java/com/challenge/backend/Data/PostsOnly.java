package com.challenge.backend.Data;

import java.net.URL;
import java.time.LocalDate;

public interface PostsOnly {

    int getId();
    String getTitle();
    String getCategory();
    URL getImage();
    LocalDate getCreationDate();


}
