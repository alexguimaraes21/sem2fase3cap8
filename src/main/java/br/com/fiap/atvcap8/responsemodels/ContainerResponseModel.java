package br.com.fiap.atvcap8.responsemodels;

import br.com.fiap.atvcap8.models.ContainerModel;

public record ContainerResponseModel(Long id, String location, Float capacity, Integer currentLevel) {
    public ContainerResponseModel(ContainerModel containerModel) {
        this(containerModel.getId(), containerModel.getLocation(), containerModel.getCapacity(), containerModel.getCurrentLevel());
    }
}
