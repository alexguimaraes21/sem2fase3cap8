package br.com.fiap.atvcap8.controllers;

import br.com.fiap.atvcap8.responsemodels.CollectionResponseModel;
import br.com.fiap.atvcap8.services.CollectionService;
import br.com.fiap.atvcap8.viewmodels.CollectionViewModel;
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

class CollectionControllerTest {

    @Mock
    private CollectionService collectionService;

    @InjectMocks
    private CollectionController collectionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Arrange
        Pageable pageable = mock(Pageable.class);
        CollectionResponseModel responseModel = new CollectionResponseModel(1L, null, null, null);
        Page<CollectionResponseModel> page = new PageImpl<>(Collections.singletonList(responseModel));
        when(collectionService.findAll(pageable)).thenReturn(page);

        // Act
        Page<CollectionResponseModel> result = collectionController.findAll(pageable);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        verify(collectionService, times(1)).findAll(pageable);
    }

    @Test
    void testFindById() {
        // Arrange
        Long id = 1L;
        CollectionResponseModel responseModel = new CollectionResponseModel(id, null, null, null);
        when(collectionService.findById(id)).thenReturn(responseModel);

        // Act
        CollectionResponseModel result = collectionController.findById(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.id());
        verify(collectionService, times(1)).findById(id);
    }

    @Test
    void testAddCollection() {
        // Arrange
        CollectionViewModel viewModel = new CollectionViewModel(null, null, null, null);
        CollectionResponseModel responseModel = new CollectionResponseModel(1L, null, null, null);
        when(collectionService.add(viewModel)).thenReturn(responseModel);

        // Act
        CollectionResponseModel result = collectionController.add(viewModel);

        // Assert
        assertNotNull(result);
        verify(collectionService, times(1)).add(viewModel);
    }

    @Test
    void testDeleteCollection() {
        // Arrange
        Long id = 1L;
        doNothing().when(collectionService).delete(id);

        // Act
        collectionController.delete(id);

        // Assert
        verify(collectionService, times(1)).delete(id);
    }

    @Test
    void testUpdateCollection() {
        // Arrange
        CollectionViewModel viewModel = new CollectionViewModel(null, null, null, null);
        CollectionResponseModel responseModel = new CollectionResponseModel(1L, null, null, null);
        when(collectionService.update(viewModel)).thenReturn(responseModel);

        // Act
        CollectionResponseModel result = collectionController.update(viewModel);

        // Assert
        assertNotNull(result);
        verify(collectionService, times(1)).update(viewModel);
    }
}
