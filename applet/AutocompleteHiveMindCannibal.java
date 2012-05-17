import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;

import processing.core.*;

import controlP5.*;

/* TO DO
 * make backup // done
 * do "no internet" error fix
 * do autosearch timer-- get "why does, why won't, where is, how big, how small, when will, where can, who is, how do, is there"
 * cycle through each iteration /// done
 * link google trends 
 * fix how color works
 * set up twitter call for data /// not for this fork
 * do layout and instruction text /// not for this fork
 * fix fullscreen issues
 * */



public class AutocompleteHiveMindCannibal extends PApplet{
	
public static void main(String args[]){
		
		PApplet.main(new String [] {"--present", "AutocompleteHiveMindCannibal"});
		
	}
	
	// 
	BotProfile botProfile;
	
	/// set up colors
	int fillColor1;
	int fillColor2;
	float rnd;
	float rnd1;
	float rnd2;
	// TEXT FOR JSON GOOGLE SEARCH
	String hasData= "no data";
	String theName = "";
	String textValue = "";
	Textfield myTextfield;
	String theTerm = ""; // "how%20can%20i"; // // what term is being searched
	int theLimit = 5; // term limit
	/// XML STUFF
    XML xml;
    String trendsPath = "http://www.google.com/trends/hottrends/atom/hourly"; /// query for top twenty google searches
    
    /// search array
    int searchTermCounter = 0;
    // {"why does","why won't","where is","how big","how small","when will","where can","who is","how do","is there"}; 
    String searchTermArray[] = {"why does","why won't","where is","how big","how small","when will","where can","who is","how do","is there"}; 
    
    /// twitter data
  	String userAddress = "@emaqdesign"; /// default user tweets
  	String userStatus = "http://twitter.com/statuses/user_timeline/" + userAddress + ".json?count=5";
  	String twitSearch = "zombie apocalypse";
	
	// color, size, and bground parame
	int theColor=color((int) (12), (12),(165)); /// colors for text
	int WIDTH;
	int HEIGHT;
	
	// TEXT FIELDS
	ControlP5 controlP5;
	ControlFont SerifFont;
	int txtPosY = WIDTH + 50; /// position of text field
	int txtPosX = HEIGHT + 50;
	PFont SanSerif; /// font for auto fill display
	
	
	// number bots
	int numBots = 5;
	// init autofill objects array
	String[] theAutoFill;
	/// init bot manager array
	botManager[] theBotX;

	Boolean isFullScreen = false;
	Boolean isTwitterSearch = false;
	
	int numPrey; /// this will change depending on the prey category
	
	
	

	public void setup() {
		
		//// get the monitor dimensions
		 GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
	     GraphicsDevice devices[] = environment.getScreenDevices();
	     if(isFullScreen == true){
		     if(devices.length>1 ){ //we have a 2nd display/projector
	           //learn the true dimensions of the secondary display
	           WIDTH =devices[1].getDisplayMode().getWidth();
	           HEIGHT= devices[1].getDisplayMode().getHeight();
	           println("Adjusting animation size to "+WIDTH+"x"+HEIGHT+" b/c of 2ndary display");
	       }else{ //no 2nd screen but make it fullscreen anyway
	           WIDTH =devices[0].getDisplayMode().getWidth();
	           HEIGHT= devices[0].getDisplayMode().getHeight();
	           
	           println("Adjusting animation size to "+WIDTH+"x"+HEIGHT+" to fit primary display");
	       }
		 }else {
	     
	     WIDTH = 800;
	     HEIGHT = 600;
	     }
	     //WIDTH = 800;
	     //HEIGHT = 600;
	    
		 size(800,600);
		 smooth();
		// size(WIDTH,HEIGHT);
		  
		  /// do json query
		  /// API key: AIzaSyDYnzs0NBMgIvI0otRTO-M7UvtmtdzpYxE
		  /// test query: https://www.googleapis.com/customsearch/v1?key=AIzaSyDYnzs0NBMgIvI0otRTO-M7UvtmtdzpYxE&cx=017576662512468239146:omuauf_lfve&q=lectures
		  /// instantiate array length
		  /// XML query: http://google.com/complete/search?output=toolbar&q=
		  /// JSON query: http://www.freebase.com/private/suggest?prefix=" + queryTerm + "&limit=5";
		  // String theQuery="http://www.freebase.com/private/suggest?prefix="; /// actual query string
		  
		 /// init bot profile
		  botProfile = BotProfile.getInstance();
		  botProfile.botProfileInit(this);
		  numPrey = botProfile.numPrey;
		 
		  // instantiate bot manager and auto fill text arrays
		  theBotX = new botManager[numBots];
		  theAutoFill = new String[numPrey];
		  
			// init font
		  SanSerif = createFont("DIN-Regular-16.vlw", 12); /// font for display
		  // PFont pfont = createFont("Times",20,true); // use true/false for smooth/no-smooth
		  SerifFont = new ControlFont(SanSerif);
		  
		  // instantiate input text field
		  controlP5 = new ControlP5(this); 
		  
		  /// spawn bots
		  spawnBots();
		  // start search
		  // doSearch();
		  /// build text fields
		  // buildInputText();
		  
		  /// start the search array\
		  initMasterSearchArray();
		  
		  /// start search
		  doSearch();
		 
	}
	
	 //  SPAWN BOTS AND PREY
	private void spawnBots(){
		
		  // BotProfile.setApplet(this);
		  int j;
		 for (int i= 0; i<numBots; i++){
			 //// build color arrays in each bot
			 rnd = random(1);
			 rnd1 = random(1);
			 rnd2 = random(1);
			 
			 fillColor1 = color((int) (rnd1*255), (rnd2*255),(rnd*255)); // color((int) (rnd1*255), (rnd2*255),(rnd*255)); 
			 botProfile.setColor(fillColor1);
			 // fillColor1 = color((int)(rnd1*255));
			///  println("X : " + theX + " Y: " + theY + " : color : " + rnd2*255);
			 theBotX[i] = new botManager();
			 theBotX[i].createBot(i);
			 // int tempColor = int(rnd2*255);
			 /// set a random color in the bot profile array
			 
			 
			 
			 
		 }
		 //SET ALL PREY
	}

	 public void spawnAllPrey(){
		 println("SPAWNING PREY");
		 for (int i= 0; i<numBots; i++){
			 //// create prey for each bot
			 for (int y=0; y<numPrey; y++){
				 float theX = random (0,800);
				 float theY = random (0,600);
				 theBotX[i].doPrey(theX, theY);
			 }
			 
		 }
		 
	 }
	 ////// end spawn bots and prey
	 
	 
	 //// INIT MASTER ARRAY
	 private void initMasterSearchArray(){
		 theTerm = searchTermArray[searchTermCounter];
		 
	 }
	/////////////////////////////////////
	//////// DRAW //////////////////////////
	 
	public void draw() {
		// run a for loop and see if all the prey is eaten
		/// if so, increment search term counter and do search
		 int hungerCounter = 0;
		 background(0);
		 
		  ///// draw the bots
		 for (int i= 0; i<numBots; i++){
			 /// check for name
			 /// println("PREY NAME: " + theAutoFill[i]);
			 if(theAutoFill[i] != null){
				 theName = theAutoFill[i];
				 // println("prey name: " + theAutoFill[i]);
			 } else {
				 theName = "";
			 }
			 //// update bot position, name, and movement
			 theBotX[i].updateBot(theName); 
			 
			 /// check to see if the bot has eaten all prey
			 boolean isFull;
			 try {
				isFull = theBotX[i].checkBot();
				/// if so, increment counter
				 if(isFull){
					 hungerCounter ++;
					 // println("BOT" + i + " IS FULL");
				 }
					
			 } catch (Exception e){
				 println("Failed to return food check");
			 }
			 
			 //// if counter == number of bots, then all bots are full
			 //// reset hunger counter
			 //// increment search term counter and 
			 //// redo search
			 if (hungerCounter >= numBots){
				  hungerCounter = 0;
				  searchTermCounter ++;
				  if(searchTermCounter >= searchTermArray.length){
					  searchTermCounter = 0;
				  }
				  try{
				  println("EATEN ALL PREY FOR ALL BOTS");
				  println("NEW SEARCH TERM IS: " +searchTermArray[searchTermCounter]);
				  } catch (Exception e){
					  println("reset search error: " + e);
				  }
				  
				  doSearch(); //// do a new search on the thread
				  spawnAllPrey(); /// redo all prey
			  }
			 
			 
		 }
		 showAutoFills();
		
	}
	//// end draw
	////////////////////
	
    /// JSON SEARCH FUNCTIONS
	public void doSearch(){
		/// println("search");
		// parse for unicode
		// theTerm = myTextfield.getText();  /// we're using the master array now, not the text field data
		theTerm = searchTermArray[searchTermCounter].replaceAll(" ", "%20");
		if(isTwitterSearch){
				doTwitterThread();
			} else {
			// add term to search and do the json query
			if(theTerm != null){
				/// this calls to an external thread
				thread("doQueryThread");
				// doTwitterThread();
				
			}
		}
		
	}
	public void doTwitterThread(){
		twitSearch = twitSearch.replaceAll(" ", "%20");
		/// this is for user tweets
		/// String request = "http://twitter.com/statuses/user_timeline/" + searchTerm + ".xml?count=" + String(searchLength)); /// gets particular user tweets
		/// this is for searchterms	
		String request = "http://search.twitter.com/search.rss?q=%23" + twitSearch;
		String theXML[] = loadStrings(request);
		/// PARSE XML
		/// get rid of whitespace
		String theXMLString = join(theXML, "").replace(">  <", "><");
		//// get the results
		XML xmlReturn = XML.parse(theXMLString);
		XML resultNode = xmlReturn.getChild(0);
		try{
			int twitNodeMin = 6; /// first couple nodes in twitter return are useless, so uset twitnodemin to jump to good data
			for (int i = twitNodeMin; i < numBots + twitNodeMin; i++) {
				 XML kid = resultNode.getChild(i);
				    /// XML subNode = kid.getChild(1);
				 String check = kid.getContent();
				 //// populate the entries of the autoFillArray that 
				 /// correspond with the bot fill array
			     theAutoFill[i - twitNodeMin] = check;
				 println(theAutoFill[i]);
				 // println("value1: " + resultNode.getChild(0).getContent());
			   
			  }
		} catch (Exception ex){
			println("no data: " + ex);
			hasData = "no data";
		}
		
	}
	
	
	/// this is a separate thread just for doing a query
	public void doQueryThread(){

		/// returns xml, but the flavor of returns is really good
		// String request = "https://www.googleapis.com/complete/v1?key=AIzaSyDYnzs0NBMgIvI0otRTO-M7UvtmtdzpYxE&cx=017576662512468239146:omuauf_lfve&q=" + theTerm;
		String request = "http://clients5.google.com/complete/search?hl=en&q=" + theTerm + "&client=ie8&inputencoding=UTF-8&outputencoding=hjson";
		String theXML[] = loadStrings(request);

		/// PARSE XML
		/// get rid of whitespace
		String theXMLString = join(theXML, "").replace(">  <", "><");
		try{
			//// get the results
			xml = XML.parse(theXMLString);
			XML resultNode = xml.getChild(1);
			int numResults = resultNode.getChildCount();
			println("numSites: " + numResults);
			println("name: " +resultNode.getName());
			hasData = "data yes! " + resultNode.getName();
			for (int i = 0; i < 5; i++) {
			    XML kid = resultNode.getChild(i);
			    /// XML subNode = kid.getChild(1);
			    String check = kid.getContent();
			    // String url = kid.getString("query");
			    // String site = kid.getContent();
			    println(i + " : " + check + " : ");
			    theAutoFill[i] = check;
				if(theAutoFill[i] != null){
					println("NAME: " + theAutoFill[i]);
				}
			  }
		} catch (Exception ex){
			println("XML: " + ex);
			hasData = "no data";
		}
	}

	 //create prey object with mouse clicks
	 public void mousePressed(){
		 // println("X: " + mouseX + " Y " + mouseY);
		 spawnAllPrey();
		
	 }

	 
	 /////// TEXT INPUT FUNCTIONS
	private void buildInputText(){
		/// theName,theX,theY,theWidth,theHeight
		 myTextfield = controlP5.addTextfield("",txtPosX,txtPosY,200,25);
		 myTextfield.setFocus(true);
		 // myTextfield.setColorBackground(theColor);
		 myTextfield.setColorActive(color(0,0,0,0));
		 myTextfield.setColorBackground(color(125,125,125,125));
		 controlP5.setControlFont(SanSerif);
		 myTextfield.keepFocus(true);
		
	}
	
	//// activates on press "return"
	public void controlEvent(ControlEvent theEvent) {
		String theInput = theEvent.controller().stringValue();
		/// parse for unicode
		// println("I HAVE TYPED: " + theInput);
		// theTerm=theInput; /// we're using the term array now, not the entered text
		twitSearch = theInput;
		/// spawn prey instead
		spawnAllPrey();
		// doSearch();
	}
	
	//// this shows the autofill info below the text box
	public void showAutoFills(){
		String af0 = theAutoFill[0];
		// 
		if(af0 != null){
			
			//// println("show autofill pos 0" + af0);
			 for (int i= 0; i<numBots; i++){
				 if(theAutoFill[i] != null || theAutoFill[i] != ""){
					 // String txtFldTxt = myTextfield.getText();
					 /// float newTxtPos = txtFldTxt.length() *8;
					 /// remove typed text from results
					 String tmpString = "";
					 /*
					 if(isTwitterSearch != true){
						 tmpString = theAutoFill[i].replace(txtFldTxt,"");
					 }
					 */
					 int newTxtPosY = txtPosY + i * 15;
					 fill(255,255,255);
					 SanSerif = createFont("DIN-Regular-16.vlw", 12); /// font for display
					 textFont(SanSerif);
				     text(tmpString, txtPosX + txtPosX + 5, newTxtPosY + 40);
				 }
				 
			 }
		}
		/*
		fill(255,255,255);
		SanSerif = createFont("DIN-Regular-16.vlw", 12);
		textFont(SanSerif);
		text("What", 100, 100);
		*/

	}
////KEY INPUT
	public void keyPressed() {
		/// grrr
		/// myTextfield.setText(hasData);
		 if (key == CODED) {
		   if (keyCode == LEFT) {
			   println("SHOW SHARING");
			   if(isTwitterSearch){
				   isTwitterSearch = false;
			   } else {
				   isTwitterSearch = true; 
			   }
			   isFullScreen = true;
		   } else if (keyCode == RIGHT) {
			   println("HIDE SHARING");
			   isFullScreen = false;
		   }
		 }
	}

	
	/// end class
/////// 
}
