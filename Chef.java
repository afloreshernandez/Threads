package com.cognixia.jump.advancedjava.threads;

import java.util.concurrent.ThreadLocalRandom;

public class Chef implements Runnable {

	private Orders orders;

	public Chef(Orders orders) {
		this.orders = orders;
	}

	@Override
	public void run() {

		// ADD CODE HERE
		String name = Thread.currentThread().getName();

		
			//Thread.sleep(3000);

			synchronized (orders) {
				try {
				System.out.println(name + " waiting for an order to prepare");

				orders.wait();
				String thisOrder = orders.nextOrder();
				if(thisOrder != null) {


					System.out.println(name + "received order for "+thisOrder);
					System.out.println(name + " preparing "+thisOrder);
					Thread.sleep(ThreadLocalRandom.current().nextInt(2000,5000));
					System.out.println(name + " completed order for "+thisOrder);

				}
				else {
					System.out.println("No order to take, "+name+ " can rest.");
				}

			} catch (InterruptedException e) {

				e.printStackTrace();
			}


		}

	}

}