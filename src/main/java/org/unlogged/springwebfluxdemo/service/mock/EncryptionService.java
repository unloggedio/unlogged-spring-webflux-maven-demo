package org.unlogged.springwebfluxdemo.service.mock;

import org.springframework.stereotype.Service;

@Service
public interface EncryptionService {
    public byte[] encrypt(String pt);
}
