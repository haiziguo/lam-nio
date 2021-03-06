import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lam.delaytask.DelaySegment;
import lam.delaytask.DelayTaskRunner;
import lam.delaytask.support.Segment;
import lam.util.FinalizeUtils;

/**
* <p>
* TODO
* </p>
* @author linanmiao
* @date 2017年4月12日
* @version 1.0
*/
public class DelayTaskTest {
	
	private static Logger logger = LoggerFactory.getLogger(DelayTaskTest.class);
	
	public static void main(String[] args){
		final DelayTaskRunner runner = new DelayTaskRunner();
		runner.start();
		
		//shutdown grancefull on command:kill pid, not command:kill -9 pid
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				//runner.close();
				FinalizeUtils.closeQuietly(runner);//close runner gracefully
				logger.info("DelayTaskRunner close gracefully.");
			}
		});
		
		sleep(20);
		
		int taskNum = 100000;
		Random r = new Random();
		for(int i = 0; i < taskNum; i++){
			sleep(r.nextInt(800));
			int s = r.nextInt(3600);
			int c = r.nextInt(3);
			Segment.Task task = new DelaySegment.DelayTask(i, c, s + 3600 * c, runner);
			runner.addTask(task);
		}
		logger.info(taskNum + " task has already been added.");
	}
	
	private static void sleep(long millisecond){
		try {
			Thread.sleep(millisecond);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
