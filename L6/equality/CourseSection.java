import java.time.LocalTime;

class CourseSection
{
   private final String prefix;
   private final String number;
   private final int enrollment;
   private final LocalTime startTime;
   private final LocalTime endTime;

   public CourseSection(final String prefix, final String number,
      final int enrollment, final LocalTime startTime, final LocalTime endTime)
   {
      this.prefix = prefix;
      this.number = number;
      this.enrollment = enrollment;
      this.startTime = startTime;
      this.endTime = endTime;
   }

   public boolean equals(Object other){
      if (other == null)
         return false;
      
      if (getClass() != other.getClass())
         return false;
      
      CourseSection c = (CourseSection) other;

      boolean result = true;

      if (prefix == null)
         result = result && c.prefix == null;
      else
         result = result && prefix.equals(c.prefix);

      if (number == null)
         result = result && c.number == null;
      else
         result = result && number.equals(c.number);

      if (startTime == null)
         result = result && c.startTime == null;
      else
         result = result && startTime.equals(c.startTime);

      if (endTime == null)
         result = result && c.endTime == null;
      else
         result = result && endTime.equals(c.endTime);

      result = result && (enrollment == c.enrollment);

      return result;
      }

   public int hashCode(){
      int result = 1;

      result = result * 37 + ((prefix == null) ? 0: prefix.hashCode());
      result = result * 37 + ((number == null) ? 0: number.hashCode());
      result = result * 37 + ((endTime == null) ? 0: endTime.hashCode());
      result = result * 37 + ((startTime == null) ? 0: startTime.hashCode());

      result = result * 37 + enrollment;

      return result;
   }
}
