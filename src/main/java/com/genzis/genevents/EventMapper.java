package com.genzis.genevents;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The EventMapper class to register and deal with event listeners
 * @author Kavin Subramanian
 *
 */
public class EventMapper 
{
    private static Map<String,Map<Method,Object>> event_listeners = new LinkedHashMap<String,Map<Method,Object>>();
    
    /**
     * Private constructor to prevent instantiation
     */
    private EventMapper() {
    	
    }
    
    /**
     * Register event listener methods in an object
     * @param object the object which contains event lister methods to be registered
     */
    public static void register(Object object) {
    	/**
    	 * Iterate through all object methods
    	 */
    	for(Method method : object.getClass().getDeclaredMethods()) {
    		/**
    		 * Check if the object is annotated with out Listener annotation
    		 */
    		if(method.isAnnotationPresent(Listener.class)) {
    			method.setAccessible(true);
    			/**
    			 * Get event type and register it
    			 */
    			String type = method.getAnnotation(Listener.class).type();
    			Map<Method,Object> methods = event_listeners.containsKey(type) ? event_listeners.get(type) : new LinkedHashMap<Method,Object>();
    			methods.put(method,object);
    			event_listeners.put(type, methods);
    		}
    	}
    }
    
    /**
     * Unregister all listeners for a event type
     * @param type The event type
     */
    public static void unregister(String type) {
    	event_listeners.remove(type);
    }
    
    /**
     * Get list of event listeners for the particular type
     * @param type The event type
     * @return List of event listeners
     */
    public static Map<Method,Object> getListeners(String type) {
    	return event_listeners.containsKey(type) ? event_listeners.get(type) : new LinkedHashMap<Method,Object>();
    }
}