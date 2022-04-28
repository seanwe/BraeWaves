class Color {
  float r,g,b;
  Color (float r, float g, float b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }
}

Color randomColor()
{
  return new Color(random(0,255),random(0,255), random(0,255));
}