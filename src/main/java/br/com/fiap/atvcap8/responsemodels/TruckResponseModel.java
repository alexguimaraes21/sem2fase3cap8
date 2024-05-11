package br.com.fiap.atvcap8.responsemodels;

import br.com.fiap.atvcap8.models.RouteModel;
import br.com.fiap.atvcap8.models.TruckModel;

import java.util.List;

//public record TruckResponseModel(Long id, String licensePlate, Float capacity, Boolean available, List<RouteModel> routes) {
public record TruckResponseModel(Long id, String licensePlate, Float capacity, Boolean available) {

    public TruckResponseModel(TruckModel truck) {
//        this(truck.getId(), truck.getLicensePlate(), truck.getCapacity(), truck.getAvailable(), truck.getRoutes());
        this(truck.getId(), truck.getLicensePlate(), truck.getCapacity(), truck.getAvailable());
    }
}
