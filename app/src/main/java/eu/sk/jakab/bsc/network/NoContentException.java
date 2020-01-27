package eu.sk.jakab.bsc.network;

import java.io.IOException;

public class NoContentException extends IOException {
    public NoContentException(String message) {
        super(message);
    }
}
