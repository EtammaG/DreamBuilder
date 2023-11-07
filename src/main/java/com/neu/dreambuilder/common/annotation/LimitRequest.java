package com.neu.dreambuilder.common.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LimitRequest {

    int timesInAUnit() default 1;

    TimeUnit unit() default TimeUnit.MILLISECONDS;

}