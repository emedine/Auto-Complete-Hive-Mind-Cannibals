import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphics;

class Prey{
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
  int fillColorMod;

  //
  BotProfile botProfile;
  
  String tName;
  
  PApplet pApp;
  
  /// fonts
  PFont SanSerif; /// font for display
  
  boolean selected = false; ///selected for hunt
  Prey(int ID){
	pApp.println("prey color: " + theID);
	  /// get an instance of a color profille
    BotProfile botProfile = BotProfile.getInstance();
    pApp = botProfile.pApp;
	
	theID = ID;
	fillColorMod = botProfile.getBotColor(theID);
    x = pApp.random(20,pApp.width);
    y = pApp.random(20,pApp.height);
    xoff = pApp.random(-5,5);
    yoff = pApp.random(-4,2);
    speedX = pApp.random(-1.5f,1.5f);
    speedY = pApp.random(-1.5f,1.5f);
    
    // println("Prey x: " + x);
    // println("Prey y: " + y);
  }
   
public void update(String theName){
    
    
	tName = theName;

    xoff = xoff + 0.05f;
    yoff = yoff + 0.09f;
    float vx =  pApp.noise(xoff) * 2;
    float vy =  pApp.noise(yoff)* 3;
    //line(n, 0, n, height);
    // g.ellipse(n,n1,20,20);
   
    x += speedX * vx;
    y += speedY * vy;
     
    x += pApp.noise(xoff);
    y += pApp.noise(yoff);
    if(x <=0){
      x = 0;
      speedX *=-1;
    }
    else if(x >= pApp.width){
      x = pApp.width;
      speedX *=-1;
    }
   
    if(y <=0){
      y = 0;
      speedY *=-1;
    }
    else if(y >= pApp.height){
      y = pApp.height;
      speedY *=-1;
    }
 
     //draw
    SanSerif = pApp.createFont("data/ArialMT-12.vlw", 14); /// font for display
    pApp.fill(fillColorMod, myAlpha);
    pApp.textFont(SanSerif);
    pApp.text(tName, x, y);
    // println("Prey: " + tName);
    

  }
   
}
