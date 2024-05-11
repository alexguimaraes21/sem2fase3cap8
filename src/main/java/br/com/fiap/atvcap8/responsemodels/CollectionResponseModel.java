package br.com.fiap.atvcap8.responsemodels;

import br.com.fiap.atvcap8.models.CollectionModel;

import java.time.LocalDateTime;

public record CollectionResponseModel(
        Long id, LocalDateTime dateTime, ContainerResponseModel container, RouteResponseModel route) {
    public CollectionResponseModel(CollectionModel model) {
        this(model.getId(), model.getDateTime(), new ContainerResponseModel(model.getContainer()), new RouteResponseModel(model.getRoute()));
    }
}
