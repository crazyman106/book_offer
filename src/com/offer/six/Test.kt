package six

object Test {
    @JvmStatic
    fun main(args: Array<String>) {
        var queue = Queue<Int>();
        queue.appendTail(1)
        queue.appendTail(2)
        queue.appendTail(3)
        queue.appendTail(4)
        println(queue.deleteHead())
        queue.appendTail(5)
        println(queue.deleteHead())
    }
}