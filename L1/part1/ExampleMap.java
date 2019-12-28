import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class ExampleMap
{
   public static List<String> highEnrollmentStudents(
      Map<String, List<Course>> courseListsByStudentName, int unitThreshold)
   {

      List<String> overEnrolledStudents = new LinkedList<>();
      int unitCount = 0;

      for (Map.Entry<String, List<Course>> student: courseListsByStudentName.entrySet()){
         unitCount = 0;

         for (Course c: student.getValue()) {
            unitCount += c.getNumUnits();
            System.out.println(c.getNumUnits());
         }
         
         
         if (unitThreshold < unitCount){
            overEnrolledStudents.add(student.getKey());
            
         }
      }
      
      return overEnrolledStudents;      
      
   }  
}
