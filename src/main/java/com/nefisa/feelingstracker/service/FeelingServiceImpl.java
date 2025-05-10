package com.nefisa.feelingstracker.service;

import com.nefisa.feelingstracker.entity.Feeling;
import com.nefisa.feelingstracker.entity.User;
import com.nefisa.feelingstracker.repository.FeelingRepository;
import com.nefisa.feelingstracker.request.FeelingRequest;
import com.nefisa.feelingstracker.response.FeelingResponse;
import com.nefisa.feelingstracker.util.FindAuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.List;

@Service
public class FeelingServiceImpl implements FeelingService{

    private final FeelingRepository feelingRepository;
    private final FindAuthenticatedUser findAuthenticatedUser;

    @Autowired
    public FeelingServiceImpl(FeelingRepository feelingRepository, FindAuthenticatedUser findAuthenticatedUser) {
        this.feelingRepository = feelingRepository;
        this.findAuthenticatedUser = findAuthenticatedUser;
    }


    @Override
    @Transactional
    public FeelingResponse createFeeling(FeelingRequest request) throws AccessDeniedException {
        User user = findAuthenticatedUser.getAuthenticatedUser();

        Feeling feeling = new Feeling(0,
                request.getTitle(),
                request.getDescription(),
                new Date(System.currentTimeMillis()),
                null,
                user);

        feelingRepository.save(feeling);

        return convertToFeelingResponse(feeling);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeelingResponse> getAllFeelings() throws AccessDeniedException {
        User user = findAuthenticatedUser.getAuthenticatedUser();

        return feelingRepository.findByOwner(user)
                .stream()
                .map(this::convertToFeelingResponse)
                .toList();
    }

    private FeelingResponse convertToFeelingResponse(Feeling feeling) {
        return new FeelingResponse(feeling.getId(),
                feeling.getTitle(),
                feeling.getDescription(),
                feeling.getDateAdded(),
                feeling.getDateUpdated());
    }


}
