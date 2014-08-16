package com.genzis.genevents;

import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Kavin Subramanian
 *
 * Annotation used to annotate event listeners.
 *
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Listener {
	
	/**
	 * The user defined event type
	 * @return The event type
	 */
	String type() default "";
	
}