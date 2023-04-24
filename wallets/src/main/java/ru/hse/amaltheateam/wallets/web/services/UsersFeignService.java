package ru.hse.amaltheateam.wallets.web.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hse.amaltheateam.wallets.model.User;
import ru.hse.amaltheateam.wallets.web.feign.UsersFeignClient;
import ru.hse.amaltheateam.wallets.web.mappers.UsersMapper;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersFeignService {
    private final UsersFeignClient usersFeignClient;
    private final UsersMapper usersMapper;


    public Optional<User> getUserById(Long userId) {
        return Optional.of(usersMapper.toUser(usersFeignClient.getUserById(userId)));
    }
}
