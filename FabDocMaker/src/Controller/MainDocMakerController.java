package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Controller.Forms.LaserCuttingController;
import Controller.Forms._3DPrinterController;
import View.MainDocMaker;
import View.Forms._3DPrinterForm;

@SuppressWarnings("unused")
public class MainDocMakerController {
	
	private String selecteditem;
	MainDocMaker main_frame;
	
	public MainDocMakerController(){
		main_frame = new MainDocMaker();
		selecteditem = "3DPrinter";
		main_frame.getComboBox().addActionListener(operationChoseformListener);
		main_frame.getButton().addActionListener(operationOkListener);
	}
	
	private ActionListener operationChoseformListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox<?> cb = (JComboBox<?>)e.getSource();
	        String form = (String)cb.getSelectedItem();
	        selecteditem = form;
		}
	};
	
	private ActionListener operationOkListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			selecteditem = selecteditem.replaceAll("\\s+" ,"");
	        if(selecteditem.equals("3DPrinter")){
	        	main_frame.getFrame().setVisible(false);
				_3DPrinterController _3DController = new _3DPrinterController();
	        }
	        if(selecteditem.equals("LaserCutting")){
	        	main_frame.getFrame().setVisible(false);
				LaserCuttingController laserCuttingController = new LaserCuttingController();
	        }
		}
	};
	
	public static void main(String[] args) {
		MainDocMakerController main_controller = new MainDocMakerController();
	}
}
