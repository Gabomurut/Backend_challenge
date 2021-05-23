package com.challenge.backend.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    String title;

    public int getId() {
        return id;
    }

    public void setId(int Id) {
        this.id = Id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }




    public Category(int id, String title) {
        this.id = id;
        this.title = title;

    }

    

    public Category() {
    }

    @Override
    public String toString() {
        return "Category [id=" + id  + ", title=" + title + "]";
    }

    

}
