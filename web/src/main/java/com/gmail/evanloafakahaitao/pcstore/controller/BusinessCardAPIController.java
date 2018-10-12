package com.gmail.evanloafakahaitao.pcstore.controller;

import com.gmail.evanloafakahaitao.pcstore.service.BusinessCardService;
import com.gmail.evanloafakahaitao.pcstore.service.UserService;
import com.gmail.evanloafakahaitao.pcstore.service.dto.BusinessCardDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/business/cards")
public class BusinessCardAPIController {

    private final BusinessCardService businessCardService;
    private final UserService userService;

    @Autowired
    public BusinessCardAPIController(
            BusinessCardService businessCardService,
            UserService userService
    ) {
        this.businessCardService = businessCardService;
        this.userService = userService;
    }

    @GetMapping(value = "/user/{id}")
    @PreAuthorize("hasAuthority('manage_business_card_api')")
    public Set<BusinessCardDTO> getBusinessCards(
            @PathVariable(name = "id") Long userId
    ) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);
        UserDTO user = userService.findById(userDTO);
        return user.getBusinessCards();
    }

    @DeleteMapping(value = "/{id}/delete")
    @PreAuthorize("hasAuthority('manage_business_card_api')")
    public String deleteOrder(
            @PathVariable("id") Long id
    ) {
        BusinessCardDTO businessCard = new BusinessCardDTO();
        businessCard.setId(id);
        businessCardService.deleteById(businessCard);
        return "Deleted business card id = " + id;
    }
}
