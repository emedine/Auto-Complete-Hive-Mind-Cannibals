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
  
 
  public static void setApplet(PApplet p){
	 /// pApp = p;
	   
 }
 //////
  
  
// end class
}
