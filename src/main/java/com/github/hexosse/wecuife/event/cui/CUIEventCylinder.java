package com.github.hexosse.wecuife.event.cui;

import com.github.hexosse.wecuife.CUIController;
import com.github.hexosse.wecuife.event.CUIEvent;
import com.github.hexosse.wecuife.event.CUIEventType;

/**
 * Called when cylinder event is received
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIEventCylinder extends CUIEvent
{
	
	public CUIEventCylinder(String[] args)
	{
		super(args);
	}
	
	@Override
	public CUIEventType getEventType()
	{
		return CUIEventType.CYLINDER;
	}
	
	@Override
	public String raise()
	{
		
		int x = this.getInt(0);
		int y = this.getInt(1);
		int z = this.getInt(2);
		double radX = this.getDouble(3);
		double radZ = this.getDouble(4);

		CUIController.getSelection().setCylinderCenter(x, y, z);
		CUIController.getSelection().setCylinderRadius(radX, radZ);

		CUIController.getDebugger().debug("Setting centre/radius");
		
		return null;
	}
}
