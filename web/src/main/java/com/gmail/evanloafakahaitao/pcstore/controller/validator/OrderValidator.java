package com.gmail.evanloafakahaitao.pcstore.controller.validator;

import com.gmail.evanloafakahaitao.pcstore.service.dto.DataOrderDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class OrderValidator implements Validator {


    @Override
    public boolean supports(Class<?> aClass) {
        return DataOrderDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors err) {
        DataOrderDTO order = (DataOrderDTO) obj;
        ValidationUtils.rejectIfEmpty(err, "quantity", "order.quantity.empty");
        if (order.getQuantity() > 10) {
            err.rejectValue("quantity", "order.quantity.bulk");
        }
    }
}
