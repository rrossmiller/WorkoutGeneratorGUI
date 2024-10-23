
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class Generator
{
	private static Scanner in;
	private static PrintWriter fileOut;
		
	int classTime = -1;
	String endDrill;
	String userChoice;
	// Setup lists
	ArrayList<Drill> warmUp = new ArrayList<Drill>();
	ArrayList<Drill> allDrills = new ArrayList<Drill>();
	ArrayList<Drill> drills = new ArrayList<Drill>();
	Drill end;
	Workout spinClass;
	
	// minute go
	String mdir = "One minute at elevated pace //	Control HR and breathing //Gear: 2 – 5 above FR | RPM 85 - 110 //30 seconds rest //repeat once";
	Drill go = new Drill("Minute Go", 3.0,  mdir);
	// TABATA
	String tdir = "1-4 above FR | RPM 90 - 110 //8 reps //	20 seconds sprint //	10 seconds rest";
	Drill tabata = new Drill("TABATA", 4.0, tdir);
	
	ArrayList<Object> workout = new ArrayList<Object>();
	
	public Generator()
	{
		// initialize the workout
		spinClass = new Workout();
				
	}

	public void setUp(int time, String end)
	{
		if(!drills.isEmpty())
		{
			drills.clear();
			drills = new ArrayList<Drill>();
		}
		if(!warmUp.isEmpty())
		{
			warmUp.clear();
			warmUp = new ArrayList<Drill>();
		}
		if(!allDrills.isEmpty())
		{
			allDrills.clear();
			allDrills = new ArrayList<Drill>();
		}
		
		classTime = time;
		endDrill = end;
		// create list of drills
		createDrillList(warmUp, allDrills, endDrill, in);
	}

	public ArrayList<Drill> createWorkout()
	{
		if(!workout.isEmpty())
		{
			workout.clear();
			workout = new ArrayList<Object>();
		}
		// psuedorandomizer method in workout class
		workout = spinClass.createWorkout(warmUp, allDrills,classTime, endDrill);
		drills = (ArrayList<Drill>) workout.get(0);
		if(endDrill.equals("TABATA")) drills.add(tabata);
		else if(endDrill.equals("Minute Go")) drills.add(go);
		
		return drills;
					
	}
	/**
	 * the createDrillList method creates ArrayLists to hold the warmup and workout Drill instances 
	 * @param warmUp, arraylist to add warmup drills to
	 * @param drills, arraylist to add workout drills to
	 * @param in, scanner to parse the raw spin drills text file
	 */
	public void createDrillList(ArrayList<Drill> warmUp, ArrayList<Drill> drills, String endDrill, Scanner in)
	{
		// hold info from drills text file
		String line;
		String name;
		String t;
		double time;
		String directions;
		String[] lineArray;
		
		// count lines to get warmup drills
		int x = 0;
		
		try
		{
			// if the end drill is a minute go, the class can have a TABATA
			if(endDrill.equals("Minute Go"))
				in = new Scanner(new File("SpinDrills.txt"));
			else if(endDrill.equals("TABATA"))
				in = new Scanner(new File("NoTabata.txt"));
			
			while(in.hasNextLine())
			{
				// handle each line
				line = in.nextLine();
				if(line.length() > 0)
				{
					if (x <= 2)
					{
						lineArray = line.split(";");
						
						// handle the current line and make a new Drill
						name = lineArray[0].trim();
						t = lineArray[1].trim();
						time = Double.parseDouble(t);
						directions = lineArray[2].trim();
						
						Drill d = new Drill(name, time, directions);
						
						warmUp.add(d);	
						x++;
					}
					else
					{
						lineArray = line.split(";");
						
						// handle the line and make a new Drill
						name = lineArray[0].trim();
						t = lineArray[1].trim();
						time = Double.parseDouble(t);
						directions = lineArray[2].trim();
						
						Drill d = new Drill(name, time, directions);
						
						drills.add(d);	
					}
					
				}
				else
					continue;
			}
			in.close();
			
		} catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * the writeWorkout method creates the text file to be used as a Word doc format.
	 * @param warmUp
	 * @param drills
	 * @param go
	 * @param fileOut
	 */
	public static void writeWorkout(ArrayList<Drill> warmUp, ArrayList<Drill> drills, PrintWriter fileOut)
	{
		// hold the string to be written, and an array of directions.
		String fileString = "";
		String[] d;
		String home = System.getProperty("user.home");
		
		try{
			fileOut = new PrintWriter(new File(home + "/Desktop/SpinWorkout.txt"));
			
			//Spin class intro
			fileString = "Display explanation\n	RPM		Gear"
					+ "\n\nRiding posture\n\tToes pointed forward\n	Knees in line with the toes (pointing forward)"
					+ "\n	Hips: stable – engage abdominal muscles to combat rocking\n	Flat back"
					+"\n	Shoulders relaxed, away from ears\n	Light grip on handle bars \n\nPedal stroke\n"+
					"\tKeep your pedaling circular \n\tScraping mud off the bottom of your shoes at the bottom of your pedal stroke"
					+ "\n\nBuild to FR gear\n	Feels similar to riding a bike outside; feel the road underneath.\n	Comfortable resistance "
					+ "(something you could hold for about an hour).\n\n";
			fileOut.println(fileString);
			fileString = "";

//			for(int i = 0 ; i < warmUp.size() ; i++)
//			{
//				fileString += warmUp.get(i).getName();
//				fileString += " [";
//				fileString += warmUp.get(i).getTime();
//				fileString += "]\n\t";
//				d = warmUp.get(i).getDirections().split("//");
//				for(String s : d)
//					fileString += s + "\n\t";
//				
//				fileOut.println(fileString);
//				fileString = "";
//			}
			for(int i = 0 ; i < 2 ; i++)
			{
				fileString += drills.get(i).getName();
				fileString += " [";
				fileString += drills.get(i).getTime();
				fileString += "]\n\t";
				d = drills.get(i).getDirections().split("//");
				for(String s : d)
					fileString += s + "\n\t";
				
				fileOut.println(fileString);
				fileString = "";
			}
			
			// spaces so intro + warmup is all that is on first page of doc
			fileOut.println("\n\n");
			
			for(int i = 2 ; i < drills.size() ; i++) // i = 0
			{
				// append to fileString name, time
				fileString += drills.get(i).getName();
				fileString += " [";
				fileString += drills.get(i).getTime();
				fileString += "]\n\t";
				
				// split the dirrections by line denoted by //
				d = drills.get(i).getDirections().split("//");
				for(String s : d)
					fileString += s + "\n\t";
				
				fileOut.println(fileString);
				fileString = "";
			}
			
			// print cooldown
			fileOut.println("Cooldown [5.0]");
			
			fileOut.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	public void writeWorkout()
	{
			writeWorkout(this.warmUp,this.drills,fileOut);
	}
	
}
