package com.tinkoff.sirius.amalthea.controller;

import com.tinkoff.sirius.amalthea.dto.operation.request.CreateOperationRequestDTO;
import com.tinkoff.sirius.amalthea.dto.operation.request.UpdateOperationRequestDTO;
import com.tinkoff.sirius.amalthea.dto.operation.response.OperationResponseDTO;
import com.tinkoff.sirius.amalthea.service.OperationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/operations")
public class OperationController {

    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @GetMapping
    @Operation(summary = "Get list of operations by wallet id sorted by date descending.")
    public List<OperationResponseDTO> getOperationsByWalletId(@RequestParam Long walletId) {
        return operationService.findAll(walletId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get operation by id.")
    public OperationResponseDTO getOperation(@PathVariable("id") Long id) {
        return operationService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create operation.")
    public ResponseEntity<OperationResponseDTO> postOperation(@RequestBody CreateOperationRequestDTO operationRequestDTO) {
        OperationResponseDTO operation = operationService.save(operationRequestDTO);
        return new ResponseEntity<>(operation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete operation by id.")
    public Long deleteOperation(@PathVariable Long id) {
        return operationService.deleteById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update operation by id.")
    public ResponseEntity<OperationResponseDTO> updateOperation(@RequestBody UpdateOperationRequestDTO operationRequestDTO,
                                     @PathVariable Long id) {
        OperationResponseDTO operation = operationService.update(operationRequestDTO, id);
        return new ResponseEntity<>(operation, HttpStatus.OK);
    }
}
