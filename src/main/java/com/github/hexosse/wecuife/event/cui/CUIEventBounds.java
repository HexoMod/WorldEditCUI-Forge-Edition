package com.github.hexosse.wecuife.event.cui;

import com.github.hexosse.wecuife.CUIController;
import com.github.hexosse.wecuife.event.CUIEvent;
import com.github.hexosse.wecuife.event.CUIEventType;

/**
 * Called when resize event is received
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIEventBounds extends CUIEvent
{
	
	public CUIEventBounds(String[] args)
	{
		super(args);
	}
	
	@Override
	public CUIEventType getEventType()
	{
		return CUIEventType.MINMAX;
	}
	
	@Override
	public String raise()
	{
		int min = this.getInt(0);
		int max = this.getInt(1);
		CUIController.getSelection().setMinMax(min, max);
		
		CUIController.getDebugger().debug("Expanding/contracting selection.");
		
		return null;
	}
}
