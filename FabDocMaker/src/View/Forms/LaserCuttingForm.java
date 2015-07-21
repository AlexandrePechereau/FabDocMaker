package View.Forms;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;
import javax.swing.text.Keymap;

public class LaserCuttingForm  extends AbstractForm{


	private ArrayList<JTextField> textfieldlist;

	private JFrame frame2;
	private JFrame frame3;
	
	private JButton Save_Button, Next_Button, Next_Button_1, Previous_Button_1, Next_Button_2, Previous_Button_2;
	boolean ENOUGH_SCREEN_HEIGHT;
	
	public JLabel createLabel(String text_title){
		JLabel label = new JLabel(text_title, JLabel.LEFT);
		label.setBorder(BorderFactory.createEmptyBorder(5, 10, 0, 0));
		return label;
	}
	
	public JTextField createTextField(){
		JTextField textfield = new JTextField();
		Border border = BorderFactory.createLineBorder(this.frame.getBackground(), 5);
		textfield.setBorder(border);
		return textfield;
	}
	
	public JPanel createTextedGridedPanel(String title, int rows, int cols){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(rows,cols));
		panel.setBorder(BorderFactory.createTitledBorder(title));
		return panel;
	}
	
	public LaserCuttingForm() {
		
		this.frame = new JFrame();
		this.frame.setTitle("FabLab Doc Maker - Laser Cutting");
		this.addMenuBar();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		int HEIGHT_FRAME = 907;
		ENOUGH_SCREEN_HEIGHT = true;
		if(height<HEIGHT_FRAME) ENOUGH_SCREEN_HEIGHT = false;
		
		
		int NB_TEXTFIELD = 24;
		this.frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		textfieldlist = new ArrayList<JTextField>();
		
		//Disabling copy cut paste for JTextField
		JTextComponent.KeyBinding[] newBindings = {
	        new JTextComponent.KeyBinding(
	          KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK),
	          DefaultEditorKit.beepAction),
	        new JTextComponent.KeyBinding(
	          KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK),
	          DefaultEditorKit.beepAction),
	        new JTextComponent.KeyBinding(
	            KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK),
	            DefaultEditorKit.beepAction)
		};
		
		for(int i = 0 ; i < NB_TEXTFIELD ; i++){
			JTextField textfield = createTextField();
			textfield.setHorizontalAlignment(JTextField.CENTER);
			         
		    Keymap k = textfield.getKeymap();
		    JTextComponent.loadKeymap(k, newBindings, textfield.getActions());
			textfieldlist.add(textfield);
		}
		
		Save_Button = new JButton("Save without screenshots or pictures");
		Next_Button = new JButton("Add screenshots or pictures");
		
		if(ENOUGH_SCREEN_HEIGHT){
			JPanel title_panel = createTextedGridedPanel("Title", 1, 1);
			title_panel.add(textfieldlist.get(0));
			c.fill = GridBagConstraints.HORIZONTAL;	
			c.gridwidth = 1;
			c.gridheight = 1;
			c.gridx = 0;
			c.gridy = 0;
			this.addComponent(this.frame, title_panel, c);
			
			JPanel machine_panel = createTextedGridedPanel("Machine", 2, 2);
			machine_panel.add(createLabel("Machine Brand :"));
			machine_panel.add(textfieldlist.get(1));
			machine_panel.add(createLabel("Machine version :"));
			machine_panel.add(textfieldlist.get(2));
			c.gridx = 0;
			c.gridy = 1;
			this.addComponent(this.frame, machine_panel,c);
			
			JPanel material_panel = createTextedGridedPanel("Material", 4, 2);
			material_panel.add(createLabel("Material you used :               "));
			material_panel.add(textfieldlist.get(3));
			
			material_panel.add(createLabel("Material Brand :"));
			material_panel.add(textfieldlist.get(4));
			
			material_panel.add(createLabel("Material thickness :"));
			material_panel.add(textfieldlist.get(5));
			
			material_panel.add(createLabel("Material dimensions :"));
			material_panel.add(textfieldlist.get(6));
			c.gridx = 0;
			c.gridy = 2;
			this.addComponent(this.frame, material_panel, c);
			
			JPanel vector_panel = createTextedGridedPanel("Vector", 4, 2);	
			vector_panel.add(createLabel("Vector color :"));
			vector_panel.add(textfieldlist.get(7));
			
			vector_panel.add(createLabel("Vector Speed :"));
			vector_panel.add(textfieldlist.get(8));
			
			vector_panel.add(createLabel("Vector Power :"));
			vector_panel.add(textfieldlist.get(9));
			
			vector_panel.add(createLabel("Vector frequency :"));
			vector_panel.add(textfieldlist.get(10));
			c.gridx = 0;
			c.gridy = 3;
			this.addComponent(this.frame, vector_panel, c);
			
			JPanel raster_panel = createTextedGridedPanel("Raster", 3, 2);	
			raster_panel.add(createLabel("Raster Color :"));
			raster_panel.add(textfieldlist.get(11));
			
			raster_panel.add(createLabel("Raster Speed :"));
			raster_panel.add(textfieldlist.get(12));
			
			raster_panel.add(createLabel("Raster Power :"));
			raster_panel.add(textfieldlist.get(13));
			c.gridx = 0;
			c.gridy = 4;
			this.addComponent(this.frame, raster_panel, c);
			
			JPanel laser_panel = createTextedGridedPanel("Laser", 3, 2);
			laser_panel.add(createLabel("Cut width of the laser :"));
			laser_panel.add(textfieldlist.get(14));
			
			laser_panel.add(createLabel("Laser Focusing :"));
			laser_panel.add(textfieldlist.get(15));
			
			laser_panel.add(createLabel("Job Resolution :"));
			laser_panel.add(textfieldlist.get(16));	
			c.gridy = 5;
			this.addComponent(this.frame, laser_panel, c);
			
			JPanel file_panel = createTextedGridedPanel("File", 3, 2);
			file_panel.add(createLabel("Files were designed by You :"));
			file_panel.add(textfieldlist.get(17));
			
			file_panel.add(createLabel("Files were downloaded from :"));
			file_panel.add(textfieldlist.get(18));
			
			file_panel.add(createLabel("Files were downloaded and modified by you :"));
			file_panel.add(textfieldlist.get(19));
			c.gridy = 6;
			this.addComponent(this.frame, file_panel, c);
			
			JPanel software_panel = createTextedGridedPanel("Software", 2, 2);
			software_panel.add(createLabel("Software used to draw this 2D object :"));
			software_panel.add(textfieldlist.get(20));
			
			software_panel.add(createLabel("Software used to send your job to the laser-cutter :"));
			software_panel.add(textfieldlist.get(21));
			c.gridy = 7;
			this.addComponent(this.frame, software_panel, c);
			
			JPanel info_panel = createTextedGridedPanel("Information about yourself", 2, 2);
			info_panel.add(createLabel("Your Name :"));
			info_panel.add(textfieldlist.get(22));
			
			info_panel.add(createLabel("Are you a member of :"));
			info_panel.add(textfieldlist.get(23));
			c.gridy = 8;
			this.addComponent(this.frame, info_panel, c);
			
			JPanel button_panel = new JPanel();
			button_panel.setLayout(new GridLayout(1,2));
				
			Save_Button.setActionCommand("Save");
			
			Next_Button.setActionCommand("Next");
			
			button_panel.add(Save_Button);
			button_panel.add(Next_Button);
			c.insets = new Insets(10,0,0,0);
			c.gridy = 9;
			this.addComponent(this.frame, button_panel, c);
	
			this.frame.pack();
			this.frame.setLocation((((int)width/2)-(this.frame.getWidth()/2)), (((int)height/2)-(this.frame.getHeight()/2)));
			this.frame.setResizable(false);
			this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.frame.setVisible(true);
		}
		else{
			JPanel title_panel = createTextedGridedPanel("Title", 1, 1);
			title_panel.add(textfieldlist.get(0));
			c.fill = GridBagConstraints.HORIZONTAL;	
			c.gridwidth = 1;
			c.gridheight = 1;
			c.gridx = 0;
			c.gridy = 0;
			this.addComponent(this.frame, title_panel, c);
			
			JPanel machine_panel = createTextedGridedPanel("Machine", 2, 2);
			machine_panel.add(createLabel("Machine Brand :                   "));
			machine_panel.add(textfieldlist.get(1));
			machine_panel.add(createLabel("Machine version :"));
			machine_panel.add(textfieldlist.get(2));
			c.gridx = 0;
			c.gridy = 1;
			this.addComponent(this.frame, machine_panel,c);
			
			JPanel material_panel = createTextedGridedPanel("Material", 4, 2);
			material_panel.add(createLabel("Material you used :                                      "));
			material_panel.add(textfieldlist.get(3));
			
			material_panel.add(createLabel("Material Brand :"));
			material_panel.add(textfieldlist.get(4));
			
			material_panel.add(createLabel("Material thickness :"));
			material_panel.add(textfieldlist.get(5));
			
			material_panel.add(createLabel("Material dimensions :"));
			material_panel.add(textfieldlist.get(6));
			c.gridx = 0;
			c.gridy = 2;
			this.addComponent(this.frame, material_panel, c);
			
			JPanel vector_panel = createTextedGridedPanel("Vector", 4, 2);	
			vector_panel.add(createLabel("Vector color :"));
			vector_panel.add(textfieldlist.get(7));
			
			vector_panel.add(createLabel("Vector Speed :"));
			vector_panel.add(textfieldlist.get(8));
			
			vector_panel.add(createLabel("Vector Power :"));
			vector_panel.add(textfieldlist.get(9));
			
			vector_panel.add(createLabel("Vector frequency :"));
			vector_panel.add(textfieldlist.get(10));
			c.gridx = 0;
			c.gridy = 3;
			this.addComponent(this.frame, vector_panel, c);
			
			Next_Button_1 = new JButton("Next");
			
			c.insets = new Insets(10,0,0,0);
			c.gridy = 4;
			this.addComponent(this.frame, Next_Button_1, c);
			
			this.frame.pack();
			this.frame.setLocation((((int)width/2)-(this.frame.getWidth()/2)), (((int)height/2)-(this.frame.getHeight()/2)));
			this.frame.setResizable(false);
			this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.frame.setVisible(true);
			
			/////////FRAME 2////////////
			
			frame2 = new JFrame();
			frame2.setTitle("FabLab Doc Maker - Laser Cutting");
			frame2.setLayout(new GridBagLayout());
			
			Previous_Button_1 = new JButton("Previous");
			
			c.insets = new Insets(10,0,0,0);
			c.gridy = 0;
			this.addComponent(frame2, Previous_Button_1, c);
			
			JPanel raster_panel = createTextedGridedPanel("Raster", 3, 2);	
			raster_panel.add(createLabel("Raster Color :                       "));
			raster_panel.add(textfieldlist.get(11));
			
			raster_panel.add(createLabel("Raster Speed :"));
			raster_panel.add(textfieldlist.get(12));
			
			raster_panel.add(createLabel("Raster Power :"));
			raster_panel.add(textfieldlist.get(13));
			c.gridx = 0;
			c.gridy = 1;
			this.addComponent(frame2, raster_panel, c);
			
			JPanel laser_panel = createTextedGridedPanel("Laser", 3, 2);
			laser_panel.add(createLabel("Cut width of the laser :                             "));
			laser_panel.add(textfieldlist.get(14));
			
			laser_panel.add(createLabel("Laser Focusing :"));
			laser_panel.add(textfieldlist.get(15));
			
			laser_panel.add(createLabel("Job Resolution :"));
			laser_panel.add(textfieldlist.get(16));	
			c.gridy = 2;
			this.addComponent(frame2, laser_panel, c);
			
			Next_Button_2 = new JButton("Next");
			
			c.insets = new Insets(10,0,0,0);
			c.gridy = 3;
			this.addComponent(frame2, Next_Button_2, c);
			
			frame2.pack();
			frame2.setLocation((((int)width/2)-(this.frame.getWidth()/2)), (((int)height/2)-(this.frame.getHeight()/2)));
			frame2.setResizable(false);
			frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			///////FRAME 3//////////////
			
			frame3 = new JFrame();
			frame3.setTitle("FabLab Doc Maker - Laser Cutting");
			frame3.setLayout(new GridBagLayout());
			
			Previous_Button_2 = new JButton("Previous");
			
			c.insets = new Insets(10,0,0,0);
			c.gridy = 0;
			this.addComponent(frame3, Previous_Button_2, c);
			
			JPanel file_panel = createTextedGridedPanel("File", 3, 2);
			file_panel.add(createLabel("Files were designed by You :"));
			file_panel.add(textfieldlist.get(17));
			
			file_panel.add(createLabel("Files were downloaded from :"));
			file_panel.add(textfieldlist.get(18));
			
			file_panel.add(createLabel("Files were downloaded and modified by you :"));
			file_panel.add(textfieldlist.get(19));
			c.gridy = 1;
			this.addComponent(frame3, file_panel, c);
			
			JPanel software_panel = createTextedGridedPanel("Software", 2, 2);
			software_panel.add(createLabel("Software used to draw this 2D object :"));
			software_panel.add(textfieldlist.get(20));
			
			software_panel.add(createLabel("Software used to send your job to the laser-cutter :"));
			software_panel.add(textfieldlist.get(21));
			c.gridy = 2;
			this.addComponent(frame3, software_panel, c);
			
			JPanel info_panel = createTextedGridedPanel("Information about yourself", 2, 2);
			info_panel.add(createLabel("Your Name :"));
			info_panel.add(textfieldlist.get(22));
			
			info_panel.add(createLabel("Are you a member of :"));
			info_panel.add(textfieldlist.get(23));
			c.gridy = 3;
			this.addComponent(frame3, info_panel, c);
			
			JPanel button_panel = new JPanel();
			button_panel.setLayout(new GridLayout(1,2));
			
			Save_Button.setActionCommand("Save");
			
			Next_Button.setActionCommand("Next");
			
			button_panel.add(Save_Button);
			button_panel.add(Next_Button);
			c.insets = new Insets(10,0,0,0);
			c.gridy = 4;
			this.addComponent(frame3, button_panel, c);
			
			frame3.pack();
			frame3.setLocation((((int)width/2)-(this.frame.getWidth()/2)), (((int)height/2)-(this.frame.getHeight()/2)));
			frame3.setResizable(false);
			frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}

	public ArrayList<JTextField> getTextfieldlist(){
		return textfieldlist;
	}
	
	public JButton getSave_Button(){
		return Save_Button;
	}
	
	public JButton getNext_Button(){
		return Next_Button;
	}
	
	public JButton getNext_Button_1(){
		return Next_Button_1;
	}
	
	public JButton getPrevious_Button_1(){
		return Previous_Button_1;
	}
	
	public JButton getNext_Button_2(){
		return Next_Button_2;
	}
	
	public JButton getPrevious_Button_2(){
		return Previous_Button_2;
	}
	
	public JFrame getFrame(){
		return this.frame;
	}
	
	public JFrame getFrame2(){
		return frame2;
	}
	
	public JFrame getFrame3(){
		return frame3;
	}
	
	public boolean getENOUGH_SCREEN_HEIGHT(){
		return ENOUGH_SCREEN_HEIGHT;
	}

}
