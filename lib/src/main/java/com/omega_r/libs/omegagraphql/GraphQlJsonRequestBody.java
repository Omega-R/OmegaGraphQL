package com.omega_r.libs.omegagraphql;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import okhttp3.MediaType;

public class GraphQlJsonRequestBody extends GraphQlRequestBody {

    private static final MediaType sMediaType = MediaType.parse("application/json");

    public GraphQlJsonRequestBody(Map<String, Object> paramsList, List<Object> objectList,
                                  String objectName, boolean isNeedQueryField) {
        super(paramsList, objectList, objectName, isNeedQueryField);
    }

    public static GraphQlJsonRequestBody from(Class targetClass) {
        return from(new DefaultQueryParams(targetClass));
    }

    public static GraphQlJsonRequestBody from(QueryParams query) {
        GraphQlJsonRequestBodyBuilder builder = new GraphQlJsonRequestBodyBuilder(null, query);
        return builder.from(query.getTargetClass()).build();
    }

    @Override
    protected String getBody() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\"query\": \"");
        String content = super.getBody();
        content = content.replaceAll("\"", "\\\\\"");
        stringBuilder.append(content);
        stringBuilder.append("\" }");
        return stringBuilder.toString();
    }

    @Override
    public MediaType contentType() {
        return sMediaType;
    }




}
