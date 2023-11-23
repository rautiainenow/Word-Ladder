/**
	This class implements a circular array.
	It expands if the queue becomes full.
**/
public class Queue {
	private QNode [] queue;		// Array that stores the queue elements.
	private int front;				// Index pointing to the front of the queue.
	private int end;					// Index pointing to the element that is one less than the end of the queue.
	private int numElements;	// The number of elements currently stored in the queue.
	private int size;					// The capacity of the allocated array. If the number of elements reaches this capacity, we need to expand.

	/**
		Constructor: Allocates a queue with inital size of 1000.
	**/
	public Queue() {
		numElements = 0;
		size = 1000;
		front = size - 1;
		end = size - 1;
		queue = new QNode[size];
	}
	
	/**
		This function enqueues a new element p into the queue. 
		This also expands the array if it is full.
	**/
	public void enqueue(QNode p) {
		if (numElements == size) {
			//Expand the array, by first creating another one with twice the size and copying the contents of the old array
			int origSize = size-1;
			size = size*2;
			QNode [] temp = new QNode[size];
			int sizeTemp = size-1;
			
			if (front > end)
			{
				for(int i = front; i > end ; i--)
				{
					temp[sizeTemp] = queue[i];
					sizeTemp--;
				}
			}
			else 
			{
				while (front >= 0)
				{
					temp[sizeTemp] = queue[front];
					front--;
				}
				front = origSize;
				while(front >= end)
				{
					temp[sizeTemp] = queue[front];
					front--;
				}
			}


			end = (size-1) - numElements;
			front = size-1;
			queue = new QNode[size];
			queue = temp;
		}

		if(end < 0) //puts end back at the far side of the array
			end = size-1;
	
		queue[end] = p;
		end--;
		numElements++;
	
	}

	/**
		This funciton removes and returns the end front element in the queue.
	**/
	public QNode dequeue() {
		if (numElements == 0) {
			return null;
		}
		// Code to remove and return the front element.
		if(front < 0)
		{
			front = size-1;
		}
		QNode dq = new QNode(queue[front]);
		front--;
		numElements--;
		return dq;	
	}
		

	/**
		This funciton returns true if the queue is empty, otherwise returns false.
	**/
	public boolean isEmpty() {
		if (numElements == 0) 
			return true;
		return false;
	}

	/**
		This function prints the contents of the queue.
	**/
	public void print() {
        // print the contents of the queue from front to end. Please print each element on its own line. You may use the toString() method of QNode to print it on a line.
		int t = front;
		while (t != end)
		{
			if(t < 0) t = size-1; 
			System.out.println(queue[t].getWord());
			t--;
		}
	}

}
