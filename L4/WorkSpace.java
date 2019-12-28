import java.util.*; 
import java.awt.Color;
import java.awt.Point;
import java.util.List;
import java.lang.Math;

class WorkSpace{

	private LinkedList<Shape> LOfShapes;

	// A default constructor to initialize its instance variable to an empty List. The constructor should not take any parameters. (If you do nothing in this constructor, you may omit it from your code altogether.)
	public WorkSpace(){

		LOfShapes = new LinkedList<Shape>();
	}


	// Adds an object which implements the Shape interface to the end of the List in the WorkSpace.
	public void add(Shape shape){
		LOfShapes.add(shape);
	}

	// Returns the specified Shape from the WorkSpace.
	Shape get(int index){
		return LOfShapes.get(index);
	}

	 // Returns the number of Shapes contained by the WorkSpace.
	int size(){
		return LOfShapes.size();
	}

	// Returns a List of all the Circle objects contained by the WorkSpace.
	List<Circle> getCircles(){
		LinkedList<Circle> LOfCircles = new LinkedList<Circle>();
		for (Shape shpe: LOfShapes){
			if (shpe instanceof Circle){
				LOfCircles.add((Circle)shpe);
			}
		}
		return LOfCircles;
	} 

	// Returns a List of all the Rectangle objects contained by the WorkSpace.
	List<Rectangle> getRectangles(){
		LinkedList<Rectangle> LOfRecs = new LinkedList<Rectangle>();
		for (Shape shpe: LOfShapes){
			if (shpe instanceof Rectangle){
				LOfRecs.add((Rectangle)shpe);
			}
		}
		return LOfRecs;
	} 
	

	// Returns a List of all the Triangle objects contained by the WorkSpace.	 
	List<Triangle> getTriangles(){
		LinkedList<Triangle> LOfTri = new LinkedList<Triangle>();
		for (Shape shpe: LOfShapes){
			if (shpe instanceof Triangle){
				LOfTri.add((Triangle)shpe);
			}
		}
		return LOfTri;

	}

	// Returns a List of all the Shape objects contained by the WorkSpace that match the specified Color.
	List<Shape> getShapesByColor(Color color){
		LinkedList<Shape> LOfColors = new LinkedList<Shape>();
		for (Shape shpe: LOfShapes){
			if (shpe.getColor().equals(color)){
				LOfColors.add(shpe);
			}
		}
		return LOfColors;
	}  

	// Returns the sum of the areas of all the Shapes contained by the WorkSpace.
	double getAreaOfAllShapes(){
		double totalArea = 0.0;
		for (Shape shpe: LOfShapes){
			totalArea += shpe.getArea();
		}
		return totalArea;
	}

	// Returns the sum of the perimeters of all the Shapes contained by the WorkSpace.
	double getPerimeterOfAllShapes(){
		double totalPerim = 0.0;
		for (Shape shpe: LOfShapes){
			totalPerim += shpe.getPerimeter();
		}
		return totalPerim;
	}
}

