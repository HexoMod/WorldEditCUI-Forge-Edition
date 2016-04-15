package com.github.hexosse.wecuife.event.cui;

import com.github.hexosse.wecuife.event.CUIEvent;
import com.github.hexosse.wecuife.event.CUIEventType;

/**
 * Called when update event is received
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIEventUpdate extends CUIEvent
{
	
	public CUIEventUpdate(String[] args)
	{
		super(args);
	}
	
	@Override
	public CUIEventType getEventType()
	{
		return CUIEventType.UPDATE;
	}
	
	@Override
	public String raise()
	{
		return null;
	}
}
