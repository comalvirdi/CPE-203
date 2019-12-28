public class Bigger
{
	public static double whichIsBigger(Circle circ, Rectangle rect, Polygon poly){
		double perimCirc = Util.perimeter(circ);
		double perimRect = Util.perimeter(rect);
		double perimPoly = Util.perimeter(poly);

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