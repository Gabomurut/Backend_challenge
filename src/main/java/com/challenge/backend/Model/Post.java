package com.challenge.backend.Model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@SQLDelete(sql = "UPDATE post SET deleted=true WHERE id = ?") // SOFT DELETE
@Where(clause = "deleted = false")

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String title;
    private String content;
    private String image;

    private String category;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate creationDate;

    private int userId;

    private boolean deleted;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Post() {
    }

    public Post(int id, String title, String content, String image, String category, LocalDate creationDate,
            int userId) {

        Id = id;
        this.title = title;
        this.content = content;
        this.image = image;
        this.category = category;
        this.creationDate = creationDate;
        this.userId = userId;

    }

    @Override
    public String toString() {
        return "Post [Id=" + Id + ", category=" + category + ", content=" + content + ", creationDate=" + creationDate
                + ", image=" + image + ", title=" + title + ", userId=" + userId + "]";
    }

}
