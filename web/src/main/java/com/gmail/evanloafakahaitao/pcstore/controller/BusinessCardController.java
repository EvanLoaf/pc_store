package com.gmail.evanloafakahaitao.pcstore.controller;

import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import com.gmail.evanloafakahaitao.pcstore.controller.properties.WebProperties;
import com.gmail.evanloafakahaitao.pcstore.controller.util.FieldTrimmerUtil;
import com.gmail.evanloafakahaitao.pcstore.service.BusinessCardService;
import com.gmail.evanloafakahaitao.pcstore.service.UserService;
import com.gmail.evanloafakahaitao.pcstore.service.dto.BusinessCardDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.UserDTO;
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

@Controller
@RequestMapping(WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/cards")
public class BusinessCardController {

    private static final Logger logger = LogManager.getLogger(BusinessCardController.class);

    private final BusinessCardService businessCardService;
    private final PageProperties pageProperties;
    private final UserService userService;
    private final Validator businessCardValidator;
    private final FieldTrimmerUtil fieldTrimmerUtil;

    @Autowired
    public BusinessCardController(
            BusinessCardService businessCardService,
            PageProperties pageProperties,
            UserService userService,
            @Qualifier("businessCardValidator") Validator businessCardValidator,
            FieldTrimmerUtil fieldTrimmerUtil
    ) {
        this.businessCardService = businessCardService;
        this.pageProperties = pageProperties;
        this.userService = userService;
        this.businessCardValidator = businessCardValidator;
        this.fieldTrimmerUtil = fieldTrimmerUtil;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('manage_business_card')")
    public String getBusinessCards(
            ModelMap modelMap
    ) {
        logger.debug("Executing BusinessCard Controller method : getBusinessCards");
        UserDTO user = userService.findByCurrentId();
        modelMap.addAttribute("businessCards", user.getBusinessCards());
        return pageProperties.getBusinessCardsPagePath();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('manage_business_card')")
    public String createBusinessCard(
            @ModelAttribute("businessCard") BusinessCardDTO businessCard,
            BindingResult result,
            ModelMap modelMap
    ) {
        logger.debug("Executing BusinessCard Controller method : createBusinessCard");
        businessCard = fieldTrimmerUtil.trim(businessCard);
        businessCardValidator.validate(businessCard, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("businessCard", businessCard);
            return pageProperties.getBusinessCardCreatePagePath();
        } else {
            businessCardService.save(businessCard);
            return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/cards";
        }
    }

    @GetMapping(value = "/create")
    @PreAuthorize("hasAuthority('manage_business_card')")
    public String createBusinessCardPage(
            ModelMap modelMap
    ) {
        logger.debug("Executing BusinessCard Controller method : createBusinessCardPage");
        modelMap.addAttribute("businessCard", new BusinessCardDTO());
        return pageProperties.getBusinessCardCreatePagePath();
    }

    @GetMapping(value = "/{id}/delete")
    @PreAuthorize("hasAuthority('manage_business_card')")
    public String deleteBusinessCard(
            @PathVariable("id") Long id
    ) {
        logger.debug("Executing BusinessCard Controller method : deleteBusinessCardPage with id " + id);
        businessCardService.deleteById(id);
        return "redirect:" + WebProperties.PUBLIC_ENTRY_POINT_PREFIX + "/cards";
    }
}
