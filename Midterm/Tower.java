
public class Tower {
	
	Stack values;
	int height;
	
	public Tower() {
		this.height = 0;
		this.values = new Stack();
	}
	
	public boolean isEmpty() {
		return height == 0;
	}
	
	public void addValue(int value) {
		
		if (height == 0) {
			values.push(value);
			height++;
		}

		int temp = values.pop();
		values.push(temp);
        if (value < temp) {
		    values.push(value);
			height++;
		}
		else {
			return;
		}
		
	}
	public int removeTop() {
		height--;
		return values.pop();
	}
	public void demolishAbove(int threshold) {
		
		int tempHeight = height;
		for(int i = 0; i < tempHeight; i++) {
			if (height == 0) {
				return;
			}

			int temp = values.pop();
			values.push(temp);
			if (threshold >= temp) {
				values.pop();
				height--;
			}
			else {
				return;
			}
		}

	}
	
	public void print() {
		values.print();
	}
	
}
