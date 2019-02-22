import java.util.ArrayList;
import java.util.Random;

public class Workout 
{
	public static double TOTALTIME = 0.0;
	private ArrayList<Drill> spinClass = new ArrayList<Drill>();
	private static Random rng = new Random();
	private ArrayList<Object> rtn = new ArrayList<Object>(); // {Drill list, TOTALTIME}
	
	public Workout(){}
	
	public ArrayList<Object> createWorkout(ArrayList<Drill> warmUp, ArrayList<Drill> drills, double classTime, String endDrill) 
	{
		// if RPM pyramid was selected to warmup, remove it form drills
		int rem = rng.nextInt(2);
		warmUp.remove(rem);
		
		
	// constant times
		// announcements
//		TOTALTIME += 4; // Spend more time explaining early in the semester
		TOTALTIME += 3;	// Less time spent explaining later in semester
		// warm up
		TOTALTIME += 7;
		
		if(endDrill.equals("Minute Go"))
			// minute go
			TOTALTIME += 3;
		else if(endDrill.equals("TABATA"))
			// TABATA
			TOTALTIME += 4;
		//cooldown
		TOTALTIME += 5;		
		
		// randomize drill selection
		int loops = 0;
		ArrayList<Integer> indexes = new ArrayList<Integer>();
		
		while(!(TOTALTIME <= classTime - 1 && TOTALTIME >= classTime - 3))
		{
			//if there have been more than 50 loops (trys), start from scratch
			if(loops == 50)
			{
				//reset TOTALTIME, clear indexes, clear spinClass and reset trys
				if(endDrill.equals("TABATA"))
					TOTALTIME = 20; 
				else if(endDrill.equals("Minute Go"))
					TOTALTIME = 19;
				indexes.clear();
				spinClass.clear();
				loops = 0;
			}
			
			// generate a random index of allDrills
			int index = rng.nextInt(drills.size());
			if(!(indexes.contains(index)))
			{
				// hold the index used
				indexes.add(index);
				// if the drill isn't already part of the warm up
				if(!warmUp.get(0).equals(drills.get(index).getName())){
					// add the drill to spinClass and add its time to TOTALTIME
					spinClass.add(drills.get(index));}
				
				// if the drill is an AMRAP, add extra time for recovery
				if( drills.get(index).getName().equals("AMRAP") || drills.get(index).getName().equals("AMGAP"))
				{
					TOTALTIME += drills.get(index).getTime() + 1.5;
				}
				else
					TOTALTIME += drills.get(index).getTime() + 1;
				
				// if the time goes past the threshold, remove the drill and subtract its time
				if(TOTALTIME > classTime - 3)
				{
					if( drills.get(index).getName().equals("AMRAP") || drills.get(index).getName().equals("AMGAP"))
						TOTALTIME -= drills.get(index).getTime() + 1.5;
					else
						TOTALTIME -= drills.get(index).getTime() + 1;
					
					spinClass.remove(drills.get(index));
				}
			}
			
			// increment the loop count
			loops++;
		}
		

		for(int i = warmUp.size() - 1 ; i >=0 ; i--)
			spinClass.add(0,warmUp.get(i));
		rtn.add(spinClass);
		rtn.add(TOTALTIME);
		return rtn;
	}
}


