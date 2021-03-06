import kotlinPuzzleLibrary.whenRegex
import java.util.*

fun main(args: Array<String>) {
    val input = """
set i 31
set a 1
mul p 17
jgz p p
mul a 2
add i -1
jgz i -2
add a -1
set i 127
set p 464
mul p 8505
mod p a
mul p 129749
add p 12345
mod p a
set b p
mod b 10000
snd b
add i -1
jgz i -9
jgz a 3
rcv b
jgz b -1
set f 0
set i 126
rcv a
rcv b
set p a
mul p -1
add p b
jgz p 4
snd a
set a b
jgz 1 3
snd b
set f 1
add i -1
jgz i -11
snd a
jgz f -16
jgz a -19
""".trim()

    val instructions = input.lines()
    operator fun HashMap<Char,Long>.get(key: String) = key.toLongOrNull() ?: this.getOrPut(key[0], {0})

    var part1 = 0L
    run {
        var lastPlayed = 0L
        var pc = 0
        var vars: HashMap<Char,Long> = hashMapOf()
        while (pc in instructions.indices && part1 == 0L) {
            whenRegex(instructions[pc]) {
                "snd (.+)"      then { lastPlayed = vars[g1] }
                "set (.) (.+)"  then { vars[c1] = vars[g2] }
                "add (.) (.+)"  then { vars[c1] = vars[g1] + vars[g2] }
                "mul (.) (.+)"  then { vars[c1] = vars[g1] * vars[g2] }
                "mod (.) (.+)"  then { vars[c1] = vars[g1] % vars[g2] }
                "rcv (.)"       then { if (vars[g1] != 0L) part1 = lastPlayed }
                "jgz (.+) (.+)" then { if (vars[g1] >  0L) pc += vars[g2].toInt()-1 }
            }
            pc++
        }
    }

    class ProgramInstance {
        var pc: Int = 0
        val vars: HashMap<Char,Long> = hashMapOf()
        val queue: ArrayDeque<Long> = ArrayDeque()
        var other: ProgramInstance = this
        var sent = 0

        fun step(): Boolean {
            if (pc !in instructions.indices)
                return false
            var running = true
            whenRegex(instructions[pc]) {
                "snd (.+)"      then { other.queue.addLast(vars[g1]); sent++ }
                "set (.) (.+)"  then { vars[c1] = vars[g2] }
                "add (.) (.+)"  then { vars[c1] = vars[g1] + vars[g2] }
                "mul (.) (.+)"  then { vars[c1] = vars[g1] * vars[g2] }
                "mod (.) (.+)"  then { vars[c1] = vars[g1] % vars[g2] }
                "rcv (.)"       then { if (queue.isEmpty()) running = false else vars[c1] = queue.removeFirst() }
                "jgz (.+) (.+)" then { if (vars[g1] > 0L) pc += vars[g2].toInt()-1 }
            }
            if (running)
                pc++
            return running
        }
    }

    val prog = listOf(ProgramInstance(),ProgramInstance())
    prog[1].vars['p'] = 1
    prog[0].other = prog[1]
    prog[1].other = prog[0]

    while (prog[0].step() or prog[1].step())
        ;
    val part2 = prog[1].sent

    println(part1)
    println(part2)
}
