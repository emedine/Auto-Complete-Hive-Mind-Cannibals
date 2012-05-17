import processing.core.PApplet;
import processing.core.*;
// aalebot all movement code goes here
// bot with its body parts it contols its movement
 
class AaleBot{
	
  // 
  BotProfile botProfile;
  //
  PApplet pApp;
		
  float xPos = 300;
  float yPos = 200; // position of the bot
  float speedX, speedY; // speed of the bot
  float delta_x, delta_y; /// angle to calculate antennae
  float fSpeedX = 3.5f; /// follow speed of the bot
  float fSpeedY = 4.5f; // follow speed of the bot
  float cx,cy; // stage centers
  float xOff,yOff; // random location
  float targX, targY;// target x and y
  float dx, dy; //distance to target x and y
  float deltaX, deltaY; //distance to target x and y
  float dst; /// distance between angles
  float angle; // relation of bot to prey
  float theta; // relation of bot to prey for antenna rotation

  boolean follow = false;
  // import antennas, tentacle, and tail and do the magic
  Antenna theAntenna1;
  Antenna theAntenna2;
  Antenna theAntenna3;
  Antenna theAntenna4;
  Antenna theAntenna5;
  bTail tail;
  Tentacles tenta;
  
  int theFillColor;
  /// this randomness should adjust mvt
  float theFindX;
  float theFindY;
  ///
  String theName;
  
  /// array of prey for each aale
  Prey[] prey;
  
  int theID;
  
  int numPrey;  
  
  boolean eatenAllPrey = false; //// eaten all prey flag
  
  AaleBot(int ID) {
    
	/// init bot profile
	botProfile = BotProfile.getInstance();
	pApp = botProfile.pApp;
	
	numPrey = botProfile.numPrey;
	  
	theID = ID;
	/// println("AALE BOT ID: " + theID);
	/// theFillColor = fillColor1;
    //create the prey
    prey = new Prey[numPrey];
    /// instead of an object
    /// make prey a simple shape with 
    /// x and y coordinates
    prey[0] = new Prey(theID);
    prey[1] = new Prey(theID);
    prey[2] = new Prey(theID);
    prey[3] = new Prey(theID);
    prey[4] = new Prey(theID);
    /*
    prey[5] = new Prey(theID);
    prey[6] = new Prey(theID);
    prey[7] = new Prey(theID);
    prey[8] = new Prey(theID);
    prey[9] = new Prey(theID);
    */
    
    // this should make it wander more
    // but it doesn't
    theFindX = pApp.random(6);
    theFindY = pApp.random(6);
    
    // degrees rotation for antenna
    // this relates to the angle somehow
    theta = 0;
    
    cx = pApp.width/2;
    cy = pApp.height/2;
    // targdt
    targX =0;
    targY =0;
    // tail
    tail = new bTail(theID);
    tail.xPos = cx;
    tail.yPos = cy;
    
    // tentacle
    tenta = new Tentacles(theID);
    
    /// arm
    // there should be a bunch of these
    // constructor: x position, y position, initial theta, length, ID
 
    theAntenna1 = new Antenna(cx, cy, theta, 10, theID);
    theAntenna2 = new Antenna(cx, cy, theta, 10, theID);
    theAntenna3 = new Antenna(cx, cy, theta, 10, theID);
    theAntenna4 = new Antenna(cx, cy, theta, 10, theID);
    theAntenna5 = new Antenna(cx, cy, theta, 10, theID);
    
    // tenta.initID(theID);
   
    xPos = cx;
    yPos = cy;
    speedX = .92f;
    speedY = .92f;
    xOff = 0.0f;
    yOff = 0.0f;
    
   
  }
   
public void update(String tName) {
	 theName = tName;
     roam();
     followTarget();
     tail.xPos = xPos;
     tail.yPos = yPos;
     tail.update();
     tenta.update(tail.xPos, tail.yPos);
     /// make tentacle reach for prey
     
     /*
       pApp.pushMatrix();
	  pApp.rotate(pApp.radians(degree));
	  pApp.translate(x, y);
	  pApp.rotate(pApp.radians(-degree));
      * */

     theAntenna1.move(tail.xPos, tail.yPos, theta+72);
     theAntenna2.move(tail.xPos, tail.yPos, theta-72);
     theAntenna3.move(tail.xPos, tail.yPos, theta);
     theAntenna4.move(tail.xPos, tail.yPos, theta+ 144);
     theAntenna5.move(tail.xPos, tail.yPos, theta-144);

     /// this draws the prey
     renderPrey();
     
  }
   
  void render() {
    tail.render();
  }
   
  //Roam around the screen
  public void roam(){
    xOff = xOff + 0.005f;
    yOff = yOff + 0.009f;
    float vx =  pApp.noise(xOff) * 4;
    float vy =  pApp.noise(yOff)* 3;

    xPos += speedX * vx;
    yPos += speedY * vy;
    
    xPos += pApp.noise(yOff);
    yPos += pApp.noise(xOff);
    
    //// constrain to stage
    ///*
    if(xPos <=0){
      xPos = 0;
      // speedX *=-theFindX; //previous
      speedX *=-1;
    }
    else if(xPos >= pApp.width){
	    xPos = pApp.width;
	    speedX *=-1; // speedX *=-theFindX; // previous
    }
   
    if(yPos <=0){
      yPos = 0;
      speedY *=-1;
      // speedY *=-theFindY; // previous
    }
    else if(yPos >= pApp.height){
      yPos = pApp.height;
      speedY *=-1;
      // speedY *=-theFindY; // previous
    }
  }
   
  //
  public void calculateTheta(float theX, float theY, float pTheX, float pTheY){
	  ///*
	  /// pApp.println("THEEYA: " + theta);
	  deltaX = theX - pTheX;
	  deltaY = theY - pTheY;
	  theta = pApp.atan2(deltaX, deltaY);
	  theta = theta*-36;
	  //*/
	  // theta +=20;
	  
  }

  public void followTarget(){
	/// if the bot hits the prey, and its alpha is 255
	/// set prey as selected
	  
    for(int i =0; i<numPrey; i++) {
      if(prey[i].myAlpha == 255){
    	
        targX = prey[i].x;
        targY = prey[i].y;
        follow = true;
        prey[i].selected = true;
        i=numPrey+1;
      }
     } 
    
     if(follow) {
       fSpeedX = 3.5f;
       fSpeedY = 4.5f;
       dx = targX - xPos;
       dy = targY - yPos;
       dst = pApp.sqrt(pApp.sq(dx) + pApp.sq(dy));
       angle = pApp.atan2(dy,dx);
       
        
       if(dst>numPrey){
          xPos += fSpeedX * pApp.cos(angle);
          yPos += fSpeedY * pApp.sin(angle);
       }else{
    	   
    	   /// if prey's alpha is 0, then selected is false
           for(int i=0;i<numPrey;i++){
             if(prey[i].selected){
               prey[i].myAlpha=0;
               prey[i].isVisible = false; /// visible flag
               prey[i].selected = false;
               
              

               // check prey toggle
               checkPreyEaten();
               
             }
           }
         follow = false;
         // theta = angle * 36;
         // theta_radians = atan2(delta_y, delta_x)
         
       }
     }
     
     // calculateTheta(targX, targY, xPos, yPos); /// calculate angle of bot to prey
     calculateTheta(targX, targY, tail.xPos, tail.yPos); /// calculate angle of bot to prey
     
  }
 private void checkPreyEaten(){
	  int theCounter = 0;
	  for(int i=0;i<numPrey;i++){
		  if(prey[i].isVisible == false){
			  theCounter+=1;
		  }
		 
	  }
	  if (theCounter >= numPrey){
		  pApp.println("EATEN ALL PREY FOR BOT" + theID);
		  eatenAllPrey = true;
		  theCounter = 0;
	  }
	  
  }
   
  
  
  ///// add an x and y to a prey item-- if it exists it has an alpha
  ///// this resets all prey and bot's eating state to visible
public void setPrey(float theX, float theY){
	pApp.println("SET PREY: BOT" + theID);
	eatenAllPrey = false;
    for(int i=0; i<numPrey; i++){
      if(prey[i].myAlpha == 0){
        prey[i].x = theX;
        prey[i].y = theY;
        prey[i].myAlpha = 255;
        prey[i].isVisible = true;
        i=numPrey+1;
        
      }
    }
    // println("botX: " + theX + " botY" + theY);
    
  }
  // make sure prey is visible
  ///*
/// draw prey, update its alpha, pass its id, give it text
public void renderPrey(){
    for(int i=0; i<numPrey; i++){
        if(prey[i].myAlpha != 0){

          prey[i].update(theName);
          i=numPrey+1;
        }
      }
  }
  // */
/// end class
}

