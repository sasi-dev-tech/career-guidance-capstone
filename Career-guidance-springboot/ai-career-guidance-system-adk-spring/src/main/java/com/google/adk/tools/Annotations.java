package com.google.adk.tools;

public class Annotations {

    @java.lang.annotation.Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
    @java.lang.annotation.Target({java.lang.annotation.ElementType.TYPE})
    public @interface Schema {
        String description() default "";
    }
}
