package com.github.hexosse.wecuife.render.shapes;

import com.github.hexosse.wecuife.render.LineColour;
import com.github.hexosse.wecuife.render.LineInfo;
import com.github.hexosse.wecuife.util.Vector3;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;

import org.lwjgl.opengl.GL11;


/**
 * Draws a polygon
 * 
 * @author yetanotherx
 * @author lahwran
 */
public class Render3DPolygon
{
	
	protected LineColour colour;
	protected Vector3[] vertices;
	
	public Render3DPolygon(LineColour colour, Vector3... vertices)
	{
		this.colour = colour;
		this.vertices = vertices;
	}
	
	public void render(Vector3 cameraPos)
	{
		Tessellator tessellator = Tessellator.instance;
		
		for (LineInfo tempColour : this.colour.getColours())
		{
			tempColour.prepareRender();

			tessellator.startDrawing(GL11.GL_LINE_LOOP );
			tempColour.prepareColour();
			for (Vector3 vertex : this.vertices)
			{
				tessellator.addVertex(vertex.getX() - cameraPos.getX(), vertex.getY() - cameraPos.getY(), vertex.getZ() - cameraPos.getZ());
			}
			tessellator.draw();
		}
	}
}
