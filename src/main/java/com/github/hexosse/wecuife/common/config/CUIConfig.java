package com.github.hexosse.wecuife.common.config;

import com.github.hexosse.wecuife.CUIController;
import com.github.hexosse.wecuife.common.Colour;
import com.github.hexosse.wecuife.common.McColor;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class CUIConfig extends Configuration
{
	public static final String VERSION = "1.0";

	public static final String CATEGORY_MOD = "WorldEditCuiFe";
	public static final String CATEGORY_COLORS = "colors";


	// Mod
	private boolean debugMode;
	//private boolean ignoreUpdates;
	private boolean enable;

	// Colors
	private Colour cuboidGridColor;
	private Colour cuboidEdgeColor;
	private Colour cuboidFirstPointColor;
	private Colour cuboidSecondPointColor;
	private Colour polyGridColor;
	private Colour polyEdgeColor;
	private Colour polyPointColor;
	private Colour ellipsoidGridColor;
	private Colour ellipsoidPointColor;
	private Colour cylinderGridColor;
	private Colour cylinderEdgeColor;
	private Colour cylinderPointColor;



	public CUIConfig()
	{

		super(new File(Minecraft.getMinecraft().mcDataDir.toString() + "/config/" + CUIController.MODID + ".cfg"), VERSION);

		if(hasChanged()) save();
	}

	public void load()
	{
		super.load();

		this.reload();
		this.initialise();
	}

	public void save()
	{
		super.save();
		this.reload();
		this.initialise();
	}

	public Property get(String key)
	{
		//      Mod
		if(key=="debugMode")				return this.get(CATEGORY_MOD, "debugMode", false);
		//if(key=="ignoreUpdates")			return this.get(CATEGORY_MOD, "ignoreUpdates", false);
		if(key=="enable")					return this.get(CATEGORY_MOD, "enable", true).setLanguageKey("com.github.hexosse.wecuife.keys.toggle");

		//      Colors
		String[] colors = new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
		if(key=="cuboidFirstPointColor")	return this.get(CATEGORY_COLORS, "cuboidFirstPointColor", McColor.GREEN.getCode(),"", Property.Type.COLOR).setLanguageKey("colour.cuboidpoint1").setValidValues(colors);
		if(key=="cuboidSecondPointColor")	return this.get(CATEGORY_COLORS, "cuboidSecondPointColor", McColor.DARK_BLUE.getCode(),"", Property.Type.COLOR).setLanguageKey("colour.cuboidpoint2").setValidValues(colors);
		if(key=="cuboidEdgeColor")			return this.get(CATEGORY_COLORS, "cuboidEdgeColor", McColor.DARK_RED.getCode(),"", Property.Type.COLOR).setLanguageKey("colour.cuboidgrid").setValidValues(colors);
		if(key=="cuboidGridColor")			return this.get(CATEGORY_COLORS, "cuboidGridColor", McColor.DARK_RED.getCode(),"", Property.Type.COLOR).setLanguageKey("colour.cuboidedge").setValidValues(colors);
		if(key=="polyPointColor")			return this.get(CATEGORY_COLORS, "polyPointColor", McColor.AQUA.getCode(),"", Property.Type.COLOR).setLanguageKey("colour.polypoint").setValidValues(colors);
		if(key=="polyEdgeColor")			return this.get(CATEGORY_COLORS, "polyEdgeColor", McColor.DARK_RED.getCode(),"", Property.Type.COLOR).setLanguageKey("colour.polyedge").setValidValues(colors);
		if(key=="polyGridColor")			return this.get(CATEGORY_COLORS, "polyGridColor", McColor.DARK_RED.getCode(),"", Property.Type.COLOR).setLanguageKey("colour.polygrid").setValidValues(colors);
		if(key=="ellipsoidPointColor")		return this.get(CATEGORY_COLORS, "ellipsoidPointColor", McColor.DARK_GREEN.getCode(),"", Property.Type.COLOR).setLanguageKey("colour.ellipsoidpoint").setValidValues(colors);
		if(key=="ellipsoidGridColor")		return this.get(CATEGORY_COLORS, "ellipsoidGridColor", McColor.DARK_RED.getCode(),"", Property.Type.COLOR).setLanguageKey("colour.ellipsoidgrid").setValidValues(colors);
		if(key=="cylinderPointColor")		return this.get(CATEGORY_COLORS, "cylinderPointColor", McColor.LIGHT_PURPLE.getCode(),"", Property.Type.COLOR).setLanguageKey("colour.cylinderpoint").setValidValues(colors);
		if(key=="cylinderEdgeColor")		return this.get(CATEGORY_COLORS, "cylinderEdgeColor", McColor.DARK_RED.getCode(),"", Property.Type.COLOR).setLanguageKey("colour.cylinderedge").setValidValues(colors);
		if(key=="cylinderGridColor")		return this.get(CATEGORY_COLORS, "cylinderGridColor", McColor.DARK_RED.getCode(),"", Property.Type.COLOR).setLanguageKey("colour.cylindergrid").setValidValues(colors);

		return null;
	}

	public void reload()
	{
		// Mod
		debugMode 				= get("debugMode").getBoolean();
		//ignoreUpdates			= get("ignoreUpdates").getBoolean();
		enable					= get("enable").getBoolean();

		// Colors
		cuboidFirstPointColor	= new Colour(McColor.get(get("cuboidFirstPointColor").getString()).getForeground()+"CC");
		cuboidSecondPointColor	= new Colour(McColor.get(get("cuboidSecondPointColor").getString()).getForeground()+"CC");
		cuboidEdgeColor			= new Colour(McColor.get(get("cuboidEdgeColor").getString()).getForeground()+"CC");
		cuboidGridColor			= new Colour(McColor.get(get("cuboidGridColor").getString()).getForeground()+"CC");
		polyPointColor			= new Colour(McColor.get(get("polyPointColor").getString()).getForeground()+"CC");
		polyEdgeColor			= new Colour(McColor.get(get("polyEdgeColor").getString()).getForeground()+"CC");
		polyGridColor			= new Colour(McColor.get(get("polyGridColor").getString()).getForeground()+"CC");
		ellipsoidPointColor		= new Colour(McColor.get(get("ellipsoidPointColor").getString()).getForeground()+"CC");
		ellipsoidGridColor		= new Colour(McColor.get(get("ellipsoidGridColor").getString()).getForeground()+"CC");
		cylinderPointColor		= new Colour(McColor.get(get("cylinderPointColor").getString()).getForeground()+"CC");
		cylinderEdgeColor		= new Colour(McColor.get(get("cylinderEdgeColor").getString()).getForeground()+"CC");
		cylinderGridColor		= new Colour(McColor.get(get("cylinderGridColor").getString()).getForeground()+"CC");

		//      Mod
		List<String> modPropOrder = new ArrayList<String>();
		modPropOrder.add("enabled");
		setCategoryPropertyOrder(CATEGORY_MOD, modPropOrder);

		//      Colors
		List<String> colorsPropOrder = new ArrayList<String>();
		colorsPropOrder.add("cuboidFirstPointColor");
		colorsPropOrder.add("cuboidSecondPointColor");
		colorsPropOrder.add("cuboidEdgeColor");
		colorsPropOrder.add("cuboidGridColor");
		colorsPropOrder.add("polyPointColor");
		colorsPropOrder.add("polyEdgeColor");
		colorsPropOrder.add("polyGridColor");
		colorsPropOrder.add("ellipsoidPointColor");
		colorsPropOrder.add("ellipsoidGridColor");
		colorsPropOrder.add("cylinderPointColor");
		colorsPropOrder.add("cylinderEdgeColor");
		colorsPropOrder.add("cylinderGridColor");
		setCategoryPropertyOrder(CATEGORY_COLORS, colorsPropOrder);
	}

	/**
	 * Copies the default config file to the proper directory if it does not
	 * exist. It then reads the file and sets each variable to the proper value.
	 */
	public void initialise()
	{
		this.cuboidFirstPointColor  = Colour.setDefault(this.cuboidFirstPointColor,  McColor.get(get("cuboidFirstPointColor").getDefault()).getForeground());
		this.cuboidSecondPointColor = Colour.setDefault(this.cuboidSecondPointColor, McColor.get(get("cuboidSecondPointColor").getDefault()).getForeground());
		this.cuboidEdgeColor        = Colour.setDefault(this.cuboidEdgeColor,        McColor.get(get("cuboidEdgeColor").getDefault()).getForeground());
		this.cuboidGridColor        = Colour.setDefault(this.cuboidGridColor,        McColor.get(get("cuboidGridColor").getDefault()).getForeground());
		this.polyPointColor         = Colour.setDefault(this.polyPointColor,         McColor.get(get("polyPointColor").getDefault()).getForeground());
		this.polyEdgeColor          = Colour.setDefault(this.polyEdgeColor,          McColor.get(get("polyEdgeColor").getDefault()).getForeground());
		this.polyGridColor          = Colour.setDefault(this.polyGridColor,          McColor.get(get("polyGridColor").getDefault()).getForeground());
		this.ellipsoidPointColor    = Colour.setDefault(this.ellipsoidPointColor,    McColor.get(get("ellipsoidPointColor").getDefault()).getForeground());
		this.ellipsoidGridColor     = Colour.setDefault(this.ellipsoidGridColor,     McColor.get(get("ellipsoidGridColor").getDefault()).getForeground());
		this.cylinderPointColor     = Colour.setDefault(this.cylinderPointColor,     McColor.get(get("cylinderPointColor").getDefault()).getForeground());
		this.cylinderEdgeColor      = Colour.setDefault(this.cylinderEdgeColor,		 McColor.get(get("cylinderEdgeColor").getDefault()).getForeground());
		this.cylinderGridColor      = Colour.setDefault(this.cylinderGridColor,      McColor.get(get("cylinderGridColor").getDefault()).getForeground());

		com.github.hexosse.wecuife.render.LineColour.CUBOIDPOINT1.setColour(this.cuboidFirstPointColor);
		com.github.hexosse.wecuife.render.LineColour.CUBOIDPOINT2.setColour(this.cuboidSecondPointColor);
		com.github.hexosse.wecuife.render.LineColour.CUBOIDBOX.setColour(this.cuboidEdgeColor);
		com.github.hexosse.wecuife.render.LineColour.CUBOIDGRID.setColour(this.cuboidGridColor);
		com.github.hexosse.wecuife.render.LineColour.POLYPOINT.setColour(this.polyPointColor);
		com.github.hexosse.wecuife.render.LineColour.POLYBOX.setColour(this.polyEdgeColor);
		com.github.hexosse.wecuife.render.LineColour.POLYGRID.setColour(this.polyGridColor);
		com.github.hexosse.wecuife.render.LineColour.ELLIPSOIDCENTRE.setColour(this.ellipsoidPointColor);
		com.github.hexosse.wecuife.render.LineColour.ELLIPSOIDGRID.setColour(this.ellipsoidGridColor);
		com.github.hexosse.wecuife.render.LineColour.CYLINDERCENTRE.setColour(this.cylinderPointColor);
		com.github.hexosse.wecuife.render.LineColour.CYLINDERBOX.setColour(this.cylinderEdgeColor);
		com.github.hexosse.wecuife.render.LineColour.CYLINDERGRID.setColour(this.cylinderGridColor);
	}

	public boolean isEnable()
	{
		return this.enable;
	}

	public void setEnable(boolean enable)
	{
		get("enable").set(enable);
		this.enable = get("enable").getBoolean();
	}

	public boolean isDebugMode()
	{
		return this.debugMode;
	}
}
