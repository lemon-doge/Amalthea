package ru.hse.amaltheateam.users.web.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hse.amaltheateam.users.dto.request.UserRequestDTO;
import ru.hse.amaltheateam.users.dto.response.UserResponseDTO;
import ru.hse.amaltheateam.users.model.User;
import ru.hse.amaltheateam.users.web.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public UserResponseDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
        return new UserResponseDTO()
                .setId(user.getId())
                .setEmail(user.getEmail());
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> findAll() {
        return userRepository.findAll().stream().map(user -> new UserResponseDTO().setId(user.getId()).setEmail(user.getEmail())).toList();
    }

    @Transactional
    public Long deleteById(Long id) {
        userRepository.deleteById(id);
        return id;
    }

    @Transactional
    public UserRequestDTO save(UserRequestDTO userRequestDTO) {
        User user = new User()
                .setEmail(userRequestDTO.getEmail());
        userRepository.save(user);

        return userRequestDTO;
    }
}
