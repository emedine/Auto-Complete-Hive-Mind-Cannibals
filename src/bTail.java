import processing.core.PApplet;
import processing.core.PGraphics;
class bTail extends PApplet{
    //// color changing values
  int theFillColor;
  float rnd;
  float rnd1;
  float switchID; 
  
  //
  int theID;
//
  BotProfile botProfile;
 
  /// make sure graphics exist
  PGraphics g;
  
  int numNodes = 35; //previously 35
  Vector[] node = new Vector[numNodes];
  float angle, tx,ty;
  float freq = 1;
  float radius = 1;
  float cx;  // = 400; // g.width/2;
  float cy; //  = 300; // g.height/2;
  float xPos, yPos; // fill these value from bot class
  float speedCoefficient = 0.1f;
  

  
  bTail(int ID){
	theID = ID;
	/// println("The TAIL ID: " + theID);
	/// botProfile = new BotProfile();

    for (int n=0; n<numNodes; n++){
      Vector pos = new Vector(0,0);  
      node[n] = pos;
      node[n].setX(n*15);
      node[n].setY(n*15);
    }
  }
 
  void update() {
     //xPos = mouseX;
     //yPos = mouseY;
   
    freq = random(-15.5f,15.5f);
    tx = xPos + cos(radians(angle))*(radius/2);
    ty = yPos + sin(radians(angle))*(radius/2);
    angle -= freq;
    node[0].x = tx;
    node[0].y = ty;
    node[1].x = tx;
    node[1].y = ty;
    for (int i=2; i<numNodes; i++){
      float dx = node[i].getX() - node[i-2].getX();
      float dy = node[i].getY() - node[i-2].getY();
      float d = sqrt(sq(dx) + sq(dy));
       
      node[i].x = node[i-1].x + (dx * 15)/d;
      node[i].y = node[i-1].y + (dy * 15)/d;
      if(i == 2) {
        xPos = xPos - dx * speedCoefficient;
        yPos = yPos - dy * speedCoefficient;
      }
    }
  }
 
  // function to draw bot
  void render(int fillColor, PGraphics pg) {
	g = pg;
    theFillColor = fillColor;
    
    // take the preset color we passed from the wrangler
    // add to two random colors
    rnd = random(1);
    rnd = rnd*125;
    // rnd1 = random(1);
    // rnd1=rnd1*255;
    // theFillColor = color((int) theFillColor, (rnd*255), (rnd1*255),65);
    // switchID = random(1);
    // fillColor = color((int) (rnd*255), (rnd1*65),0, 65);
  
    // int fillColorMod= color((int) (rnd*255), (rnd1*255), theFillColor); ///  use this one
    
    // int fillColorMod= color((int) theFillColor, (rnd*255), (rnd1*255),65);
    // fillColor = fillColor((rnd1*255),0, 65);
    
    /// get an instance of a color profille
	BotProfile botProfile = BotProfile.getInstance();
    //draw the bot
    int fillColorMod;
    try{
    fillColorMod = botProfile.getColor(theID);
    } catch (Exception ex){
    	fillColorMod = 255;
    }
    // println("THE GOTTEN COLOR IS: " + botProfile.getColor(theID));
    for (int i=1; i<numNodes; i++){
      g.strokeWeight( (17-i)*(17-i)/40  );
      g.stroke(fillColorMod, 123);
      // g.stroke(theFillColor, rnd);
      g.line(node[i].getX(), node[i].getY(), node[i-1].getX(), node[i-1].getY());
    }
    
    //draw head
    g.fill(fillColorMod, rnd);
    // rect(node[0].x,node[0].x, 60, 30);
    g.ellipse(node[0].x, node[0].y, 15, 15);
  }
}
