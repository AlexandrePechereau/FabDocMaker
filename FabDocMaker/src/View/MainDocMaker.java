package View;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



import View.Forms._3DPrinterForm;

@SuppressWarnings({ "unused", "serial" })
public class MainDocMaker extends JFrame{
	
	JComboBox<?> choseform;
	JButton ok_button;
	private ArrayList<Component> componentlist = new ArrayList<Component>();
	private JFrame frame;
	
	public MainDocMaker(){

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();

		this.setLocation(((int)width/2)-125, ((int)height/2)-130);
		this.setSize(250, 130);
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;	
		c.gridwidth = 1;
		c.weighty = 1;
		c.gridx = 0;
		c.gridy = 0;
		String[] forms = { "                     3D Printer              ","                   Laser Cutting"};
		choseform = new JComboBox<Object>(forms);
		this.add(choseform, c);
		componentlist.add(choseform);
		c.gridx = 0;
		c.gridy = 1;
		ok_button = new JButton("OK");
		this.add(ok_button, c);
		componentlist.add(ok_button);
		this.setTitle("Chose a form");
		
		frame=this;
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public JFrame getFrame(){
		return frame;
	}
	
	public JComboBox<?> getComboBox(){
		return choseform;
	}
	
	public JButton getButton(){
		return ok_button;
	}
	
	public static void main(String[] args) {
		MainDocMaker main = new MainDocMaker();
	}
}
