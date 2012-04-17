import processing.core.PApplet;
import processing.core.PGraphics;
// aalebot all movement code goes here
// bot with its body parts it contols its movement
 
class AaleBot extends PApplet{
  float xPos = 300;
  float yPos = 200; // position of the bot
  float speedX, speedY; // speed of the bot
  float cx,cy; // stage centers
  float xOff,yOff; // random location
  float targX, targY;// target x and y
  boolean follow = false;
  // import tail and do the magic
  bTail tail;
  Tentacles tenta;
  
  int theFillColor;
  /// this randomness should adjust mvt
  float theFindX;
  float theFindY;
  ///
  String theName;
  
  PGraphics g;
  /// array of prey for each aale
  Prey[] prey;
  
  int theID;
  
  int numPrey = 10;
  AaleBot(PGraphics pG,int ID) {
	theID = ID;
	/// println("AALE BOT ID: " + theID);
	/// theFillColor = fillColor1;
	g = pG;
    //create the prey
    prey = new Prey[numPrey];
    /// instead of an object
    /// make prey a simple shape with 
    /// x and y coordinates
    prey[0] = new Prey(g, theID);
    prey[1] = new Prey(g, theID);
    prey[2] = new Prey(g, theID);
    prey[3] = new Prey(g, theID);
    prey[4] = new Prey(g, theID);
    prey[5] = new Prey(g, theID);
    prey[6] = new Prey(g, theID);
    prey[7] = new Prey(g, theID);
    prey[8] = new Prey(g, theID);
    prey[9] = new Prey(g, theID);
    
    // this should make it wander more
    // but it doesn't
    theFindX = random(6);
    theFindY = random(6);
    
    
    cx = g.width/2;
    cy = g.height/2;
    targX =0;
    targY =0;
    tail = new bTail(theID);
    tenta = new Tentacles(theID);
    // tenta.initID(theID);
    tail.xPos = cx;
    tail.yPos = cy;
    xPos = cx;
    yPos = cy;
    speedX = .62f;
    speedY = .62f;
    xOff = 0.0f;
    yOff = 0.0f;
    
   
  }
   
  void update(String tName) {
	 theName = tName;
     roam();
     followTarget();
     // followMouse();
     tail.xPos = xPos;
     tail.yPos = yPos;
     tail.update();
     tenta.update(tail.xPos, tail.yPos, g);
     /// this draws the prey
     renderPrey(theFillColor);
     
  }
   
  void render() {
    tail.render(theFillColor, g);
  }
   
  //Roam around the screen
  void roam(){
    xOff = xOff + 0.005f;
    yOff = yOff + 0.009f;
    float vx =  noise(xOff) * 4;
    float vy =  noise(yOff)* 3;
    //line(n, 0, n, height);
    // ellipse(n,n1,20,20);
   

    xPos += speedX * vx;
    yPos += speedY * vy;
    
    // xPos += 1; // noise(yOff);
    // yPos += 1; // noise(xOff);
    
    xPos += noise(yOff);
    yPos += noise(xOff);
    
    //// constrain to stage
    ///*
    if(xPos <=0){
      xPos = 0;
      // speedX *=-theFindX; //previous
      speedX *=-1;
    }
    else if(xPos >= g.width){
	    xPos = g.width;
	    speedX *=-1; // speedX *=-theFindX; // previous
    }
   
    if(yPos <=0){
      yPos = 0;
      speedY *=-1;
      // speedY *=-theFindY; // previous
    }
    else if(yPos >= g.height){
      yPos = g.height;
      speedY *=-1;
      // speedY *=-theFindY; // previous
    }
    // println("FIND Y: " + theFindY);
   // ellipse(xPos, yPos, 20, 20);
   //*/
  }
   
  //
  void followTarget(){
	  
    for(int i =0; i<numPrey; i++) {
      if(prey[i].myAlpha == 255){
        targX = prey[i].x;
        targY = prey[i].y;
        // println("targX: " + targX + " targY" + targY);
        follow = true;
        prey[i].selected = true;
        i=11;
      }
     } 
    
     if(follow) {
       float speedX = 3.5f;
       float speedY = 4.5f;
       float dx = targX - xPos;
       float dy = targY - yPos;
       float dst = sqrt(sq(dx) + sq(dy));
       float angle = atan2(dy,dx);
   
       if(dst>numPrey){
          xPos += speedX * cos(angle);
          yPos += speedY * sin(angle);
       }else{
           for(int i=0;i<numPrey;i++){
             if(prey[i].selected){
               prey[i].myAlpha=0;
               prey[i].selected = false;
               i=11;
             }
           }
         follow = false;
       }
     }
     
  }
   
  // follow mouse
  // disabled
  void followMouse(){
     float speedX = 1.5f;
     float speedY = 1.5f;
     float dx = mouseX - xPos;
     float dy = mouseY - yPos;
     float dst = sqrt(sq(dx) + sq(dy));
     float angle = atan2(dy,dx);
     //for orient convert angle to degree
     // and assign to rotation
     if(dst>numPrey){
        xPos += speedX * cos(angle);
        yPos += speedY * sin(angle);
     }
  }
  
  
  ///// add an x and y to a prey item-- if it exists it has an alpha
  void setPrey(float theX, float theY){
     
    for(int i=0; i<numPrey; i++){
      if(prey[i].myAlpha == 0){
        prey[i].x = theX;
        prey[i].y = theY;
        prey[i].myAlpha = 255;
        i=11;
      }
    }
    // println("botX: " + theX + " botY" + theY);
    
  }
  // make sure prey is visible
  ///*
  void renderPrey(int fillColor){
    for(int i=0; i<numPrey; i++){
        if(prey[i].myAlpha != 0){

          prey[i].update(fillColor, theName);
          i=11;
        }
      }
  }
  // */
/// end class
}

