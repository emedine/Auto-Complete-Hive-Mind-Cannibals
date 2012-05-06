import java.util.Arrays;
//java libraries
import java.util.ArrayList;

import processing.core.*;

class BotProfile extends PApplet{
  float x;
  float y;
/// set up colors
  int[] fillColors;
  int fillColor1;
  int fillColor2;
  float rnd;
  float rnd1;
  float rnd2;
  
  int theID;
  String theName="clone";
  
  // ArrayList<int> BotColorArray = new ArrayList<int>();
  ArrayList<Integer> BotColorArray = new ArrayList<Integer>();
  
  int numPrey = 10; // this is the number of prey for each bot
  
  //
/// locate applet
  PApplet pApp;
  
  private static BotProfile instance = new BotProfile();
  private BotProfile() {
	  fillColors = new int[4]; 
	  x = X;
	  y = Y;
  }
  //// instantiate
  public static synchronized BotProfile getInstance() {
	  if(instance == null) {
	         instance = new BotProfile();
	   }
	   return instance;
  }
 
 
  public void botProfileInit(PApplet p){
	  // fillColors = new int[4];
	  theName = "Master";
	  pApp = p;
  }
  public void setColor(int theColor){
	  /// init our color and set its value in the array
	  fillColor1 = theColor;
	  BotColorArray.add(fillColor1);

	  println("ID: " + BotColorArray.size() + " color: "  + fillColor1);

  }
  
  int getBotColor(int ID){
	  theID = ID;
	  int theColor = BotColorArray.get(theID);
	  return (theColor);
	  
  }
  
  int getColor(int ID) {
	  
	 //  println(Arrays.toString(fillColors));
	  theID = ID;
	  rnd = random(1);
	  rnd = rnd*75;
	  
	  switch (theID){

	  // all the colors for all possible bots, pass the ID in to see what color you are!
	  // should do this as an array instead
	  case 0:
		   fillColor2 = color((int) (rnd), (rnd),Math.round(fillColors[theID]));
		   // fillColor2 = 255;
		   // println("Bot Color0: " + theID + " : " + fillColors[theID]); 
		   break;
	  case 1:
		  fillColor2 = color((int) (rnd),Math.round(fillColors[theID] + 75),(rnd));
		   // println("Bot Color1: " + theID + " : " + fillColors[theID]); 
		   break;
	  case 2:
		  fillColor2 = color((int) Math.round(fillColors[theID] + 15),(rnd),(rnd));
		  //  println("Bot Color2: " + theID + " : " + fillColors[theID]); 
		   break;
	  case 3:
		  fillColor2 = color((int) (fillColors[theID]),(255),(225));
		  //  println("Bot Color3: " + theID + " : " + fillColors[theID]); 
		   break;
	  case 4:
		  fillColor2 = color((int) (fillColors[theID]),(255),(225));
		   // println("Bot Color4: " + theID + " : " + fillColors[theID]); 
		   break;
	  case 5: //exit
		  fillColor2 = color((int) (fillColors[theID]),(255),(225));
		   println("Bot Color: " + theID + " : " + fillColors[theID]); 
		   break;
	  default:
		   println("Bot Color: " + theID); 
	  }
	// println("Sending Color: " + fillColor2);
    return (fillColor2);
  }
  
  public static void setApplet(PApplet p){
	 /// pApp = p;
	   
 }
 //////
  
  
// end class
}
