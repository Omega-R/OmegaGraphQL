[![](https://jitpack.io/v/Omega-R/OmegaGraphQl.svg)](https://jitpack.io/#Omega-R/OmegaGraphQl)
[![GitHub license](https://img.shields.io/github/license/mashape/apistatus.svg)](https://opensource.org/licenses/MIT)

# OmegaGraphQl
This is a GraphQL Java implementation.

# Installation
To get a Git project into your build:

**Step 1.** Add the JitPack repository to your build file
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
**Step 2.** Add the dependency
```
dependencies {
    compile 'com.github.Omega-R:OmegaGraphQl:0.0.2'
}
```
# Usage

```
public class EventData {

    @Expose
    @SerializedName("event")
    private Event event;
    
}
```

```
public class Event {

    @Expose
    @SerializedName("address")
    private String address;

    @Expose
    @SerializedName("currency")
    private String currency;

    @Expose
    @SerializedName("title")
    private String title;

    public static class Query implements QueryParams {

        @GraphQlQuery("id")
        private String id;

        public Query(String id) {
            this.id = id;
        }

        @Nullable
        @Override
        public Object getFieldQuery(Class<?> cls, String fieldName) {
            if (cls.equals(Event.class)) {
                return this;
            }
            return null;
        }

        @Override
        public boolean isHideField(Field field, String fieldName) {
            return false;
        }

        @Override
        public boolean isNeedQueryField() {
            return true;
        }

        @Override
        public Class getTargetClass() {
            return EventData.class;
        }
    }
}
```

Retrofit Api
```
public interface RetrofitApi {

    @POST("https://www.universe.com/graphql")
    Call<Data> requestEventData(@Body GraphQlJsonRequestBody queryObject);

}
```

Make request

```
GraphQlJsonRequestBody requestBody = GraphQlJsonRequestBody.from(new Event.Query("5879ad8f6672e70036d58ba5"));
getRetrofitApi().requestEventData(requestBody).enqueue(....);
```



# License
```
The MIT License

Copyright 2018 Omega-R

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and 
associated documentation files (the "Software"), to deal in the Software without restriction, 
including without limitation the rights to use, copy, modify, merge, publish, distribute, 
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT 
LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE 
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```
