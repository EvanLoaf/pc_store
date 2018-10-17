package com.gmail.evanloafakahaitao.pcstore.controller.validator;

import com.gmail.evanloafakahaitao.pcstore.controller.model.ItemDiscountData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component("itemDiscountDataValidator")
public class ItemDiscountDataValidator implements Validator {

    private static final Logger logger = LogManager.getLogger(ItemDiscountDataValidator.class);

    @Override
    public boolean supports(Class<?> aClass) {
        return ItemDiscountData.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors err) {
        logger.info("Validating item discount data");

        ItemDiscountData discountData = (ItemDiscountData) obj;
        ValidationUtils.rejectIfEmpty(err, "discountId", "discount.data.id.empty");
        ValidationUtils.rejectIfEmpty(err, "minPriceRange", "discount.data.min.price.empty");
        ValidationUtils.rejectIfEmpty(err, "maxPriceRange", "discount.data.max.price.empty");

        Pattern pattern = Pattern.compile(
                "^-?\\d+\\.?\\d*$",
                Pattern.CASE_INSENSITIVE
        );
        if (!(pattern.matcher(discountData.getMinPriceRange().toString()).matches())) {
            err.rejectValue("minPriceRange", "discount.data.price.invalid");
        }
        if (!(pattern.matcher(discountData.getMaxPriceRange().toString()).matches())) {
            err.rejectValue("minPriceRange", "discount.data.price.invalid");
        }
        if (discountData.getMaxPriceRange().compareTo(discountData.getMinPriceRange()) < 0) {
            err.rejectValue("maxPriceRange", "discount.data.max.price.low");
        }
    }
}
