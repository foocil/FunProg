package greeter


object testExa {
  val xs = List(1,2,4,3)                          //> xs  : List[Int] = List(1, 2, 4, 3)
  val ys = List("Bss", "Apps", "Abb")             //> ys  : List[String] = List(Bss, Apps, Abb)
  
  
  val xreverse = xs.reverse                       //> xreverse  : List[Int] = List(3, 4, 2, 1)
  val xsorted = xs.sortBy(x => x - 3 )            //> xsorted  : List[Int] = List(1, 2, 3, 4)
  val xordre = xs sortBy(x=>x) mkString(" ensuite on a  " )
                                                  //> xordre  : String = 1 ensuite on a  2 ensuite on a  3 ensuite on a  4
  val xSansLAst = xs.init                         //> xSansLAst  : List[Int] = List(1, 2, 4)
  val xAvec2premier = xs take 2                   //> xAvec2premier  : List[Int] = List(1, 2)
  val xAvec2last = xs drop 2                      //> xAvec2last  : List[Int] = List(4, 3)
  
  val x3emeelement = xs(2)                        //> x3emeelement  : Int = 4
  val listIntString = xs ++ ys                    //> listIntString  : List[Any] = List(1, 2, 4, 3, Bss, Apps, Abb)
  val x3elem5 = xs updated(3,5)                   //> x3elem5  : List[Int] = List(1, 2, 4, 5)
  
  val xIndexOf = xs.indexOf(2)                    //> xIndexOf  : Int = 1
  val xIndexofnothing = xs.indexOf(6)             //> xIndexofnothing  : Int = -1
  val xcontains = xs.contains(2)                  //> xcontains  : Boolean = true
  val xcontainnothing = xs.contains(6)            //> xcontainnothing  : Boolean = false
  
  val xmapSquare = xs map(x => x*x)               //> xmapSquare  : List[Int] = List(1, 4, 16, 9)
  val xfiltreG5 = xs filter (x => (x*x) > 5)      //> xfiltreG5  : List[Int] = List(4, 3)
  val xfiltreNotG5 = xs filterNot (x => (x*x) > 5)//> xfiltreNotG5  : List[Int] = List(1, 2)
  
  val xpartition = xs partition (x => (x*x) > 5)  //> xpartition  : (List[Int], List[Int]) = (List(4, 3),List(1, 2))
  val xtakeWhile = xs takeWhile (x => (x*x) < 3)  //> xtakeWhile  : List[Int] = List(1)
  val xdropWhile = xs dropWhile (x => (x*x) < 3)  //> xdropWhile  : List[Int] = List(2, 4, 3)
  val xSpan = xs span (x => (x*x) < 3)            //> xSpan  : (List[Int], List[Int]) = (List(1),List(2, 4, 3))
  
  val xReduceLeft = xs.reduceLeft((x,y) => x*y)   //> xReduceLeft  : Int = 24
  val xFoldLeft = xs.foldLeft(10)(_*_)            //> xFoldLeft  : Int = 240
  val xReduceRight = xs.reduceRight((x,y) => x*y) //> xReduceRight  : Int = 24
  val xFoldRight = xs.foldRight(10)(_*_)          //> xFoldRight  : Int = 240
  
  val xexist2 = xs exists (x => x*x > 15)         //> xexist2  : Boolean = true
  val xforall0 = xs forall (x => x > 0)           //> xforall0  : Boolean = true
  val xforallnot = xs forall (x => (x + 5 == 10)) //> xforallnot  : Boolean = false
  val xzipy = xs zip ys                           //> xzipy  : List[(Int, String)] = List((1,Bss), (2,Apps), (4,Abb))
  val xdezip = xzipy.unzip                        //> xdezip  : (List[Int], List[String]) = (List(1, 2, 4),List(Bss, Apps, Abb))
                                                  //| 
  val ymin = ys.min                               //> ymin  : String = Abb
  val ymax = ys.max                               //> ymax  : String = Bss
  
  val xFlatMap = xs.flatMap(x => List(x , "Son carre est : " , x*x))
                                                  //> xFlatMap  : List[Any] = List(1, "Son carre est : ", 1, 2, "Son carre est : 
                                                  //| ", 4, 4, "Son carre est : ", 16, 3, "Son carre est : ", 9)
  val xmax = xs.max                               //> xmax  : Int = 4
  val xmodMax = (xs map (x => x&3)) max           //> xmodMax  : Int = 3
  
  val testFlatten = List(List(2,1,3),List(2,3,4)) //> testFlatten  : List[List[Int]] = List(List(2, 1, 3), List(2, 3, 4))
  val appFlatten = testFlatten flatten            //> appFlatten  : List[Int] = List(2, 1, 3, 2, 3, 4)
  
  val xSorted = xs.sorted                         //> xSorted  : List[Int] = List(1, 2, 3, 4)
  val ySorted = ys.sorted                         //> ySorted  : List[String] = List(Abb, Apps, Bss)
  val xSortedWith = xs.sortWith((x,y) => y<x)     //> xSortedWith  : List[Int] = List(4, 3, 2, 1)
  
  val yGroupBy = ys groupBy ( x => x.charAt(0) )  //> yGroupBy  : scala.collection.immutable.Map[Char,List[String]] = Map(A -> Li
                                                  //| st(Apps, Abb), B -> List(Bss))

  	
  val listNil = List ()                           //> listNil  : List[Nothing] = List()
  val list1elem = List (1)                        //> list1elem  : List[Int] = List(1)
  
  def difference(ls: List[Int]) : List[Int] = {
   //@tailrec
   def loop(xs : List[Int], acc : List[Int]) : List[Int] = xs match {
   	case x::Nil => acc.reverse
   	case x::y::ys => loop(y::ys, (y-x) :: acc)
	}
	loop(0 :: ls, Nil)
  
  }                                               //> difference: (ls: List[Int])List[Int]
  
  difference(list1elem)                           //> res0: List[Int] = List(1)
  
  
}