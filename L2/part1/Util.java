import java.util.List;
import java.util.Arrays;


public class Util
{
	public static double perimeter(Circle circ){

		return (2 * Math.PI * circ.getRadius());
	}


	public static double perimeter(Polygon polyg){
		double perimSum = 0;
		List<Point> pointList = polyg.getPoints();
		
		for (int i = 0; i < pointList.size(); i++){
			int next = i+1;
			if (i == pointList.size() - 1){
				next = 0;
			}
			double currentX = pointList.get(i).getX();
			double currentY = pointList.get(i).getY();
			double nextX = pointList.get(next).getX();
			double nextY = pointList.get(next).getY();
			
			double lineLength = Math.sqrt(Math.pow(currentX - nextX,2) + Math.pow(currentY - nextY, 2));
			perimSum += lineLength;
			
		}

		return perimSum;

	}


	public static double perimeter(Rectangle rect){
		Point tL = rect.getTopLeft();
		Point bR = rect.getBottomRight();
		double tLX = tL.getX();
		double tLY = tL.getY();
		double bRX = bR.getX();
		double bRY = bR.getY();

		double height = Math.abs(bRX - tLX);
		double width = Math.abs(bRY - tLY);

		System.out.println(height);
		System.out.println(width);

		return (height*2) + (width*2);

	}

}