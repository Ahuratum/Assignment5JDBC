package business;

import java.util.Date;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;


@Stateless
public class MyTimerService {
	
	@Resource
    TimerService timerService;
	
	public static final Logger logger = Logger.getLogger("business.MyTimerService");

    public MyTimerService() {
        
    }
	
    public void setTimer(long interval) {
    	timerService.createTimer(interval, "Set Timer");
    }
    
    @Timeout
    public void timeout(Timer timer) {
    	// Create an instance instead of a new object, use less resources 
    	Date date = java.util.Calendar.getInstance().getTime();
    	//logger.info("Timeout trigger @ " + date);
    }
    
	@SuppressWarnings("unused")
	@Schedule(second="*/10", minute="*", hour="0-23", dayOfWeek="Mon-Fri",
      dayOfMonth="*", month="*", year="*", info="MyTimer")
    private void scheduledTimeout(final Timer t) {
		// Create an instance instead of a new object, use less resources 
    	Date date = java.util.Calendar.getInstance().getTime();
    	//logger.info("Scheduled trigger @ " + date);
    }
}