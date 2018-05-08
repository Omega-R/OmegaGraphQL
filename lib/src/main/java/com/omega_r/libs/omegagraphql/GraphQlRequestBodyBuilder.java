package com.omega_r.libs.omegagraphql;

import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;
import com.omega_r.libs.omegagraphql.annotations.GraphQlConst;
import com.omega_r.libs.omegagraphql.annotations.GraphQlField;
import com.omega_r.libs.omegagraphql.annotations.GraphQlObject;
import com.omega_r.libs.omegagraphql.annotations.GraphQlQuery;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphQlRequestBodyBuilder {

    protected final String objectName;
    @Nullable
    protected final QueryParams query;
    protected final Map<String, Object> paramsList = new HashMap<>();
    protected final List<Object> objectList = new ArrayList<>();

    public GraphQlRequestBodyBuilder(String objectName) {
        this(objectName, null);
    }

    GraphQlRequestBodyBuilder(String objectName, @Nullable QueryParams query) {
        this.objectName = objectName;
        this.query = query;
    }

    public GraphQlRequestBodyBuilder param(String key, Object value) {
        paramsList.put(key, value);
        return this;
    }

    public GraphQlRequestBodyBuilder field(String name) {
        objectList.add(name);
        return this;
    }

    public GraphQlRequestBodyBuilder object(GraphQlRequestBody object) {
        objectList.add(object);
        return this;
    }

    public GraphQlRequestBodyBuilder from(Class<?> cls) {

            Set<Field> declaredFields = getFields(cls);

            for (Field field : declaredFields) {

                if (field.isAnnotationPresent(GraphQlField.class)) {
                    GraphQlField graphQlField = field.getAnnotation(GraphQlField.class);
                    field(graphQlField.value());
                } else {
                    String objectName = null;
                    if (field.isAnnotationPresent(GraphQlObject.class)) {
                        objectName = field.getAnnotation(GraphQlObject.class).value();
                    } else if (field.isAnnotationPresent(SerializedName.class)) {
                        objectName = field.getAnnotation(SerializedName.class).value();
                    }
                    if (objectName != null) {
                        Class<?> type = field.getType();

                        Class<?> fieldClass;
                        if (type.isAssignableFrom(List.class)) {
                            ParameterizedType genericType = (ParameterizedType) field.getGenericType();
                            fieldClass = (Class<?>) genericType.getActualTypeArguments()[0];
                        } else if (type.isArray()) {
                            fieldClass = type.getComponentType();
                        } else {
                            fieldClass = type;
                        }
                        //noinspection unchecked
                        if (query == null || !query.isHideField(field, objectName)) {
                            GraphQlRequestBodyBuilder builder = new GraphQlRequestBodyBuilder(objectName, query)
                                    .from(fieldClass);

                            if (field.isAnnotationPresent(GraphQlConst.class)) {
                                GraphQlConst graphQlConst = field.getAnnotation(GraphQlConst.class);
                                for (GraphQlConst.Param param : graphQlConst.value()) {
                                    builder.param(param.key(), param.value());
                                }
                            }

                            applyQuery(type, objectName, builder);
                            object(builder.build());
                        }

                    }
                }
            }
            if (cls != null && cls.isAnnotationPresent(GraphQlConst.class)) {
                GraphQlConst graphQlConst = cls.getAnnotation(GraphQlConst.class);
                for (GraphQlConst.Param param : graphQlConst.value()) {
                    param(param.key(), param.value());
                }
            }

        return this;
    }

    private void applyQuery(Class<?> type, String fieldName, GraphQlRequestBodyBuilder builder) {
        if (query != null) {
            //noinspection unchecked
            Object query = this.query.getFieldQuery(type, fieldName);
            if (query != null) {
                Class<?> queryClass = query.getClass();
                Set<Field> declaredQueryFields = getFields(queryClass);

                for (Field queryField : declaredQueryFields) {
                    if (queryField.isAnnotationPresent(GraphQlQuery.class)) {
                        GraphQlQuery graphQlQuery = queryField.getAnnotation(GraphQlQuery.class);
                        queryField.setAccessible(true);
                        try {
                            Object queryObject = queryField.get(query);
                            if (queryObject != null) {
                                builder.param(graphQlQuery.value(), queryObject);
                            }
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }

    private Set<Field> getFields(Class cls) {
        Set<Field> fields = new HashSet<>();
        findAllField(cls, fields);
        return fields;
    }

    private void findAllField(@Nullable Class cls, Set<Field> fieldSet) {
        if (cls == null) return;

        fieldSet.addAll(Arrays.asList(cls.getDeclaredFields()));
        Class superclass = cls.getSuperclass();
        if (superclass != null) {
            findAllField(superclass, fieldSet);
        }
    }

    boolean isNeedQueryField() {
        return query == null ? DefaultQueryParams.isNeedQueryField : query.isNeedQueryField();
    }

    public GraphQlRequestBody build() {
        return new GraphQlRequestBody(paramsList, objectList, objectName, isNeedQueryField());
    }

}
