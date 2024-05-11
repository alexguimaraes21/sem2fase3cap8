package br.com.fiap.atvcap8.responsemodels;

import br.com.fiap.atvcap8.models.RouteModel;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record RouteResponseModel(Long id, String description, LocalDateTime startTime, LocalDateTime endTime, TruckResponseModel truck) {

    public RouteResponseModel(RouteModel routeModel) {
        this(routeModel.getId(), routeModel.getDescription(), routeModel.getStartTime(), routeModel.getEndTime(), new TruckResponseModel(routeModel.getTruck()));
    }
}
