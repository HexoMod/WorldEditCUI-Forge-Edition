package com.github.hexosse.wecuife.render.region;

import com.github.hexosse.wecuife.render.LineColour;
import com.github.hexosse.wecuife.render.points.PointCube;
import com.github.hexosse.wecuife.render.shapes.Render3DPolygon;
import com.github.hexosse.wecuife.util.Vector3;

import java.util.ArrayList;
import java.util.List;

/**
 * Main controller for a polygon-type region
 * 
 * @author TomyLobo
 */
public class PolyhedronRegion extends BaseRegion
{
	
	protected List<PointCube> vertices = new ArrayList<PointCube>();
	protected List<Vector3[]> faces = new ArrayList<Vector3[]>();
	
	private List<Render3DPolygon> faceRenders = new ArrayList<Render3DPolygon>();
	
	public PolyhedronRegion()
	{
		super();
	}
	
	@Override
	public void render(Vector3 cameraPos)
	{
		for (PointCube vertex : this.vertices)
		{
			vertex.render(cameraPos);
		}
		
		for (Render3DPolygon face : this.faceRenders)
		{
			face.render(cameraPos);
		}
	}

	@Override
	public void setCuboidPoint(int id, double x, double y, double z)
	{
		final PointCube vertex = new PointCube(x, y, z);
		vertex.setColour(id == 0 ? LineColour.CUBOIDPOINT1 : LineColour.POLYPOINT);
		
		if (id < this.vertices.size())
		{
			this.vertices.set(id, vertex);
		}
		else
		{
			for (int i = 0; i < id - this.vertices.size(); i++)
			{
				this.vertices.add(null);
			}
			this.vertices.add(vertex);
		}
	}
	
	private static final Vector3 half = new Vector3(0.5, 0.5, 0.5);
	
	@Override
	public void addPolygon(int[] vertexIds)
	{
		final Vector3[] face = new Vector3[vertexIds.length];
		for (int i = 0; i < vertexIds.length; ++i)
		{
			final PointCube vertex = this.vertices.get(vertexIds[i]);
			if (vertex == null)
			{
				// This should never happen
				return;
			}
			
			face[i] = vertex.getPoint().add(half);
		}
		this.faces.add(face);
		this.update();
	}
	
	private void update()
	{
		this.faceRenders.clear();
		
		for (Vector3[] face : this.faces)
		{
			this.faceRenders.add(new Render3DPolygon(LineColour.POLYBOX, face));
		}
	}
	
	@Override
	public RegionType getType()
	{
		return RegionType.POLYHEDRON;
	}
}
