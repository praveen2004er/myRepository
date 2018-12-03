package sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventLoopExample {
	public static List<String> queue = new ArrayList<String>();

	private static Random random = new Random();
	
	public static void main(String[] args) {
		Thread looper = new Thread(new EventLooper());
		looper.start();
		
		Thread producer = new Thread(new EventProducer());
		producer.start();
		
		
		
	}
	
	private static class EventProducer implements Runnable {

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getThreadGroup().getName());
			
			for(;;) {
				
				try {
					Thread.sleep(1000);
					synchronized(queue) {
						queue.add(""+random.nextInt()+"");
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
			
			
			
		}
		
	}
		
	
	private static class EventLooper implements Runnable {

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getThreadGroup().getName());
			for(;;) {
				synchronized(queue) {
					while(!queue.isEmpty()) {
						System.out.println(queue.remove(0));
						
					}
				}
			}
			
			
			
		}
		
		
	}
	
}
