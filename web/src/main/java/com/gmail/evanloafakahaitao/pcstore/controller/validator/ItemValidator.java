package com.gmail.evanloafakahaitao.pcstore.controller.validator;

import com.gmail.evanloafakahaitao.pcstore.service.ItemService;
import com.gmail.evanloafakahaitao.pcstore.service.dto.ItemDTO;
import com.gmail.evanloafakahaitao.pcstore.service.dto.SimpleItemDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {

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
        if (item.getId() == null) {
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
                ItemDTO itemDTO = new ItemDTO();
                itemDTO.setVendorCode(item.getVendorCode());
                ItemDTO itemByVendorCode = itemService.findByVendorCode(itemDTO);
                if (itemByVendorCode.getId() != null) {
                    err.rejectValue("vendorCode", "item.vendorcode.exists");
                }
            }
        } else {
            if (item.getVendorCode() != null && item.getVendorCode().length() > 20) {
                err.rejectValue("vendorCode", "item.vendorcode.length");
            }
            if (item.getName() != null && item.getName().length() > 20) {
                err.rejectValue("name", "item.name.length");
            }
            if (item.getDescription() != null && item.getDescription().length() > 100) {
                err.rejectValue("description", "item.description.length");
            }
        }
    }
}
