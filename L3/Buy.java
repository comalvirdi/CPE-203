public class Buy
{
   private int sessionId;
   private int productId;
   private int price;
   private int quantity;

   public Buy(int sessionId, int productId, int price, int quantity){
      this.sessionId = sessionId;
      this.productId = productId;
      this.price = price;
      this.quantity = quantity;
   }

   public String toString(){
      return (this.sessionId + " " + this.productId + " " + this.price + " " + this.quantity);
   }

   public int price(){
      return this.price;
   }

   public int productID(){
      return this.productId;
   }
}
