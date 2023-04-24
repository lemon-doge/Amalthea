package ru.hse.amaltheateam.users.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.hse.amaltheateam.users.dto.request.UserRequestDTO;
import ru.hse.amaltheateam.users.dto.response.UserResponseDTO;
import ru.hse.amaltheateam.users.web.services.UserService;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UsersController {

    private final UserService userService;

    @Operation(summary = "Get user by id.")
    @GetMapping("/{id}")
    public UserResponseDTO getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @Operation(summary = "Get all users.")
    @GetMapping
    public List<UserResponseDTO> getUsers() {
        return userService.findAll();
    }

    @Operation(summary = "Delete user by id.")
    @DeleteMapping("/{id}")
    public Long deleteUserById(@PathVariable Long id) {
        return userService.deleteById(id);
    }

    @Operation(summary = "Create user.")
    @PostMapping
    public UserRequestDTO save(@Valid @RequestBody UserRequestDTO user) {
        return userService.save(user);
    }
}
