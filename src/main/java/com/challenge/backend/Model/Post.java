package com.challenge.backend.Model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
    private int id;

    private String title;
    private String content;
    private String image;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "category_id")
    private Category category;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate creationDate;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;

    private boolean deleted;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post() {
    }

    public Post(int id, String title, String content, String image, Category category, LocalDate creationDate,
            User user) {

        this.id = id;
        this.title = title;
        this.content = content;
        this.image = image;
        this.category = category;
        this.creationDate = creationDate;
        this.user = user;

    }

    @Override
    public String toString() {
        return "Post [Id=" + id + ", category=" + category + ", content=" + content + ", creationDate=" + creationDate
                + ", image=" + image + ", title=" + title + ", user=" + user + "]";
    }

}
