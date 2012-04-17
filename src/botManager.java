import processing.core.PGraphics;
import processing.core.PApplet;

class botManager extends PApplet{

  AaleBot aaleBot;
  
  float mX;
  float mY;
  
  int theID;
  
  String theName;
  
  PGraphics g;
  
	// 
	BotProfile botProfile;
  
  /// init constructor
  botManager(){
  }
  //create the aale bot here
  void  createBot(PGraphics pg, int ID) {
	theID = ID;
	 println("Manager ID: " + theID);
	 /// init p graphics
	g = pg;
    aaleBot = new AaleBot(g, theID);
  }
   //
  void updateBot(String tName) {
	theName = tName;
    aaleBot.update(theName);
    for(int i =0; i<5; i++){
      // prey[i].update();
      renderBot();
      // renderPrey(fillColor);
    }
    
  }
  
  
  /// add a prey to the array and give it a position
/// *   
  void doPrey(float theX, float theY){
    mX = theX;
    mY = theY;
    aaleBot.setPrey(mX, mY);
    // println("mgrX: " + theX + " mgrY" + theY);
    
  }
  // */
   
  void renderBot() {
    aaleBot.render();
  }
  
   
 //// end class
}
