package br.com.fiap.atvcap8.controllers;

import br.com.fiap.atvcap8.responsemodels.CollectionResponseModel;
import br.com.fiap.atvcap8.services.CollectionService;
import br.com.fiap.atvcap8.viewmodels.CollectionViewModel;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CollectionController {

    CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping("/v1/collection")
    @ResponseStatus(HttpStatus.OK)
    public Page<CollectionResponseModel> findAll(Pageable pageable) {
        return collectionService.findAll(pageable);
    }

    @GetMapping(value = "/v1/collection", params = "id")
    @ResponseStatus(HttpStatus.OK)
    public CollectionResponseModel findById(Long id) {
        return collectionService.findById(id);
    }

    @PostMapping("/v1/collection")
    @ResponseStatus(HttpStatus.CREATED)
    public CollectionResponseModel add(@RequestBody @Valid CollectionViewModel collectionViewModel) {
        return collectionService.add(collectionViewModel);
    }

    @DeleteMapping(value = "/v1/collection", params = "id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam Long id) {
        collectionService.delete(id);
    }

    @PutMapping(value = "/v1/collection")
    @ResponseStatus(HttpStatus.OK)
    public CollectionResponseModel update(@RequestBody @Valid CollectionViewModel collectionViewModel) {
        return collectionService.update(collectionViewModel);
    }
}
