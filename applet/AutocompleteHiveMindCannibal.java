import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;

import processing.core.*;
import processing.opengl.*;
import javax.media.opengl.GL;
/// import processing.core.Graphics;
/// import org.json.*;
import controlP5.*;

public class AutocompleteHiveMindCannibal extends PApplet{
	
	// 
	BotProfile botProfile;
	
	/// set up colors
	int fillColor1;
	int fillColor2;
	float rnd;
	float rnd1;
	float rnd2;
	// TEXT FOR JSON QUERY
	String hasData= "no data";
	String theName = "";
	String textValue = "";
	Textfield myTextfield;
	String theTerm = ""; // "how%20can%20i"; // // what term is being searched
	int theLimit = 5; // term limit
	/// XML STUFF
    XML xml;
    
	/// screen sizes;
	int WIDTH;
	int HEIGHT;
	
	// TEXT FIELDS
	ControlP5 controlP5;
	ControlFont SerifFont;
	int txtPosY = WIDTH + 50; /// position of text field
	int txtPosX = HEIGHT + 50;
	int theColor=color((int) (12), (12),(165)); /// colors for text
	// PFont theFont;
	// theFont = createFont("ArialMT-12",14);
	PFont SanSerif; /// font for auto fill display
	/*
	 * PFont font;
	// Name system font and reqd size
	font = createFont("Arial",14);
	textFont(font);
	text("word", 15, 30); 
	*/
	// number categories
	int numBots = 3;
	// init autofill objects array
	String[] theAutoFill;
	/// init bot manager array
	botManager[] theBotX;
	
	/// query thread

	Boolean isFullScreen = false;
	
	int numPrey = 10; /// this will change depending on the prey category
	
	
	/// set up full screen
	/*
	public static void main(String args[]){
		
		PApplet.main(new String [] {"--present", "src.AutocompleteHiveMindCannibal"});
		
	}
	*/

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
		// size(WIDTH,HEIGHT);
		  
		  /// do json query
		  /// API key: AIzaSyDYnzs0NBMgIvI0otRTO-M7UvtmtdzpYxE
		  /// test query: https://www.googleapis.com/customsearch/v1?key=AIzaSyDYnzs0NBMgIvI0otRTO-M7UvtmtdzpYxE&cx=017576662512468239146:omuauf_lfve&q=lectures
		  /// instantiate array length
		  /// XML query: http://google.com/complete/search?output=toolbar&q=
		  /// JSON query: http://www.freebase.com/private/suggest?prefix=" + queryTerm + "&limit=5";
		  // String theQuery="http://www.freebase.com/private/suggest?prefix="; /// actual query string
		  

		  // botProfile.botProfileInit();
		 
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
		  buildInputText();
		 
	}
	
	 //  SPAWN BOTS AND PREY
	private void spawnBots(){
		//// init the bot color profile
		  BotProfile botProfile = BotProfile.getInstance();

		 for (int i= 0; i<numBots; i++){
			 //// this color stuff should be in the bots themselves
			 rnd = random(1);
			 rnd1 = random(1);
			 rnd2 = random(1);
			 
			 // fillColor1 = color((int) (rnd1*255), (rnd2*255),(rnd*255)); // color((int) (rnd1*255), (rnd2*255),(rnd*255)); 
			 // fillColor1 = color((int)(rnd1*255));
			 float theX = random (0,800);
			 float theY = random (0,600);
			///  println("X : " + theX + " Y: " + theY + " : color : " + rnd2*255);
			 theBotX[i] = new botManager();
			 theBotX[i].createBot(g, i);
			 // int tempColor = int(rnd2*255);
			 /// set a random color in the bot profile array
			 botProfile.setColor(i, Math.round(rnd2*255));
			 
		 }
		 
	}

	 public void spawnAllPrey(){
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
	 
	//////// DRAW
	public void draw() {
		 background(0,0,0);
		 smooth();
		  ///// draw the bots
		 for (int i= 0; i<numBots; i++){
			 /// check for name
			 if(theAutoFill[i] != null){
				 theName = theAutoFill[i];
			 } else {
				 theName = "";
			 }
			 theBotX[i].updateBot(theName); 
		 }
		 // if typing, do an autofill search and populate the array
		 if (keyPressed == true) {
			 doSearch();
			
		 }
		 showAutoFills();
		 
		// textFont(SanSerif);
		// text("I AM TEST TEXT", 15, 30); 

		
	}
	//// end draw
	////////////////////
	
    /// JSON SEARCH FUNCTIONS
	public void doSearch(){
		/// println("search");
		// parse for unicode
		theTerm = myTextfield.getText();
		theTerm = theTerm.replaceAll(" ", "%20");
		// /*
		// add term to search and do the json query
		if(theTerm != null){
			/// this calls to an external thread
			thread("doQueryThread");
			
		}
		
	}
	
	/// this is a separate thread just for doing a query
	public void doQueryThread(){
		/// returns poorly formed json
		// String request = "http://suggestqueries.google.com/complete/search?client=firefox&q=" + theTerm;
		
		/// returns xml, but the flavor of returns is really good
		// String request = "https://www.googleapis.com/complete/v1?key=AIzaSyDYnzs0NBMgIvI0otRTO-M7UvtmtdzpYxE&cx=017576662512468239146:omuauf_lfve&q=" + theTerm;
		String request = "http://clients5.google.com/complete/search?hl=en&q=" + theTerm + "&client=ie8&inputencoding=UTF-8&outputencoding=hjson";
		
		/// this one works, but the results are lame
		/// String request = "http://www.freebase.com/private/suggest?prefix=" + theTerm + "&limit=5"; //  + theTerm;

		String theXML[] = loadStrings(request);
		// loadStrings( request );
		
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
		 // myTextfield.upperCase(true);
		 /// controlP5.addTextfield("autoFill",0,40,200,20);
		 /*
		  *   b.captionLabel().setControlFont(font);
		  b.captionLabel().setControlFontSize(80);
		  */
	}
	
	//// activates on press "return"
	public void controlEvent(ControlEvent theEvent) {
		String theInput = theEvent.controller().stringValue();
		/// parse for unicode
		// println("I HAVE TYPED: " + theInput);
		theTerm=theInput;
		/// spawn prey instead
		spawnAllPrey();
		// doSearch();
	}
	
	//// this shows the autofill info below the text box
	public void showAutoFills(){
		String af0 = theAutoFill[0];
		/// myTextfield.setText(hasData);
		 // SanSerif = createFont("DIN-Regular-16.vlw", 12); /// font for display
		 textFont(SanSerif);
		// 
		if(af0 != null){
			
			//// println("show autofill pos 0" + af0);
			 for (int i= 0; i<numBots; i++){
				 if(theAutoFill[i] != null || theAutoFill[i] != ""){
					 String txtFldTxt = myTextfield.getText();
					 float newTxtPos = txtFldTxt.length() *8;
					 /// remove typed text from results
					 String tmpString = theAutoFill[i].replace(txtFldTxt,"");
					 int newTxtPosY = txtPosY + i * 15;
					 fill(255,255,255);
					 SanSerif = createFont("DIN-Regular-16.vlw", 12); /// font for display
					 textFont(SanSerif);
				     text(tmpString, txtPosX + newTxtPos + 5, newTxtPosY + 40);
				     
				    
				     // text(theAutoFill[i], txtPosX + 20, txtPosY + 50);
				 }
				 
			 }
		}
		fill(255,255,255);
		SanSerif = createFont("DIN-Regular-16.vlw", 12);
		textFont(SanSerif);
		text("What", 100, 100);

	}
////KEY INPUT
	public void keyPressed() {
		/// grrr
		/// myTextfield.setText(hasData);
		 if (key == CODED) {
		   if (keyCode == LEFT) {
			   println("SHOW SHARING");
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
