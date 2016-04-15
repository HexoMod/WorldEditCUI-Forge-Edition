package com.github.hexosse.wecuife.event.cui;

import com.github.hexosse.wecuife.CUIController;
import com.github.hexosse.wecuife.event.CUIEvent;
import com.github.hexosse.wecuife.event.CUIEventType;

/**
 * Called when polygon event is received
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIEventPolygon extends CUIEvent
{
	
	public CUIEventPolygon(String[] args)
	{
		super(args);
	}
	
	@Override
	public CUIEventType getEventType()
	{
		return CUIEventType.POLYGON;
	}
	
	@Override
	public String raise()
	{
		final int[] vertexIds = new int[this.args.length];
		for (int i = 0; i < this.args.length; ++i)
		{
			vertexIds[i] = this.getInt(i);
		}

		CUIController.getSelection().addPolygon(vertexIds);
		//CUIController.getDebugger().debug("Setting point #" + id);
		
		return null;
	}
}
