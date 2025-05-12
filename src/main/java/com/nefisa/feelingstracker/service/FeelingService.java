package com.nefisa.feelingstracker.service;

import com.nefisa.feelingstracker.request.FeelingRequest;
import com.nefisa.feelingstracker.response.FeelingResponse;

import java.nio.file.AccessDeniedException;
import java.util.List;

public interface FeelingService {

    FeelingResponse createFeeling(FeelingRequest request) throws AccessDeniedException;
    List<FeelingResponse> getAllFeelings() throws AccessDeniedException;
    FeelingResponse getFeelingByIdAndOwner(long id) throws AccessDeniedException;
//
//    Feeling updateFeeling(long id, FeelingRequest request);
//    void deleteFeeling(long id);
}
