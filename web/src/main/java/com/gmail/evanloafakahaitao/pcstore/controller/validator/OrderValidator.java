package com.gmail.evanloafakahaitao.pcstore.controller.validator;

import com.gmail.evanloafakahaitao.pcstore.dao.model.Order;
import com.gmail.evanloafakahaitao.pcstore.service.dto.DataOrderDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component("orderValidator")
public class OrderValidator implements Validator {

    private static final Logger logger = LogManager.getLogger(OrderValidator.class);

    @Override
    public boolean supports(Class<?> aClass) {
        return DataOrderDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors err) {
        DataOrderDTO order = (DataOrderDTO) obj;
        ValidationUtils.rejectIfEmpty(err, "quantity", "order.quantity.empty");
        if (order.getQuantity() != null) {
            logger.info("Validating order - create");

            Pattern quantityPattern = Pattern.compile(
                    "^[0-9]{1,9}$"
            );
            if (!(quantityPattern.matcher(order.getQuantity().toString()).matches())) {
                err.rejectValue("quantity", "order.quantity.invalid");
            }
            if (order.getQuantity() > 10) {
                err.rejectValue("quantity", "order.quantity.bulk");
            }
        }
    }
}
