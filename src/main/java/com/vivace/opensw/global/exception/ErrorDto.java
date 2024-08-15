package com.vivace.opensw.global.exception;

public record ErrorDto(
        String timestamp,
        int status,
        String errorCode,
        String message,
        String path
) {}
