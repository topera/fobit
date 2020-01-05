package com.terahorse.fobit.model.items;

import java.util.Objects;

public class Item {

    private String code;
    private String shortCode;

    public Item(ItemEnum item) {
        this.code = item.CODE;
        this.shortCode = item.SHORT_CODE;
    }

    public String getCode() {
        return code;
    }

    @SuppressWarnings("unused") // used in front
    public String getShortCode() {
        return shortCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Item)) {
            return false;
        }
        return Objects.equals(((Item)obj).getCode(), code);
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public String toString() {
        return code;
    }

}
