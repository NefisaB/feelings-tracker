package com.nefisa.feelingstracker.service;

import com.nefisa.feelingstracker.response.UserResponse;

import java.util.List;

public interface AdminService {

    List<UserResponse> getAllUsers();
    UserResponse promoteToAdmin(long userId);
    void deleteNonAdminUser(long userId);
}
