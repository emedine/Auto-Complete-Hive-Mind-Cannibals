import processing.core.PGraphics;

//// this initializes the legs and sets their position

class Tentacles{
  int theID;
  PGraphics g;
  int theFillColor;
  
  Leg L1;
  Leg L2;
  Leg L3;
  Leg L4;
  Leg L5;
  Leg L6;
  
  
  Tentacles(int ID){
	  theID = ID;
	
	  
	  L1 = new Leg(120, 4, .04f, 0, 0, 0, theID);
	  L2 = new Leg(110, 6, .06f, 0, 0, 0, theID);
	  L3 = new Leg(110, 8, .08f, 0, 0, 0, theID);
	  L4 = new Leg(60, 10, .10f, 0, 0, 0, theID);
	  L5 = new Leg(110, 12, .11f, 0, 0, 0, theID);
	  L6 = new Leg(110, 14, .12f, 0, 0, 0, theID);
	  /*
	   * 
	   
	  Leg L7 = new Leg(120, 16, .13f, 0, 0, 0);
	  Leg L8 = new Leg(120, 18, .14f, 0, 0, 0);
	  Leg L9 = new Leg(120, 20, .15f, 0, 0, 0);
	  Leg L10 = new Leg(120, 22, .16f, 0, 0, 0);
	  */
  }

  Meander mea = new Meander();
  
  
   
  void update(float x, float y, PGraphics pG) {
	g = pG;
    L1.wriggle(x,y, g);
    L2.wriggle(x,y, g);
    L3.wriggle(x,y, g);
    L4.wriggle(x,y, g);
    L5.wriggle(x,y, g);
    L6.wriggle(x,y, g);
    /*
    L7.wriggle(x,y, theFillColor, g);
    L8.wriggle(x,y, theFillColor, g);
    L9.wriggle(x,y, theFillColor, g);
    L10.wriggle(x,y, theFillColor, g);
    */
    mea.move(g);
  }
}
