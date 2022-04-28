class SpikyWave { 
  float SpikeAngle = 90; //how far the spikes extrude
  int numSegments;         //the amount of bubble segments there are
  int numNodes;
  float theta;             //stores the distance between each stored node. there are 2*numSegments nodes
  boolean fill;            //is the shape filled in or not
  float spawnOffset;
  
  Color lineColor = new Color(0,0,0);
  polarPoint[] points; //initialized in constructor
  
  //constructor
  SpikyWave (int numSegments,float spawnRadius, float spawnOffset, Color lineColor, boolean fill) {  
    this.numSegments = numSegments;
    this.numNodes = numSegments * 2;
    this.lineColor = lineColor; 
    this.fill = fill;
    this.spawnOffset = spawnOffset;
    
    points = new polarPoint[numNodes];
    
    theta = (2*PI) / numNodes;
    for (int i = 0; i < numNodes; i ++)
    {
      points[i] = new polarPoint(spawnRadius, MASTER_ROTATION + theta*i + spawnOffset*theta/2);
    }
    calculateBulgeEdge();
  } 
  
  void calculateBulgeEdge()
  {
    for (int i = 1; i < numNodes-1; i=i+2) //last one must be calculated manually since it wraps around the first value
    {
      points[i] = findBulge(points[i-1], points[i+1]);
    }
    points[numNodes-1] = findBulge(points[numSegments*2-2], points[0]); //last value
  }
  
  polarPoint findBulge(polarPoint point1, polarPoint point2) //finds the node point the bulge will extend to based on the bubble angle given. must be updated as radius increases
  {
    float interiorAngle = (180 - SpikeAngle)/2;
    float distanceBetweenPoints = sqrt(pow(point1.r,2)+pow(point2.r,2)-2*point1.r*point2.r*cos(point1.t-point2.t));
    float newR = point1.r + abs(tan(interiorAngle*2*PI/360))*distanceBetweenPoints/2;
    float newT = point1.t + theta;
    polarPoint point = new polarPoint(newR, newT);
    return point;
  }
  
  void changeCoords() //update the coordinates and move the nodes
  {
    for (int i = 0; i < numSegments*2; i++)
    {
      
      points[i].r += MASTER_SPEED; //mkae the radius bigger at a certain speed
      points[i].t = MASTER_ROTATION + theta*i + spawnOffset*theta/2; //spinny! based on master rotation so everything moves together regardless of when it spawns in
    }
  }
  
  void drawShape() {
    float cartX1, cartY1; //have to draw in cartesian coordinates
    if (fill)
    {
      fill(lineColor.r,lineColor.g,lineColor.b);
    }
    beginShape();
    
    for (int i = 0; i < numSegments*2; i++)
    {
      cartX1 = width/2 + points[i].r * cos(points[i].t);
      cartY1 = height/2 + points[i].r * sin(points[i].t);
      vertex(cartX1, cartY1);
    }
    
    endShape(CLOSE);
    noFill();
  }
  
  void drawPoints()
  {
    stroke(255,0,0); //draw them red to be easier to see
    strokeWeight(4); //draw them nice and thicc
    for (int i = 0; i < numSegments*2; i++)
    {
      stroke(points[i].t*255/2/PI,0,0);
      float cartX = points[i].r * cos(points[i].t);
      float cartY = points[i].r * sin(points[i].t);
      point(width/2 + cartX, height/2 + cartY);
    }
    stroke(0,0,0);
    strokeWeight(1);
  }
  
  void update() { 
    stroke(lineColor.r, lineColor.g, lineColor.b);
    changeCoords();
    calculateBulgeEdge();
    drawShape();
  } 
} 