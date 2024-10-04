package br.com.fiap.atvcap8.controllers;

import br.com.fiap.atvcap8.responsemodels.ContainerResponseModel;
import br.com.fiap.atvcap8.services.ContainerService;
import br.com.fiap.atvcap8.viewmodels.ContainerViewModel;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/api")
public class ContainerController {

    private final ContainerService containerService;

    public ContainerController(ContainerService containerService) {
        this.containerService = containerService;
    }

    @GetMapping("/v1/container")
    @ResponseStatus(HttpStatus.OK)
    public Page<ContainerResponseModel> findAll(Pageable pageable) {
        return containerService.findAll(pageable);
    }

    @GetMapping(value = "/v1/container", params = "id")
    @ResponseStatus(HttpStatus.OK)
    public ContainerResponseModel findById(@RequestParam int id) {
        return containerService.findById(id);
    }

    @PostMapping("/v1/container")
    @ResponseStatus(HttpStatus.CREATED)
    public ContainerResponseModel add(@RequestBody @Valid ContainerViewModel containerViewModel) {
        return containerService.add(containerViewModel);
    }

    @DeleteMapping(value = "/v1/container", params = "id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam int id) {
        containerService.delete(id);
    }

    @PutMapping("/v1/container")
    @ResponseStatus(HttpStatus.OK)
    public ContainerResponseModel update(@RequestBody @Valid ContainerViewModel containerViewModel) {
        return containerService.update(containerViewModel);
    }
}
