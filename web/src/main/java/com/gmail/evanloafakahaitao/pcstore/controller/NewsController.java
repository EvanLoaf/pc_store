package com.gmail.evanloafakahaitao.pcstore.controller;

import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import com.gmail.evanloafakahaitao.pcstore.controller.properties.WebProperties;
import com.gmail.evanloafakahaitao.pcstore.controller.util.PaginationUtil;
import com.gmail.evanloafakahaitao.pcstore.controller.validator.CommentValidator;
import com.gmail.evanloafakahaitao.pcstore.controller.validator.NewsValidator;
import com.gmail.evanloafakahaitao.pcstore.service.NewsService;
import com.gmail.evanloafakahaitao.pcstore.service.CommentService;
import com.gmail.evanloafakahaitao.pcstore.service.dto.NewsDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.CommentDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleArticleDTO;
import com.gmail.evanloafakahaitao.pcstore.controller.model.Pagination;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping(WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/news")
public class NewsController {

    private static final Logger logger = LogManager.getLogger(NewsController.class);

    private final PageProperties pageProperties;
    private final NewsService newsService;
    private final CommentService commentService;
    private final PaginationUtil paginationUtil;
    private final Validator newsValidator;
    private final Validator commentValidator;

    @Autowired
    public NewsController(
            PageProperties pageProperties,
            NewsService newsService,
            CommentService commentService,
            PaginationUtil paginationUtil,
            @Qualifier("newsValidator") Validator newsValidator,
            @Qualifier("commentValidator") Validator commentValidator
    ) {
        this.pageProperties = pageProperties;
        this.newsService = newsService;
        this.commentService = commentService;
        this.paginationUtil = paginationUtil;
        this.newsValidator = newsValidator;
        this.commentValidator = commentValidator;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('view_news')")
    public String getNews(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            ModelMap modelMap
    ) {
        logger.debug("Executing News Controller method : getNews");
        List<SimpleArticleDTO> news = newsService.findAll(paginationUtil.getStartPosition(page), pageProperties.getPaginationMaxResults());
        modelMap.addAttribute("news", news);
        Pagination pagination = new Pagination();
        pagination.setPage(page);
        pagination.setPageNumbers(
                paginationUtil.getPageNumbers(newsService.countAll().intValue())
        );
        pagination.setStartPosition(paginationUtil.getStartPosition(page) + 1);
        modelMap.addAttribute("pagination", pagination);
        return pageProperties.getNewsPagePath();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('create_news')")
    public String createNews(
            @ModelAttribute("news") NewsDTO news,
            BindingResult result,
            ModelMap modelMap
    ) {
        logger.debug("Executing News Controller method : createNews");
        newsValidator.validate(news, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("news", news);
            return pageProperties.getNewsCreatePagePath();
        } else {
            newsService.save(news);
            return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/news";
        }
    }

    @GetMapping(value = "/create")
    @PreAuthorize("hasAuthority('create_news')")
    public String createNewsPage(ModelMap modelMap) {
        logger.debug("Executing News Controller method : createNewsPage");
        modelMap.addAttribute("news", new NewsDTO());
        return pageProperties.getNewsCreatePagePath();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('view_news')")
    public String getNewsPiece(
            @PathVariable("id") Long id,
            ModelMap modelMap
    ) {
        logger.debug("Executing News Controller method : getNewsPiece with id " + id);
        /*SimpleArticleDTO simpleNews = new SimpleArticleDTO();
        simpleNews.setId(id);*/
        NewsDTO news = newsService.findById(id);
        modelMap.addAttribute("news", news);
        modelMap.addAttribute("comment", new CommentDTO());
        return pageProperties.getNewsShowOnePagePath();
    }

    @PostMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('update_news_all')")
    public String updateNews(
            @PathVariable("id") Long id,
            @ModelAttribute("user") NewsDTO news,
            BindingResult result,
            ModelMap modelMap
    ) {
        logger.debug("Executing News Controller method : updateNews with id " + id);
        news.setId(id);
        newsValidator.validate(news, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("news", news);
            return pageProperties.getNewsShowOnePagePath();
        } else {
            newsService.update(news);
            return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/news/" + id;
        }
    }

    @GetMapping(value = "/{id}/delete")
    @PreAuthorize("hasAuthority('remove_news_all')")
    public String deleteNewsPiece(
            @PathVariable("id") Long id
    ) {
        logger.debug("Executing News Controller method : deleteNewsPiece with id " + id);
        /*SimpleArticleDTO news = new SimpleArticleDTO();
        news.setId(id);*/
        newsService.deleteById(id);
        return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/news";
    }

    @PostMapping(value = "/delete")
    @PreAuthorize("hasAuthority('remove_news_all')")
    public String deleteNews(
            @RequestParam("ids") Long[] ids
    ) {
        logger.debug("Executing News Controller method : deleteNews with id's " + Arrays.toString(ids));
        for (Long id : ids) {
            /*SimpleArticleDTO news = new SimpleArticleDTO();
            news.setId(id);*/
            newsService.deleteById(id);
        }
        return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/news";
    }

    @PostMapping(value = "/{id}/comments/create")
    @PreAuthorize("hasAuthority('create_comment')")
    public String createComment(
            @PathVariable("id") Long id,
            @ModelAttribute("comment") CommentDTO comment,
            BindingResult result,
            ModelMap modelMap
    ) {
        logger.debug("Executing News Controller method : createComment");
        commentValidator.validate(comment, result);
        if (result.hasErrors()) {
            modelMap.addAttribute(comment);
            /*SimpleArticleDTO simpleNews = new SimpleArticleDTO();
            simpleNews.setId(id);*/
            NewsDTO news = newsService.findById(id);
            modelMap.addAttribute("news", news);
            modelMap.addAttribute("comment", comment);
            return pageProperties.getNewsShowOnePagePath();
        } else {
            NewsDTO news = new NewsDTO();
            news.setId(id);
            news.getComments().add(comment);
            commentService.save(news);
            return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/news/" + id;
        }
    }

    @GetMapping(value = "/{newsid}/comments/{commentid}/delete")
    @PreAuthorize("hasAuthority('remove_comments_all')")
    public String deleteComment(
            @PathVariable("newsid") Long newsId,
            @PathVariable("commentid") Long commentId
    ) {
        logger.debug("Executing News Controller method : deleteComment with id " + commentId);
        /*NewsDTO news = new NewsDTO();
        news.setId(newsId);
        CommentDTO comment = new CommentDTO();
        comment.setId(commentId);
        news.getComments().add(comment);*/
        commentService.deleteById(newsId, commentId);
        return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/news/" + newsId;
    }

}
