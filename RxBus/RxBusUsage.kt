// Custom MessageEvent class
data class MessageEvent(val action: Int,
                        val message: String)

// Listen for MessageEvents only
RxBus.listen(MessageEvent::class.java).subscribe({
        println("Im a Message event ${it.action} ${it.message}")
    })

// Listen for String events only
RxBus.listen(String::class.java).subscribe({
        println("Im a String event $it")
    })
	
// Listen for Float events only
RxBus.listen(Float::class.java).subscribe({
        println("Im a Float event $it")
    })


// Publish events
RxBus.publish(MessageEvent(1, "Hello, World"))
RxBus.publish("Testing")
RxBus.publish(12.3f)