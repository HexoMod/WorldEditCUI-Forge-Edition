package com.github.hexosse.wecuife.event.cui;

import com.github.hexosse.wecuife.CUIController;
import com.github.hexosse.wecuife.event.CUIEvent;
import com.github.hexosse.wecuife.event.CUIEventType;
import com.github.hexosse.wecuife.render.region.BaseRegion;

/**
 * Called when selection event is received
 * 
 * @author lahwran
 * @author yetanotherx
 */
public class CUIEventSelection extends CUIEvent
{
	public CUIEventSelection(String[] args)
	{
		super(args);
	}
	
	@Override
	public CUIEventType getEventType()
	{
		return CUIEventType.SELECTION;
	}
	
	@Override
	public String raise()
	{
		String key = this.getString(0);
		BaseRegion selection = CUIController.getSelectionProvider().createSelection(key);
		
		if (selection != null)
			selection.initialise();

		CUIController.setSelection(selection);
		CUIController.getDebugger().debug("Received selection event, initalizing new region instance.");
		
		return null;
	}
}
