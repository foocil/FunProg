package constraints

import cafesat.api.Formulas._
import cafesat.api.FormulaBuilder._
import cafesat.api.Solver._

/**
 * This component implements a constraint solver
 * for assigning time slots to bands at a festival
 */
object ConcertsPlanner {

  case class Band(name: String) {
    override def toString = name
  }

  case class Stage(name: String) {
    override def toString = name
  }

  case class Time(time: String) {
    override def toString = time
  }

  type Slot = (Stage, Time)

  /*
   * This function schedules bands to slots. It takes as input
   * a list of preferences, as a map from bands to the list of 
   * slots the band wishes to play in.
   *
   * The result is an `Option[Map[Band, Slot]]`. The function attemps to
   * assign a unique and different slot to each band. It returns None if
   * no complete valid scheduling exists.
   * If the problem has a complete valid assignment, a map from every
   * band to a slot is returned.
   * No partial solution is returned, if only some of the band can be assigned
   * a slot, then None is returned.
   */
  def plan(preferences: Map[Band, List[Slot]]): Option[Map[Band, Slot]] = {

    val bands: Seq[Band] = preferences.keys.toSeq
    val slots: Seq[Slot] = getAllSlots(preferences).toSeq

    //generates one propositional variable per band/slot combination
    val propVariables: Map[(Band, Slot), PropVar] =
      bands.flatMap({
        case b @ Band(name) =>
          slots.map(s => (b, s) -> propVar(name))
      }).toMap

    //Set of constraints ensuring each band gets a desired slot
    val desirableSlots: Seq[Formula] =
      bands map { x => preferences(x).map(propVariables(x, _)).foldLeft[Formula](false)(_ || _) }

    //A set of constraints ensuring that a band gets at most one slot
    val eachBandPlaysOnce: Seq[Formula] =
      bands map { x =>
        {
          for {
            slot1 <- slots
            slot2 <- slots.dropWhile(_ != slot1)
            if (slot1 != slot2)
          } yield !propVariables(x, slot1) || !propVariables(x, slot2)
        }.foldLeft[Formula](true)(_ && _)
      }

    //A set of constraints ensuring that each slot is used at most once
    val eachSlotUsedOnce: Seq[Formula] = 
       slots map { x =>
        {
          for {
            band1 <- bands
            band2 <- bands.dropWhile(_ != band1)
            if (band1 != band2)
          } yield !propVariables(band1, x) || !propVariables(band2, x)
        }.foldLeft[Formula](true)(_ && _)
      }

    //combining all the constraints together
    val allConstraints: Seq[Formula] =
      desirableSlots ++ eachBandPlaysOnce ++ eachSlotUsedOnce

    //finding a satisfying assignment to the constraints
    val res = solveForSatisfiability(and(allConstraints: _*))

    res.map(model => {
      bands.map(band => {
        val assignedSlot = slots.find(slot => model(propVariables((band, slot))))
        (band, assignedSlot.get)
      }).toMap
    })
  }

  /**
   * This function takes a preference map, and returns all unique slots that are
   * part of the preferences.
   */
  def getAllSlots(preferences: Map[Band, List[Slot]]): Set[Slot] = 
    preferences.toList.flatMap(_._2).toSet

}
