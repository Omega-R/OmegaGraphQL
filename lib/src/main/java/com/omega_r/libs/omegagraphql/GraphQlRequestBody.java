package com.omega_r.libs.omegagraphql;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import okio.BufferedSink;

public class GraphQlRequestBody extends RequestBody {

    private static final MediaType sMediaType = MediaType.parse("text/plain");

    private final Map<String, Object> paramsList;
    private final List<Object> objectList;
    private final String objectName;
    private final boolean isNeedQueryField;

    public GraphQlRequestBody(Map<String, Object> paramsList, List<Object> objectList,
                              String objectName, boolean isNeedQueryField) {
        this.paramsList = paramsList;
        this.objectList = objectList;
        this.objectName = objectName;
        this.isNeedQueryField = isNeedQueryField;
    }

    public static GraphQlRequestBody from(Class targetClass) {
        return from(new DefaultQueryParams(targetClass));
    }

    public static GraphQlRequestBody from(QueryParams query) {
        GraphQlRequestBodyBuilder builder = new GraphQlRequestBodyBuilder(null, query);

        return builder.from(query.getTargetClass()).build();
    }

    protected String getBody() {
        StringBuilder stringBuilder = new StringBuilder();
        if (isNeedQueryField) stringBuilder.append("query ");
        appendContent(stringBuilder);
        return stringBuilder.toString();
    }

    private void appendContent(StringBuilder stringBuilder) {

        if (!TextUtils.isEmpty(objectName)) {
            stringBuilder.append(objectName);

            if (!paramsList.isEmpty()) {
                stringBuilder.append(" (");
                for (String key : paramsList.keySet()) {
                    stringBuilder
                            .append(" ")
                            .append(key)
                            .append(": ");
                    Object value = paramsList.get(key);
                    if (value instanceof String) {
                        stringBuilder.append("\"")
                                .append(value)
                                .append("\"");
                    } else {
                        stringBuilder.append(value);
                    }
                }
                stringBuilder.append(" ) ");
            }
        }

        if (!objectList.isEmpty()) {
            stringBuilder.append(" {");
            for (Object object : objectList) {
                stringBuilder.append(" ");
                if (object instanceof String) {
                    stringBuilder.append(object);
                } else if (object instanceof GraphQlRequestBody) {
                    ((GraphQlRequestBody) object).appendContent(stringBuilder);
                } else {
                    stringBuilder.append(object.toString());
                }
            }
            stringBuilder.append(" }");
        }

    }

    @Override
    public MediaType contentType() {
        return sMediaType;
    }

    @Override
    public void writeTo(@NonNull BufferedSink sink) throws IOException {
        sink.write(getBody().getBytes(Util.UTF_8));
    }

}
