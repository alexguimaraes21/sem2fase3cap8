package br.com.fiap.atvcap8.controllers;

import br.com.fiap.atvcap8.responsemodels.TruckResponseModel;
import br.com.fiap.atvcap8.services.TruckService;
import br.com.fiap.atvcap8.viewmodels.TruckViewModel;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TruckController {

    private final TruckService truckService;

    public TruckController(TruckService truckService) {
        this.truckService = truckService;
    }

    @GetMapping("/v1/truck")
    @ResponseStatus(HttpStatus.OK)
    public Page<TruckResponseModel> findAll(Pageable pageable) {
        return truckService.findAll(pageable);
    }

    @GetMapping(value = "/v1/truck", params = "id")
    @ResponseStatus(HttpStatus.OK)
    public TruckResponseModel findById(@RequestParam Long id) {
        return truckService.findById(id);
    }

    @GetMapping(value = "/v1/truck", params = "licensePlate")
    @ResponseStatus(HttpStatus.OK)
    public TruckResponseModel findByLicensePlate(@RequestParam String licensePlate) {
        return truckService.findByLicensePlate(licensePlate);
    }

    @PostMapping(value = "/v1/truck")
    @ResponseStatus(HttpStatus.CREATED)
    public TruckResponseModel add(@RequestBody @Valid TruckViewModel viewModel) {
        return truckService.add(viewModel);
    }

    @DeleteMapping(value = "/v1/truck", params = "id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@RequestParam Long id) {
        truckService.remove(id);
    }

    @PutMapping(value = "/v1/truck")
    @ResponseStatus(HttpStatus.OK)
    public TruckResponseModel update(@RequestBody @Valid TruckViewModel viewModel) {
        return truckService.update(viewModel);
    }
}
