package com.github.hexosse.wecuife.render.shapes;

import com.github.hexosse.wecuife.render.LineColour;
import com.github.hexosse.wecuife.render.LineInfo;
import com.github.hexosse.wecuife.render.points.PointCube;
import com.github.hexosse.wecuife.util.Vector3;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import org.lwjgl.opengl.GL11;

/**
 * Draws the top and bottom circles around a cylindrical region
 * 
 * @author yetanotherx
 */
public class RenderCylinderBox
{
	
	protected LineColour colour;
	protected double radX = 0;
	protected double radZ = 0;
	protected int minY;
	protected int maxY;
	protected double centreX;
	protected double centreZ;
	
	public RenderCylinderBox(LineColour colour, PointCube centre, double radX, double radZ, int minY, int maxY)
	{
		this.colour = colour;
		this.radX = radX;
		this.radZ = radZ;
		this.minY = minY;
		this.maxY = maxY;
		this.centreX = centre.getPoint().getX() + 0.5;
		this.centreZ = centre.getPoint().getZ() + 0.5;
	}
	
	public void render(Vector3 cameraPos)
	{
		Tessellator tessellator = Tessellator.instance;

		double xPos = this.centreX - cameraPos.getX();
		double zPos = this.centreZ - cameraPos.getZ();

		for (LineInfo tempColour : this.colour.getColours())
		{
			tempColour.prepareRender();
			
			double twoPi = Math.PI * 2;
			for (int yBlock : new int[] { this.minY, this.maxY + 1 })
			{
				tessellator.startDrawing(GL11.GL_LINE_LOOP );
				tempColour.prepareColour();
				
				for (int i = 0; i <= 75; i++)
				{
					double tempTheta = i * twoPi / 75;
					double tempX = this.radX * Math.cos(tempTheta);
					double tempZ = this.radZ * Math.sin(tempTheta);
					
					tessellator.addVertex(xPos + tempX, yBlock - cameraPos.getY(), zPos + tempZ);
				}
				tessellator.draw();
			}
		}
	}
}
