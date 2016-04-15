package com.github.hexosse.wecuife.listeners;

import com.github.hexosse.wecuife.CUIController;
import com.github.hexosse.wecuife.common.network.CUIPacket;
import com.github.hexosse.wecuife.render.region.BaseRegion;
import com.github.hexosse.wecuife.render.region.CuboidRegion;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

/**
 * @author <b>hexosse</b> (<a href="https://github.comp/hexosse">hexosse on GitHub</a>))
 */
public class CUIListenerPlayerJoin
{
	private WorldClient lastWorld = null;
	private boolean handShake = false;

	private static int handShakeTask = 0;
	private static int handShakeTimer = 0;

	/**
	 * triggered when world is joined
	 *
	 * @param event
	 */
	@SubscribeEvent
	public void onPlayerJoinWorld(EntityJoinWorldEvent event)
	{
		if(event.entity == null) return;
		if((event.entity instanceof EntityPlayer) == false) return;

		if(lastWorld != Minecraft.getMinecraft().theWorld)
			onWorldChange();
	}

	@SubscribeEvent
	public void ontick(TickEvent event)
	{
		if(handShakeTask == 1)
		{
			handShakeTimer++;
			if(handShakeTimer == 20)
			{
				CUIController.getDebugger().debug("Start handshake task");

				// Store the last world
				lastWorld = Minecraft.getMinecraft().theWorld;
				// Empty the current selection
				CUIController.setSelection(new CuboidRegion());
				// Send handshake
				CUIPacket.handShake();
				// Get the last WorldEdit selection
				CUIPacket.currentSelection();

				// No handShakeTask
				handShakeTask = 0;
				handShakeTimer = 0;
			}
		}
	}

	private void onWorldChange()
	{

		CUIController.getDebugger().debug("World change detected, sending new handshake");

		// Active handShakeTask
		handShakeTask =1;
		handShakeTimer = 0;
	}
}
