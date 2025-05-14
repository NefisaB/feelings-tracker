package com.nefisa.feelingstracker.service;

import com.nefisa.feelingstracker.entity.Authority;
import com.nefisa.feelingstracker.entity.User;
import com.nefisa.feelingstracker.repository.UserRepository;
import com.nefisa.feelingstracker.response.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{

    private final UserRepository userRepository;

    public AdminServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        List<UserResponse> users = new ArrayList<>();
        for(User user : userRepository.findAll()){
            users.add(convertToUserResponse(user));
        }
        return users;
    }


    @Override
    @Transactional
    public UserResponse promoteToAdmin(long userId) {
        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty() || user.get().getAuthorities().stream().anyMatch(authority ->
                "ROLE_ADMIN".equals(authority.getAuthority()))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found or already an admin");
        }

        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority("ROLE_USER"));
        authorities.add(new Authority("ROLE_ADMIN"));
        user.get().setAuthorities(authorities);

        User savedUser = userRepository.save(user.get());

        return convertToUserResponse(savedUser);
    }

    @Override
    @Transactional
    public void deleteNonAdminUser(long userId) {

        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty() || user.get().getAuthorities().stream().anyMatch(authority ->
                "ROLE_ADMIN".equals(authority.getAuthority()))){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found or already an admin");
        }

        userRepository.delete(user.get());
    }


    private UserResponse convertToUserResponse(User user) {
        return new UserResponse(user.getId(),
                user.getFirstName() + " " + user.getLastName(),
                user.getEmail(),
                user.getAuthorities()
                        .stream()
                        .map(auth -> (Authority) auth)
                        .toList());
    }
}
