package lam.delaytask.support;

import java.io.Closeable;

/**
* <p>
* This interface defines an segment in the task array.
* </p>
* @author linanmiao
* @date 2017年4月12日
* @version 1.0
*/
public interface Segment extends Closeable{
	
	/**
	 * get slot of this segment. 
	 */
	public int getSlot();
	
	public boolean addTask(Task task);
	
	public void doTask();
	
	public boolean isEmpty();
	
	interface Task{
		
		public int getId();
		
		public int getSegmentSlot();
		
		int getOriginalCycle();
		
		int getCycleNum();
	
		/**
		 * To determine whether the task can be executed. 
		 */
		boolean canDo();
		
		void runOneTime();
		
		boolean doTaskSelf(long taskTimeMillis);
	}

}
