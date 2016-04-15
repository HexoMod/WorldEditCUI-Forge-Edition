package com.github.hexosse.wecuife.event;

import com.github.hexosse.wecuife.CUIController;
import com.github.hexosse.wecuife.exceptions.InitialisationException;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Adam Mummery-Smith
 */
public class CUIEventDispatcher
{
	private Map<String, Constructor<? extends CUIEvent>> eventConstructors = new HashMap<String, Constructor<? extends CUIEvent>>();

	public CUIEventDispatcher()
	{
	}

	public void initialise() throws InitialisationException
	{
		for (CUIEventType eventType : CUIEventType.values())
		{
			try
			{
				Class<? extends CUIEvent> eventClass = eventType.getEventClass();
				Constructor<? extends CUIEvent> ctor = eventClass.getDeclaredConstructor(String[].class);

				this.eventConstructors.put(eventType.getKey(), ctor);
			}
			catch (NoSuchMethodException ex)
			{
				CUIController.getDebugger().debug("Error getting constructor for event " + eventType.getKey());
			}
		}
	}

	public void raiseEvent(CUIEventArgs eventArgs)
	{
		try
		{
			Constructor<? extends CUIEvent> eventCtor = this.eventConstructors.get(eventArgs.getType());
			CUIEvent event = eventCtor.newInstance((Object)eventArgs.getParams());
			event.prepare();

			String response = event.raise();
			if (response != null)
				this.handleEventResponse(response);
		}
		catch (NullPointerException ex) {
			CUIController.getDebugger().debug("No such event " + eventArgs.getType());
		}
		catch (Exception ex) {
			ex.printStackTrace();
			CUIController.getDebugger().debug("Error raising event " + eventArgs.getType() + ": " + ex.getClass().getSimpleName() + " " + ex.getMessage());
		}
	}

	private void handleEventResponse(String response)
	{
	}
}
