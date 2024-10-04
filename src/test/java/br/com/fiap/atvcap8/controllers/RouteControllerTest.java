package br.com.fiap.atvcap8.controllers;

import br.com.fiap.atvcap8.responsemodels.RouteResponseModel;
import br.com.fiap.atvcap8.services.RouteService;
import br.com.fiap.atvcap8.viewmodels.RouteViewModel;
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

class RouteControllerTest {

    @Mock
    private RouteService routeService;

    @InjectMocks
    private RouteController routeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Arrange
        Pageable pageable = mock(Pageable.class);
        RouteResponseModel responseModel = new RouteResponseModel(1L, "Route A", null, null, null);
        Page<RouteResponseModel> page = new PageImpl<>(Collections.singletonList(responseModel));
        when(routeService.findAll(pageable)).thenReturn(page);

        // Act
        Page<RouteResponseModel> result = routeController.findAll(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(routeService, times(1)).findAll(pageable);
    }

    @Test
    void testFindById() {
        // Arrange
        long id = 1L;
        RouteResponseModel responseModel = new RouteResponseModel(id, "Route A", null, null, null);
        when(routeService.findById(id)).thenReturn(responseModel);

        // Act
        RouteResponseModel result = routeController.findById(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.id());
        verify(routeService, times(1)).findById(id);
    }

    @Test
    void testAddRoute() {
        // Arrange
        RouteViewModel viewModel = new RouteViewModel(null,"Route A", null, null, null);
        RouteResponseModel responseModel = new RouteResponseModel(1L, "Route A", null, null, null);
        when(routeService.add(viewModel)).thenReturn(responseModel);

        // Act
        RouteResponseModel result = routeController.add(viewModel);

        // Assert
        assertNotNull(result);
        assertEquals(viewModel.description(), result.description());
        verify(routeService, times(1)).add(viewModel);
    }

    @Test
    void testDeleteRoute() {
        // Arrange
        long id = 1L;
        doNothing().when(routeService).delete(id);

        // Act
        routeController.delete(id);

        // Assert
        verify(routeService, times(1)).delete(id);
    }

    @Test
    void testUpdateRoute() {
        // Arrange
        RouteViewModel viewModel = new RouteViewModel(null, "Route A", null, null, null);
        RouteResponseModel responseModel = new RouteResponseModel(1L, "Route A", null, null, null);
        when(routeService.update(viewModel)).thenReturn(responseModel);

        // Act
        RouteResponseModel result = routeController.update(viewModel);

        // Assert
        assertNotNull(result);
        assertEquals(viewModel.description(), result.description());
        verify(routeService, times(1)).update(viewModel);
    }
}
