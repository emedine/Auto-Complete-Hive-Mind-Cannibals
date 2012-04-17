import processing.core.PApplet;
import processing.core.PGraphics;

class Arm extends PApplet{
  
  BotProfile botProfile;
  
  int theID;
  
  /// num segs
  int numSegs = 15;
  
  /// cur position
  float xPos;
  float yPos;
  
  //
  PApplet pApp;
  
  
  Arm(int ID){
	theID = ID;
	botProfile = BotProfile.getInstance();
	pApp = botProfile.pApp;
  }
 
  void update(float theX, float theY) {
    /// find new target
	xPos = theX;
	yPos = theY;
	/// get some colors
	int fillColorMod;
    try{
    fillColorMod = botProfile.getColor(theID);
    } catch (Exception ex){
    	fillColorMod = 255;
    }
    /// cycle through arm segments
    float rnd;
    for (int i=numSegs; i>0; i--){
      rnd = random(1);
      rnd = rnd*255;
      pApp.fill(fillColorMod, rnd);
      //strokeWeight(1);
      // stroke(0);
      pApp.noStroke();
      // sin
      // pApp.ellipse(xPos[i], yPos[i], ((dist[i]*3.5f)/total) * (total - i), ((dist[i]*3.5f)/total) * (total - i));
      // pApp.ellipse(sin(xPos * i), sin(xPos *i), 5 * i, 5*i);
      pApp.ellipse(xPos + i, yPos + i, new Float(1.25 * i), new Float(1.25*i));
      
    }  
	  
  }
 
  // draw segments
  void render(int fillColor, PGraphics pg) {
	
  }
}
