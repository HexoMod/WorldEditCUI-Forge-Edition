package com.github.hexosse.wecuife.render.shapes;

import com.github.hexosse.wecuife.render.LineColour;
import com.github.hexosse.wecuife.render.LineInfo;
import com.github.hexosse.wecuife.render.points.PointCube;
import com.github.hexosse.wecuife.util.Vector3;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import org.lwjgl.opengl.GL11;

/**
 * Draws the grid lines around a cylindrical region
 * 
 * @author yetanotherx
 */
public class RenderCylinderGrid
{
	
	protected LineColour colour;
	protected double radX = 0;
	protected double radZ = 0;
	protected int minY;
	protected int maxY;
	protected double centreX;
	protected double centreZ;
	
	public RenderCylinderGrid(LineColour colour, PointCube centre, double radX, double radZ, int minY, int maxY)
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
			
			int tmaxY = this.maxY + 1;
			int tminY = this.minY;
			int posRadiusX = (int)Math.ceil(this.radX);
			int negRadiusX = (int)-Math.ceil(this.radX);
			int posRadiusZ = (int)Math.ceil(this.radZ);
			int negRadiusZ = (int)-Math.ceil(this.radZ);
			
			for (double tempX = negRadiusX; tempX <= posRadiusX; ++tempX)
			{
				double tempZ = this.radZ * Math.cos(Math.asin(tempX / this.radX));
				tessellator.startDrawing(GL11.GL_LINE_LOOP );
				tempColour.prepareColour();
				
				tessellator.addVertex(xPos + tempX, tmaxY - cameraPos.getY(), zPos + tempZ);
				tessellator.addVertex(xPos + tempX, tmaxY - cameraPos.getY(), zPos - tempZ);
				tessellator.addVertex(xPos + tempX, tminY - cameraPos.getY(), zPos - tempZ);
				tessellator.addVertex(xPos + tempX, tminY - cameraPos.getY(), zPos + tempZ);
				
				tessellator.draw();
			}
			
			for (double tempZ = negRadiusZ; tempZ <= posRadiusZ; ++tempZ)
			{
				double tempX = this.radX * Math.sin(Math.acos(tempZ / this.radZ));
				tessellator.startDrawing(GL11.GL_LINE_LOOP );
				tempColour.prepareColour();
				
				tessellator.addVertex(xPos + tempX, tmaxY - cameraPos.getY(), zPos + tempZ);
				tessellator.addVertex(xPos - tempX, tmaxY - cameraPos.getY(), zPos + tempZ);
				tessellator.addVertex(xPos - tempX, tminY - cameraPos.getY(), zPos + tempZ);
				tessellator.addVertex(xPos + tempX, tminY - cameraPos.getY(), zPos + tempZ);
				
				tessellator.draw();
			}
		}
	}
}
