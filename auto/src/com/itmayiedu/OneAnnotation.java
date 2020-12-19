
package com.itmayiedu;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = { ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface OneAnnotation {

	int beanId() default 0;

	String className() default "";

	String[]arrays();

}

@OneAnnotation(beanId = 20, className = "com.itmayiedu", arrays = { "0" })
class AutoDemo01 {
	public void add() {

	}

}
