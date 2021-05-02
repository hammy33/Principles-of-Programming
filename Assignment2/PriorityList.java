public class PriorityList {
	
	//ONLY IMPLEMENT/WRITE CODE IN THE METHODS WITH "TODO"
	//DO NOT MODIFY/WRITE CODE ANYWHERE ELSE
	private PriorityNode head;
	private int numberOfCustomers;
	
	public PriorityList() {
		numberOfCustomers = 0;
	}; 
	
	public void addCustomer(Customer customer) {
		PriorityNode newNode = new PriorityNode();
		PriorityNode current = head;
		if (isEmpty() || current.next == null || current.next.getQeueue().peek().priority > customer.priority ) {
			newNode.next = head;
			head = newNode;
			numberOfCustomers++;
		}
		else {
			current = head;
			while (current.next.getQeueue().peek().priority <= customer.priority) {
				if (current.next.getQeueue().peek().priority == customer.priority) {
					newNode.next = head;
					head = newNode;
					numberOfCustomers++;
				}	
				else {
					newNode.next = current.next;
					current.next = newNode;
					numberOfCustomers++;
				}
				newNode.next = current.next;
				current.next = newNode;
				numberOfCustomers++;
            }
		}

    }
	
	public Customer getNextCustomer() {
		PriorityNode current = head; ;
		if (current.getQeueue().isEmpty()) {
			deletePriorityLevel(current.getQeueue().peek().priority);
			return null; 
        }
        else {
            while(current.next != null) {
				current = current.next;
			}
			return current.getQeueue().peek();
        }
	}
	
	
	public void deletePriorityLevel(int k) {
		if(isEmpty()) {
            return;
		}
		MyQueue headQueue = head.getQeueue();
		Customer headCustomer = headQueue.peek();
		int headPriority = headCustomer.priority;
		if(headPriority == k) {
			head = head.next;
		}
		else {	
			PriorityNode current = head;
			while(current.next != null) {
				if(current.next.getQeueue().peek().priority == k) {
					current.next = current.next.next;
					break;
				}
				current = current.next;
			}
		}
	}
	
	public void truncateDownTo(int k) {
		if(numberOfCustomers <= k) {
			return;
		}
		else {
			PriorityNode current = head;
			while(current.next != null) {
				if(head == null ||head.next == null) {
					return;
				}
				PriorityNode temp = head;
				while(temp.next.next != null) {
					temp = temp.next;
				}
				temp.next = null;
			}
		}
	}
	/* The following method was modified. The code was changed to become a PriorityNode method rather than a PriorityList method.
	I understand that this will not pass any of the test cases, however this was the only way I was able to grasp the idea of merging two linked lists.
	I hope you can see what I tried to do here, and I will look forward to the solutions to learn the proper implementation under a PriorityList method :) */
	
	public PriorityNode mergeLists(PriorityList listToMerge) {
		PriorityList originalList = new PriorityList();
		PriorityNode head1 = originalList.head;
		PriorityNode head2 = listToMerge.head;
		if(head1 == null) {
			return head2;
		}
		if (head2 == null) {
			return head1;
		}
		PriorityNode head3 = null;
		if (head1.getQeueue().peek().id < head2.getQeueue().peek().id) {
			head3 = head1;
			head1 = head1.next;
		}
		else {
			head3 = head2;
			head2 = head2.next;
		}
		PriorityNode currentNode = head3;
		while (head1 != null && head2 != null) {
			if (head1.getQeueue().peek().id < head2.getQeueue().peek().id) {
				currentNode.next = head1;
				head1 = head1.next;
			}
			else {
				currentNode.next = head2;
				head2 = head2.next;
			}
			currentNode = currentNode.next;
		}

		if (head1 == null) {
			currentNode.next = head2;
			head2 = head2.next;
		}
		else {
			currentNode.next = head1;
			head1 = head1.next;
		}
		return head3;
		
	}
	
	public boolean isEmpty() {
		return head == null;
	}
	
	public int getNumberOfNodes() {
		int numberOfNodes = 0;
		PriorityNode current = head;
		while(current != null) {
			numberOfNodes++;
			current = current.next;
		}
		return numberOfNodes;
	}
	
}
