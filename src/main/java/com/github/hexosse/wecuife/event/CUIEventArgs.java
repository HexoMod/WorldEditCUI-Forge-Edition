package com.github.hexosse.wecuife.event;

import com.github.hexosse.wecuife.CUIController;
import com.google.common.base.Joiner;

/**
 * CUI communication event
 * Called when a CUI event is sent from the server.
 * 
 * @author lahwran
 * @author yetanotherx
 *
 */
public class CUIEventArgs
{
	private String type;
	private String[] params;
	
	public CUIEventArgs(String type, String[] params)
	{
		this.type = type;
		this.params = params;

		CUIController.getDebugger().debug("CUI Event (" + type + ") - Params: " + Joiner.on(", ").join(params));
	}
	
	public int getInt(int index)
	{
		return (int)Float.parseFloat(this.params[index]);
	}
	
	public String getString(int index)
	{
		return this.params[index];
	}
	
	public String[] getParams()
	{
		return this.params;
	}
	
	public String getType()
	{
		return this.type;
	}
}
