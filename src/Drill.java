/**
 * Song Generator Tester
 * @author robertrossmiller
 *
 */
public class Drill
{
	private String name;
	private double time;
	private String directions;
	
	public Drill(String n, double t, String dir)
	{
		name = n;
		time = t;
		directions = dir;		
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public double getTime()
	{
		return this.time;
	}
	
	public String getDirections()
	{
		return this.directions;
	}
}
