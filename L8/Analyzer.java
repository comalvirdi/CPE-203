import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Analyzer
{
   private static void processLineData(String line, ArrayList<Point> points){
      String [] lineComps= line.split(",\\h");
      Point newPoint = new Point(Double.parseDouble(lineComps[0]), Double.parseDouble(lineComps[1]), Double.parseDouble(lineComps[2]));
      points.add(newPoint);
      
      // System.out.println(newPoint);
   }

   private static List<Point> streamer(ArrayList<Point> points){
      List<Point> newPoints = points.stream().filter(p -> p.getZ() <= 2).map(p -> p.scalePoints()).map(p -> p.translatePoints(-150, -37)).collect(Collectors.toList());
      try{
         List<String> strList = newPoints.stream()
                                 .map(point -> point.toString())
                                 .collect(Collectors.toList());

         Path file = Paths.get("drawMe.txt");
         Files.write(file, strList, StandardCharsets.UTF_8);
      }
      catch (Exception e){

      }
      
      return newPoints;

   }

   public static void main(String[] args)
   {
      /* create additional data structures to hold relevant information */
      /* they will most likely be maps to important data in the logs */


      if (args.length < 1)
      {
         System.err.println("Log file not specified.");
         System.exit(1);
      }

      else
      {
         final String filename = args[0];
         ArrayList<Point> points = new ArrayList<Point>();
         try
         {
            try(Scanner input = new Scanner(new File(filename)))
               {
                  while (input.hasNextLine())
                  {
                     String nextLine = input.nextLine();
                     if (nextLine.split("\\h").length != 0)
                        processLineData(nextLine, points);

                  }
                  streamer(points);

               }
         }
         catch (FileNotFoundException e)
         {
            System.err.println(e.getMessage());
         }
      }

      
   }
}



