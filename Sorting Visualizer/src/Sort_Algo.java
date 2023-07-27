public class Sort_Algo {
	
	int block;
	int[] color; 
	Node[] node;
	int k,m; // k = j & m = i
	int key;
	int currentIndex, previousIndex, greenNode;
	int limit;
	int cnt = 0;	int cnt2 = 0;
	int minNode; 
	
	Sort_Algo(int block,int[] color,Node[] node,int currentIndex,int previousIndex,int k,int m){
		this.block = block;
		this.node = node;
		this.color = color;
		this.currentIndex = currentIndex = 0 ;
		this.previousIndex = previousIndex = 0 ;
		this.k = k = 0;
		this.m = m = 0;
	}
	
	public void swap(int a,int b) {
		int temp = a;
		a = b;
		b = temp;
	}
	
	public void swap(Node[] n,int tempK) {
		int temp = n[tempK].y;
		n[tempK].y = n[tempK+1].y;
		n[tempK+1].y = temp;
	}
	
	public int bubbleSort() {
		if(node[k].y < node[k+1].y) {
			swap(node,k);
			currentIndex = k;
			previousIndex = k+1;
//			color[currentIndex] = 1;
//			color[previousIndex] = 1;
		}	
		
		if(m >= block-1) {
			k = m = 0;
			return 1;
		}
		
		k++;
		if(k >= block-1-m) {
//			color[currentIndex] = 0;
//			color[previousIndex] = 0;
			k = 0;
			m++;
		}
		return 0;
	}
	
	public int insertionSort() {
		
		if(m == block) {
			return 1;
		}
		color[m] = 2;
		greenNode = m;		
		if(cnt == 0) {
			key = node[m].y;
			color[m] = 2;
			greenNode = m;
			k = m-1;
			currentIndex = previousIndex = k;
			color[k] = 1;
			cnt = 1;
//			System.out.println("first Condition"+ m+" "+k);
		}
		
		if(k>=0 && cnt == 1 && node[k].y < key) {
			node[k+1].y = node[k].y;
			color[k+1] = 0;
			
			if(k != m-2) {
				color[k] = 1;	
				currentIndex = k;
			}
			
			k--;
//			System.out.println("Second Condition"+ m+" "+k);
			return 0;
		}
		
		node[k+1].y = key;
		if(k <= 0 || node[k].y > key) {
			m++;
			cnt = 0;
		}
		
		color[greenNode] = 0;
		color[currentIndex] = 0;
		return 0;
		
//		for(int i=0;i<block;i++) {
//			key = node[i].y;
//			int j = i-1;
//			while(j>=0 && node[j].y < key) {
//				node[j+1].y = node[j].y;
//				j--;
//			}
//			node[j+1].y = key;
//			k = i;
//			System.out.println("Block: "+block+" , "+"Value: "+ i);
//		}
//		
//		if(k == block-1) {
//			return 1;
//		}
		
	}
	
	public int selectionSort() {
//		System.out.println("Block : "+ block+" , "+"K : "+k+", M : "+m+" , minNode : "+ minNode);
		if(m == block) {
			return 1;
		}
		
		if(cnt == 0 && m < block) {
			minNode = previousIndex = greenNode =  m;
			try {
				color[greenNode-1] = 2;	
			}
			catch(Exception c) {}
			
			cnt = 1;
			k = m+1;
			return 0;
		}	
		
		if(k >= block) {
			try { color[previousIndex-1] = 0;	}catch(Exception c) {}
			cnt = 2;
			k = 0;
		}
		
		if(cnt == 1 && k < block) {
			if(node[k].y > node[minNode].y) {
				minNode = currentIndex =  k;
			}
			color[k] = 0;	 
			k++;
			color[k] = 1;
			return 0;
		
		}
		
		if(cnt == 2) {
			int temp4 = node[minNode].y;
			node[minNode].y = node[m].y;
			node[m].y = temp4;
			m++;
			cnt = 0;
		}
		return 0;
	}
	
	public void mergeSort() {
		
	}
	
	public void resetValue() {
		k = m = limit;
		cnt = cnt2 = minNode = key = 0;
		greenNode = currentIndex = previousIndex = 0;
	}
	
	public void setBubbleSort() {
		k = m = 0;
		cnt = cnt2 = 0;
	}
	public void setInsertionSort() {
		k = 0;
		m = 1;
	}
	public void setSelectionSort() {
		k = 0;
		m = 0;
	}
	public int returnCurrentIndex() {
		return currentIndex;
	}
	
	public int returnPreviousIndex() {
		return previousIndex;
	}
	
}
