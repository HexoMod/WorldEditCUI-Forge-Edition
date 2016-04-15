package com.github.hexosse.wecuife.listeners;

import com.github.hexosse.wecuife.CUIController;
import com.github.hexosse.wecuife.util.Vector3;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;

/**
 * Listener for WorldRenderEvent
 * 
 * @author lahwran
 * @author yetanotherx
 * 
 */
@SideOnly(Side.CLIENT)
public class CUIListenerWorldRender
{
	public CUIListenerWorldRender() {}

	@SubscribeEvent
	public void onWorldRender(RenderWorldLastEvent event)
	{
		onRender(event.partialTicks);
	}

	public void onRender(float partialTicks)
	{
		if(CUIController.isEnable()==false)
			return;

		try
		{
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
			GL11.glEnable(GL11.GL_LINE_SMOOTH);
			GL11.glDisable(GL11.GL_LIGHT0);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glDepthMask(false);

			try
			{
				Vector3 cameraPos = new Vector3(Minecraft.getMinecraft().renderViewEntity, partialTicks);
				GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.5F);
				if (CUIController.getSelection() != null)
				{
					CUIController.getSelection().render(cameraPos);
				}
			}
			catch (Exception e)
			{
			}

			GL11.glDepthMask(true);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_LIGHT0);

			GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();
		}
		catch (Exception ex) {}
	}
}
