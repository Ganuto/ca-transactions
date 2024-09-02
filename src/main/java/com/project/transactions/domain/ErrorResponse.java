package com.project.transactions.domain;

import java.time.LocalDateTime;

public record ErrorResponse(
    LocalDateTime timeStamp, int httpCode, String httpStatus, String errorMessage) {}
