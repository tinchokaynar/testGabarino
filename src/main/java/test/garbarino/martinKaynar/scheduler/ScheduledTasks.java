package test.garbarino.martinKaynar.scheduler;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import test.garbarino.martinKaynar.services.CartService;

@Component
public class ScheduledTasks {
	
	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
	
	@Autowired
	private CartService cartService;

	@Scheduled(cron = "*/10 * * * * *")
	public void payment() {
		//Simulo como que llega un id de carrito random entre varios
		
		Long ram =(long) new Random().nextInt(4);		
		logger.info("[" + Thread.currentThread() + "] Se llamo a payment con el id: " + ram );
		cartService.payment(ram);
	}
	
}