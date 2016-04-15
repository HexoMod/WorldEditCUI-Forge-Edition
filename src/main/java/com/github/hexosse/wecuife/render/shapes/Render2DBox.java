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
 * Draws the top and bottom rings of a polygon region
 * 
 * @author yetanotherx
 * @author lahwran
 */
public class Render2DBox
{
	
	protected LineColour colour;
	protected List<PointRectangle> points;
	protected int min;
	protected int max;
	
	public Render2DBox(LineColour colour, List<PointRectangle> points, int min, int max)
	{
		this.colour = colour;
		this.points = points;
		this.min = min;
		this.max = max;
	}
	
	public void render(Vector3 cameraPos)
	{
		Tessellator tessellator = Tessellator.instance;
		double off = 0.03 - cameraPos.getY();
		for (LineInfo tempColour : this.colour.getColours())
		{
			tempColour.prepareRender();

			tessellator.startDrawing(GL11.GL_LINES);
			tempColour.prepareColour();

			for (PointRectangle point : this.points)
			{
				if (point != null)
				{
					Vector2 pos = point.getPoint();
					double x = pos.getX() - cameraPos.getX();
					double z = pos.getY() - cameraPos.getZ();
					tessellator.addVertex(x + 0.5, this.min + off, z + 0.5);
					tessellator.addVertex(x + 0.5, this.max + 1 + off, z + 0.5);
				}
			}
			tessellator.draw();
		}
	}
}
