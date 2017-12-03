fun main(args: Array<String>) {
    val input = 368078

    val dx = Math.ceil(Math.sqrt(input.toDouble())).toInt() / 2
    var dy = input - (2*dx - 1) * (2*dx - 1) - 1
    dy %= 2 * dx
    dy = Math.abs((dx - 1) - dy)

    val part1 = dx + dy

    ///////////////

    var mat = array2dOfInt(100,100)
    var part2 = 0
    mat[50][50]=1

    fun check(x:Int, y:Int) {
        if (part2 != 0)
            return
        mat[x+50][y+50] = ((-1)..1).productSelf().sumBy { (xo,yo) -> mat[x+50+xo][y+50+yo] }
        if (mat[x+50][y+50] > input)
            part2 = mat[x+50][y+50]
    }

    var n=0
    var x=0
    var y=0
    while (part2 == 0) {
        for (i in 0..n) check(x++, y)
        for (i in 0..n) check(x,   y++)
        n++
        for (i in 0..n) check(x--, y)
        for (i in 0..n) check(x,   y--)
        n++
    }

    println(part1)
    println(part2)
}
