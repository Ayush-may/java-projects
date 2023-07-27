import java.util.Arrays;

public class RadixSort extends Thread{

	int block;
	int[] color; 
	Node[] node;
	int check = 0;
	int delay;
	Boolean loopBreak = false;
	
	RadixSort(Node node[],int color[], int block,int check,int delay){
		this.node = node;
		this.color = color;
		this.block = block;
		this.check = check;
		this.delay = delay;
	}
	
	public void run() {
		radixSort(node,block);
	}
	
	public int getMax(Node arr[], int n){
        int mx = arr[0].y;
        for (int i = 1; i < n; i++) {
        	if(loopBreak) {break;}
        	if (arr[i].y > mx) {
            	mx = arr[i].y;
            	sleep();
            }
        }
        return mx;
    }
    // A function to do counting sort of arr[] according to
    // the digit represented by exp.
	public void countSort(Node arr[], int n, int exp){
        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);

        // Store count of occurrences in count[]
        for (i = 0; i < n; i++) {
        	if(loopBreak) {break;}
        	count[(arr[i].y / exp) % 10]++;
        }
            

        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++) {
        	count[i] += count[i - 1];
        }
            

        // Build the output array
        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i].y / exp) % 10] - 1] = arr[i].y;
            count[(arr[i].y / exp) % 10]--;
//            sleep();
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to current
        // digit
        for (i = 0; i < n; i++) {
        	arr[i].y = output[i];
        	sleep();
        }
            
    }

    // The main function to that sorts arr[] of
    // size n using Radix Sort
	public void radixSort(Node arr[], int n){
        int m = getMax(arr, n);

        for (int exp = 1; m / exp > 0; exp *= 10)
            countSort(arr, n, exp);
    }
	
	public void sleep() { try{ Thread.sleep(delay);}catch(Exception e) {} }
	public void setDelay( int delay ) { this.delay = delay; }
	public int returnCheck() { return check; }
	public void setBreak(boolean x) { loopBreak  = x; }
}
