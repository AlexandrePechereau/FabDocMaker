package Controller.Forms;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.Spring;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;

import Controller.ImageLoaderController;
import Model.CreatePDF_3DPrinter;
import Model.CreatePDF_3DPrinter;
//import View.Forms.ImageLoader;
import View.Forms._3DPrinterForm;

@SuppressWarnings("unused")
public class _3DPrinterController {
	
	private ArrayList<String> strlist;
	
	private CreatePDF_3DPrinter cpdf;
	private Color LIGHT_RED = new Color(250,102,102);
	
	public _3DPrinterForm _3DForm;
	private ArrayList<JTextField> textfieldlist;

	private JFrame frame;
	private JFrame frame2;
	private JFrame frame3;
	
	private JButton Save_Button, Next_Button, Next_Button_1, Previous_Button_1, Next_Button_2, Previous_Button_2;
	
	
	
	public _3DPrinterController(){
		_3DForm = new _3DPrinterForm();
		
		textfieldlist = _3DForm.getTextfieldlist();
		
		for(int i=0; i < textfieldlist.size(); i++){
			if(i == 0) textfieldlist.get(i).addKeyListener(titleListener);
			else textfieldlist.get(i).addKeyListener(textListener);
			
			textfieldlist.get(i).addMouseListener(textfieldListener);
			textfieldlist.get(i).addFocusListener(textfieldFocusListener);
		}
		if(!_3DForm.getENOUGH_SCREEN_HEIGHT()){
			
			Next_Button_1 = _3DForm.getNext_Button_1();
			Next_Button_1.addActionListener(operationPanel2Listener);
			Previous_Button_1 = _3DForm.getPrevious_Button_1();
			Previous_Button_1.addActionListener(operationPanel1Listener);
			Next_Button_2 = _3DForm.getNext_Button_2();
			Next_Button_2.addActionListener(operationPanel3Listener);
			Previous_Button_2 = _3DForm.getPrevious_Button_2();
			Previous_Button_2.addActionListener(operationPanel2Listener);
			frame2 = _3DForm.getFrame2();
			frame3 = _3DForm.getFrame3();
		}
		Save_Button = _3DForm.getSave_Button();
		Save_Button.addActionListener(operationSaveListener);
		Next_Button = _3DForm.getNext_Button();
		Next_Button.addActionListener(operationNextListener);
		frame = _3DForm.getFrame();

	}
	
	private void saveButton(File file, ArrayList<String> strlist, ArrayList<Image> imagelist) throws DocumentException, MalformedURLException, IOException{
		this.strlist = strlist;
		cpdf = new CreatePDF_3DPrinter(file, this.strlist, imagelist);
	}
	
	public float getWidthPoint(String str){
		Chunk ch = new Chunk();
		ch.append(str);
		return ch.getWidthPoint();
	}
	
	private ActionListener operationPanel1Listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(true);
			frame2.setVisible(false);
			frame3.setVisible(false);
		}
	};
	
	private ActionListener operationPanel2Listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String str = textfieldlist.get(0).getText();
			if(str.equals("")){
				textfieldlist.get(0).setBackground(LIGHT_RED);
			}
			else{
				frame.setVisible(false);
				frame2.setVisible(true);
				frame3.setVisible(false);
			}
		}
	};
	
	private ActionListener operationPanel3Listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
			frame2.setVisible(false);
			frame3.setVisible(true);
		}
	};
	
	private ActionListener operationNextListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			ImageLoaderController imload;
			boolean next = true;
			
			ArrayList<String> infos = new ArrayList<String>();
			for(int i = 0;i<textfieldlist.size();i++){
				String str = textfieldlist.get(i).getText();
				if(i == 0){
					if(str.equals("")){
						next=false;
						textfieldlist.get(i).setBackground(LIGHT_RED);
					}
				}
				infos.add(infos.size(), str);
			}
			
			if(next == true) imload = new ImageLoaderController(infos, "3DPrinter");
		}
	};
	
	private ActionListener operationSaveListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			boolean save=true;
			ArrayList<String> infos = new ArrayList<String>();
			for(int i = 0;i<textfieldlist.size();i++){
				String str = textfieldlist.get(i).getText();
				if(i == 0){
					if(str.equals("")){
						save=false;
						textfieldlist.get(i).setBackground(LIGHT_RED);
					}
				}
				infos.add(infos.size(), str);
			}
			
			try {	
				if(save==true){
					JFileChooser fchooser = new JFileChooser();
					FileNameExtensionFilter filter = new FileNameExtensionFilter("Portable Document Format .pdf","pdf","pdf");
					fchooser.setFileFilter(filter);
					int returnVal = fchooser.showSaveDialog(null);
					if(returnVal == JFileChooser.APPROVE_OPTION) {
						File fichier =  fchooser.getSelectedFile();
						String filePath = fichier.getPath();
						if(!filePath.toLowerCase().endsWith(".pdf"))
						{
							fichier = new File(filePath + ".pdf");
						}
						saveButton(fichier, infos, null);
					}
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DocumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	};
	
	private FocusListener textfieldFocusListener = new FocusListener(){

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			if(e.getComponent().getBackground().equals(LIGHT_RED)){
				e.getComponent().setBackground(Color.WHITE);
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub
			
		}
	};
	
	private KeyListener titleListener = new KeyListener(){
		int nb_letters = 0;
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub		
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			int NB_PIXELS_LIMIT = 290;
			JTextField textfield = ((JTextField)e.getComponent());
			String text = textfield.getText();
			float f = getWidthPoint(text);
			if(f>NB_PIXELS_LIMIT&&e.getKeyCode() != KeyEvent.VK_BACK_SPACE){		
				textfield.setText(text.substring(0, text.length()-1));
				nb_letters = text.length();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub		
		}
		
	};
	
	private KeyListener textListener = new KeyListener(){

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub		
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			int NB_PIXELS_LIMIT = 180;
			JTextField textfield = ((JTextField)e.getComponent());
			String text = textfield.getText();
			float f = getWidthPoint(text);
			if(f>NB_PIXELS_LIMIT) {
				textfield.setText(text.substring(0, text.length()-1));
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub		
		}
		
	};
	
	private MouseListener textfieldListener = new MouseListener() {
		@Override
		public void mousePressed(MouseEvent e) {
			if(e.getComponent().getBackground().equals(LIGHT_RED)){
				e.getComponent().setBackground(Color.WHITE);
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	};

}
