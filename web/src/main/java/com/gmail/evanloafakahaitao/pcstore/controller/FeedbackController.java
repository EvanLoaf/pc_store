package com.gmail.evanloafakahaitao.pcstore.controller;

import com.gmail.evanloafakahaitao.pcstore.controller.model.Pagination;
import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import com.gmail.evanloafakahaitao.pcstore.controller.util.PaginationUtil;
import com.gmail.evanloafakahaitao.pcstore.controller.validator.FeedbackValidator;
import com.gmail.evanloafakahaitao.pcstore.service.FeedbackService;
import com.gmail.evanloafakahaitao.pcstore.service.dto.FeedbackDTO;
import com.gmail.evanloafakahaitao.pcstore.service.util.CurrentUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/web/feedback")
public class FeedbackController {

    private static final Logger logger = LogManager.getLogger(FeedbackController.class);

    private final FeedbackService feedbackService;
    private final FeedbackValidator feedbackValidator;
    private final PageProperties pageProperties;
    private final PaginationUtil paginationUtil;

    @Autowired
    public FeedbackController(FeedbackService feedbackService, FeedbackValidator feedbackValidator, PageProperties pageProperties, PaginationUtil paginationUtil) {
        this.feedbackService = feedbackService;
        this.feedbackValidator = feedbackValidator;
        this.pageProperties = pageProperties;
        this.paginationUtil = paginationUtil;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('view_feedback')")
    public String getFeedback(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            ModelMap modelMap
    ) {
        logger.debug("Executing Feedback Controller method : getFeedback");
        List<FeedbackDTO> feedbacks = feedbackService.findAll(paginationUtil.getStartPosition(page), pageProperties.getPaginationMaxResults());
        modelMap.addAttribute("feedback", feedbacks);
        Pagination pagination = new Pagination();
        pagination.setPage(page);
        pagination.setPageNumbers(
                paginationUtil.getPageNumbers(feedbackService.countAll().intValue())
        );
        pagination.setStartPosition(paginationUtil.getStartPosition(page) + 1);
        modelMap.addAttribute("pagination", pagination);
        return pageProperties.getFeedbackPagePath();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('create_feedback')")
    public String createFeedback(
            @ModelAttribute("feedback") FeedbackDTO feedback,
            BindingResult result,
            ModelMap modelMap
    ) {
        logger.debug("Executing Feedback Controller method : createFeedback");
        feedbackValidator.validate(feedback, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("feedback", feedback);
            return pageProperties.getFeedbackCreatePagePath();
        } else {
            feedbackService.save(feedback);
            return "redirect:/web/users/" + CurrentUser.getCurrentId() + "?feedback=true";
        }
    }

    @GetMapping(value = "/create")
    @PreAuthorize("hasAuthority('create_feedback')")
    public String createFeedbackPage(ModelMap modelMap) {
        logger.debug("Executing Feedback Controller method : createFeedbackPage");
        modelMap.addAttribute("feedback", new FeedbackDTO());
        return pageProperties.getFeedbackCreatePagePath();
    }

    @PostMapping(value = "/delete")
    @PreAuthorize("hasAuthority('delete_feedback')")
    public String deleteFeedback(
            @RequestParam("ids") Long[] ids
    ) {
        logger.debug("Executing Feedback Controller method : deleteFeedback with id's : " + Arrays.toString(ids));
        for (Long id : ids) {
            feedbackService.deleteById(id);
        }
        return "redirect:/web/feedback";
    }
}
