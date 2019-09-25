package eight

object Test {
    @JvmStatic
    fun main(args: Array<String>) {
        /*println(System.currentTimeMillis())
        println(Fibonacci().fibonacci1(100))*/
        println(System.currentTimeMillis())
        println(Fibonacci().fibonacci2(100))
        println(System.currentTimeMillis())
    }
}