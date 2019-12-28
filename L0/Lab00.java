public class Lab00
{
   public static void main(String[] args)
   {
      int x = 5;
      String y = "hello";
      double z = 9.8;

      System.out.println("x: " + x + " " +  "y: " + y + " "+  "z: " + z);

      int[] nums = {3, 6, -1, 2};
      for (int i = 0; i < nums.length; i++){
         System.out.println(nums[i]);
      }

      int numFound = charCount(y, 'l');
      System.out.println("Found: "+ numFound);


      for (int t = 1; t<11; t++) {
         System.out.print(t);
         if (t != 10) {
            System.out.print(" ");
         }

      }
      System.out.print("\n");
   }

   public static int charCount(String s, char c){
      int count = 0;
      for (int i = 0; i < s.length(); i++) {
         if (s.charAt(i) == c) {
            count += 1;
         }
      }
      return count;
   }
}