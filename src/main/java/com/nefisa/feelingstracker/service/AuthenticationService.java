package com.nefisa.feelingstracker.service;

import com.nefisa.feelingstracker.request.RegisterRequest;

public interface AuthenticationService {
    void register(RegisterRequest input) throws Exception;
}
