import java.awt.Color;
import java.awt.Point;
import java.util.List;
import java.lang.Math;

class Rectangle implements Shape{

	private double width;
	private double height;
	private Point topLeft;
	private Color color;

	// A constructor with parameters to initialize all its instance variables. Do not implement a default constructor.
	Rectangle(double width, double height, Point topLeft, Color color){
		this.width = width;
		this.height = height;
		this.topLeft = topLeft;
		this.color = color;
	} 


	// Sets the width of the Rectangle
	public void setWidth(double width){
		this.width = width;
	} 

	// Sets the height of the Rectangle
	public void setHeight(double height){
		this.height = height;
	} 

		//Sets the java.awt.Color of the Shape.
	public void setColor(Color c){
		this.color = c;
	}

	// Overrides the equals method inherited from Object in the manner we discussed in class.
	public boolean equals(Object other){
		if (other == this){
			return true;
		}
		if (!(other instanceof Rectangle)){
			return false;
		}

		Rectangle o = (Rectangle) other;

		return o.getColor() == this.color && o.getWidth() == this.width && o.getHeight() == this.height && o.getTopLeft() == this.topLeft;
	 
	} 

	//Translates the entire shape by the (x,y) coordinates of a given java.awt.Point
	public void translate(Point p){
		this.topLeft.translate(p.x, p.y);
	}	

		// Returns the width of the Rectangle.
	public double getWidth(){
		return this.width;
	} 

	//Returns the perimeter of the Shape
	public double getPerimeter(){
		double perimeter = (2*this.width) + (2*this.height);
		return perimeter;
	}

	//Returns the area of the Shape
	public double getArea(){
		double area = this.width * this.height;
		return area;
	}

	public Color getColor(){
		return this.color;
	}

	// Returns the Point representing the top left corner of the Rectangle
	public Point getTopLeft(){
		return this.topLeft;
	} 

	// Returns the height of the Rectangle.
	public double getHeight(){
		return this.height;
	} 


}








