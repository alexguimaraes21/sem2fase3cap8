package br.com.fiap.atvcap8.services;

import br.com.fiap.atvcap8.libs.DefaultMessage;
import br.com.fiap.atvcap8.models.CollectionModel;
import br.com.fiap.atvcap8.models.ContainerModel;
import br.com.fiap.atvcap8.models.RouteModel;
import br.com.fiap.atvcap8.repositories.CollectionRepository;
import br.com.fiap.atvcap8.repositories.ContainerRepository;
import br.com.fiap.atvcap8.repositories.RouteRepository;
import br.com.fiap.atvcap8.responsemodels.CollectionResponseModel;
import br.com.fiap.atvcap8.viewmodels.CollectionViewModel;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CollectionService {

    private final CollectionRepository collectionRepository;
    private final RouteRepository routeRepository;
    private final ContainerRepository containerRepository;

    public CollectionService(CollectionRepository collectionRepository,
                             RouteRepository routeRepository,
                             ContainerRepository containerRepository) {
        this.collectionRepository = collectionRepository;
        this.routeRepository = routeRepository;
        this.containerRepository = containerRepository;
    }

    public Page<CollectionResponseModel> findAll(Pageable pageable) {
        return collectionRepository.findAll(pageable).map(CollectionResponseModel::new);
    }

    public CollectionResponseModel findById(Long id) {
        return collectionRepository.findById(id).map(CollectionResponseModel::new).orElseThrow(
                () -> new EntityNotFoundException(DefaultMessage.COLLECTION_NOT_FOUND)
        );
    }

    public CollectionResponseModel add(CollectionViewModel collectionViewModel) {
        CollectionModel collectionModel = new CollectionModel();
        Optional<RouteModel> routeModel = routeRepository.findById(collectionViewModel.route());
        Optional<ContainerModel> containerModel = containerRepository.findById(collectionViewModel.container());
        if(routeModel.isEmpty()) {
            throw new EntityNotFoundException(DefaultMessage.ROUTE_NOT_FOUND);
        }

        if (containerModel.isEmpty()) {
            throw new EntityNotFoundException(DefaultMessage.CONTAINER_NOT_FOUND);
        }

        BeanUtils.copyProperties(collectionViewModel, collectionModel);
        collectionModel.setRoute(routeModel.get());
        collectionModel.setContainer(containerModel.get());
        return new CollectionResponseModel(collectionRepository.save(collectionModel));
    }

    public CollectionResponseModel update(CollectionViewModel collectionViewModel) {
        Optional<CollectionModel> collectionModelOriginal = collectionRepository.findById(collectionViewModel.id());
        Optional<RouteModel> routeModel = routeRepository.findById(collectionViewModel.route());
        Optional<ContainerModel> containerModel = containerRepository.findById(collectionViewModel.container());
        if (collectionModelOriginal.isEmpty()) {
            throw new EntityNotFoundException(DefaultMessage.COLLECTION_NOT_FOUND);
        }

        if(routeModel.isEmpty()) {
            throw new EntityNotFoundException(DefaultMessage.ROUTE_NOT_FOUND);
        }

        if (containerModel.isEmpty()) {
            throw new EntityNotFoundException(DefaultMessage.CONTAINER_NOT_FOUND);
        }

        CollectionModel collectionModel = new CollectionModel();
        BeanUtils.copyProperties(collectionViewModel, collectionModel);
        collectionModel.setRoute(routeModel.get());
        collectionModel.setContainer(containerModel.get());
        return new CollectionResponseModel(collectionRepository.save(collectionModel));
    }

    public void delete(Long id) {
        Optional<CollectionModel> collectionModel = collectionRepository.findById(id);
        if (collectionModel.isEmpty()) {
            throw new EntityNotFoundException(DefaultMessage.COLLECTION_NOT_FOUND);
        }
        collectionRepository.delete(collectionModel.get());
    }
}
