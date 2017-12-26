import kotlinPuzzleLibrary.Dir
import kotlinPuzzleLibrary.toHashMap
import java.util.*

fun main(args: Array<String>) {
    val input =
""""
Begin in state A.
Perform a diagnostic checksum after 12261543 steps.

In state A:
  If the current value is 0:
    - Write the value 1.
    - Move one slot to the right.
    - Continue with state B.
  If the current value is 1:
    - Write the value 0.
    - Move one slot to the left.
    - Continue with state C.

In state B:
  If the current value is 0:
    - Write the value 1.
    - Move one slot to the left.
    - Continue with state A.
  If the current value is 1:
    - Write the value 1.
    - Move one slot to the right.
    - Continue with state C.

In state C:
  If the current value is 0:
    - Write the value 1.
    - Move one slot to the right.
    - Continue with state A.
  If the current value is 1:
    - Write the value 0.
    - Move one slot to the left.
    - Continue with state D.

In state D:
  If the current value is 0:
    - Write the value 1.
    - Move one slot to the left.
    - Continue with state E.
  If the current value is 1:
    - Write the value 1.
    - Move one slot to the left.
    - Continue with state C.

In state E:
  If the current value is 0:
    - Write the value 1.
    - Move one slot to the right.
    - Continue with state F.
  If the current value is 1:
    - Write the value 1.
    - Move one slot to the right.
    - Continue with state A.

In state F:
  If the current value is 0:
    - Write the value 1.
    - Move one slot to the right.
    - Continue with state A.
  If the current value is 1:
    - Write the value 1.
    - Move one slot to the right.
    - Continue with state E.
""".trim()

    data class StateTransition(val write: Boolean, val move: Int, val next: Char)
    data class State(val id: Char, val ifZero: StateTransition, val ifOne: StateTransition)

    val states: HashMap<Char, State> = input.split("\n\n").drop(1).map {
        val id = Regex("In state (.):.*").find(it)!!.groupValues[1][0]
        val transitions = it.split("If").drop(1).map {
            val write = Regex("Write the value (.).").find(it)!!.groupValues[1].toInt()==1
            val move = if (Regex("Move one slot to the (.+).").find(it)!!.groupValues[1] == "right") 1 else -1
            val next = Regex("Continue with state (.).").find(it)!!.groupValues[1][0]
            StateTransition(write, move, next)
        }
        State(id, transitions[0], transitions[1])
    }.toHashMap { it.id }

    fun Char.toState() = states[this]!!

    val steps = Regex("Perform a diagnostic checksum after (.+) steps.").find(input)!!.groupValues[1].toInt()
    var state = Regex("Begin in state (.).").find(input)!!.groupValues[1][0].toState()
    var pos = 0
    val tape: HashSet<Int> = hashSetOf()
    repeat(steps) {
        when (tape.contains(pos)) {
            false -> {
                if (state.ifZero.write) tape.add(pos) else tape.remove(pos)
                pos      += state.ifZero.move
                state     = state.ifZero.next.toState()
            }
            true -> {
                if (state.ifOne.write) tape.add(pos) else tape.remove(pos)
                pos      += state.ifOne.move
                state     = state.ifOne.next.toState()
            }
        }
    }
    val part1 = tape.size

    println(part1)
}
