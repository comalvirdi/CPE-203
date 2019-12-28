import java.awt.Color;
import java.awt.Point;
import java.util.List;
import java.lang.Math;

class Triangle implements Shape{

	private Point a;
	private Point b;
	private Point c;
	private Color color;

	// A constructor with parameters to initialize all its instance variables. Do not implement a default constructor.
	Triangle(Point a, Point b, Point c, Color color){
		this.a = a;
		this.b = b;
		this.c = c;
		this.color = color;
	} 

	// Overrides the equals method inherited from Object in the manner we discussed in class.
	public boolean equals(Object other){
		if (other == this){
			return true;
		}
		if (!(other instanceof Triangle)){
			return false;
		}

		Triangle o = (Triangle) other;

		return o.getColor() == this.color && o.getVertexA() == this.a && o.getVertexB() == this.b && o.getVertexC() == this.c;
	} 


	//Sets the java.awt.Color of the Shape.
	public void setColor(Color c){
		this.color = c;
	} 

	//Translates the entire shape by the (x,y) coordinates of a given java.awt.Point
	public void translate(Point p){
		this.a.x += p.x;
		this.a.y += p.y;

		this.b.x += p.x;
		this.b.y += p.y;

		this.c.x += p.x;
		this.c.y += p.y;

	} 

	//Returns the area of the Shape
	public double getArea(){
		double compA = this.a.x * (this.b.y - this.c.y);
		double compB = this.b.x * (this.c.y - this.a.y);
		double compC = this.c.x * (this.a.y - this.b.y);
		double area = Math.abs((compA + compB + compC)/2.0);
		return area;
	} 

	//Returns the perimeter of the Shape
	public double getPerimeter(){
		double sideA = Math.sqrt(Math.pow((this.b.x - this.a.x),2) + Math.pow((this.b.y - this.a.y),2));
		double sideB = Math.sqrt(Math.pow((this.c.x - this.b.x),2) + Math.pow((this.c.y - this.b.y),2));
		double sideC = Math.sqrt(Math.pow((this.a.x - this.c.x),2) + Math.pow((this.a.y - this.c.y),2));

		return sideA + sideB + sideC;
	} 

	public Color getColor(){
			return this.color;
		}

	// Returns the Point representing vertex A of the Triangle.
	public Point getVertexA(){
		return this.a;
	} 

	// Returns the Point representing vertex B of the Triangle.
	public Point getVertexB(){
		return this.b;
	} 

	 // Returns the Point representing vertex C of the Triangle.
	public Point getVertexC(){
		return this.c;

	}
}