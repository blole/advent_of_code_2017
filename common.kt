import kotlin.coroutines.experimental.buildSequence

inline fun <reified INNER> array2d(sizeOuter: Int, sizeInner: Int, noinline innerInit: (Int)->INNER): Array<Array<INNER>>
        = Array(sizeOuter) { Array<INNER>(sizeInner, innerInit) }
fun array2dOfInt(sizeOuter: Int, sizeInner: Int): Array<IntArray> = Array(sizeOuter) { IntArray(sizeInner) }
fun array2dOfLong(sizeOuter: Int, sizeInner: Int): Array<LongArray> = Array(sizeOuter) { LongArray(sizeInner) }
fun array2dOfByte(sizeOuter: Int, sizeInner: Int): Array<ByteArray> = Array(sizeOuter) { ByteArray(sizeInner) }
fun array2dOfChar(sizeOuter: Int, sizeInner: Int): Array<CharArray> = Array(sizeOuter) { CharArray(sizeInner) }
fun array2dOfBoolean(sizeOuter: Int, sizeInner: Int): Array<BooleanArray> = Array(sizeOuter) { BooleanArray(sizeInner) }



fun <A,B,AC: Iterable<A>> AC.product(bs: Iterable<B>): Sequence<Pair<A,B>> = buildSequence {
    for (a: A in this@product) {
        for (b: B in bs) {
            yield(Pair(a, b))
        }
    }
}
fun <A,AC: Iterable<A>> AC.productSelf(): Sequence<Pair<A,A>> = this.product(this)

fun <A,AC: Iterable<A>> AC.productSelfExcluding(): Sequence<Pair<A,A>> = buildSequence {
    for ((i, a) in this@productSelfExcluding.withIndex()) {
        for ((j, b) in this@productSelfExcluding.withIndex()) {
            if (i != j)
                yield(Pair(a, b))
        }
    }
}

fun <A,AC: List<A>> AC.combinations(r: Int): Sequence<List<A>> = buildSequence {
    val n: Int = this@combinations.size
    if (r > n)
        return@buildSequence
    val indices: MutableList<Int> = (0..(r-1)).toMutableList()
    yield(this@combinations.slice(indices))

    outer@
    while (true) {
        for (i: Int in (0..(r-1)).reversed()) {
            if (indices[i] != i + n - r) {
                indices[i] += 1
                for (j: Int in (i+1)..(r-1))
                    indices[j] = indices[j-1] + 1
                yield(this@combinations.slice(indices))
                continue@outer
            }
        }
        break
    }
}
fun <A,AC: Iterable<A>> AC.combinations(r: Int): Sequence<List<A>> = toList().combinations(r)

fun main(args: Array<String>) {
    require("ABCD".toList().combinations(2).toList().map { it.joinToString("") }.toString() == "[AB, AC, AD, BC, BD, CD]")
    require((0..3).combinations(3).toList().map { it.joinToString("") }.toString() == "[012, 013, 023, 123]")
}
