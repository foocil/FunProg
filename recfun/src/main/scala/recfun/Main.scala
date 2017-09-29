package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = if(c == 0 || r == 1 || c == r) 1 else 
        pascal(c-1,r-1) + pascal(c,r-1)
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {
      def loop(chars:List[Char], openP:Int): Boolean = {
        if(chars.isEmpty) openP == 0
        
          else 
            if (chars.head == '(') loop(chars.tail, openP+1)
     
            else if(chars.head == ')') openP>0 && loop(chars.tail,openP - 1)
                else loop(chars.tail,openP)
      
      }
      loop(chars, 0)
    }
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = 
        if(money < 0) 0
        else if(coins.isEmpty) 0
        else if (money == 0) 1
        else countChange(money-coins.head, coins) + countChange(money, coins.tail)
        
  }
