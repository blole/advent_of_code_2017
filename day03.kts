var input = 368078

val dx = Math.ceil(Math.sqrt(input.toDouble())).toInt() / 2
var dy = input - (2*dx - 1) * (2*dx - 1) - 1
dy %= 2 * dx
dy = Math.abs((dx - 1) - dy)

val part1 = dx + dy

println(part1)
