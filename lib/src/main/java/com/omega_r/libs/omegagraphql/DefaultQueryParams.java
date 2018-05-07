package com.omega_r.libs.omegagraphql;

import android.support.annotation.Nullable;

import java.lang.reflect.Field;

public class DefaultQueryParams implements QueryParams {

    // You could update this field
    public static boolean isNeedQueryField = true;
    private final Class targetClass;

    public DefaultQueryParams(Class targetClass) {
        this.targetClass = targetClass;
    }

    @Nullable
    @Override
    public Object getFieldQuery(Class<?> cls, String fieldName) {
        return null;
    }

    @Override
    public boolean isHideField(Field field, String fieldName) {
        return false;
    }

    @Override
    public boolean isNeedQueryField() {
        return isNeedQueryField;
    }

    @Override
    public Class getTargetClass() {
        return targetClass;
    }

}
