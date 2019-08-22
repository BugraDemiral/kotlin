import java.util.Timer
import kotlin.concurrent.schedule

class Queue<T> {
    val elements: MutableList<T> = mutableListOf()
    fun isEmpty() = elements.isEmpty()
    fun count() = elements.size
    fun enqueue(item: T) = elements.add(item)
    fun dequeue() = if (!isEmpty()) elements.removeAt(0) else null
    fun peek() = if (!isEmpty()) elements[0] else null

    override fun toString(): String = elements.toString()
}

open class APIMock() {

private val delay: Long = 50 // delay in milliseconds
private var timer = Timer(true)
private var chatQueue = Queue<String>

// call the API
fun send(message: String) {
timer.cancel()
timer = Timer(true)
timer.schedule(delay) {
onSend(message)
}

}

// callback when the API call has returned
open fun onSend(message: String) : Unit {
	
	chatQueue.enqueue(message)
}

}


fun main() {
val api = APIMock()

// Only ", World!" is sent because subsequent calls cancel pending calls
api.send("Hello")
api.send(", World!")

while (!chatQueue.isEmpty()) { 

	println("Sent: $chatQueue.peek()")
	chatQueue.dequeue()
}

}