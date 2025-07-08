package org.acme.service;

import org.acme.dto.UserResponseDTO;
import org.acme.entity.User;
import org.acme.dto.UserCreatedDTO;
import org.acme.exception.EmailAlreadyExistsException;
import org.acme.exception.UserNotFoundException;

import org.acme.repository.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAllOrderedByName()
            .stream()
            .map(UserResponseDTO::new)
            .collect(Collectors.toList());
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findByIdOptional(id)
                    .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return new UserResponseDTO(user);
    }

    @Transactional
    public UserResponseDTO createUser(UserCreatedDTO userCreatedDTO) {
        // Check if the email already exists
        if ( userRepository.existsByEmail(userCreatedDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists: " + userCreatedDTO.getEmail());
        }

        User user = new User(userCreatedDTO.getName(), userCreatedDTO.getEmail());
        userRepository.persist(user);
        return new UserResponseDTO(user);
    }

    @Transactional
    public UserResponseDTO updateUser(Long id, UserCreatedDTO userCreatedDTO) {
        User user = userRepository.findByIdOptional(id)
            .orElseThrow(() ->  new UserNotFoundException("User not found with id: " + id));
        
        // Check if email is being changed and if new email already exists

        if (!user.getEmail().equals(userCreatedDTO.getEmail()) && userRepository.existsByEmail(userCreatedDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists: " + userCreatedDTO.getEmail());
        }

        user.setName(userCreatedDTO.getName());
        user.setEmail(userCreatedDTO.getEmail());

        return new UserResponseDTO(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findByIdOptional(id)
                .orElseThrow(() ->  new UserNotFoundException("User not foud with id: " + id));
        userRepository.delete(user);
    }

    public List<UserResponseDTO> searchUsersByName(String name) {
        return userRepository.findByNameContaining(name)
        .stream()
        .map(UserResponseDTO::new)
        .collect(Collectors.toList());
    }
}
