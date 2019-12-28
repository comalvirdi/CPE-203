import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

public class PartOneTestCases
{
   public static final double DELTA = 0.00001;

   @Test
   public void testPolygonGetPoints(){

      List<Point> poly1 = new ArrayList<Point>();
      Point p1 = new Point(1,1);
      poly1.add(p1);
      Point p2 = new Point(1,3);
      poly1.add(p2);
      Point p3 = new Point(3,3);
      poly1.add(p3);
      Point p4 = new Point(3,1);
      poly1.add(p4);

      Polygon polyg = new Polygon(poly1);

      assertEquals(poly1, polyg.getPoints());
   }

   @Test
   public void testRectangleGets(){
      Rectangle rect1 = new Rectangle(new Point(3,3), new Point(1,1));
      assertEquals(3, rect1.getTopLeft().getX(), DELTA);
      assertEquals(3, rect1.getTopLeft().getY(), DELTA);
      assertEquals(1, rect1.getBottomRight().getX(), DELTA);
      assertEquals(1, rect1.getBottomRight().getX(), DELTA);
   }

   @Test
   public void testCircleGets(){
      Circle cir1 = new Circle(new Point(0,1), 3);
      assertEquals(0, cir1.getCenter().getX(), DELTA);
      assertEquals(1, cir1.getCenter().getY(), DELTA);
      assertEquals(3, cir1.getRadius(), DELTA);
   }

   @Test
   public void testCirclePerimeter(){
      Circle cir1 = new Circle(new Point(0,1), 3);
      assertEquals(18.8495559, Util.perimeter(cir1), DELTA);
   }

   @Test
   public void testPolygonPerimeter(){
      List<Point> poly1 = new ArrayList<Point>();
      Point p1 = new Point(0,0);
      poly1.add(p1);
      Point p2 = new Point(10,0);
      poly1.add(p2);
      Point p3 = new Point(10,10);
      poly1.add(p3);
      Point p4 = new Point(0,10);
      poly1.add(p4);
      Polygon polyg = new Polygon(poly1);
      assertEquals(40, Util.perimeter(polyg), DELTA);
   }

   @Test
   public void testRectanglePerimeter(){
      Rectangle rect1 = new Rectangle(new Point(3,3), new Point(1,1));      
      assertEquals(8.0, Util.perimeter(rect1), DELTA);
      Rectangle rect2 = new Rectangle(new Point(5.5,3.0), new Point(6.0,1.0));      
      assertEquals(5.0, Util.perimeter(rect2), DELTA);
   }

   @Test
   public void testBigger(){
      Rectangle rect1 = new Rectangle(new Point(3,3), new Point(1,1));      
     
      List<Point> poly1 = new ArrayList<Point>();
      Point p1 = new Point(0,0);
      poly1.add(p1);
      Point p2 = new Point(10,0);
      poly1.add(p2);
      Point p3 = new Point(10,10);
      poly1.add(p3);
      Point p4 = new Point(0,10);
      poly1.add(p4);
      Polygon polyg = new Polygon(poly1);

      Circle cir1 = new Circle(new Point(0,1), 3);


      assertEquals(40, Bigger.whichIsBigger(cir1, rect1, polyg), DELTA);
   }


   @Test
   public void testCircleImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getCenter", "getRadius");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Point.class, double.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[0]);

      verifyImplSpecifics(Circle.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }


   @Test
   public void testRectangleImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getTopLeft", "getBottomRight");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Point.class, Point.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[0]);

      verifyImplSpecifics(Rectangle.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   @Test
   public void testPolygonImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getPoints");

      final List<Class> expectedMethodReturns = Arrays.asList(
         List.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[][] {new Class[0]});

      verifyImplSpecifics(Polygon.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   @Test
   public void testUtilImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "perimeter", "perimeter", "perimeter");

      final List<Class> expectedMethodReturns = Arrays.asList(
         double.class, double.class, double.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[] {Circle.class},
         new Class[] {Polygon.class},
         new Class[] {Rectangle.class});

      verifyImplSpecifics(Util.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   private static void verifyImplSpecifics(
      final Class<?> clazz,
      final List<String> expectedMethodNames,
      final List<Class> expectedMethodReturns,
      final List<Class[]> expectedMethodParameters)
      throws NoSuchMethodException
   {
      assertEquals("Unexpected number of public fields",
         0, Point.class.getFields().length);

      final List<Method> publicMethods = Arrays.stream(
         clazz.getDeclaredMethods())
            .filter(m -> Modifier.isPublic(m.getModifiers()))
            .collect(Collectors.toList());

      assertEquals("Unexpected number of public methods",
         expectedMethodNames.size(), publicMethods.size());

      assertTrue("Invalid test configuration",
         expectedMethodNames.size() == expectedMethodReturns.size());
      assertTrue("Invalid test configuration",
         expectedMethodNames.size() == expectedMethodParameters.size());

      for (int i = 0; i < expectedMethodNames.size(); i++)
      {
         Method method = clazz.getDeclaredMethod(expectedMethodNames.get(i),
            expectedMethodParameters.get(i));
         assertEquals(expectedMethodReturns.get(i), method.getReturnType());
      }
   }
}
