package com.gmail.evanloafakahaitao.pcstore.temp.web.model;

import java.util.Objects;

public class AccessMode {

    private RoleEnum role;
    private CommandEnum command;
    private RequestEnum request;

    private AccessMode(Builder builder) {
        role = builder.role;
        command = builder.command;
        request = builder.request;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public RoleEnum getRole() {
        return role;
    }

    public CommandEnum getCommand() {
        return command;
    }

    public RequestEnum getRequest() {
        return request;
    }

    public static final class Builder {
        private RoleEnum role;
        private CommandEnum command;
        private RequestEnum request;

        private Builder() {
        }

        public Builder withRole(RoleEnum val) {
            role = val;
            return this;
        }

        public Builder withCommand(CommandEnum val) {
            command = val;
            return this;
        }

        public Builder withRequest(RequestEnum val) {
            request = val;
            return this;
        }

        public AccessMode build() {
            return new AccessMode(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessMode that = (AccessMode) o;
        return role == that.role &&
                command == that.command &&
                request == that.request;
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, command, request);
    }
}
