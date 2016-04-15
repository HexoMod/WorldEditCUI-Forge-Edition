package com.github.hexosse.wecuife.render.shapes;

import com.github.hexosse.wecuife.CUIController;
import com.github.hexosse.wecuife.render.LineColour;
import com.github.hexosse.wecuife.render.LineInfo;
import com.github.hexosse.wecuife.util.BoundingBox;
import com.github.hexosse.wecuife.util.Vector3;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import org.lwjgl.opengl.GL11;

/**
 * Draws a rectangular prism around 2 corners
 * 
 * @author yetanotherx
 * @author lahwran
 */
public class Render3DBox
{
	
	protected LineColour colour;
	protected Vector3 first;
	protected Vector3 second;
	
	public Render3DBox(LineColour colour, BoundingBox region)
	{
		this(colour, region.getMin(), region.getMax());
	}
	
	public Render3DBox(LineColour colour, Vector3 first, Vector3 second)
	{
		this.colour = colour;
		this.first = first;
		this.second = second;
	}
	
	public void render(Vector3 cameraPos)
	{
		Tessellator tessellator = Tessellator.instance;

		double x1 = this.first.getX() - cameraPos.getX(); 
		double y1 = this.first.getY() - cameraPos.getY(); 
		double z1 = this.first.getZ() - cameraPos.getZ(); 
		double x2 = this.second.getX() - cameraPos.getX();
		double y2 = this.second.getY() - cameraPos.getY();
		double z2 = this.second.getZ() - cameraPos.getZ();
		
		for (LineInfo tempColour : this.colour.getColours())
		{
			tempColour.prepareRender();

			// Draw bottom face
			tessellator.startDrawing(GL11.GL_LINE_LOOP );
			tempColour.prepareColour();
			tessellator.addVertex(x1, y1, z1);
			tessellator.addVertex(x2, y1, z1);
			tessellator.addVertex(x2, y1, z2);
			tessellator.addVertex(x1, y1, z2);
			tessellator.draw();

			// Draw top face
			tessellator.startDrawing(GL11.GL_LINE_LOOP );
			tempColour.prepareColour();
			tessellator.addVertex(x1, y2, z1);
			tessellator.addVertex(x2, y2, z1);
			tessellator.addVertex(x2, y2, z2);
			tessellator.addVertex(x1, y2, z2);
			tessellator.draw();
			
			// Draw join top and bottom faces
			tessellator.startDrawing(GL11.GL_LINES);
			tempColour.prepareColour();
			
			tessellator.addVertex(x1, y1, z1);
			tessellator.addVertex(x1, y2, z1);
			
			tessellator.addVertex(x2, y1, z1);
			tessellator.addVertex(x2, y2, z1);
			
			tessellator.addVertex(x2, y1, z2);
			tessellator.addVertex(x2, y2, z2);
			
			tessellator.addVertex(x1, y1, z2);
			tessellator.addVertex(x1, y2, z2);
			
			tessellator.draw();
		}
	}
}
