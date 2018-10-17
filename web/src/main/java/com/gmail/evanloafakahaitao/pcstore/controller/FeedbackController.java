package com.gmail.evanloafakahaitao.pcstore.controller;

import com.gmail.evanloafakahaitao.pcstore.controller.model.Pagination;
import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import com.gmail.evanloafakahaitao.pcstore.controller.properties.WebProperties;
import com.gmail.evanloafakahaitao.pcstore.controller.util.FieldTrimmer;
import com.gmail.evanloafakahaitao.pcstore.controller.util.PaginationUtil;
import com.gmail.evanloafakahaitao.pcstore.service.FeedbackService;
import com.gmail.evanloafakahaitao.pcstore.service.dto.FeedbackDTO;
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
@RequestMapping(WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/feedback")
public class FeedbackController {

    private static final Logger logger = LogManager.getLogger(FeedbackController.class);

    private final FeedbackService feedbackService;
    private final PageProperties pageProperties;
    private final PaginationUtil paginationUtil;
    private final Validator feedbackValidator;
    private final FieldTrimmer fieldTrimmer;

    @Autowired
    public FeedbackController(
            FeedbackService feedbackService,
            PageProperties pageProperties,
            PaginationUtil paginationUtil,
            @Qualifier("feedbackValidator") Validator feedbackValidator,
            FieldTrimmer fieldTrimmer
    ) {
        this.feedbackService = feedbackService;
        this.pageProperties = pageProperties;
        this.paginationUtil = paginationUtil;
        this.feedbackValidator = feedbackValidator;
        this.fieldTrimmer = fieldTrimmer;
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
        pagination.setStartPosition(paginationUtil.getPageNumerationStart(page));
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
        feedback = fieldTrimmer.trim(feedback);
        feedbackValidator.validate(feedback, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("feedback", feedback);
            return pageProperties.getFeedbackCreatePagePath();
        } else {
            feedbackService.save(feedback);
            //TODO change url logic - w/o user id
            return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/users/profile" + "?feedback=true";
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
        return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/feedback";
    }
}
