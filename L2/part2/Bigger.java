public class Bigger
{
	public static double whichIsBigger(Circle circ, Rectangle rect, Polygon poly){
		double perimCirc = circ.perimeter();
		double perimRect = rect.perimeter();
		double perimPoly = poly.perimeter();

		if (perimCirc > perimRect){
			if (perimCirc > perimPoly){
				return perimCirc;
			}
		}
		else{
			if (perimRect > perimPoly){
				return perimRect;
			}
		}

		return perimPoly;
	}
}