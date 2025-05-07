package com.nefisa.feelingstracker.service;

import com.nefisa.feelingstracker.response.UserResponse;

import java.nio.file.AccessDeniedException;

public interface UserService {
    UserResponse getUserInfo() throws Exception;
    void deleteUser() throws AccessDeniedException;
}
