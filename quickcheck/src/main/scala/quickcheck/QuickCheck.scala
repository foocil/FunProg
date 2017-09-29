package quickcheck

import common._

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._
import Math._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  property("min of 1") = forAll {e :A =>
    val h = insert(e,empty)
    findMin(h) == e
  }
  property("min of 2") = forAll{ (e:A, f:A) => 
    val h = insert (e, insert(f, empty))
    findMin(h) == min(e,f)
  }
  
  property("min of 3") = forAll {(e:A, f: A, g: A) =>
    val h = insert(e, insert(f,(insert (g, empty))))
    findMin(h) == min(e,min(f,g))
      
  }
  

  //a checker
  property("Emptiness of one element") = forAll{e:A =>
   
    isEmpty(deleteMin(insert(e,empty)))
  }
    property("Emptiness of two elements") = forAll{(e:A,f:A) =>
   val h = insert(e,insert(f,empty))
    !isEmpty(deleteMin(h))
  }
  
  property("is not empty") = forAll{ e:A => 
    val h = insert(e,empty)
    !isEmpty(h) 
  }
    
  property("meld 2 heaps - getmin") = forAll { (h1:H, h2:H) =>
    findMin(meld(h1,h2)) == min(findMin(h1),findMin(h2))
  }

  property("sorted sequence") = forAll { (heap2: H) =>
    def loop(heap2:H) : Boolean ={
      if (isEmpty(heap2)) true
      else {
        val min = findMin(heap2)
        val h3 = deleteMin(heap2)
        isEmpty(h3)|| (min <= findMin(h3) && loop(h3))
      }
    }
    loop(heap2)
  }
  
  property("different merging") = forAll { (h1:H, h2:H) => 
    def equalHeap(h1:H, h2:H) : Boolean = {
      if(isEmpty(h1) && isEmpty(h2)) true
      else {
        val min1 = findMin(h1)
        val min2 = findMin(h2) 
        min1 == min2 && equalHeap(deleteMin(h1),deleteMin(h2))
      }
      
    }
    equalHeap(meld(h1,h2),meld(deleteMin(h1),insert(findMin(h1),h2)))
  }
  
  lazy val genHeap: Gen[H] = for {
    x <- arbitrary[Int]
    h <- oneOf(const(empty), genHeap)
  } yield insert(x, h)

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)
  
}
