package com.gmail.evanloafakahaitao.pcstore.controller;

import com.gmail.evanloafakahaitao.pcstore.controller.properties.PageProperties;
import com.gmail.evanloafakahaitao.pcstore.controller.validator.BusinessCardValidator;
import com.gmail.evanloafakahaitao.pcstore.service.BusinessCardService;
import com.gmail.evanloafakahaitao.pcstore.service.UserService;
import com.gmail.evanloafakahaitao.pcstore.service.dto.BusinessCardDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.UserDTO;
import com.gmail.evanloafakahaitao.pcstore.service.util.CurrentUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/cards")
public class BusinessCardController {

    private static final Logger logger = LogManager.getLogger(BusinessCardController.class);

    private final BusinessCardService businessCardService;
    private final BusinessCardValidator businessCardValidator;
    private final PageProperties pageProperties;
    private final UserService userService;

    @Autowired
    public BusinessCardController(
            BusinessCardService businessCardService,
            BusinessCardValidator businessCardValidator,
            PageProperties pageProperties,
            UserService userService
    ) {
        this.businessCardService = businessCardService;
        this.businessCardValidator = businessCardValidator;
        this.pageProperties = pageProperties;
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('manage_business_card')")
    public String getBusinessCards(
            ModelMap modelMap
    ) {
        logger.debug("Executing BusinessCard Controller method : getBusinessCards");
        UserDTO userDTO = new UserDTO();
        userDTO.setId(CurrentUser.getCurrentId());
        UserDTO user = userService.findById(userDTO);
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
        businessCardValidator.validate(businessCard, result);
        if (result.hasErrors()) {
            modelMap.addAttribute("businessCard", businessCard);
            return pageProperties.getBusinessCardCreatePagePath();
        } else {
            businessCardService.save(businessCard);
            return "redirect:/web/business/cards";
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
        BusinessCardDTO businessCard = new BusinessCardDTO();
        businessCard.setId(id);
        businessCardService.deleteById(businessCard);
        return "redirect:/web/business/cards";
    }
}
