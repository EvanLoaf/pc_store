package com.gmail.evanloafakahaitao.service.converter;

import java.util.List;

public interface Converter<D, E> {

    E toEntity(D dto);

    List<E> toEntityList(List<D> dtoList);
}
