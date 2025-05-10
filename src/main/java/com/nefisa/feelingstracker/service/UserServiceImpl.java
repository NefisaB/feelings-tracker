package com.nefisa.feelingstracker.service;

import com.nefisa.feelingstracker.entity.Authority;
import com.nefisa.feelingstracker.entity.User;
import com.nefisa.feelingstracker.repository.UserRepository;
import com.nefisa.feelingstracker.request.PasswordUpdateRequest;
import com.nefisa.feelingstracker.response.UserResponse;
import com.nefisa.feelingstracker.util.FindAuthenticatedUser;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.nio.file.AccessDeniedException;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final FindAuthenticatedUser findAuthenticatedUser;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, FindAuthenticatedUser findAuthenticatedUser, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.findAuthenticatedUser = findAuthenticatedUser;
        this.passwordEncoder = passwordEncoder;
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

    @Override
    @Transactional
    public void updatePassword(PasswordUpdateRequest request) throws Exception {
        User user = findAuthenticatedUser.getAuthenticatedUser();

        // check if current password is correct
        if(!isOldPasswordCorrect(user.getPassword(), request.getOldPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Current password is incorrect");
        }

        // check if the new passwords are same
        if(!isSamePassword(request.getNewPassword1(), request.getNewPassword2())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New password do not match");
        }

        // check if new password is same as current password
        if(isSamePassword(user.getPassword(), request.getNewPassword1())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New password must be different from old password");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword1()));
        userRepository.save(user);
    }

    private boolean isOldPasswordCorrect(String currentPassword, String oldPassword){
        return passwordEncoder.matches(currentPassword, oldPassword);
    }

    private boolean isSamePassword(String password1, String password2){
        return password1.equals(password2);
    }
}
