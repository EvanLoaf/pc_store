package com.gmail.evanloafakahaitao.pcstore.dao.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class SoftDeleteAndDisableEntity {

    @Column
    @NotNull
    private boolean isDisabled;
    @Column
    @NotNull
    private boolean isDeleted;

    public boolean isDisabled() {
        return isDisabled;
    }

    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
