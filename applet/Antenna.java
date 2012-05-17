import processing.core.PApplet;
import processing.core.PVector;

// The Tentacle class
public class Antenna {
	// applet
  PApplet pApp;
  BotProfile botProfile;
  /// color
  int theFillColor;
  
  // ID
  int theID;


  int numNodes = 10;        // total number of nodes
  PVector[] nodes;
  float x, y;               // current location

  float head;        // the general size
  float girth;      // the general speed
  float speedCoefficient;  // locomotion efficiency (0 - 1)
  float friction;          // the viscosity of the water (0 - 1)
  float muscleRange;              // muscular range
  float muscleFreq;       // muscular frequency
  float theDegree; 		/// degree rotation
  float tv, theta;
  int count;
  int ext = 50;                      // boundary bleeding
  int angleMod = -90;		// modifier so they face forward

  // constructor: x position, y position, initial theta, length
  Antenna (float xLoc, float yLoc, float initTheta, int theLength, int ID) {
  theID = ID;
  /// init the applet
  /// get an instance of a color profille
  BotProfile botProfile = BotProfile.getInstance();
  theFillColor = botProfile.getBotColor(theID);
  pApp = botProfile.pApp;

  /// init the objects
  numNodes = theLength; // number of nodes
  nodes = new PVector[numNodes]; /// init nodes array
  head = 2 + pApp.random(4);        // the general size
  girth = 8 + pApp.random(12);      // the general speed
  speedCoefficient = .09f + pApp.random(10) / 500;  // locomotion efficiency (0 - 1)
  friction = 0.9f + pApp.random(10) / 100;          // the viscosity of the water (0 - 1)
  muscleRange = 20 + pApp.random(50);              // muscular range
  muscleFreq = 0.1f + pApp.random(100) / 250;       // muscular frequency
  ext = 50;                      // boundary bleeding

    x = xLoc;
    y = yLoc;
    theta = initTheta;

    for (int n = 0; n < numNodes; n++) {
      nodes[n] = new PVector(0, 0);
    }
  }

  // calculates position
  public void move(float theX, float theY, float degree) {
	theDegree = degree;
    float thetaMuscle;
    float dx, dy, d;
    x = theX;
    y = theY;

    // directional node with orbiting handle
    // arbitrary direction
    /// should make this go outward or inward instead
    // tv += 0.5 * (pApp.random(10) - pApp.random(10)) / 10.0;
    tv += 0.5 * (pApp.random(theta) - pApp.random(theta)) / 10.0;
    theta = theDegree + angleMod;
    // theta += tv;
    tv *= friction;
    // pApp.println("TV: " + tv);
    if(theID == 1){
    	pApp.println("MY THETA" + theta);
    }

    nodes[0].x = head * pApp.cos(pApp.radians(theta));
    nodes[0].y = head * pApp.sin(pApp.radians(theta));

    // muscular node
    count += muscleFreq;
    thetaMuscle = muscleRange * pApp.sin(count);
    nodes[1].x = -head * pApp.cos(pApp.radians(theta + thetaMuscle));
    nodes[1].y = -head * pApp.sin(pApp.radians(theta + thetaMuscle));

    // apply kinetic forces down through body nodes
    for (int i = 2; i < nodes.length; i++) {
      dx = nodes[i].x - nodes[i - 2].x;
      dy = nodes[i].y - nodes[i - 2].y;

      d = pApp.sqrt(dx * dx + dy * dy);
      nodes[i].x = nodes[i - 1].x + (dx * girth) / d;
      nodes[i].y = nodes[i - 1].y + (dy * girth) / d;

    }
    display();
  }
  
  // does drawing
private void display() {

	// pApp.println("ANTENNA COLOR: " + theFillColor);
	int newFillColor = 0;
	pApp.pushMatrix();
	pApp.translate(x, y);
    // draw nodes using lines

    for (int i = 0; i < numNodes-1; i++) {
    	newFillColor = theFillColor; //  += i*25;
    	pApp.fill(newFillColor);
    	pApp.stroke(newFillColor);
      // this.lineStyle((this.numNodes/(i-1))*1.5, 0xFFFFFF, 100);  // with head
      // this.lineStyle((this.numNodes-i), 0xFFFFFF, 100);  // with no head

    	pApp.strokeWeight(pApp.sq(numNodes - i) / 10);
    	pApp.line(nodes[i].x, nodes[i].y, nodes[i].x, nodes[i].y);
    }
    pApp.popMatrix();
  }

}
