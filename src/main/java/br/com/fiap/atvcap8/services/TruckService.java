package br.com.fiap.atvcap8.services;

import br.com.fiap.atvcap8.libs.DefaultMessage;
import br.com.fiap.atvcap8.models.TruckModel;
import br.com.fiap.atvcap8.repositories.TruckRepository;
import br.com.fiap.atvcap8.responsemodels.TruckResponseModel;
import br.com.fiap.atvcap8.viewmodels.TruckViewModel;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TruckService {

    private final TruckRepository truckRepository;

    public TruckService(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    public Page<TruckResponseModel> findAll(Pageable pageable) {
        return truckRepository.findAll(pageable).map(TruckResponseModel::new);
    }

    public TruckResponseModel findById(long id) {
        return truckRepository.findById(id).map(TruckResponseModel::new).orElseThrow(
                () -> new EntityNotFoundException(DefaultMessage.TRUCK_NOT_FOUND)
        );
    }

    public TruckResponseModel findByLicensePlate(String licensePlate) {
        return truckRepository.findByLicensePlate(licensePlate).map(TruckResponseModel::new).orElseThrow(
                () -> new EntityNotFoundException(DefaultMessage.TRUCK_NOT_FOUND)
        );
    }

    public TruckResponseModel add(TruckViewModel truck) {
        TruckModel truckModel = new TruckModel();
        BeanUtils.copyProperties(truck, truckModel);
        return new TruckResponseModel(truckRepository.save(truckModel));
    }

    public TruckResponseModel update(TruckViewModel truck) {
        Optional<TruckModel> truckModelOriginal = truckRepository.findById(truck.id());
        if(truckModelOriginal.isPresent()) {
            TruckModel truckModel = new TruckModel();
            BeanUtils.copyProperties(truck, truckModel);
            return new TruckResponseModel(truckRepository.save(truckModel));
        } else {
            throw new EntityNotFoundException(DefaultMessage.TRUCK_NOT_FOUND);
        }
    }

    public void remove(Long id) {
        Optional<TruckModel> truckModelOriginal = truckRepository.findById(id);
        if(truckModelOriginal.isPresent()) {
            truckRepository.delete(truckModelOriginal.get());
        } else {
            throw new EntityNotFoundException(DefaultMessage.TRUCK_NOT_FOUND);
        }
    }
}
