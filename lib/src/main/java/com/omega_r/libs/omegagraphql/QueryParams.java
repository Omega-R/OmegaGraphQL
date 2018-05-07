package com.omega_r.libs.omegagraphql;

import android.support.annotation.Nullable;

import java.lang.reflect.Field;

public interface QueryParams {

    @Nullable
    Object getFieldQuery(Class<?> cls, String fieldName);

    boolean isHideField(Field field, String fieldName);

    boolean isNeedQueryField();

    Class getTargetClass();

}
