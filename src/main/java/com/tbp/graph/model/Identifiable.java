package com.tbp.graph.model;


public abstract class Identifiable<ID> {
    public abstract ID getId();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Identifiable identifiable = (Identifiable) o;
        return getId() != null ? getId().equals(identifiable.getId()) : identifiable.getId() == null;

    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
