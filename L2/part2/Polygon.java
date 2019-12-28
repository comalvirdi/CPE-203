import java.util.List;
import java.util.Arrays;

public class Polygon
{
	private final List<Point> points;


	public Polygon(List<Point> points){
		this.points = points;
	}

	public List<Point> getPoints(){
		return this.points;
	}

	public double perimeter(){
		double perimSum = 0;
		
		for (int i = 0; i < this.points.size(); i++){
			int next = i+1;
			if (i == this.points.size() - 1){
				next = 0;
			}
			double currentX = this.points.get(i).getX();
			double currentY = this.points.get(i).getY();
			double nextX = this.points.get(next).getX();
			double nextY = this.points.get(next).getY();
			
			double lineLength = Math.sqrt(Math.pow(currentX - nextX,2) + Math.pow(currentY - nextY, 2));
			perimSum += lineLength;
			
		}

		return perimSum;

	}
}