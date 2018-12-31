package com.oreo.website.controller;

import com.oreo.website.dao.ArticleDao;
import com.oreo.website.until.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    ArticleDao articleDao = null;
    @RequestMapping
    public String blogIndex(){
        return "blog";
    }

    /**
     * 插入文章
     * @param httpServletRequest
     */
    @RequestMapping("/submit")
    @ResponseBody
    public void insertArticle(HttpServletRequest httpServletRequest){
        String artile = httpServletRequest.getParameter("content");
        System.out.println(artile);
        Article article = new Article();
        article.setaText(artile);
        article.setaTitle("test");
        article.setaAuthorId(1);
        article.setaAuthority(1);
        articleDao.insert(article);
    }


    @RequestMapping("/search")
    @ResponseBody
    public Map<String,Object> searchArticle(HttpServletRequest httpServletRequest){
        String title = httpServletRequest.getParameter("title");
        Article article = articleDao.findArticleByTitle(title);
        Map<String,Object> map = new HashMap<>();
        map.put("article",article);
        return map;
    }
    @RequestMapping("/show")
    public ModelAndView show(){
        ModelAndView modelAndView = new ModelAndView("showArticle.html");
        Article article = new Article();
        article =
    }
}
