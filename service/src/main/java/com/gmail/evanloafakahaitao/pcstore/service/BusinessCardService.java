package com.gmail.evanloafakahaitao.pcstore.service;

import com.gmail.evanloafakahaitao.pcstore.service.dto.BusinessCardDTO;

public interface BusinessCardService {

    BusinessCardDTO save(BusinessCardDTO businessCard);

    void deleteById(Long id);
}
