package com.genzis.genevents;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * The Event class used to define and trigger a new event.
 * 
 * @author Kavin Subramanian
 *
 */
public class Event {
	private String type;
	private Map<String,Object> extras;
	
	/**
	 * Construct an event with user defined event type
	 * @param type The user defined event type
	 */
	public Event(String type) {
		this.type = type;
	}
	
	/**
	 * Construct an event with user defined event type and extra datas.
	 * @param type The user defined event type
	 * @param extras Any extra data you wish to send
	 */
	public Event(String type,Map<String,Object> extras) {
		this.type = type;
		this.extras = extras;
	}
	
	/**
	 * Get the event type
	 * @return The event type
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * Get any extra data associated with this event
	 * @return The Extra data
	 */
	public Map<String,Object> getExtras() {
		/**
		 * Check if the extras is null
		 */
		if(this.extras == null) {
			this.extras = new LinkedHashMap<String,Object>();
		}
		return this.extras;
	}
	
	/**
	 * Trigger the event to notify all listening methods
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public void trigger() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		/**
		 * Get all listeners and iterate over them
		 */
		Map<Method,Object> listeners = EventMapper.getListeners(getType());
		for(Iterator<Map.Entry<Method,Object>> it = listeners.entrySet().iterator(); it.hasNext();) {
			Map.Entry<Method,Object> entry = it.next();
			
			/**
			 * Invoke the listener method.
			 */
			entry.getKey().invoke(entry.getValue(), this);
		}
	}
}