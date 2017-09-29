package constraints

import cafesat.api.FormulaBuilder._
import cafesat.api.Formulas._
import cafesat.api.Solver._

import scala.annotation.tailrec

/**
  * This object contains utility functions for encoding
  * some arithmetic constraints as boolean ones
  */
object Arithmetic {

  /**
   * Transforms a positive integer in binary form into its integer representation.
   * The `head` element of the input list contains the most
   * significant bit (the list is in big-endian form).
   */
  def binary2int(n: List[Boolean]): Int = {
    @tailrec
    def loop(exponent: List[Boolean], accum: Int): Int = {
      if (exponent.isEmpty) accum
      else if (exponent.head) loop(exponent.tail, accum + (1 << (exponent.length - 1)))
      else loop(exponent.tail, accum)
    }

    loop(n, 0)
  }

  /**
   * Encodes a positive integer number into base 2.
   * The `head` element of the resulting list contains the most significant
   * bit. This function should not return unnecessary leading zeros.
   */
  def int2binary(n: Int): List[Boolean] = {
    @tailrec
    def loop(number: Int, futurBinary: List[Boolean]): List[Boolean] = {
      if (number == 0) futurBinary
      else if (number % 2 == 0) loop(number / 2, false :: futurBinary)
      else loop((number - 1) / 2, true :: futurBinary)
    }

    if (n == 0) List(false)
    else loop(n, List())
  }


  /**
   * This function takes two arguments, both representing positive
   * integers encoded in binary as lists of propositional formulas
   * (true for 1, false for 0). It returns
   * a formula that represents a boolean circuit that constraints
   * `n1` to be less than or equal to `n2`
   */
  def lessEquals(n1: List[Formula], n2: List[Formula]): Formula = {
    @tailrec 
    def loop(n1n2: List[(Formula, Formula)], pre: Formula, acc: Formula): Formula = {
      if (n1n2.isEmpty) acc
      else n1n2.head match {
        case (b1, b2) => loop(n1n2.tail, pre && (b1 iff b2), acc || and(b1, !b2, pre))
      }
    }
      // was greater only, so reverse it to have less or equal
    val t: Formula = false;
    !loop(n1.reverse.zipAll(n2.reverse, t, t).reverse, true, false)
  }


  /**
   * A full adder is a circuit that takes 3 one bit numbers, and returns the
   * result encoded over two bits: (cOut, s)
   */
  def fullAdder(a: Formula, b: Formula, cIn: Formula): (Formula, Formula) = 
     (or(and(a, b), and(cIn, !(a iff b))),
      !(a iff !(b iff cIn)))

  /**
   * This function takes two arguments, both representing positive integers
   * encoded as lists of propositional variables. It returns a pair.
   *
   * The first element of the pair is a `List[Formula]`, and it represents
   * the resulting binary number.
   * The second element is a set of intermediate constraints that are created
   * along the way.
   *
   */
  def adder(n1: List[Formula], n2: List[Formula]): (List[Formula], Set[Formula]) = 
    {
    def loop(n1: List[Formula], n2: List[Formula]): (List[Formula], Set[Formula]) = {
      val (p1, p2) = (propVar(), propVar())
      (n1, n2) match {
        case (x :: Nil, y :: Nil) => {
          val (head, last) = fullAdder(x, y, false)
          (p1 :: p2 :: Nil, Set(p1 iff head, p2 iff last))
        }
        case (x :: xs, y :: ys) => {
          val (z :: zs, constraint) = loop(xs, ys)
          val (head, second) = fullAdder(x, y, z)
          (p1 :: p2 :: zs, constraint + (p1 iff head, p2 iff second))
        }
        case _ => sys.error("Error: not a case...")
      }
    }

    val (x, y) = uniformizeLength(n1, n2)
    loop(x, y)
  }

  def uniformizeLength(n1: List[Formula], n2: List[Formula]): (List[Formula], List[Formula]) = {
    @tailrec
    def loop(size: Int, acc: List[Formula]): List[Formula] = {
      if (size <= 0) acc
      else loop(size - 1, false :: acc)
    }
    
    (n1.size - n2.size) match {
      case 0 => (n1, n2)
      case x => if (x < 0) (loop(-x, n1), n2) else (n1, loop(x, n2))
    }
  }
  /**
   * A helper function that creates a less-equals formula
   * taking an integer and a formula as parameters
   */
  def lessEqualsConst(cst: Int, n: List[Formula]): Formula = {
    lessEquals(int2binary(cst), n)
  }

  /**
   * A helper function that creates a less-equals formula
   * taking a formula and an integer as parameters
   */
  def lessEqualsConst(n: List[Formula], cst: Int): Formula = {
    lessEquals(n, int2binary(cst))
  }


}
