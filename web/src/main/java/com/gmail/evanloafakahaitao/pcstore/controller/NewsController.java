package com.gmail.evanloafakahaitao.pcstore.controller;

import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import com.gmail.evanloafakahaitao.pcstore.controller.util.PaginationUtil;
import com.gmail.evanloafakahaitao.pcstore.controller.validator.CommentValidator;
import com.gmail.evanloafakahaitao.pcstore.controller.validator.NewsValidator;
import com.gmail.evanloafakahaitao.pcstore.service.ArticleService;
import com.gmail.evanloafakahaitao.pcstore.service.CommentService;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ArticleDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.CommentDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleArticleDTO;
import com.gmail.evanloafakahaitao.pcstore.controller.model.Pagination;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/web/news")
public class NewsController {

    private static final Logger logger = LogManager.getLogger(NewsController.class);

    private final PageProperties pageProperties;
    private final ArticleService articleService;
    private final CommentService commentService;
    private final NewsValidator newsValidator;
    private final CommentValidator commentValidator;
    private final PaginationUtil paginationUtil;

    @Autowired
    public NewsController(
            PageProperties pageProperties,
            ArticleService articleService,
            CommentService commentService,
            NewsValidator newsValidator,
            CommentValidator commentValidator,
            PaginationUtil paginationUtil
    ) {
        this.pageProperties = pageProperties;
        this.articleService = articleService;
        this.commentService = commentService;
        this.newsValidator = newsValidator;
        this.commentValidator = commentValidator;
        this.paginationUtil = paginationUtil;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('view_news')")
    public String getNews(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            ModelMap modelMap
    ) {
        List<SimpleArticleDTO> news = articleService.findAll(paginationUtil.getStartPosition(page), pageProperties.getPaginationMaxResults());
        modelMap.addAttribute("news", news);
        Pagination pagination = new Pagination();
        pagination.setPage(page);
        pagination.setPageNumbers(
                paginationUtil.getPageNumbers(articleService.countAll().intValue())
        );
        pagination.setStartPosition(paginationUtil.getStartPosition(page) + 1);
        modelMap.addAttribute("pagination", pagination);
        return pageProperties.getNewsPagePath();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('create_news')")
    public String createNews(
            @ModelAttribute("news") ArticleDTO news,
            BindingResult result,
            ModelMap modelMap
    ) {
        newsValidator.validate(news, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("news", news);
            return pageProperties.getNewsCreatePagePath();
        } else {
            articleService.save(news);
            return "redirect:/web/news";
        }
    }

    @GetMapping(value = "/create")
    @PreAuthorize("hasAuthority('create_news')")
    public String createNewsPage(ModelMap modelMap) {
        modelMap.addAttribute("news", new ArticleDTO());
        return pageProperties.getNewsCreatePagePath();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('view_news')")
    public String getNewsPiece(
            @PathVariable("id") Long id,
            ModelMap modelMap
    ) {
        SimpleArticleDTO simpleNews = new SimpleArticleDTO();
        simpleNews.setId(id);
        ArticleDTO news = articleService.findById(simpleNews);
        modelMap.addAttribute("news", news);
        modelMap.addAttribute("comment", new CommentDTO());
        return pageProperties.getNewsShowOnePagePath();
    }

    @PostMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('update_news_all')")
    public String updateNews(
            @PathVariable("id") Long id,
            @ModelAttribute("user") ArticleDTO news,
            BindingResult result,
            ModelMap modelMap
    ) {
        news.setId(id);
        newsValidator.validate(news, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("news", news);
            return pageProperties.getNewsShowOnePagePath();
        } else {
            articleService.update(news);
            return "redirect:/web/news/" + id;
        }
    }

    @GetMapping(value = "/{id}/delete")
    @PreAuthorize("hasAuthority('remove_news_all')")
    public String deleteNewsPiece(
            @PathVariable("id") Long id
    ) {
        SimpleArticleDTO news = new SimpleArticleDTO();
        news.setId(id);
        articleService.deleteById(news);
        return "redirect:/web/news";
    }

    @PostMapping(value = "/delete")
    @PreAuthorize("hasAuthority('remove_news_all')")
    public String deleteNews(
            @RequestParam("ids") Long[] ids
    ) {
        for (Long id : ids) {
            SimpleArticleDTO news = new SimpleArticleDTO();
            news.setId(id);
            articleService.deleteById(news);
        }
        return "redirect:/web/news";
    }

    @PostMapping(value = "/{id}/comment/create")
    @PreAuthorize("hasAuthority('create_comment')")
    public String createComment(
            @PathVariable("id") Long id,
            @ModelAttribute("comment") CommentDTO comment,
            BindingResult result,
            ModelMap modelMap
    ) {
        commentValidator.validate(comment, result);
        if (result.hasErrors()) {
            modelMap.addAttribute(comment);
            SimpleArticleDTO simpleNews = new SimpleArticleDTO();
            simpleNews.setId(id);
            ArticleDTO news = articleService.findById(simpleNews);
            modelMap.addAttribute("news", news);
            modelMap.addAttribute("comment", comment);
            return pageProperties.getNewsShowOnePagePath();
        } else {
            ArticleDTO news = new ArticleDTO();
            news.setId(id);
            news.getComments().add(comment);
            commentService.save(news);
            return "redirect:/web/news/" + id;
        }
    }

    @GetMapping(value = "/{newsid}/comment/{commentid}/delete")
    @PreAuthorize("hasAuthority('remove_comments_all')")
    public String deleteComment(
            @PathVariable("newsid") Long newsId,
            @PathVariable("commentid") Long commentId
    ) {
        ArticleDTO news = new ArticleDTO();
        news.setId(newsId);
        CommentDTO comment = new CommentDTO();
        comment.setId(commentId);
        news.getComments().add(comment);
        commentService.deleteById(news);
        return "redirect:/web/news/" + newsId;
    }

}
