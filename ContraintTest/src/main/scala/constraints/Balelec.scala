package constraints

import cafesat.api.Formulas._
import cafesat.api.FormulaBuilder._
import cafesat.api.Solver._

/**
 * This component implements a constraint solver for assigning time slots to volunteers
 * for various tasks at a festival. A task may require more than one volunteer,
 * and a volunteer can take a limited number of tasks
 */
object Balelec {

  import Arithmetic._

  case class Volunteer(name: String) {
    override def toString = name
  }

  /**
   * A task is represented by its name and
   * its capacity, i.e. the exact number of people
   * required to complete it.
   */
  case class Task(name: String, capacity: Int) {
    override def toString = name
  }

  /**
   * This function schedules volunteers to tasks.
   * It takes as input a list of volunteers and a list of tasks.
   * The `availability` map contains mappings from volunteers to the
   * tasks they are available for.
   * A volunteer can be assigned to several tasks, but only
   * up to a maximum number of task specified by the `maxWorkload` parameter.
   * It is ok to not assign a volunteer to any task.
   *
   * The return value is a list of volunteers assigned to each task. The function only
   * returns a complete valid assignment, if no such assignment exists then the
   * function returns None.
   */
  def schedule(
    volunteers: List[Volunteer],
    tasks: List[Task],
    availability: Map[Volunteer, List[Task]],
    maxWorkload: Int
  ): Option[Map[Task, List[Volunteer]]] = 
    {
    //x: Volunteer
    //y: Task
    
    
    //1 propositional variable for a volunteer - task
    val volTask: Map[(Volunteer, Task), PropVar] =
      volunteers.flatMap({
        case x @ Volunteer(name) =>
          tasks.map(y => (x, y) -> propVar(name + "_" + y.name))
      }).toMap

    
    val notTooMuch: Seq[Formula] =
      (volunteers map (x => atMostMaxTrue(tasks map (y => volTask(x, y)), maxWorkload))).toSeq

    //only he wants
    val taskAsked: Seq[Formula] = {
      volunteers map (x => {
        val l: List[Task] = availability(x)
        (for {
          y <- tasks
          if !(l.contains(y))
        } yield !volTask(x, y)).foldLeft[Formula](true)((acc, el) => acc && el)
      })
    }.toSeq

   
    val volunteerPerTask: Seq[Formula] =
      (tasks map (y => equals(volunteers map (volTask(_, y)), y.capacity))).toSeq

    //constraints together
    val constrainTogether: Seq[Formula] =
      notTooMuch ++ taskAsked ++ volunteerPerTask

    //satisfying assignment
    val res = solveForSatisfiability(and(constrainTogether: _*))

    res.map(m => tasks.map(y => (y, volunteers.filter(x => m(volTask((x, y)))))).toMap)
  }

  /**
   * This function takes a list of constraint, and returns a
   * constraint that is true if and only if at most max
   * of them are true.
   */
  def atMostMaxTrue(ns: List[Formula], max: Int): Formula = {
    val (r, c) = countPositiveBits(ns)
    lessEquals(r, int2binary(max)) && and(c.toSeq:_*)
  }
  
  
  
    /**
   * This function takes a list of constraint, and returns a
   * constraint that is true if and only if at n are true
   */
  def equals(ns: List[Formula], n: Int): Formula = {
    val (r, c) = countPositiveBits(ns)
    lessEquals(r, int2binary(n)) && lessEquals(int2binary(n), r) && and(c.toSeq: _*)
  }
  
  

  /**
   * This function counts the number of positive bits in a number.
   * 
   * It takes a list of formulas, and returns a pair.
   * The first element of the pair is a list of formulas representing the number
   * of ones in `ns`.
   * The second element is a set of additional constraints that have been gathered along
   * the way. Hint: see `adder` for understanding how to use additional constraints
   */
  def countPositiveBits(ns: List[Formula]): (List[Formula], Set[Formula]) = {
    ns.foldLeft((List[Formula](false), Set[Formula]())) { case ((tmpSum, tmpAcc), n) =>
      val (r, c) = adder(tmpSum, List(n))
      (r, tmpAcc ++ c)
    }
  }

}
