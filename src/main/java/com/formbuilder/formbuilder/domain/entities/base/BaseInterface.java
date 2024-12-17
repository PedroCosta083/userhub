package com.formbuilder.formbuilder.domain.entities.base;

import java.util.UUID;

public interface BaseInterface {
    UUID getId();

    String getName();

    void validate();

    void activate();

    void deactivate();

}
