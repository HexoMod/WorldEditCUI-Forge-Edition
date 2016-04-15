package com.github.hexosse.wecuife.event.cui;

import com.github.hexosse.wecuife.CUIController;
import com.github.hexosse.wecuife.event.CUIEventType;

/**
 * Called when poly point event is received
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIEventPoint2D extends CUIEventPoint3D
{
	
	public CUIEventPoint2D(String[] args)
	{
		super(args);
	}
	
	@Override
	public CUIEventType getEventType()
	{
		return CUIEventType.POINT2D;
	}
	
	@Override
	public String raise()
	{
		
		int id = this.getInt(0);
		int x = this.getInt(1);
		int z = this.getInt(2);
		@SuppressWarnings("unused")
		int regionSize = this.getInt(3);
		CUIController.getSelection().setPolygonPoint(id, x, z);

		CUIController.getDebugger().debug("Setting point2d #" + id);
		
		return null;
	}
}
