class BubbleWaveManager {
  LinkedList<BubbleWave> bubbleWaves;
  int numberOfWaves;
  
  BubbleWaveManager() {
    this.bubbleWaves = new LinkedList<BubbleWave>();
  }
  
  void Add(BubbleWave bubbleWaveInput)
  {
    bubbleWaves.add(bubbleWaveInput);
  }
  
  void DeleteFirst()
  {
    bubbleWaves.removeFirst();
  }
}