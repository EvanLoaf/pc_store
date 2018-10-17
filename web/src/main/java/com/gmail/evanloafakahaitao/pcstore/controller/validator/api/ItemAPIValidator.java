package com.gmail.evanloafakahaitao.pcstore.controller.validator.api;

import com.gmail.evanloafakahaitao.pcstore.service.ItemService;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ItemDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Component
public class ItemAPIValidator {

    private static final Logger logger = LogManager.getLogger(ItemAPIValidator.class);

    private final ItemService itemService;

    @Autowired
    public ItemAPIValidator(ItemService itemService) {
        this.itemService = itemService;
    }

    public Set<String> validate(ItemDTO item) {
        Set<String> errors = new HashSet<>();

        if (item.getId() == null) {
            logger.info("Validating item - create API");

            if (item.getVendorCode() == null || item.getVendorCode().equals("")) {
                errors.add("Vendor code can't be empty");
            } else if (item.getVendorCode().length() > 20) {
                errors.add("Vendor code can't be longer than 20 characters");
            } else {
                ItemDTO existingItem = itemService.findByVendorCode(item.getVendorCode());
                if (existingItem != null) {
                    errors.add("Item with unique vendor code already exists");
                }
            }

            if (item.getName() == null || item.getName().equals("")) {
                errors.add("Name can't be empty");
            } else if (item.getName().length() > 20) {
                errors.add("Name can't be longer than 20 characters");
            }

            if (item.getDescription() == null || item.getDescription().equals("")) {
                errors.add("Description can't be empty");
            } else if (item.getDescription().length() > 100) {
                errors.add("Description can't be longer than 100 characters");
            }

            if (item.getPrice() == null) {
                errors.add("Price can't be empty");
            } else {
                Pattern pattern = Pattern.compile(
                        "^-?\\d+\\.?\\d*$",
                        Pattern.CASE_INSENSITIVE
                );
                if (!(pattern.matcher(item.getPrice().toString()).matches())) {
                    errors.add("Price is invalid");
                }
            }

        } else {
            logger.info("Validating item - update API");

            if (item.getVendorCode() != null && item.getVendorCode().equals("")) {
                errors.add("Vendor code can't be empty");
            } else if (item.getVendorCode() != null && item.getVendorCode().length() > 20) {
                errors.add("Vendor code can't be longer than 20 characters");
            }

            if (item.getName() != null && item.getName().equals("")) {
                errors.add("Name can't be empty");
            } else if (item.getName() != null && item.getName().length() > 20) {
                errors.add("Name can't be longer than 20 characters");
            }

            if (item.getDescription() != null && item.getDescription().equals("")) {
                errors.add("Description can't be empty");
            } else if (item.getDescription() != null && item.getDescription().length() > 100) {
                errors.add("Description can't be longer than 100 characters");
            }

            if (item.getPrice() != null) {
                Pattern pattern = Pattern.compile(
                        "^-?\\d+\\.?\\d*$",
                        Pattern.CASE_INSENSITIVE
                );
                if (!(pattern.matcher(item.getPrice().toString()).matches())) {
                    errors.add("Price is invalid");
                }
            }
        }
        return errors;
    }
}
