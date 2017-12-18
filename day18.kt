import kotlinPuzzleLibrary.whenRegex

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
    val vars: HashMap<Char,Long> = hashMapOf(*(('a'..'z').map { Pair(it,0L) }.toTypedArray()))

    var lastPlayed = 0L
    var part1 = 0L

    var pc = 0
    while (pc in instructions.indices) {
        whenRegex(instructions[pc]) {
            "snd (.)"         then { lastPlayed = vars[c1]!! }
            "set (.) ([a-z])" then { vars[c1] = vars[c2]!! }
            "set (.) (.+)"    then { vars[c1] = d2.toLong() }
            "add (.) ([a-z])" then { vars[c1] = vars[c1]!! + vars[c2]!! }
            "add (.) (.+)"    then { vars[c1] = vars[c1]!! + d2 }
            "mul (.) ([a-z])" then { vars[c1] = vars[c1]!! * vars[c2]!! }
            "mul (.) (.+)"    then { vars[c1] = vars[c1]!! * d2 }
            "mod (.) ([a-z])" then { vars[c1] = vars[c1]!! % vars[c2]!! }
            "mod (.) (.+)"    then { vars[c1] = vars[c1]!! % d2 }
            "rcv (.)"         then { if (vars[c1]!! != 0L) part1 = lastPlayed }
            "jgz (.) (.+)"    then { if (vars[c1]!! >  0L) pc += d2-1 }
        }
        if (part1 != 0L)
            break
        pc++
    }

    println(part1)
