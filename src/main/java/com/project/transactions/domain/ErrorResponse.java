package com.project.transactions.domain;

import java.time.LocalDateTime;

public record ErrorResponse(
    LocalDateTime timestamp, int httpCode, String httpStatus, String errorMessage) {}
