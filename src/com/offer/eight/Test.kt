package eight

object Test {
    @JvmStatic
    fun main(args: Array<String>) {
        println(System.currentTimeMillis())
        println(Fibonacci().fibonacci1(4))
        println(System.currentTimeMillis())
        println(Fibonacci().fibonacci2(4))
        println(System.currentTimeMillis())
    }
}