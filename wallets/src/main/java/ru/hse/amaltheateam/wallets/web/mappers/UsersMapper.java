package ru.hse.amaltheateam.wallets.web.mappers;

import org.springframework.stereotype.Component;
import ru.hse.amaltheateam.wallets.dto.user.response.UserResponseDTO;
import ru.hse.amaltheateam.wallets.model.User;

@Component
public class UsersMapper {
    public User toUser(UserResponseDTO userResponseDTO) {
        return new User().setId(userResponseDTO.getId())
                .setEmail(userResponseDTO.getEmail());
    }
}
