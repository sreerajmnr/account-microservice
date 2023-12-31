package com.banking.core.model.mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class BaseMapper<E, D> {

	public abstract Optional<E> convertToEntity(Optional<D> dto);

	public abstract Optional<D> convertToDto(Optional<E> entity);

	public final List<Optional<E>> convertToEntity(final List<Optional<D>> dtos) {
		return dtos.stream().map(dto -> convertToEntity(dto)).collect(Collectors.toList());
	}

	public final List<Optional<D>> convertToDto(final List<Optional<E>> entities) {
		return entities.stream().map(entity -> convertToDto(entity)).collect(Collectors.toList());
	}

}
