package org.unlogged.springwebfluxdemo.listener;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.unlogged.springwebfluxdemo.service.mock.EncryptionService;

public class EncryptionMongoEventListener<E> extends AbstractMongoEventListener<E> {

    private EncryptionService service;

    public EncryptionMongoEventListener(EncryptionService service) {
        this.service = service;
    }

    public void onBeforeConvert(BeforeConvertEvent<E> event) {
        E source = event.getSource();
    }

    public void onAfterConvert(AfterConvertEvent<E> event) {
        E source = event.getSource();
    }
}
