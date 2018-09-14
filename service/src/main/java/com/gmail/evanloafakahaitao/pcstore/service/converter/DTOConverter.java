package com.gmail.evanloafakahaitao.pcstore.service.converter;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public interface DTOConverter<D, E> {

    D toDto(E entity);

    default List<D> toDTOList(List<E> entityList) {
        return entityList.stream().filter(Objects::nonNull).map(this::toDto).collect(Collectors.toList());
    }

    default Set<D> toDTOSet(Set<E> entitySet) {
        return entitySet.stream().filter(Objects::nonNull).map(this::toDto).collect(Collectors.toSet());
    }
}
