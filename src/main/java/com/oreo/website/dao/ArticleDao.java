package com.oreo.website.dao;

import com.oreo.website.until.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleDao {
    List<Article> findArticleByAuthorId(int autorId);
    Article findArticleByTitle(String title);
    boolean insert(Article article);
    boolean updateByArticleId(Article article);
    boolean deleteByArticleId(int id);
}
