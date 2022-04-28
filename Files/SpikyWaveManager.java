class SpikyWaveManager {
  LinkedList<SpikyWave> spikyWaves;
  int numberOfWaves;
  
  SpikyWaveManager() {
    this.spikyWaves = new LinkedList<SpikyWave>();
  }
  
  void Add(SpikyWave spikyWaveInput)
  {
    spikyWaves.add(spikyWaveInput);
  }
  
  void DeleteFirst()
  {
    spikyWaves.removeFirst();
  }
}