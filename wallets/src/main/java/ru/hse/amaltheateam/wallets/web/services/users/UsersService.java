package ru.hse.amaltheateam.wallets.web.services.users;

import ru.hse.amaltheateam.wallets.dto.user.response.UserResponseDTO;
import ru.hse.amaltheateam.wallets.model.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    Optional<User> getUserById(Long id);
}
