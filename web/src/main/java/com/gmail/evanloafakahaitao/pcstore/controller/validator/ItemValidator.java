package com.gmail.evanloafakahaitao.pcstore.controller.validator;

import com.gmail.evanloafakahaitao.pcstore.service.ItemService;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ItemDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component("itemValidator")
public class ItemValidator implements Validator {

    private static final Logger logger = LogManager.getLogger(ItemValidator.class);

    private final ItemService itemService;

    @Autowired
    public ItemValidator(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return ItemDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors err) {
        ItemDTO item = (ItemDTO) obj;
        logger.info("Validating item - create");

        ValidationUtils.rejectIfEmpty(err, "vendorCode", "item.vendorcode.empty");
        ValidationUtils.rejectIfEmpty(err, "name", "item.name.empty");
        ValidationUtils.rejectIfEmpty(err, "description", "item.description.empty");
        ValidationUtils.rejectIfEmpty(err, "price", "item.price.empty");

        if (item.getVendorCode().length() > 20) {
            err.rejectValue("vendorCode", "item.vendorcode.length");
        }
        if (item.getName().length() > 20) {
            err.rejectValue("name", "item.name.length");
        }
        if (item.getDescription().length() > 100) {
            err.rejectValue("description", "item.description.length");
        }
        if (item.getVendorCode() != null && item.getVendorCode().length() <= 20) {
            ItemDTO itemByVendorCode = itemService.findByVendorCode(item.getVendorCode());
            if (itemByVendorCode != null) {
                err.rejectValue("vendorCode", "item.vendorcode.exists");
            }
        }
        if (item.getPrice() != null) {
            Pattern pattern = Pattern.compile(
                    "^-?\\d+\\.?\\d*$",
                    Pattern.CASE_INSENSITIVE
            );
            if (!(pattern.matcher(item.getPrice().toString()).matches())) {
                err.rejectValue("price", "item.price.invalid");
            }
        }
    }
}
