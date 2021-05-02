public class MyQueue {

	//DO NOT CHANGE OR ADD VARIABLES
	private Customer[] customers;
	private int length;
	private int front = 0;
	private int back = 0;
	
	public MyQueue() {
		this.customers = new Customer[10];
		this.length = 0;
	}
	
	//IMPLEMENT THIS METHOD
	public void resizeUp() {
		if ((length*(3/2) >= 10)) {
			Customer[] resizedQueue = new Customer[length*2];
			for (int i = 0; i < length; i++) {
				resizedQueue[i] = customers[front];
				front = (front + 1) % length;
			}
			customers = resizedQueue;
		}
		else {
			return;
		}
		
	}
	//IMPLEMENT THIS METHOD
	public void resizeDown() {
		if (length - 1 >= 10) {
			Customer[] resizedQueue = new Customer[length - 1];
			for (int i = 0; i < length; i++) {
				resizedQueue[i] = customers[front];
				front = (front - 1) % length;
			}
			customers = resizedQueue;
		}
		else {
			return;
		}
	}
	
	//DO NOT CHANGE THIS METHOD
	public void add(Customer value) {
		if(length == customers.length) {
			resizeUp();
		}
		customers[back] = value;
		length++;
		if(back == customers.length - 1) {
			back = 0;
		}
		else {
			back++;
		}
	}
	
	//ONLY CHANGE THE ONE LINE
	public Customer remove() {
		Customer customer = customers[front];
		if(front == customers.length - 1) {
			front = 0;
		}
		else {
			front++;
		}
		length--;
		if(customers.length >= 10 && customers.length > 4*(front - back) && (front - back) > 5) {
			resizeDown(); 
		}
		return customer;
	}
	
	//DO NOT CHANGE THIS METHOD
	public Customer peek() {
		return customers[front];
	}
	
	//DO NOT CHANGE THIS METHOD
	public boolean isEmpty() {
		return length == 0;
	}
	
	//DO NOT CHANGE THIS METHOD
	public int getLength() {
		return length;
	}	
	
}
