import java.awt.Color;
import java.awt.Point;
import java.util.List;
import java.lang.Math;

class Circle implements Shape {
	
	private Color color;
	private double radius;
	private Point center;

	// A constructor with parameters to initialize all its instance variables. Do not implement a default constructor.
	public Circle(double radius, Point center, Color color){
		this.color = color;
		this.center = center;
		this.radius = radius;
	}

	 
	// Returns the radius of the Circle. 
	public double getRadius(){
		return this.radius;
	} 

	// Sets the radius of the Circle
	public void setRadius(double radius){
		this.radius = radius;
	} 

	// Returns the center of the Circle
	public Point getCenter(){
		return this.center;
	} 

	// Overrides the equals method inherited from Object in the manner we discussed in class.
	public boolean equals(Object other){
		if (other == this){
			return true;
		}
		if (!(other instanceof Circle)){
			return false;
		}

		Circle o = (Circle) other;

		return o.getColor() == this.color && o.getRadius() == this.radius && o.getCenter() == this.center;
	} 

	public Color getColor(){
		return this.color;
	}

	//Sets the java.awt.Color of the Shape.
	public void setColor(Color c){
		this.color = c;
	} 

	//Returns the area of the Shape
	public double getArea(){
		double area = Math.PI * this.radius * this.radius;
		return area;
	} 

	//Returns the perimeter of the Shape
	public double getPerimeter(){
		double perimeter = Math.PI * this.radius * 2;
		return perimeter;
	} 

	//Translates the entire shape by the (x,y) coordinates of a given java.awt.Point
	public void translate(Point p){
		this.center.translate(p.x, p.y);

	} 
}









