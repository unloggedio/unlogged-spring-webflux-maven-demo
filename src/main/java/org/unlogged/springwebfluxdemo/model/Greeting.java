package org.unlogged.springwebfluxdemo.model;


import org.springframework.context.annotation.Bean;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

public class Greeting {

    private String message;
    private TypeWrapper typeWrapper;

    public Greeting() {
    }

    public Greeting(String message) {
        this.message = message;
    }

    public Greeting(TypeWrapper typeWrapper) {
        this.typeWrapper = typeWrapper;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TypeWrapper getTypeWrapper() {
        return typeWrapper;
    }

    public GenericTypeWrapper<String> genericTypeWrapperString() {
        return new GenericTypeWrapper<String>("string value");
    }

    public Bean getSomeBean() {
        return new Bean() {

            @Override
            public Class<? extends Annotation> annotationType() {
                return null;
            }

            @Override
            public String[] value() {
                return new String[0];
            }

            @Override
            public String[] name() {
                return new String[0];
            }

            @Override
            public boolean autowireCandidate() {
                return false;
            }

            @Override
            public String initMethod() {
                return null;
            }

            @Override
            public String destroyMethod() {
                return null;
            }
        };
    }

    public void setTypeWrapper(TypeWrapper typeWrapper) {
        this.typeWrapper = typeWrapper;
    }

    public List<String> getListOfStrings() {
        return Arrays.asList("123", "65", "513", "3", "47", "23", "255", "363");
    }

    @Override
    public String toString() {
        return "Greeting{" +
                "message='" + message + '\'' +
                ", typeWrapper=" + typeWrapper +
                '}';
    }
}