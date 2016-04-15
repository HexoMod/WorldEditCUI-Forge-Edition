package com.github.hexosse.wecuife.event.cui;

import com.github.hexosse.wecuife.CUIController;
import com.github.hexosse.wecuife.event.CUIEvent;
import com.github.hexosse.wecuife.event.CUIEventType;

/**
 * Called when point event is received
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIEventPoint3D extends CUIEvent
{
	public CUIEventPoint3D(String[] args)
	{
		super(args);
	}
	
	@Override
	public CUIEventType getEventType()
	{
		return CUIEventType.POINT;
	}
	
	@Override
	public String raise()
	{
		int id = this.getInt(0);
		double x = this.getDouble(1);
		double y = this.getDouble(2);
		double z = this.getDouble(3);

		CUIController.getSelection().setCuboidPoint(id, x, y, z);
		CUIController.getDebugger().debug("Setting point #" + id);
		
		return null;
	}
}
