public class View
{
   private int sessionId;
   private int productId;
   private int price;

   public View(int sessionId, int productId, int price){
      this.sessionId = sessionId;
      this.productId = productId;
      this.price = price;
   }

   public String toString(){
      return (this.sessionId + " " + this.productId + " " + this.price);
   }

   public int price(){
      return this.price;
   }

   public int productID(){
      return this.productId;
   }
}