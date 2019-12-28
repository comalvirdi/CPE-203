public class Point
{
	private double x;
	private double y;
	private double z;

	public Point(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;

	}

	public double getX(){return x;}
	public double getY(){return y;}
	public double getZ(){return z;}
	public String toString(){return x + ", " + y + ", " + z;}
	public Point scalePoints(){
		x*=0.5;
		y*=0.5;
		z*=0.5;

		return new Point(x,y,z);
	}
	public Point translatePoints(int xT, int yT){
		x += xT;
		y += yT;
		return new Point(x,y,z);
	}


}
