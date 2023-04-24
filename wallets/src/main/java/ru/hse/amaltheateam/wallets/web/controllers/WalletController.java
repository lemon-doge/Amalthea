package ru.hse.amaltheateam.wallets.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hse.amaltheateam.wallets.dto.wallet.request.CreateWalletRequestDTO;
import ru.hse.amaltheateam.wallets.dto.wallet.request.UpdateWalletRequestDTO;
import ru.hse.amaltheateam.wallets.dto.wallet.response.AllWalletsResponseDTO;
import ru.hse.amaltheateam.wallets.dto.wallet.response.SingleWalletResponseDTO;
import ru.hse.amaltheateam.wallets.web.services.WalletService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/{userId}/wallets")
public class WalletController {
    private final WalletService walletService;

    @GetMapping
    @Operation(summary = "Get all wallets by user id.")
    // todo: @RequestParam(value = "username") String username ?
    // https://stackoverflow.com/questions/28039709/what-is-difference-between-requestbody-and-requestparam
    public AllWalletsResponseDTO getAllWallets(@PathVariable Long userId) {
        return walletService.getAllWalletsByUserId(userId);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get wallet by id & user id.")
    public SingleWalletResponseDTO getWalletById(@PathVariable Long userId, @PathVariable Long id) {
        return walletService.getWalletById(id);
    }

    @PostMapping
    @Operation(summary = "Create wallet & user id.")
    public ResponseEntity<SingleWalletResponseDTO> createWallet(@PathVariable Long userId, @Valid @RequestBody CreateWalletRequestDTO walletInfo) {
        var walletResponseDTO = walletService.createWallet(userId, walletInfo);
        return new ResponseEntity<>(walletResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete wallet by id & user id.")
    public ResponseEntity<Long> deleteWallet(@PathVariable Long userId, @PathVariable Long id) {
        walletService.deleteWallet(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update wallet by id & user id.", description = "Requires complete wallet info - checkout UpdateWalletRequestDTO description.")
    public SingleWalletResponseDTO updateWallet(@PathVariable Long userId, @PathVariable Long id, @Valid @RequestBody UpdateWalletRequestDTO updateWalletRequestDTO) {
        return walletService.updateWallet(id, updateWalletRequestDTO);
    }
}
