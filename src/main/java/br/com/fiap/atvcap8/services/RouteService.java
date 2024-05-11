package br.com.fiap.atvcap8.services;

import br.com.fiap.atvcap8.libs.DefaultMessage;
import br.com.fiap.atvcap8.models.RouteModel;
import br.com.fiap.atvcap8.models.TruckModel;
import br.com.fiap.atvcap8.repositories.RouteRepository;
import br.com.fiap.atvcap8.repositories.TruckRepository;
import br.com.fiap.atvcap8.responsemodels.RouteResponseModel;
import br.com.fiap.atvcap8.viewmodels.RouteViewModel;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RouteService {

    private final RouteRepository routeRepository;
    private final TruckRepository truckRepository;

    public RouteService(RouteRepository routeRepository,
                        TruckRepository truckRepository) {
        this.routeRepository = routeRepository;
        this.truckRepository = truckRepository;
    }

    public Page<RouteResponseModel> findAll(Pageable pageable) {
        return routeRepository.findAll(pageable).map(RouteResponseModel::new);
    }

    public RouteResponseModel findById(Long id) {
        return routeRepository.findById(id).map(RouteResponseModel::new).orElseThrow(
                () -> new EntityNotFoundException(DefaultMessage.ROUTE_NOT_FOUND)
        );
    }

    public RouteResponseModel add(RouteViewModel routeViewModel) {
        RouteModel routeModel = new RouteModel();
        Optional<TruckModel> truckModel = truckRepository.findById(routeViewModel.truck());
        if(truckModel.isPresent()) {
            BeanUtils.copyProperties(routeViewModel, routeModel);
            routeModel.setTruck(truckModel.get());
            return new RouteResponseModel(routeRepository.save(routeModel));
        } else {
            throw new EntityNotFoundException(DefaultMessage.TRUCK_NOT_FOUND);
        }
    }

    public RouteResponseModel update(RouteViewModel routeViewModel) {
        Optional<RouteModel> routeOriginal = routeRepository.findById(routeViewModel.id());
        Optional<TruckModel> truckModel = truckRepository.findById(routeViewModel.truck());
        if(truckModel.isEmpty()) {
            throw new EntityNotFoundException(DefaultMessage.TRUCK_NOT_FOUND);
        }
        if (routeOriginal.isPresent()) {
            RouteModel routeModel = new RouteModel();
            BeanUtils.copyProperties(routeViewModel, routeModel);
            routeModel.setTruck(truckModel.get());
            return new RouteResponseModel(routeRepository.save(routeModel));
        } else {
            throw new EntityNotFoundException(DefaultMessage.ROUTE_NOT_FOUND);
        }
    }

    public void delete(Long id) {
        Optional<RouteModel> route = routeRepository.findById(id);
        if (route.isPresent()) {
            routeRepository.delete(route.get());
        } else {
            throw new EntityNotFoundException(DefaultMessage.ROUTE_NOT_FOUND);
        }
    }
}
