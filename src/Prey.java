import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphics;

class Prey extends PApplet{
  float x,y;
  float xoff = 0.0f;
  float yoff = 0.0f;
   
  float speedX = 1;
  float speedY = 1;
 
  float myAlpha=255;
  int myColor;
  
  int theID;
  //
  boolean isVisible = true;
  //
  BotProfile botProfile;
  
  String tName;
  
  PGraphics g;
  
  /// fonts
  PFont SanSerif; /// font for display
  
  boolean selected = false; ///selected for hunt
  Prey(PGraphics pG, int ID){

	g = pG;
	theID = ID;
    x = random(20,g.width);
    y = random(20,g.height);
    xoff = random(-5,5);
    yoff = random(-4,2);
    speedX = random(-1.5f,1.5f);
    speedY = random(-1.5f,1.5f);
    
    // println("Prey x: " + x);
    // println("Prey y: " + y);
  }
   
  void update(int fillColor, String theName){
  int fillColorMod;
  /// get an instance of a color profille
	BotProfile botProfile = BotProfile.getInstance();
    try{
    fillColorMod = botProfile.getColor(theID);
    } catch (Exception ex){
    	fillColorMod = 255;
    }
	tName = theName;
    myColor = fillColorMod;
    xoff = xoff + 0.05f;
    yoff = yoff + 0.09f;
    float vx =  noise(xoff) * 2;
    float vy =  noise(yoff)* 3;
    //line(n, 0, n, height);
    // g.ellipse(n,n1,20,20);
   
    x += speedX * vx;
    y += speedY * vy;
     
    x += noise(xoff);
    y += noise(yoff);
    if(x <=0){
      x = 0;
      speedX *=-1;
    }
    else if(x >= g.width){
      x = g.width;
      speedX *=-1;
    }
   
    if(y <=0){
      y = 0;
      speedY *=-1;
    }
    else if(y >= g.height){
      y = g.height;
      speedY *=-1;
    }
 
     //draw
    SanSerif = createFont("data/ArialMT-12.vlw", 14); /// font for display
    g.fill(myColor, myAlpha);
    g.textFont(SanSerif);
    g.text(tName, x, y);
    // println("Prey: " + tName);
    

  }
   
}
