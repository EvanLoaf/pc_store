package com.gmail.evanloafakahaitao.pcstore.service.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class SimpleArticleDTO implements Serializable {

    private static final long serialVersionUID = 1914894231808461361L;

    private Long id;
    private String title;
    private String created;
    private Long countOfArticles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Long getCountOfArticles() {
        return countOfArticles;
    }

    public void setCountOfArticles(Long countOfArticles) {
        this.countOfArticles = countOfArticles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleArticleDTO that = (SimpleArticleDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
