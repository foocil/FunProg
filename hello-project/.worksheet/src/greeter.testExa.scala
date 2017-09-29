package greeter


object testExa {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(59); 
  val xs = List(1,2,4,3);System.out.println("""xs  : List[Int] = """ + $show(xs ));$skip(38); 
  val ys = List("Bss", "Apps", "Abb");System.out.println("""ys  : List[String] = """ + $show(ys ));$skip(35); 
  
  
  val xreverse = xs.reverse;System.out.println("""xreverse  : List[Int] = """ + $show(xreverse ));$skip(39); 
  val xsorted = xs.sortBy(x => x - 3 );System.out.println("""xsorted  : List[Int] = """ + $show(xsorted ));$skip(60); 
  val xordre = xs sortBy(x=>x) mkString(" ensuite on a  " );System.out.println("""xordre  : String = """ + $show(xordre ));$skip(26); 
  val xSansLAst = xs.init;System.out.println("""xSansLAst  : List[Int] = """ + $show(xSansLAst ));$skip(32); 
  val xAvec2premier = xs take 2;System.out.println("""xAvec2premier  : List[Int] = """ + $show(xAvec2premier ));$skip(29); 
  val xAvec2last = xs drop 2;System.out.println("""xAvec2last  : List[Int] = """ + $show(xAvec2last ));$skip(30); 
  
  val x3emeelement = xs(2);System.out.println("""x3emeelement  : Int = """ + $show(x3emeelement ));$skip(31); 
  val listIntString = xs ++ ys;System.out.println("""listIntString  : List[Any] = """ + $show(listIntString ));$skip(32); 
  val x3elem5 = xs updated(3,5);System.out.println("""x3elem5  : List[Int] = """ + $show(x3elem5 ));$skip(34); 
  
  val xIndexOf = xs.indexOf(2);System.out.println("""xIndexOf  : Int = """ + $show(xIndexOf ));$skip(38); 
  val xIndexofnothing = xs.indexOf(6);System.out.println("""xIndexofnothing  : Int = """ + $show(xIndexofnothing ));$skip(33); 
  val xcontains = xs.contains(2);System.out.println("""xcontains  : Boolean = """ + $show(xcontains ));$skip(39); 
  val xcontainnothing = xs.contains(6);System.out.println("""xcontainnothing  : Boolean = """ + $show(xcontainnothing ));$skip(39); 
  
  val xmapSquare = xs map(x => x*x);System.out.println("""xmapSquare  : List[Int] = """ + $show(xmapSquare ));$skip(45); 
  val xfiltreG5 = xs filter (x => (x*x) > 5);System.out.println("""xfiltreG5  : List[Int] = """ + $show(xfiltreG5 ));$skip(51); 
  val xfiltreNotG5 = xs filterNot (x => (x*x) > 5);System.out.println("""xfiltreNotG5  : List[Int] = """ + $show(xfiltreNotG5 ));$skip(52); 
  
  val xpartition = xs partition (x => (x*x) > 5);System.out.println("""xpartition  : (List[Int], List[Int]) = """ + $show(xpartition ));$skip(49); 
  val xtakeWhile = xs takeWhile (x => (x*x) < 3);System.out.println("""xtakeWhile  : List[Int] = """ + $show(xtakeWhile ));$skip(49); 
  val xdropWhile = xs dropWhile (x => (x*x) < 3);System.out.println("""xdropWhile  : List[Int] = """ + $show(xdropWhile ));$skip(39); 
  val xSpan = xs span (x => (x*x) < 3);System.out.println("""xSpan  : (List[Int], List[Int]) = """ + $show(xSpan ));$skip(51); 
  
  val xReduceLeft = xs.reduceLeft((x,y) => x*y);System.out.println("""xReduceLeft  : Int = """ + $show(xReduceLeft ));$skip(39); 
  val xFoldLeft = xs.foldLeft(10)(_*_);System.out.println("""xFoldLeft  : Int = """ + $show(xFoldLeft ));$skip(50); 
  val xReduceRight = xs.reduceRight((x,y) => x*y);System.out.println("""xReduceRight  : Int = """ + $show(xReduceRight ));$skip(41); 
  val xFoldRight = xs.foldRight(10)(_*_);System.out.println("""xFoldRight  : Int = """ + $show(xFoldRight ));$skip(45); 
  
  val xexist2 = xs exists (x => x*x > 15);System.out.println("""xexist2  : Boolean = """ + $show(xexist2 ));$skip(40); 
  val xforall0 = xs forall (x => x > 0);System.out.println("""xforall0  : Boolean = """ + $show(xforall0 ));$skip(50); 
  val xforallnot = xs forall (x => (x + 5 == 10));System.out.println("""xforallnot  : Boolean = """ + $show(xforallnot ));$skip(24); 
  val xzipy = xs zip ys;System.out.println("""xzipy  : List[(Int, String)] = """ + $show(xzipy ));$skip(27); 
  val xdezip = xzipy.unzip;System.out.println("""xdezip  : (List[Int], List[String]) = """ + $show(xdezip ));$skip(20); 
  val ymin = ys.min;System.out.println("""ymin  : String = """ + $show(ymin ));$skip(20); 
  val ymax = ys.max;System.out.println("""ymax  : String = """ + $show(ymax ));$skip(72); 
  
  val xFlatMap = xs.flatMap(x => List(x , "Son carre est : " , x*x));System.out.println("""xFlatMap  : List[Any] = """ + $show(xFlatMap ));$skip(20); 
  val xmax = xs.max;System.out.println("""xmax  : Int = """ + $show(xmax ));$skip(40); 
  val xmodMax = (xs map (x => x&3)) max;System.out.println("""xmodMax  : Int = """ + $show(xmodMax ));$skip(53); 
  
  val testFlatten = List(List(2,1,3),List(2,3,4));System.out.println("""testFlatten  : List[List[Int]] = """ + $show(testFlatten ));$skip(39); 
  val appFlatten = testFlatten flatten;System.out.println("""appFlatten  : List[Int] = """ + $show(appFlatten ));$skip(29); 
  
  val xSorted = xs.sorted;System.out.println("""xSorted  : List[Int] = """ + $show(xSorted ));$skip(26); 
  val ySorted = ys.sorted;System.out.println("""ySorted  : List[String] = """ + $show(ySorted ));$skip(46); 
  val xSortedWith = xs.sortWith((x,y) => y<x);System.out.println("""xSortedWith  : List[Int] = """ + $show(xSortedWith ));$skip(52); 
  
  val yGroupBy = ys groupBy ( x => x.charAt(0) );System.out.println("""yGroupBy  : scala.collection.immutable.Map[Char,List[String]] = """ + $show(yGroupBy ));$skip(29); 

  	
  val listNil = List ();System.out.println("""listNil  : List[Nothing] = """ + $show(listNil ));$skip(27); 
  val list1elem = List (1);System.out.println("""list1elem  : List[Int] = """ + $show(list1elem ));$skip(243); 
  
  def difference(ls: List[Int]) : List[Int] = {
   //@tailrec
   def loop(xs : List[Int], acc : List[Int]) : List[Int] = xs match {
   	case x::Nil => acc.reverse
   	case x::y::ys => loop(y::ys, (y-x) :: acc)
	}
	loop(0 :: ls, Nil)
  
  };System.out.println("""difference: (ls: List[Int])List[Int]""");$skip(27); val res$0 = 
  
  difference(list1elem);System.out.println("""res0: List[Int] = """ + $show(res$0))}
  
  
}
