package com.tinkoff.sirius.amalthea.controller;

import com.tinkoff.sirius.amalthea.dto.user.request.UserRequestDTO;
import com.tinkoff.sirius.amalthea.dto.user.response.UserResponseDTO;
import com.tinkoff.sirius.amalthea.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Get user by id.")
    @GetMapping(value = "/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @Operation(summary = "Get all users.")
    @GetMapping()
    public List<UserResponseDTO> geAllUsers() {
        return userService.findAll();
    }

    @Operation(summary = "Delete user by id.")
    @DeleteMapping(value = "/{id}")
    public Long deleteUserById(@PathVariable Long id) {
        return userService.deleteById(id);
    }

    @Operation(summary = "Create user.")
    @PostMapping
    public UserRequestDTO postUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        return userService.save(userRequestDTO);
    }
}
