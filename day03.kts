var input = 368078

val dx = Math.ceil(Math.sqrt(input.toDouble())).toInt() / 2
var dy = input - (2*dx - 1) * (2*dx - 1) - 1
dy %= 2 * dx
dy = Math.abs((dx - 1) - dy)

val part1 = dx + dy

println(part1)

///////////////
fun array2dOfInt(sizeOuter: Int, sizeInner: Int): Array<IntArray>
        = Array(sizeOuter) { IntArray(sizeInner) }

var mat = array2dOfInt(100,100)
var n=0
var x=0
var y=0
fun inc() {
    x += 50
    y+= 50
    mat[x][y] = mat[x+1][y+1] + mat[x+1][y] + mat[x+1][y-1] + mat[x][y+1] + mat[x][y-1] + mat[x-1][y+1] + mat[x-1][y] + mat[x-1][y-1] + mat[x][y]

    if (mat[x][y] > input) {
        println(mat[x][y])
        kotlin.system.exitProcess(0)
    }
    x-=50
    y-=50
}
mat[50][50]=1
while(1==1){
    for (i in 0..n) {
        inc()
        x++
    }
    for (i in 0..n) {
        inc()
        y++
    }
    n++
    for (i in 0..n) {
        inc()
        x--
    }
    for (i in 0..n){
        inc()
        y--
    }
    n++
}