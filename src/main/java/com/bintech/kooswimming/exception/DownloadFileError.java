package com.bintech.kooswimming.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus()
public class DownloadFileError extends RuntimeException {

    public DownloadFileError(String msg) {
        super(msg);
    }

    public DownloadFileError(String msg, Throwable ex) {
        super(msg, ex);
    }
}
