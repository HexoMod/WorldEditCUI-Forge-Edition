package com.github.hexosse.wecuife.common.debug;

import com.github.hexosse.wecuife.CUIController;
import com.github.hexosse.wecuife.exceptions.InitialisationException;
import com.github.hexosse.wecuife.util.ConsoleLogFormatter;
import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Debugging helper class
 * 
 * @author yetanotherx
 * 
 */
public class CUIDebug
{
	protected File debugFile;
	protected boolean debugMode = false;
	protected final static Logger logger = Logger.getLogger(CUIController.MODID);
	
	public CUIDebug()
	{
	}
	
	public void initialise() throws InitialisationException
	{
		ConsoleLogFormatter formatter = new ConsoleLogFormatter();
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(formatter);
		
		logger.setUseParentHandlers(false);
		logger.addHandler(handler);
		
		try
		{
			this.debugFile = new File(Minecraft.getMinecraft().mcDataDir.toString() + "/" + CUIController.MODID + ".debug.log");
			this.debugMode = CUIController.isDebugMode();

			if (this.debugMode)
			{
				FileHandler newHandler = new FileHandler(this.debugFile.getAbsolutePath());
				newHandler.setFormatter(formatter);
				logger.addHandler(newHandler);
			}
			
		}
		catch (IOException e)
		{
			e.printStackTrace(System.err);
			throw new InitialisationException();
		}
		
	}
	
	/**
	 * Shows a message if debug mode is true
	 * @param message 
	 */
	public void debug(String message)
	{
		if (this.debugMode)
		{
			logger.info(CUIController.MODID + " Debug - " + message);
		}
	}
	
	public void info(String message)
	{
		logger.info(message);
	}
	
	public void info(String message, Throwable e)
	{
		logger.log(Level.INFO, message, e);
	}
}
