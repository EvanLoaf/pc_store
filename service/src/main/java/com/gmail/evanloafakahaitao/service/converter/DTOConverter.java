package com.gmail.evanloafakahaitao.service.converter;

import java.util.List;
import java.util.Set;

public interface DTOConverter<D, E> {

    D toDto(E entity);

    List<D> toDTOList(List<E> entityList);

    Set<D> toDTOSet(Set<E> entitySet);
}
