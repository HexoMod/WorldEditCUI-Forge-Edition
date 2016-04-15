package com.github.hexosse.wecuife.render.shapes;

import com.github.hexosse.wecuife.render.LineColour;
import com.github.hexosse.wecuife.render.LineInfo;
import com.github.hexosse.wecuife.render.points.PointRectangle;
import com.github.hexosse.wecuife.util.Vector2;
import com.github.hexosse.wecuife.util.Vector3;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import org.lwjgl.opengl.GL11;

import java.util.List;


/**
 * Draws the grid for a polygon region
 * 
 * @author yetanotherx
 * @author lahwran
 */
public class Render2DGrid
{
	protected LineColour colour;
	protected List<PointRectangle> points;
	protected int min;
	protected int max;
	
	public Render2DGrid(LineColour colour, List<PointRectangle> points, int min, int max)
	{
		this.colour = colour;
		this.points = points;
		this.min = min;
		this.max = max;
	}
	
	public void render(Vector3 cameraPos)
	{
		double off = 0.03;
		for (double height = this.min; height <= this.max + 1; height++)
		{
			this.drawPoly(cameraPos, height + off);
		}
	}
	
	protected void drawPoly(Vector3 cameraPos, double height)
	{
		Tessellator tessellator = Tessellator.instance;
		
		for (LineInfo tempColour : this.colour.getColours())
		{
			tempColour.prepareRender();

			tessellator.startDrawing(GL11.GL_LINE_LOOP );
			tempColour.prepareColour();
			for (PointRectangle point : this.points)
			{
				if (point != null)
				{
					Vector2 pos = point.getPoint();
					double x = pos.getX() - cameraPos.getX();
					double z = pos.getY() - cameraPos.getZ();
					tessellator.addVertex(x + 0.5, height - cameraPos.getY(), z + 0.5);
				}
			}
			tessellator.draw();
		}
	}
}
