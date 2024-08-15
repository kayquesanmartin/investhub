package tech.kayquedev.investhub.service;

import org.springframework.stereotype.Service;
import tech.kayquedev.investhub.dtos.CreateUserDto;
import tech.kayquedev.investhub.entity.User;
import tech.kayquedev.investhub.repository.UserRepository;

import java.time.Instant;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(CreateUserDto createUserDto) {
        var entity = new User(
                UUID.randomUUID(),
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.password(),
                Instant.now(),
                null);

        userRepository.save(entity);
    }
}
