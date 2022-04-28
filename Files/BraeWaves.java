import java.util.LinkedList;

float MASTER_ROTATION = 0; //keeps track of a position
float MASTER_SPEED = 2;    //is just a constant
float bubbleTimer = 1000; 
float spikyTimer = 1000; 


//controlers
BubbleWaveManager BWM = new BubbleWaveManager();
SpikyWaveManager SWM = new SpikyWaveManager();

void setup() {
  size(1000, 500);
  //fullScreen();
  noFill();
}
int a = 10;
void draw() {
  background(255);
  MASTER_ROTATION+=0.005; //this exists so everything syncs up
  
  UpdateControllers();
  CheckDeleteControllers();
  
  if (millis() > spikyTimer)
  {
    float randomOffset = random(0,2);
    spikyTimer += 1500;
    SWM.Add(new SpikyWave(10,0,randomOffset, randomColor(),true));

    bubbleTimer += 1000;
    BWM.Add(new BubbleWave(10, 0, randomOffset+2, randomColor(),true));
  }
}

void UpdateControllers()
{
  
  for (int i = 0; i < BWM.bubbleWaves.size(); i++)
  {
    BWM.bubbleWaves.get(i).update();
    SWM.spikyWaves.get(i).update();
  }
}

void CheckDeleteControllers()
{
  if (BWM.bubbleWaves.size() > 1)
  {
    if (BWM.bubbleWaves.get(1).points[0].r > sqrt(pow((width/2),2) + pow((height/2),2)) + 10) //if the radius is of the next one is off the page and then some
    {
      println("deleting first bubble");
      BWM.DeleteFirst();
    }
  }
  if (SWM.spikyWaves.size() > 1)
  {
    if (SWM.spikyWaves.get(1).points[0].r > sqrt(pow((width/2),2) + pow((height/2),2)) + 10) //if the radius is of the next one is off the page and then some
    {
      println("deleting first spike");
      SWM.DeleteFirst();
    }
  }
}