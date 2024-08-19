package tech.kayquedev.investhub.service;

import org.springframework.stereotype.Service;
import tech.kayquedev.investhub.dtos.CreateUserDto;
import tech.kayquedev.investhub.dtos.UpdateUserDto;
import tech.kayquedev.investhub.entity.User;
import tech.kayquedev.investhub.repository.UserRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDto createUserDto) {
        var entity = new User(
                UUID.randomUUID(),
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.password(),
                Instant.now(),
                null);

        var userSaved = userRepository.save(entity);

        return userSaved.getUserId();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(UUID.fromString(id));
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void updateUserById(String userId, UpdateUserDto updateUserDto) {
        var id = UUID.fromString(userId);

        var userEntity = userRepository.findById(id);

        if(userEntity.isPresent()) {
            var user = userEntity.get();

            if(updateUserDto.username() != null){
                user.setUsername(updateUserDto.username());
            }

            if(updateUserDto.password() != null){
                user.setPassword(updateUserDto.password());
            }

            userRepository.save(user);
        }
    }

    public Void deleteById(String userId) {
        var id = UUID.fromString(userId);

        var userExists = userRepository.existsById(UUID.fromString(userId));

        if (userExists) {
            userRepository.deleteById(id);
        }
    }
}
