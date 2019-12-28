public class Point
{
	private double xCord;
	private double yCord;

	public Point(double x, double y){
		this.xCord = x;
		this.yCord = y;

	}

	public double getX(){
		return xCord;
	}

	public double getY(){
		return yCord;
	}

	public double getRadius(){
		return Math.sqrt(Math.pow(xCord,2) + Math.pow(yCord,2));
	}

	public double getAngle(){
		return Math.atan2(yCord, xCord);
	}

	public Point rotate90(){
		double newX = -1* this.yCord;
		double newY = this.xCord;

		Point newPoint = new Point(newX, newY);
		return newPoint;
	}

}
