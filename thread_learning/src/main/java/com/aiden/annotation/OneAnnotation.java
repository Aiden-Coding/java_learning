package com.aiden.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//使用@interface 定义注解。
@Target(value = { ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface OneAnnotation {
  int beanId() default 0;
  String className() default "";
  String[]arrays();
}
