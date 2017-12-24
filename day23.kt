import kotlinPuzzleLibrary.*

fun main(args: Array<String>) {
    val input =
"""
set b 84
set c b
jnz a 2
jnz 1 5
mul b 100
sub b -100000
set c b
sub c -17000
set f 1
set d 2
set e 2
set g d
mul g e
sub g b
jnz g 2
set f 0
sub e -1
set g e
sub g b
jnz g -8
sub d -1
set g d
sub g b
jnz g -13
jnz f 2
sub h -1
set g b
sub g c
jnz g 2
jnz 1 3
sub b -17
jnz 1 -23
""".trim()
    val instructions = input.lines()
    val vars: MutableList<Int> = MutableList(128, {0})
    fun value(s:String): Int = if (s.length == 1 && s[0] in 'a'..'h') vars[s[0].toInt()] else s.toInt()

    var part1 = 0
    var pc = 0
    while (pc in instructions.indices) {
        whenRegex(instructions[pc]) {
            "set (.) (.+)" then { vars[c1.toInt()] = value(g2) }
            "sub (.) (.+)" then { vars[c1.toInt()] -= value(g2) }
            "mul (.) (.+)" then { vars[c1.toInt()] *= value(g2); part1++ }
            "jnz (.) (.+)" then { if (value(g1) != 0) pc += value(g2)-1 }
        }
        pc++
    }

    println(part1)
}
