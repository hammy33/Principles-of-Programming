public class Assignment1 {

	public static int[] findPeak(int[][] map){
		int peak = map[0][0];
		int [] location = new int[2];		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				int value = map[i][j];
				if(value > peak) {
					peak = value;
					location[0] = i;
					location[1] = j;
				}
			}
		}
		return location;
	}
	
	public static boolean isSink(int[][] map, int[] cell) {
		if ((cell[0] > map.length) || (cell[1] > map.length)) {
			return false;
		} 
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
                if (cell[0] + i >= 0 && cell[1] + j >= 0 && cell[0] + 1 < map.length && cell[1] < map.length) {
					if (map[cell[0]][cell[1]] > map [cell[0] + i][cell[1] + j] ) {
						return false;
					}
				}	
            }

        }
        return true;   
    }
	
	public static int[] findLocalSink(int[][] map, int[] startCell) {
		int local_sink_cell[] = {startCell[0], startCell[1]};
		while (!isSink(map, local_sink_cell)) {
			int max_diff = 0;
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (startCell[0] + i >= 0 && startCell[1] + j >= 0 && startCell[0] + 1 < map.length && startCell[1] < map.length) {
						int diff = map[startCell[0]][startCell[1]]; 
						if (diff > max_diff) {
							diff = max_diff;
							local_sink_cell[0] = startCell[0] + i;
							local_sink_cell[0] = startCell[1] + j;
						}
					}
				}
			}
			startCell[0] = local_sink_cell[0];
			startCell[1] = local_sink_cell[1];
		}
		return local_sink_cell;
	}
	
	public static void rotateMap(int map[][]) {
		if (map.length == 0) {
			return;
		}

		for (int i = 0; i < map.length/2; i++) { 
            for (int j = i; j < (map.length - 1 - i); j++) { 
                int temp = map[i][j]; 
                // Move values from right to top 
                map[i][j] = map[j][map.length - 1 - i]; 
                // Move values from bottom to right 
                map[j][map.length - 1 - i] = map[map.length - 1 - i][map.length - 1 - j]; 
                // Move values from left to bottom 
                map[map.length - 1 - i][map.length - 1 - j] = map[map.length - 1 - j][i]; 
                // Assign temp to left 
                map[map.length - 1 - j][i] = temp; 
			} 
		}
	} 
	
}
