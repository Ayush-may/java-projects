import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class BubbleSortVisualizer extends JFrame
{
   // =======- Values to change -===========
   private final int COLUMN_WITH;
   private final int DELAY;
   //=======================================

   private List<Panel> panelList = new ArrayList<Panel>();
   private Panel mainPanel;

   public static void main(String[] args) 
   {
      BubbleSortVisualizer bsv = parseArgs(args);
   
      bsv.BuildGui(); 
   
      bsv.UpdateVisual();
   
      bsv.BubbleSort();
   }

   public BubbleSortVisualizer() 
   {
      this.COLUMN_WITH = 5;
      this.DELAY = 0;
   }

   public BubbleSortVisualizer(int columnWidth, int delay)
   {
      this.COLUMN_WITH = columnWidth;
      this.DELAY = delay;
   }
  
   private static BubbleSortVisualizer parseArgs(String[] args)
   {
      if(args.length == 0)
      {
         return new BubbleSortVisualizer();
      }
      else if(args.length == 2)
      {
         try
         {
            return new BubbleSortVisualizer(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
         } 
         catch (Exception e) { }
      }
   
      throw new RuntimeException("Wrong parametes!");
   }

   public void BuildGui() 
   {
      this.setTitle("BubbleSort Visualizer");
      this.setResizable(false);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setLocationRelativeTo(null);     
   
      mainPanel = new Panel();
      mainPanel.setSize(300, 300);
      mainPanel.setBackground(Color.GRAY);
      mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
   
      GeneratePanelList();
      
      for (int i = 0; i < panelList.size(); i++) 
      {
         mainPanel.add(panelList.get(i));       
      }    
   
      this.add(mainPanel);
      this.setVisible(true);
   
      UpdateVisual();         
   }

   public void UpdateVisual()
   {
      this.pack();
      
      try { Thread.sleep(DELAY); } 
      catch (Exception ex) { }
   }

   public void BubbleSort()
   {
      for (int k = panelList.size() - 1; k > 0; k--)
      {
         for (int i = 0; i < panelList.size() - 1; i++)
         {
            if(panelList.get(i).getHeight() > panelList.get(i + 1).getHeight())
            {
               Panel panel = new Panel();
               panel.setSize(COLUMN_WITH, panelList.get(i + 1).getHeight());
               
               panelList.get(i + 1).setSize(COLUMN_WITH, panelList.get(i).getHeight());
               panelList.get(i).setSize(COLUMN_WITH, panel.getHeight());
            
               UpdateVisual();
            }
         }
      }
      UpdateVisual();
   
      JOptionPane.showMessageDialog(this, "Completed");
   }

   private void GeneratePanelList()
   {
      for (int i = 0; i < mainPanel.getWidth() / COLUMN_WITH; i++) 
      {
         Panel panel = new Panel();
         panel.setBackground(Color.LIGHT_GRAY);
         panel.setSize(COLUMN_WITH, new Random().nextInt(300));
         panel.setLayout(null);
         
         panelList.add(i, panel); 
      }
   }
}