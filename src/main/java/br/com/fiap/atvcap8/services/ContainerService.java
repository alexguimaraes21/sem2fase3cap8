package br.com.fiap.atvcap8.services;

import br.com.fiap.atvcap8.libs.DefaultMessage;
import br.com.fiap.atvcap8.models.ContainerModel;
import br.com.fiap.atvcap8.repositories.ContainerRepository;
import br.com.fiap.atvcap8.responsemodels.ContainerResponseModel;
import br.com.fiap.atvcap8.viewmodels.ContainerViewModel;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContainerService {

    private final ContainerRepository containerRepository;

    public ContainerService(ContainerRepository containerRepository) {
        this.containerRepository = containerRepository;
    }

    public Page<ContainerResponseModel> findAll(Pageable pageable) {
        return containerRepository.findAll(pageable).map(ContainerResponseModel::new);
    }

    public ContainerResponseModel findById(long id) {
        return containerRepository.findById(id).map(ContainerResponseModel::new).orElseThrow(
                () -> new EntityNotFoundException(DefaultMessage.CONTAINER_NOT_FOUND)
        );
    }

    public ContainerResponseModel add(ContainerViewModel containerViewModel) {
        ContainerModel containerModel = new ContainerModel();
        BeanUtils.copyProperties(containerViewModel, containerModel);
        return new ContainerResponseModel(containerRepository.save(containerModel));
    }

    public ContainerResponseModel update(ContainerViewModel containerViewModel) {
        Optional<ContainerModel> containerOriginal = containerRepository.findById(containerViewModel.id());
        if (containerOriginal.isPresent()) {
            ContainerModel containerModel = new ContainerModel();
            BeanUtils.copyProperties(containerViewModel, containerModel);
            return new ContainerResponseModel(containerRepository.save(containerModel));
        } else {
            throw new EntityNotFoundException(DefaultMessage.CONTAINER_NOT_FOUND);
        }
    }

    public void delete(long id) {
        Optional<ContainerModel> containerOriginal = containerRepository.findById(id);
        if (containerOriginal.isPresent()) {
            containerRepository.delete(containerOriginal.get());
        } else {
            throw new EntityNotFoundException(DefaultMessage.CONTAINER_NOT_FOUND);
        }
    }
}
