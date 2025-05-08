package com.nefisa.feelingstracker.service;

import com.nefisa.feelingstracker.entity.Authority;
import com.nefisa.feelingstracker.entity.User;
import com.nefisa.feelingstracker.repositories.UserRepository;
import com.nefisa.feelingstracker.response.UserResponse;
import com.nefisa.feelingstracker.util.FindAuthenticatedUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final FindAuthenticatedUser findAuthenticatedUser;

    public UserServiceImpl(UserRepository userRepository, FindAuthenticatedUser findAuthenticatedUser) {
        this.userRepository = userRepository;
        this.findAuthenticatedUser = findAuthenticatedUser;
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getUserInfo() throws AccessDeniedException {

        User user = findAuthenticatedUser.getAuthenticatedUser();

        return new UserResponse(user.getId(),
                user.getFirstName() + " " + user.getLastName(),
                user.getEmail(),
                user.getAuthorities()
                        .stream()
                        .map(auth -> (Authority) auth)
                        .toList());
    }

    @Override
    public void deleteUser() throws AccessDeniedException {

        User user = findAuthenticatedUser.getAuthenticatedUser();

        userRepository.delete(user);
    }
}
