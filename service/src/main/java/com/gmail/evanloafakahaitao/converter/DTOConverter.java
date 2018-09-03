package com.gmail.evanloafakahaitao.converter;

import java.util.List;

public interface DTOConverter<D, E> {

    D toDto(E entity);

    List<D> toDTOList(List<E> entityList);
}
