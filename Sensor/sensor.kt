Observable<Integer> observable = Observable.create(subscriber -> {

    int i = 0;
    while(true) {
        if(subscriber.isDisposed()) {
             break;
        }

        subscriber.onNext(i++);
        
        //registering a callback when the downstream subscriber unsubscribes
        subscriber.setCancellable(() -> log.info("Subscription canceled"));
    }
});

var connection_status = false

fun handleSensorLogic(Int : val) : Unit {

	if(val.rem(5) == 0) {

		connection_status = true
		
	} else if(val.rem(3) == 0 && val.rem(5) == 0) {

		connection_status = true

	} else if(val.rem(3) == 0) {
		
		connection_status = false
	}

}

observable
    .take(5000) //unsubscribes after the 5000th event. Infinite required???
    .subscribe(val -> handleSensorLogic(val),
               err -> log.error("Subscriber received error", err),
               () -> log.info("Subscriber got Completed event") //The Complete event 
               //is triggered by 'take()' operator