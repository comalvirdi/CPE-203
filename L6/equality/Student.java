import java.util.List;
import java.util.Objects;

class Student
{
   private final String surname;
   private final String givenName;
   private final int age;
   private final List<CourseSection> currentCourses;

   public Student(final String surname, final String givenName, final int age,
      final List<CourseSection> currentCourses)
   {
      this.surname = surname;
      this.givenName = givenName;
      this.age = age;
      this.currentCourses = currentCourses;
   }

   public boolean equals(Object other){
      
      if (other == null)
         return false;

      if (getClass() != other.getClass())
         return false;

      Student s = (Student) other;

      return (Objects.equals(surname, s.surname) && Objects.equals(givenName, s.givenName) 
         && Objects.equals(currentCourses, s.currentCourses) && age == s.age);
   }

   public int hashCode(){

      return Objects.hash(surname, givenName, age, currentCourses);
   }
}
