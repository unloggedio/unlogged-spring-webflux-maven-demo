package org.unlogged.springwebfluxdemo.service.mock;

import org.springframework.stereotype.Component;

@Component
public class EncryptionServiceImpl implements EncryptionService {
    @Override
    public byte[] encrypt(String pt) {
        return pt.getBytes();
    }
}
