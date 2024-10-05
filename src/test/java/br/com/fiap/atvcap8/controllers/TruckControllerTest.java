package br.com.fiap.atvcap8.controllers;

import br.com.fiap.atvcap8.responsemodels.TruckResponseModel;
import br.com.fiap.atvcap8.services.TruckService;
import br.com.fiap.atvcap8.viewmodels.TruckViewModel;
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

class TruckControllerTest {

    @Mock
    private TruckService truckService;

    @InjectMocks
    private TruckController truckController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        Pageable pageable = mock(Pageable.class);
        TruckResponseModel responseModel = new TruckResponseModel(1L, "ABC1234", 20.0f, true);
        Page<TruckResponseModel> page = new PageImpl<>(Collections.singletonList(responseModel));
        when(truckService.findAll(pageable)).thenReturn(page);

        Page<TruckResponseModel> result = truckController.findAll(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(truckService, times(1)).findAll(pageable);
    }

    @Test
    void testFindById() {
        Long id = 1L;
        TruckResponseModel responseModel = new TruckResponseModel(id, "ABC1234", 20.0f, true);
        when(truckService.findById(id)).thenReturn(responseModel);

        TruckResponseModel result = truckController.findById(id);

        assertNotNull(result);
        assertEquals(id, result.id());
        verify(truckService, times(1)).findById(id);
    }

    @Test
    void testFindByLicensePlate() {
        String licensePlate = "ABC1234";
        TruckResponseModel responseModel = new TruckResponseModel(1L, licensePlate, 20.0f, true);
        when(truckService.findByLicensePlate(licensePlate)).thenReturn(responseModel);

        TruckResponseModel result = truckController.findByLicensePlate(licensePlate);

        assertNotNull(result);
        assertEquals(licensePlate, result.licensePlate());
        verify(truckService, times(1)).findByLicensePlate(licensePlate);
    }

    @Test
    void testAddTruck() {
        TruckViewModel viewModel = new TruckViewModel(null,"ABC1234", 20.0f, true);
        TruckResponseModel responseModel = new TruckResponseModel(1L, "ABC1234", 20.0f, true);
        when(truckService.add(viewModel)).thenReturn(responseModel);

        TruckResponseModel result = truckController.add(viewModel);

        assertNotNull(result);
        assertEquals(viewModel.licensePlate(), result.licensePlate());
        verify(truckService, times(1)).add(viewModel);
    }

    @Test
    void testRemoveTruck() {
        Long id = 1L;
        doNothing().when(truckService).remove(id);

        truckController.remove(id);

        verify(truckService, times(1)).remove(id);
    }

    @Test
    void testUpdateTruck() {
        TruckViewModel viewModel = new TruckViewModel(null,"ABC1234", 20.0f, true);
        TruckResponseModel responseModel = new TruckResponseModel(1L, "ABC1234", 20.0f, true);
        when(truckService.update(viewModel)).thenReturn(responseModel);

        TruckResponseModel result = truckController.update(viewModel);

        assertNotNull(result);
        assertEquals(viewModel.licensePlate(), result.licensePlate());
        verify(truckService, times(1)).update(viewModel);
    }
}
