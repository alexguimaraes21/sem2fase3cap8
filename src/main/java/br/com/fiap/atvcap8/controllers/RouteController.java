package br.com.fiap.atvcap8.controllers;

import br.com.fiap.atvcap8.responsemodels.RouteResponseModel;
import br.com.fiap.atvcap8.services.RouteService;
import br.com.fiap.atvcap8.viewmodels.RouteViewModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/v1/route")
    @ResponseStatus(HttpStatus.OK)
    public Page<RouteResponseModel> findAll(Pageable pageable) {
        return routeService.findAll(pageable);
    }

    @GetMapping(value = "/v1/route", params = "id")
    @ResponseStatus(HttpStatus.OK)
    public RouteResponseModel findById(@RequestParam long id) {
        return routeService.findById(id);
    }

    @PostMapping("/v1/route")
    @ResponseStatus(HttpStatus.CREATED)
    public RouteResponseModel add(@RequestBody @Valid RouteViewModel routeViewModel) {
        return routeService.add(routeViewModel);
    }

    @DeleteMapping(value = "/v1/route", params = "id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam long id) {
        routeService.delete(id);
    }

    @PutMapping("/v1/route")
    @ResponseStatus(HttpStatus.OK)
    public RouteResponseModel update(@RequestBody @Valid RouteViewModel routeViewModel) {
        return routeService.update(routeViewModel);
    }
}
