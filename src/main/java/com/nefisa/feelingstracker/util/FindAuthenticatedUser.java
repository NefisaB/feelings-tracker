package com.nefisa.feelingstracker.util;

import com.nefisa.feelingstracker.entity.User;

import java.nio.file.AccessDeniedException;

public interface FindAuthenticatedUser {

    User getAuthenticatedUser() throws AccessDeniedException;
}
