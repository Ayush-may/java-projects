import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class GamePanel extends JPanel implements ActionListener , ChangeListener{
	
	static final int WIDTH =650;
	static final int HEIGHT = 400;
	int delay = 50;
	int unit = 3;
	int block = WIDTH/unit;
	int k,m;
	int tempK,tempK2;
	int tempT ;
	int t = 0;
	public int currentIndex, previousIndex;
	public int check1 ;
	
	
	Boolean bSort,iSort,sSort,mSort,qSort,slowSortBool, radixSortBool;
	Boolean running = false;
	
	Timer timer, stopTimer, rePaintTimer;
	
	String menuItem[] = {"Bubble Sort",
						"Insertion Sort", 
						"Selection Sort", 
						"Merge Sort", 
						"Quick Sort",
						"Slow Sort",
						"Radix Sort"
						};
	
	Random random = new Random();
	Sort_Algo algo ;
	
	int[] check = new int[HEIGHT+1];
	int[] color = new int[HEIGHT+1];
	Node[] node = new Node[HEIGHT+1];
	
	JLabel delayLabel = new JLabel("Delay: "+ delay);
	JLabel sizeLabel = new JLabel("Size: " + block);
	JButton startBtn = new JButton("START");
	JButton stopBtn = new JButton("STOP");
	JButton shuffleBtn = new JButton("Shuffle");
	JButton aboutBtn = new JButton("Credit");
	JComboBox menu = new JComboBox(menuItem);
	JSlider delaySlider = new JSlider(0,50,delay);
	JSlider sizeSlider = new JSlider(3,20,unit);
	
	BubbleSort bs;
	InsertionSort is;
	SelectionSort ss;
	MergeSort ms;
	QuickSort qs;
	SlowSort slowSortObj;
	RadixSort radixSortObj;
	
	GamePanel(){
		this.setLayout(null);
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.setBackground(Color.BLACK);
		
		tempT = k = m = 0;
		
		bSort = true;
		iSort = sSort = mSort = qSort = slowSortBool = false;
		algo = new Sort_Algo(block,color,node, currentIndex, previousIndex,k,m);
		
		this.add(startBtn);
		this.add(stopBtn);
		this.add(menu);
		this.add(shuffleBtn);
		this.add(delayLabel);
		this.add(delaySlider);
		this.add(sizeLabel);
		this.add(sizeSlider);
		this.add(aboutBtn);
		
		startBtn.setBounds(0,0,100,30);
		stopBtn.setBounds(110,0,100,30);
		menu.setBounds(220,0,100,30);
		shuffleBtn.setBounds(330,0,100,30);
		delaySlider.setBackground(Color.BLACK);
		delaySlider.setBounds(440,10,100,30);
		sizeSlider.setBackground(Color.BLACK);
		sizeSlider.setBounds(550,10,100,30);
		aboutBtn.setBounds(0,35,100,30);
		
		delayLabel.setForeground(Color.WHITE);
		delayLabel.setBounds(465,-5,100,30);
		sizeLabel.setForeground(Color.WHITE);
		sizeLabel.setBounds(585,-5,100,30);
				
		startBtn.addActionListener(this);
		stopBtn.addActionListener(this);
		shuffleBtn.addActionListener(this);
		menu.addActionListener(this);
		delaySlider.addChangeListener(this);
		sizeSlider.addChangeListener(this);
		aboutBtn.addActionListener(this);
		
		bSort = true ;
		iSort = sSort = mSort = qSort = false;

		rePaintTimer = new Timer(0, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				repaint();
				checkCollision();	
				if(running) {
					if(bSort) {
						bs.setDelay(delay);
						check1 = bs.returnCheck();
					}
					else if(iSort) {
						is.setDelay(delay);
						check1 = is.returnCheck();
					}
					else if(sSort) {
						ss.setDelay(delay);
						check1 = ss.returnCheck();
					}
					else if(mSort) {
						ms.setDelay(delay);
						check1 = ms.returnCheck();						
					}
					else if(qSort) {
						qs.setDelay(delay);
						check1 = qs.returnCheck();							
					}
					else if(slowSortBool) {
						slowSortObj.setDelay(delay);
						check1 = slowSortObj.returnCheck();							
					}
					else if(radixSortBool) {
						radixSortObj.setDelay(delay);
						check1 = radixSortObj.returnCheck();							
					}
				}
				
			}
		});

		shuffle();
		rePaintTimer.start();
		}
	
	public void shuffle() {
		if(running) {
			interruptThreads();	
		}
		int xCod = 0;
		tempT = 0;
		node = new Node[HEIGHT+1];
		color = new int[HEIGHT+1];
		algo = new Sort_Algo(block,color,node, currentIndex, previousIndex,k,m);
		init();
		
		for(int i=0;i<=block;i++) {
			while(true) {
				int temp = random.nextInt(HEIGHT);
				if(check[temp] == -1 && temp > 60 ){
					check[temp] = 0;
					node[i] = new Node(xCod,temp);
					xCod += unit;
					break;
				}
			}
		}
	}
	
	public void interruptThreads() {
		if(running) {
			if(bSort) {
				bs.setBreak(true);
				bs.interrupt();
				System.out.println("BS interrupt");
			}
			else if(iSort) {
				is.setBreak(true);
				is.interrupt();
				System.out.println("IS interrupt");
			}
			else if(sSort) {
				ss.setBreak(true);
				ss.interrupt();
				System.out.println("SS interrupt");
			}
			else if(mSort) {
				ms.setBreak(true);
				ms.interrupt();
			}
			else if(qSort) {
				qs.setBreak(true);
				qs.interrupt();
			}
			else if(slowSortBool) {
				slowSortObj.setBreak(true);
				slowSortObj.interrupt();
			}
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		for(int i=0;i<block;i++) {
			switch(color[i]) {
				case 0:
					g.setColor(Color.WHITE);
					break;
				case 1:	
					g.setColor(Color.RED);
					break;
				case 2:
					g.setColor(Color.GREEN);
					break;
				case 3:
					g.setColor(Color.BLUE);
					break;					
		  }
			g.fillRect(node[i].getX()+1,node[i].getY()-1,unit-1,HEIGHT);
		}	
	}
	
	public void start() {
		if(running) {
			if(bSort) {
//				t = algo.bubbleSort();
				bs = new BubbleSort(node,color,block,check1,delay);
				bs.start();
				check1 = bs.returnCheck();
				System.out.println(check1 + " sdsdsd");
			}
			else if(iSort) {
				is = new InsertionSort(node,color,block,check1,delay);
				is.start();
			}
			else if(sSort) {
				ss = new SelectionSort(node,color,block,check1,delay);
				ss.start();
			}
			else if(mSort) {
				ms = new MergeSort(node,color,block,check1,delay);
				ms.start();
			}
			else if(qSort) {
				qs = new QuickSort(node,color,block,check1,delay);
				qs.start();				
			}
			else if(slowSortBool) {
				slowSortObj = new SlowSort(node,color,block,check1,delay);
				slowSortObj .start();				
			}
			else if(radixSortBool) {
				radixSortObj = new RadixSort(node,color,block,check1,delay);
				radixSortObj.start();				
			}
		}
	}
	
	public void checkCollision() {
		if(check1 == 1) {
			running = false;
			System.out.println(check1);
			System.out.println("Timer Stopped");
			JOptionPane.showMessageDialog(new JFrame(),"Sorted <3");
			rePaintTimer.stop();
		}		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == startBtn) {
			running = true;
			start();
		}
		else if(e.getSource() == stopBtn) {
			interruptThreads();
			running = false;
		}
		else if(e.getSource() == shuffleBtn) {
			shuffle();
		}
		else if(e.getSource() == aboutBtn) {
			String info = "      Sorting Visualizer\nCopyright(c) 2023-2024\n\nCoded by Ayush Sharma\n    Second Year Student\n\n First Date :17-06-2023\nLast Date: Still Working! ";
			JOptionPane.showMessageDialog(this,info);
		}
		else if(e.getSource()==menu){
			if("Bubble Sort"==menu.getSelectedItem()){
				bSort = true ;
				iSort = sSort = mSort = qSort = slowSortBool= radixSortBool = false;
			}
			else if("Insertion Sort"==menu.getSelectedItem()){
				iSort = true;
				bSort = sSort = mSort = qSort = slowSortBool= radixSortBool  = false;
			}
			else if("Selection Sort"==menu.getSelectedItem()){
				sSort = true;
				bSort = iSort = mSort = qSort = slowSortBool= radixSortBool  =false;
			}
			else if("Merge Sort"==menu.getSelectedItem()){
				mSort = true;
				bSort = iSort = sSort = qSort = slowSortBool= radixSortBool  =false;
			}
			else if("Quick Sort"==menu.getSelectedItem()){
				qSort = true;
				bSort = iSort = sSort = mSort = slowSortBool= radixSortBool  =false;
			}
			else if("Slow Sort"==menu.getSelectedItem()){
				slowSortBool = true;
				bSort = iSort = sSort = mSort = qSort= radixSortBool  =false;
			}
			else if("Radix Sort"==menu.getSelectedItem()){
				radixSortBool = true;
				bSort = iSort = sSort = mSort = qSort= slowSortBool =false;
			}
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		try {
			if(e.getSource() == delaySlider) {
				delay = delaySlider.getValue();
				delayLabel.setText("Delay: "+ delay );
			}
			if(e.getSource() == sizeSlider) {
				unit = sizeSlider.getValue();
				block = WIDTH/unit;
				sizeLabel.setText("Size: "+ block);
				shuffle();
			}
		}catch(Exception e1) {}
	}
	public void init() {
		check = new int[HEIGHT+1];
		for(int i=0;i<HEIGHT;i++) {
			check[i] = -1;
		}
	}	
}