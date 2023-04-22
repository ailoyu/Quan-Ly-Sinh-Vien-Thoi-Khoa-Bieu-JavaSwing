package MyList;

public class MyDoublyLinkedList<T> implements IMyList<T> {
	
	public class Node extends MyDoublyLinkedList<T>{
		public T value;
		public Node next;
		public Node prev;
		
		public Node (T t) {
			this.value = t;
			this.next = null;
			this.prev = null;
		}
	}
	
	public Node head;
	
	public MyDoublyLinkedList() {
		head = null;
	}
	
	@Override
	public void addFront(T t) {
		Node newNode = new Node(t);
		newNode.next = head;
		if(head != null) {
			head.prev = newNode;	
		}
		head = newNode;
	}

	@Override
	public void add(T t) {
		Node newNode = new Node(t);
		if(head == null) {
			head = newNode;
			return;
		}
		Node temp = head;
		while(temp.next != null) {
			temp = temp.next;
		}
		temp.next = newNode;
		newNode.prev = temp;
	}

	@Override
	public boolean remove(T t) {
		if(head == null) {
			return false;
		}
		
		if(head.value == t) {
			head = head.next;
			return true;
		}
		Node pre = head;
		while(pre.next != null) {
			if(pre.next.value == t) {
				pre.next = pre.next.next;
				return true;
			}
			pre = pre.next;
		}
		return false;
	}
	
	public boolean remove(int index) {
		if(head == null) {
			return false;
		}
		if(index == 0) {
			head = head.next;
			return true;
		}
		Node pre = head;
		int count = 0;
		while(pre.next != null) {
			++count;
			if(count == index) {
				pre.next = pre.next.next;
				return true;
			}
			pre = pre.next;
		}
		return false;
	}

	@Override
	public void print() {
		Node pre = head;
		while(pre != null) {
			System.out.print(pre.value + "->");
			pre = pre.next;
		}
	}

	@Override
	public void clear() {
		head = null;
	}
	
	@Override
	public int size() {
		int count = 0;
		Node pre = head;
		while(pre != null) {
			count++;
			pre = pre.next;
		}
		return count;
	}
	
	@Override
	public boolean contains(T t) {
		if(head == null) {
			return false;
		}
		
		if(head.value == t) {
			return true;
		}
		Node pre = head;
		while(pre.next != null) {
			if(pre.next.value == t) {
				return true;
			}
			pre = pre.next;
		}
		return false;
		
	}

	@Override
	public T get(int index) {
		int count = -1;
		Node pre = head;
		while(pre != null) {
			count++;
			if(count == index) {
				return pre.value;
			}
			pre = pre.next;
		}
		return null;
	}

	public void add(T t, int index) {
		if(index == 0 || head == null) {
			addFront(t);
			return;
		}
		if(index < 0 || index < size()) {
			Node temp = head;
			for(int i = 0; i <= index - 1; i++) {
				temp = temp.next;
			}
			Node newNode = new Node(t);
			newNode.next = temp;
			temp.prev.next = newNode;
			newNode.prev = temp.prev;
			temp.prev = newNode;
		}
		if(index >= size()) {
			add(t);
		}
		
	}
	
	public static void main(String[] args) {
		MySinglyLinkedList<Integer> list = new MySinglyLinkedList<>();
		list.print();
		
		
	}
	
	
}
