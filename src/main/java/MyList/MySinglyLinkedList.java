package MyList;

public class MySinglyLinkedList<T> implements IMyList<T> {
	
	public class Node extends MySinglyLinkedList<T>{
		public T value;
		public Node next;
		
		public Node (T t) {
			this.value = t;
			this.next = null;
		}
	}
	
	public Node head;
	
	public MySinglyLinkedList() {
		head = null;
	}
	
	@Override
	public void addFront(T t) {
		// head = null; pre = head = null; head = 3; 3->null;
		// head = 3, pre = head = 3, head = 5->3, 
		Node pre = head;
		head = new Node(t);
		head.next = pre;
	}

	@Override
	public void add(T t) {
		// head = null; head = 3;
		if(head == null) {
			head = new Node(t);
		}else {
			// head = 5; t = 4; pre = head = 5; pre->4;
			// head = 5->4; t = 7; pre = head = 5; pre->next = 4; pre = pre->next = 4; break vòng lap->
			Node pre = head;
			while(pre.next != null) { 
				pre = pre.next; 
			}
			pre.next = new Node(t);
		}
	}

	@Override
	public boolean remove(T t) {
		// 
		if(head == null) {
			return false;
		}
		
		if(head.value == t) {
			head = head.next; 
			return true;
		}
		
		// 3->4->5->6- 
		// pre = head = 3; 
		// pre->next = 4; => !null
		// pre->next = 4 === (t = 4) 
		// pre->next = 4 = pre->next->next = 5
		// break vòng lặp
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

	@Override
	public void add(T t, int index) {
		if(index == 0 || head == null) {
			addFront(t);
			return;
		}
		if(index < 0 || index < size()) {
			Node temp = head;
			
			for(int i = 0; i <= index - 2; i++) {
				temp = temp.next;
			}
			Node newNode = new Node(t);
			newNode.next = temp.next;
			temp.next = newNode;
		}
		if(index >= size()) {
			add(t);
		}
		
	}
	
	public static void main(String[] args) {
		MySinglyLinkedList<Integer> list = new MySinglyLinkedList<>();
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		list.add(7);
		list.print();
	}
	
}
