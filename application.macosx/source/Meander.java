import processing.core.PApplet;
import processing.core.PGraphics;

class Meander{
  float left =  50, right = 550, bottom = 50, top = 550;
  float xMax = 6, yMax = 6;
  float xPos = -10, yPos = 300;
  float xLimit, yLimit, xSpeed, ySpeed, xDelta, yDelta;
  double dRandom;
  
  // 
  BotProfile botProfile;
  //
  PApplet pApp;
   
  //constructor
  Meander(){
	/// init bot profile
		botProfile = BotProfile.getInstance();
		pApp = botProfile.pApp;
	  
  }
   
  void move(){
    left = 50;
    right = 550;
    bottom = 50;
    top = 550;
    
     
    if (xPos < left){
    // dRandom = g.random(10,30);
    // dRandom = g.random(0,1);
     xDelta = pApp.random(0,1);
    } else if (xPos > right){
      xDelta = pApp.random (-1,0);
    } else {
      xDelta = pApp.random(-1,1);
    }
     
    if (yPos < bottom){
      yDelta = pApp.random(0,1);
    } else if (yPos > top){
      yDelta = pApp.random (-1,0);
    } else {
      yDelta = pApp.random(-1,1);
    }
     
    xSpeed = xSpeed + xDelta;
    ySpeed = ySpeed + yDelta;
     
    if (xSpeed > xMax){
      xSpeed = xMax;
    } else if (xSpeed < -xMax){
      xSpeed = -xMax;
    }
     
    if (ySpeed > yMax){
      ySpeed = yMax;
    } else if (ySpeed < -yMax){
      ySpeed = -yMax;
    }
 
    xPos = xPos + xSpeed;
    yPos = yPos + ySpeed;
  }
   
}
