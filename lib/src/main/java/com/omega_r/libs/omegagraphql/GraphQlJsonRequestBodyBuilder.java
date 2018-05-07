package com.omega_r.libs.omegagraphql;

import android.support.annotation.Nullable;

public class GraphQlJsonRequestBodyBuilder extends GraphQlRequestBodyBuilder {

    public GraphQlJsonRequestBodyBuilder(String objectName) {
        super(objectName);
    }

    GraphQlJsonRequestBodyBuilder(String objectName, @Nullable QueryParams query) {
        super(objectName, query);
    }

    @Override
    public GraphQlJsonRequestBodyBuilder from(Class<?> cls) {
        super.from(cls);
        return this;
    }

    @Override
    public GraphQlJsonRequestBody build() {
        return new GraphQlJsonRequestBody(paramsList, objectList, objectName, isNeedQueryField());
    }
}
