package com.github.hexosse.wecuife.event.cui;

import com.github.hexosse.wecuife.CUIController;
import com.github.hexosse.wecuife.event.CUIEvent;
import com.github.hexosse.wecuife.event.CUIEventType;

/**
 * Called when ellipsoid event is received
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIEventEllipsoid extends CUIEvent
{
	
	public CUIEventEllipsoid(String[] args)
	{
		super(args);
	}
	
	@Override
	public CUIEventType getEventType()
	{
		return CUIEventType.ELLIPSOID;
	}
	
	@Override
	public String raise()
	{
		
		int id = this.getInt(0);
		
		if (id == 0)
		{
			int x = this.getInt(1);
			int y = this.getInt(2);
			int z = this.getInt(3);
			CUIController.getSelection().setEllipsoidCenter(x, y, z);
		}
		else if (id == 1)
		{
			double x = this.getDouble(1);
			double y = this.getDouble(2);
			double z = this.getDouble(3);
			CUIController.getSelection().setEllipsoidRadii(x, y, z);
		}

		CUIController.getDebugger().debug("Setting centre/radius");
		
		return null;
	}
}
