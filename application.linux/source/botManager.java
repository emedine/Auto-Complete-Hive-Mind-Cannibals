import processing.core.*;
import processing.core.PApplet;

class botManager extends PApplet{

  AaleBot aaleBot;
  
  float mX;
  float mY;
  
  int theID;
  boolean hasEaten;
  
  String theName;
  
  // 
  BotProfile botProfile;
  
  /// init constructor
  botManager(){
  }
  //create the aale bot here
public void  createBot(int ID) {
	theID = ID;
	println("create bot ID: " + theID);
	 /// init p graphics

    aaleBot = new AaleBot(theID);
  }
   //
  void updateBot(String tName) {
	theName = tName;
    aaleBot.update(theName);
    for(int i =0; i<5; i++){
      // prey[i].update();
      /// 
      renderBot();
      // renderPrey(fillColor);
      
    }
    
  }
  public boolean checkBot(){
	  if(aaleBot.eatenAllPrey){
		  // println("Bot" + theID + " : " + aaleBot.eatenAllPrey);
	      return (true);
	  } else {

		  return (false);
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
