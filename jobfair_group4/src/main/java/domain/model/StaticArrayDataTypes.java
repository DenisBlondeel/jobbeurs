package domain.model;

public class StaticArrayDataTypes {
	
	private String id;
	private String coords;
	private String shape;
	
	public StaticArrayDataTypes(String id, String coords, String shape)
	{
		this.id = id;
		this.coords = coords;
		this.shape = shape;
	}

	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getShape()
	{
		return shape;
	}
	
	public void setShape(String shape)
	{
		this.shape = shape;
	}
	
}
