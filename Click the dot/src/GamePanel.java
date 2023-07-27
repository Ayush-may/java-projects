import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.awt.*;
import javax.swing.Timer;
import java.awt.event.*;

class GamePanel extends JPanel implements ActionListener ,ChangeListener {
		
	GamePanel(){
		this.setLayout(null);

		timer = new Timer(delay , new ActionListener(){
			public void actionPerformed(ActionEvent e){
				int	check = bfsClass.bfs(boxType);
				if(check==1){
					timer.stop();
					while(!shortestNode.isEmpty()){
						Box forIndex = shortestNode.remove();
						grid[forIndex.j][forIndex.i] = 4;
					}	
		
				}
				repaint();
			}
		});						

		framePlace();
		buttonInit();
		start();
	}
	
	public void buttonInit(){
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(1);
		slider.setPaintLabels(true);
		this.add(start);
		this.add(stopMap);
		this.add(reset);
		this.add(clear);
		this.add(algo);
		this.add(cb);
		this.add(slider);
		this.add(gridSize);
		this.add(delaySize);
		this.add(delayDenote);
		this.add(delaySlider);
		this.add(blockCombo);
		
		start.setBounds(615,10,100,30);
		stopMap.setBounds(615,50,100,30);
		reset.setBounds(615,90,100,30);
		clear.setBounds(615,130,100,30);
		algo.setBounds(635,170,100,30);
		cb.setBounds(615,195,100,30);
		gridSize.setBounds(635,240,130,30);
		slider.setBounds(602,260,130,50);
		delaySize.setBounds(620,310,130,30);
		delayDenote.setBounds(660,316,50,20);
		delaySlider.setBounds(602,340,130,30);
		blockCombo.setBounds(615,380,100,30);
				
		start.addActionListener(this);
		stopMap.addActionListener(this);
		reset.addActionListener(this);
		clear.addActionListener(this);
		cb.addActionListener(this);
		slider.addChangeListener(this);
		delaySlider.addChangeListener(this);
		blockCombo.addActionListener(this);
		panel.addMouseMotionListener(new MouseMotionListener(){
			public void mouseDragged(MouseEvent e){
				if(mouseDraggedBool){
					mouseX = e.getX();
					mouseY = e.getY();
					grid[c.returnXCoordinate(mouseX,unit,gameUnit)][c.returnYCoordinate(mouseY,unit,gameUnit)] = wallAndEraser;
					wall[c.returnXCoordinate(mouseX,unit,gameUnit)][c.returnYCoordinate(mouseY,unit,gameUnit)] = 1;
					repaint();
				}
			}
			public void mouseMoved(MouseEvent e){}
		});
		panel.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				mouseX = e.getX();
				mouseY = e.getY();
				
				if(startBool){
					startCount = 1;
					if(cntStart == 0){
						startX = c.returnXCoordinate(mouseX,unit,gameUnit);
						startY = c.returnYCoordinate(mouseY,unit,gameUnit);
						
						grid[startX][startY] = wallAndEraser;
						storePreStartingPoint(startX,startY);
						cntStart++;
					}
					else{
						grid[preX][preY] = 0;	
						startX = c.returnXCoordinate(mouseX,unit,gameUnit);
						startY = c.returnYCoordinate(mouseY,unit,gameUnit);
						storeCurrentEndingNodeIndex(endX,endY);
						storePreStartingPoint(startX,startY);
						grid[startX][startY] = wallAndEraser;
					}
				}
				if(endBool){
					endCount = 1;
					if(cntEnd == 0){
						endX = c.returnXCoordinate(mouseX,unit,gameUnit);
						endY = c.returnYCoordinate(mouseY,unit,gameUnit); 
						storeCurrentEndingNodeIndex(endX,endY);
						storePreEndingPoint(endX,endY);
						grid[endX][endY] = wallAndEraser;									
						cntEnd++;
					}
					else{
						grid[preXEnd][preYEnd] = 0;		
						endX = c.returnXCoordinate(mouseX,unit,gameUnit);
						endY = c.returnYCoordinate(mouseY,unit,gameUnit);
						grid[endX][endY] = wallAndEraser;
						storePreEndingPoint(endX,endY);
					}
				}
				repaint();
			}
		});

		delayDenote.setEditable(false);
	}
	
	public void framePlace(){
		
		panel.setBackground(Color.WHITE);
		this.add(panel);
		panel.setBounds(0,0,600,600);
		yellow.setBackground(Color.YELLOW);
		this.add(yellow);
		yellow.setBounds(10,610,30,30);
	}
	
	public void start(){
	wallAndEraser = 8;
		for(int i=0 ;i<gameUnit-1 ;i++){
			for(int j=0 ;j<gameUnit-1 ;j++){
				grid[j][i] = 0;
			}
		}
	}
	
	public void timerChanged(){
		if(running){
			timer.setDelay(delay);
			timer.restart();
		}
	}
	
	public void paint(Graphics g){
		super.paint(g);
		draw(g);
	}
	
	public void draw(Graphics g){
		for(int i=0 ;i<gameUnit-1 ;i++){
			for(int j=0 ;j<gameUnit-1 ;j++){
				g.setColor(Color.BLACK);
				g.drawRect(j*unit, i*unit, unit-1, unit-1 );
				
				if(running){
					grid[startX][startY] = 8;
					grid[endX][endY] = 9;
				}
				
				switch(grid[j][i]){
					case 0:
						g.setColor(Color.WHITE);
						break;
					case 1:
						g.setColor(Color.BLACK);
						break;
					case 2:
						g.setColor(Color.GREEN);
						break;
					case 3:
						g.setColor(Color.RED);
						break;
					case 4:
						g.setColor(Color.YELLOW);
						break;
					case 8:
						g.setColor(Color.BLUE);
						break;
					case 9:
						g.setColor(Color.CYAN);
						break;
				}
				g.fillRect(j*unit, i*unit, unit-1, unit-1 );
			}
		}
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==start){
			if(startButtonBool){
				boxType = new Box(startX,startY,0);
				c.setStartingIndex(startX,startY);
				c.setEndingIndex(endX,endY);
				running = true;
				if(startCount == 1 && endCount==1){
					running = true;
					timer.start();
				}
			}	
		}
		if(e.getSource()==stopMap){
			timer.stop();
		}
		if(e.getSource()==reset){
// 			startButtonBool = false;
			setStartButtonFalse();
			stopTimerAndClear();
			repaint();
		}
		if(e.getSource()==clear){
// 			startButtonBool = false;
			setStartButtonFalse();
			stopTimerAndClear();		
			grid[startX][startY] = 8;
			grid[endX][endY] = 9;
			repaint();
		}
		if(e.getSource()==cb){
			if("BFS"==cb.getSelectedItem()){
				bfs = true;
				dfs = false;
			}
			if("DFS"==cb.getSelectedItem()){
				dfs = true;
				bfs = false;
			}
		}
		if(e.getSource()==blockCombo){
			if("Start"==blockCombo.getSelectedItem()){
				wallAndEraser = 8;
				startBool = true;
				endBool = mouseDraggedBool = false;
			}
			if("End"==blockCombo.getSelectedItem()){
				wallAndEraser = 9;
				endBool = true;
				startBool = mouseDraggedBool = false;
			}
			if("Wall"==blockCombo.getSelectedItem()){
				wallAndEraser = 1;			
				mouseDraggedBool = true;
				startBool = endBool = false;
			}
			if("Eraser"==blockCombo.getSelectedItem()){
				wallAndEraser = 0;
				mouseDraggedBool = true;
			}
		}

	}
	
	public void stateChanged(ChangeEvent e){
		if(e.getSource()==slider){
			if(running){
				stopTimerAndClear();					
				running = false;
			}
			else{
				unit = c.unitSize( ((JSlider)e.getSource()).getValue() );
				gameUnit = size/unit + 1;
				bfsClass.gameUnit = gameUnit;
				repaint();	
			}
		}
	
		if(e.getSource()==delaySlider){
			int show = delaySlider.getValue();
			delayDenote.setText( show + "ms");
			delay = show;
			if( bfs || dfs )
				timerChanged();
		}
	}
	

	public void stopTimerAndClear(){
		timer.stop();
		c.clearMap(grid,gameUnit);
	}
	
	public void storePreStartingPoint(int x,int y){
		preX = x;
		preY = y;
	}
	
	public void storePreEndingPoint(int x,int y){
		preXEnd = x;
		preYEnd = y;
	}
	
	public void storeCurrentEndingNodeIndex(int x,int y){
		bfsClass.endingNodeX = x;
		bfsClass.endingNodeY = y;
		}
	
	public void setStartButtonFalse(){
		startButtonBool = false;
	}
	
//-----------------------VARIABLES-----------------------------------
	int size = 600;
	int unit = 20;
	int delay = 10;
	int mouseX , mouseY;
	int wallAndEraser;
	int startX , startY; int preX ,preY;
	int endX, endY;	     int preXEnd , preYEnd;
	int cntStart , cntEnd;
	int nodeValue;
	int biggestGameUnit = size/10;
	int gameUnit = size/unit + 1;
	int startCount,endCount;


	boolean bfs = true;
	boolean dfs = false;
	boolean startBool = true;
	boolean endBool = false;
	boolean startButtonBool = true;
	boolean endButtonBool = false;
	boolean mouseDraggedBool = false;
	boolean running = false;
	
	String idea[] = {"BFS","DFS"};
	String blockString[] = {"Start","End","Wall","Eraser"};
	int grid[][] = new int[biggestGameUnit][biggestGameUnit];
	int wall[][] = new int[biggestGameUnit][biggestGameUnit];
	
	Timer timer ;
	Box boxType ;
	ClearClass c = new ClearClass();
	Queue<Box> shortestNode = new LinkedList<>();
	BFS bfsClass = new BFS(gameUnit, biggestGameUnit, grid ,shortestNode,wall);

	JPanel panel = new JPanel();
	JPanel yellow = new JPanel();
	JPanel red = new JPanel();
	JPanel blue = new JPanel();
	JPanel CYAN = new JPanel();
	
	JButton start =  new JButton("Start");
	JButton stopMap =  new JButton("Stop");
	JButton reset =  new JButton("Reset");	
	JButton clear =  new JButton("Clear");	
	
	JTextField delayDenote = new JTextField( delay + "ms");
	
	JLabel yellowName = new JLabel("Shotest Path");
	JLabel redName = new JLabel("Visiting Nodes");
	JLabel blueName = new JLabel("Starting Node");
	JLabel cyanName = new JLabel("Destination Node");
	JLabel gridSize = new JLabel("Grid Size");
	JLabel delaySize = new JLabel("Delay");	
	JLabel algo = new JLabel("Algorithm");	
	
	JComboBox<String> cb = new JComboBox<String>(idea);
	JComboBox<String> blockCombo = new JComboBox<String>(blockString);
	JSlider slider = new JSlider(1,5,3);
	JSlider delaySlider = new JSlider(0,100,delay);

}
