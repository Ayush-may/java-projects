import java.util.*;

class storeIndex{
	int i,j;
	storeIndex(int i,int j){
		this.i = i; 
		this.j = j;
	}
}

public class NQueen {
		
	public int q;
	public int ar[][];
	
	NQueen(int q){
		this.q = q;
		this.ar = new int[q][q];
		
		printQueen();
	}
	
	public void print(int ar[][]) {
		for(int i=0;i<q;i++) {
			for(int j=0;j<q;j++) {
				System.out.print(ar[i][j] +" ");
			}
			System.out.println("");
		}
	}
	
	public boolean isSafe(int row,int col) {
		
//		System.out.println("row : " + row);
		if(row >= q) {
			return false;
		}
		
		for(int i=0;i<q;i++) {
			if(ar[i][col] == 1) {
				return false;	
			}
		}
		
		for(int i=0;i<q;i++) {
			if(ar[row][i] == 1 ) {
				return false;
			}
		}
		
		for(int i=row, j=col; i>=0 && j>=0;i-- ,j--) {
			if(ar[i][j] == 1) {
				return false;
			}
		}
		
		for(int i=row,j=col; i<q && j>=0;i++ ,j--) {
			if(ar[i][j] == 1) {
				return false;
			}
		}
		
		return true;
	}
	
	public void printQueen() {
		storeIndex stack[] = new storeIndex[q]; 
		int top = -1; 
		int row = 0;
		int col = 0;
		
		while(col < q) {
			if(isSafe(row,col)) {
				stack[++top] = new storeIndex(row,col);
				ar[row][col] = 1;
				row = 0; 
				col++;
			}
			else if(row >= q) {
				row = stack[top].i + 1;
				col = stack[top].j;
				ar[row-1][col] = 0;
				top--;
			}
			else {
				row++;
			}
		}
		
		print(ar);
	}
	
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		int q1;	
		
		System.out.print("Enter The Number of Queens : ");	
		q1 = s.nextInt();
		
		if(q1 < 4) {
			System.out.println("\nPls enter more than 3!");
		}
		else {
			new NQueen(q1);	
		}
		
		
	}

}
