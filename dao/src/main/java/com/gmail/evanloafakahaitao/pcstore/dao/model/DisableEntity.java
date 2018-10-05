package com.gmail.evanloafakahaitao.pcstore.dao.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class DisableEntity {

    @Column
    @NotNull
    private boolean isDisabled;

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }
}
