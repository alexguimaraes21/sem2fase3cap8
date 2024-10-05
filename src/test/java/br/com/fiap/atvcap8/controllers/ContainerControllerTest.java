package br.com.fiap.atvcap8.controllers;

import br.com.fiap.atvcap8.responsemodels.ContainerResponseModel;
import br.com.fiap.atvcap8.services.ContainerService;
import br.com.fiap.atvcap8.viewmodels.ContainerViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContainerControllerTest {

    @Mock
    private ContainerService containerService;

    @InjectMocks
    private ContainerController containerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Arrange
        Pageable pageable = mock(Pageable.class);
        ContainerResponseModel responseModel = new ContainerResponseModel(1L, "Location A", 100.0f, 50);
        Page<ContainerResponseModel> page = new PageImpl<>(Collections.singletonList(responseModel));
        when(containerService.findAll(pageable)).thenReturn(page);

        // Act
        Page<ContainerResponseModel> result = containerController.findAll(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(containerService, times(1)).findAll(pageable);
    }

    @Test
    void testFindById() {
        // Arrange
        int id = 1;
        ContainerResponseModel responseModel = new ContainerResponseModel(1L, "Location A", 100.0f, 50);
        when(containerService.findById(id)).thenReturn(responseModel);

        // Act
        ContainerResponseModel result = containerController.findById(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.id());
        verify(containerService, times(1)).findById(id);
    }

    @Test
    void testAddContainer() {
        // Arrange
        ContainerViewModel viewModel = new ContainerViewModel(null,"Location A", 100.0f, 50);
        ContainerResponseModel responseModel = new ContainerResponseModel(1L, "Location A", 100.0f, 50);
        when(containerService.add(viewModel)).thenReturn(responseModel);

        // Act
        ContainerResponseModel result = containerController.add(viewModel);

        // Assert
        assertNotNull(result);
        assertEquals(viewModel.location(), result.location());
        verify(containerService, times(1)).add(viewModel);
    }

    @Test
    void testDeleteContainer() {
        // Arrange
        int id = 1;
        doNothing().when(containerService).delete(id);

        // Act
        containerController.delete(id);

        // Assert
        verify(containerService, times(1)).delete(id);
    }

    @Test
    void testUpdateContainer() {
        // Arrange
        ContainerViewModel viewModel = new ContainerViewModel(null, "Location A", 100.0f, 50);
        ContainerResponseModel responseModel = new ContainerResponseModel(1L, "Location A", 100.0f, 50);
        when(containerService.update(viewModel)).thenReturn(responseModel);

        // Act
        ContainerResponseModel result = containerController.update(viewModel);

        // Assert
        assertNotNull(result);
        assertEquals(viewModel.location(), result.location());
        verify(containerService, times(1)).update(viewModel);
    }
}
