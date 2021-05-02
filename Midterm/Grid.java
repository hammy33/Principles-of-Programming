public class Grid {

	private int[][] grid;
	
	public Grid(int n) {
		grid = new int[n][n];
	}
	
	public void print() {
		String s;
		for(int i = 0; i < grid.length; i++) {
			s = "[";
			for(int j = 0; j < grid.length-1; j++) {
				s += grid[i][j] + ",";
			}
			System.out.println(s + grid[i][grid.length-1] + "]");
		}

	}
	public void dropAt(int num, int col) {
		for (int i = grid.length - 1; i >= 0; i--) {
			if (col >= 0 && col < grid.length) {
				if (grid[i][col] == 0) {
					grid[i][col] = num;
					return;
				}
			}
			else {
				return;
			}
		}
		for (int i = grid.length - 1; i >= 0; i--) {
			if ((col - 1) >= 0 && (col - 1) < grid.length) {
				if (grid[i][col - 1] == 0) {
					grid[i][col - 1] = num;
					return;
				}
			}
			else {
				return;
			}
		}
		for (int i = grid.length - 1; i >= 0; i--) {
			if ((col + 1) >= 0 && (col + 1) < grid.length) {
				if (grid[i][col + 1] == 0) {
					grid[i][col + 1] = num;
					return;
				}
			}
			else {
				return;
			}
		}
	
	}
}
