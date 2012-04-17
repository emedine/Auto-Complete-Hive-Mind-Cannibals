import processing.core.PApplet;
import processing.core.PGraphics;
class Leg extends PApplet{
      //// color changing values
  int theFillColor;
  float rnd;
  float rnd1;
  //
  int total = 120;
  float counter = 1;     // counter for sine wave offset
  float increment;       // increment for counter for sine wave offset
  float waveSize;        // wave size for sine wave offset
  float[] dist = new float[total];  // array for distance between points
  float[] xPos = new float[total];  // array for x posistions
  float[] yPos = new float[total];  // variables for movePoints()
  float xpd, xpd2, ypd, ypd2;
  float acceleration = .5f;
  float friction = .5f;
  float cr,cg,cb;
  float dx, dy, d; // variables for IK within setPoints()
  float xp = 300, yp=300, xp2, yp2; // variables for elasticity()
  
  //
  int theID;
  BotProfile botProfile;
  
  PGraphics g;
   
  //constructor
  Leg (int numOfSegments, float ws, float inc, float r, float g, float b, int ID) {
	theID = ID;
    total = numOfSegments;
    waveSize = ws;
    increment = inc;
    cr = r;
    cg = g;
    cb = b;
  }
   
  void wriggle(float x, float y, PGraphics pG){
	g = pG;
    // theFillColor = fillColor;
    elasticity(x,y);
    movePoints();
    pushArray();
    setPoints();
    drawLine();
  }
   
  void elasticity(float x, float y){
    //xp2 = xp2 * acceleration + (m1.xPos - xp) * friction;
    //yp2 = yp2 * acceleration + (m1.yPos - yp) * friction;
    xp2 = xp2 * acceleration + (x - xp) * friction;
    yp2 = yp2 * acceleration + (y - yp) * friction;
    xp = xp + xp2;
    yp = yp + yp2;
  }
   
  void movePoints(){
    for (int i=1; i<=total-1; i++){
      xpd2 = xpd2 * acceleration + (xPos[i] - xPos[i-1]) * friction;
      ypd2 = ypd2 * acceleration + (yPos[i] - yPos[i-1]) * friction;
      xpd = xpd + xpd2;
      ypd = ypd + ypd2;
      xPos[i] = xpd;
      yPos[i] = ypd;
    }
  }
   
   void pushArray(){
    for (int i=total-2; i>total/2; i--){
      dist[i+1] = dist[i]*.94f;
    }
    for (int i=total/2; i>0; i--){
      dist[i+1] = dist[i]*1.02f;
    }
    dist[1] = sin(counter) * 2 + 3;
    counter = counter + increment;
  }
 
  void setPoints(){
    xPos[0] = xp;
    yPos[0] = yp;
    for (int i=1; i<total; i++){
      dx = xPos[i] - xPos[i-1];
      dy = yPos[i] - yPos[i-1];
      d = sqrt(sq(dx) + sq(dy));
 
      xPos[i] = xPos[i-1] + dx * (dist[i]) / d;
      yPos[i] = yPos[i-1] + dy * (dist[i]) / d;
    }
  }
   
  void drawLine(){
	    /// get an instance of a color profille
	BotProfile botProfile = BotProfile.getInstance();
	int fillColorMod;
    try{
    fillColorMod = botProfile.getColor(theID);
    } catch (Exception ex){
    	println("Cant bot profile");
    	fillColorMod = 255;
    }
    for (int i=total-1; i>20; i--){
      rnd = random(1);
      rnd = rnd*255;
      // fillColor = color((int) (rnd*255), (rnd1*65),0, 65);
      //fill((cr/total) * (total-i),0,0);
      g.fill(fillColorMod, rnd);
      //strokeWeight(1);
      // stroke(0);
      g.noStroke();
      g.ellipse(xPos[i], yPos[i], ((dist[i]*3.5f)/total) * (total - i), ((dist[i]*3.5f)/total) * (total - i));
      //ellipse(xPos[i], yPos[i], (30.5f/total) * (total - i), (30.5f/total) * (total - i));
    }
  }
     
}
