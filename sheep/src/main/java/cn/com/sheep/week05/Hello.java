package cn.com.sheep.week05;

import lombok.Data;

@Data
public class Hello {

   private int id;

   public Hello(){}
   public Hello(int id){
      this.id = id;
   }


   public static Hello create(){
      return new Hello(456);
   }
   @Override
   public String toString() {
      return "Hello{" +
              "id=" + id +
              '}';
   }
}
