package View;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;



@SuppressWarnings("serial")
public class ImageLoader extends JFrame{
	
	private JList<String> list;
    private JScrollPane scroll;
    private JButton openButton, saveButton, cancelButton, removeButton;
    private int WIDTH = 300, HEIGHT = 400;

    private JFrame frame;
    
    private DefaultListModel<String> listModel;

	public ImageLoader(ArrayList<String> strlist){

		frame=this;
		
		this.setTitle("Image Loader");
		this.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		this.setSize(WIDTH, HEIGHT);
		this.setLocation(((int)width/2)-(WIDTH/2), ((int)height/2)-(HEIGHT/2));
		
		this.setLayout(null);
		
		openButton = new JButton("Open");
		openButton.setSize(this.getWidth()-15, 30);
		openButton.setLocation(5, 5);
		this.add(openButton);
		
		listModel = new DefaultListModel<String>();
    	list = new JList<String>(listModel);
    	scroll = new JScrollPane(list);
    	list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
    	scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    	scroll.setSize(WIDTH-15, HEIGHT-145);
    	scroll.setLocation(5, 40);
    	this.add(scroll);
    	
    	removeButton = new JButton("Remove");
    	removeButton.setSize(this.getWidth()-15, 30);
    	removeButton.setLocation(5, HEIGHT-100);
		this.add(removeButton);
		removeButton.setEnabled(false);
    	
    	saveButton = new JButton("Save");
    	saveButton.setSize(140, 30);
    	saveButton.setLocation(5, HEIGHT-65);
    	this.add(saveButton);
    	saveButton.setEnabled(false);
    	
    	cancelButton = new JButton("Cancel");
    	cancelButton.setSize(140, 30);
    	cancelButton.setLocation(150, HEIGHT-65);
    	this.add(cancelButton);
    	this.setVisible(true);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	protected boolean alreadyInList(String path) {
        return listModel.contains(path);
    }
	
	public void addToJList(String path){
		if(!alreadyInList(path)){
			int index = list.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }
            
            listModel.insertElementAt(path, index);         
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
		}
	}
	
	public JList<String> getlist(){
		return list;
	}

	public DefaultListModel<String> getlistModel(){
		return listModel;
	}
	
	public JButton getsaveButton(){
		return saveButton;
	}
	
	public JButton getopenButton(){
		return openButton;
	}
	
	public JButton getcancelButton(){
		return cancelButton;
	}
	
	public JButton getremoveButton(){
		return removeButton;
	}
	
	public JFrame getFrame(){
		return frame;
	}
	
}
