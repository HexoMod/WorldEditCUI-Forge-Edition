package com.github.hexosse.wecuife.render;

import com.github.hexosse.wecuife.CUIController;
import com.github.hexosse.wecuife.exceptions.InitialisationException;
import com.github.hexosse.wecuife.render.region.BaseRegion;
import com.github.hexosse.wecuife.render.region.RegionType;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Adam Mummery-Smith
 */
public class CUISelectionProvider
{
	private Map<String, Constructor<? extends BaseRegion>> regionConstructors = new HashMap<String, Constructor<? extends BaseRegion>>();

	public CUISelectionProvider()
	{
	}

	public void initialise() throws InitialisationException
	{
		for (RegionType regionType : RegionType.values())
		{
			try
			{
				Class<? extends BaseRegion> eventClass = regionType.getRegionClass();
				Constructor<? extends BaseRegion> ctor = eventClass.getDeclaredConstructor();

				this.regionConstructors.put(regionType.getKey(), ctor);
			}
			catch (NoSuchMethodException ex)
			{
				CUIController.getDebugger().debug("Error getting constructor for region type " + regionType.getKey());
			}
		}
	}
	
	public BaseRegion createSelection(String key)
	{
		try
		{
			CUIController.getDebugger().debug("Create selection type : " + key);

			Constructor<? extends BaseRegion> regionCtor = this.regionConstructors.get(key);
			BaseRegion region = regionCtor.newInstance();
			return region;
		}
		catch (NullPointerException ex)
		{
			CUIController.getDebugger().debug("No such selection type " + key);
		}
		catch (Exception ex)
		{
			CUIController.getDebugger().debug("Error creation " + key + " selection: " + ex.getClass().getSimpleName() + " " + ex.getMessage());
		}

		return null;
	}
}
