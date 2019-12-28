public class Rectangle
{
	private final Point topLeft;
	private final Point bottomRight;


	public Rectangle(Point topLeft, Point bottomRight){
		this.topLeft = topLeft;
		this.bottomRight = bottomRight;
	}

	public Point getTopLeft(){
		return this.topLeft;
	}

	public Point getBottomRight(){
		return this.bottomRight;
	}

	public double perimeter(){
		Point tL = this.topLeft;
		Point bR = this.bottomRight;
		double tLX = tL.getX();
		double tLY = tL.getY();
		double bRX = bR.getX();
		double bRY = bR.getY();

		double height = Math.abs(tLX - bRX);
		double width = Math.abs(tLY - bRY);

		return (height + height + width + width);

	}

}