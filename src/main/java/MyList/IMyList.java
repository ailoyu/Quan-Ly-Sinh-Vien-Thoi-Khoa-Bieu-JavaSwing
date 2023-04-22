package MyList;

public interface IMyList<T> {
	
	void addFront(T t); 
		
	
	void add(T t); 
		
	
	boolean remove(T t); 
		
	
	void print(); 
		
	
	void clear(); 
	
	
	int size();
	
	
	boolean contains(T t);
	
	
	T get(int index);
	
	
	void add(T t, int index);
	
}
