package com.nefisa.feelingstracker.service;

import com.nefisa.feelingstracker.request.AuthenticationRequest;
import com.nefisa.feelingstracker.request.RegisterRequest;
import com.nefisa.feelingstracker.response.AuthenticationResponse;

public interface AuthenticationService {
    void register(RegisterRequest input) throws Exception;
    AuthenticationResponse login(AuthenticationRequest request);
}
