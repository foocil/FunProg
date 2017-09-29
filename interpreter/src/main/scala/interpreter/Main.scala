package interpreter

import Lisp._

object Main extends App {
  import java.io.{BufferedReader, InputStreamReader}
  val in = new BufferedReader(new InputStreamReader(System.in))
  
  def repl {
    print("lisp> ")
    // TODO: Insert code for the REPL
      while (true) {
    System.out.print("lisp> ")
    val input = in.readLine()
    if (input != "exit") {
      evaluate(LispCode.withDifferences(input)) match {
        case listData: List[Data] => System.out.println(listData mkString("(", " ", ")"))
        case data: Data => System.out.println(data)
      }
    } else System.exit(0)
  }
    repl
  }

  repl
}

object LispCode {
  // TODO: implement the function `reverse` in Lisp.
  // From a list (a, b, c, d) it should compute (d, c, b, a)
  // Write it as a String, and test it in your REPL
  val reverse = """
    def (reverse L acc) (
      if (null? L) acc 
        (reverse (cdr L) (cons (car L) acc))
    )
  """

  // TODO: implement the function `differences` in Lisp.
  // From a list (a, b, c, d ...) it should compute (a, b-a, c-b, d-c ...)
  // You might find useful to define an inner loop def
  val differences = """
    def (differences L) (
      def (inner l1 l2 acc) (
        if (null? l1)
          (reverse acc nil)
          (inner (cdr l1) (cdr l2) (cons (- (car l1) (car l2)) acc))
      )
      (inner L (cons 0 L) nil)
    )
  """
  val rebuildList = """
    def (rebuildList L) (
      def (inner l count acc) (
        if (null? l)
          (reverse acc nil)
          (inner (cdr l) (+ count (car l)) (cons (+ count (car l)) acc))
      )
      (inner L 0 nil)
    )
  """

  val withDifferences: String => String =
    (code: String) => "(" + reverse + " (" + differences + " (" + rebuildList + " " + code + ")))"
}