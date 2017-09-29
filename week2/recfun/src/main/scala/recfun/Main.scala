package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 15) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
    
    println(balance( "()=)(()()()".toList))
    var a = countChange(20, List(1,5,10))
    print(a)
  }

  /**
   * Exercise 1
   */
    def pascal(cd: Int, rp: Int): Int = {
      if(cd == 0 || cd == rp) 1 else pascal(cd-1,rp-1)+pascal(cd,rp-1)
    }
  
  /**
   * Exercise 2
   */
   def balance(chars: List[Char]): Boolean = {
     
   def loop(acc: Int, chars : List[Char]) : Boolean =  {
       
   if(chars.isEmpty) acc == 0
   else if(chars.head == '(') loop(acc+1,chars.tail)
     else if(chars.head == ')') acc>0 && loop(acc-1,chars.tail)
        else loop(acc, chars.tail)
   }
     
     loop(0,chars)
      
   }
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = {
      
      
        if (money < 0) 0 
         else if (coins.isEmpty)0
          else if(money == 0) 1
           else countChange(money-coins.head, coins)+ countChange(money,coins.tail)
      
    }
    

  
    
  }





