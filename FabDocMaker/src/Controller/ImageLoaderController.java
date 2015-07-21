package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.filechooser.FileNameExtensionFilter;

import Model.CreatePDF_3DPrinter;
import Model.CreatePDF_LaserCutting;
import View.ImageLoader;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;

@SuppressWarnings("unused")
public class ImageLoaderController {

	private ImageLoader imload;
    private ArrayList<String> infos;
    private JFrame frame;
	DefaultListModel<?> listModel;
	private JList<?> list;
	private CreatePDF_3DPrinter cpdf_3DPrinter;
	private CreatePDF_LaserCutting cpdf_LaserCutting;
	private String caller;

	private JButton openButton, saveButton, cancelButton, removeButton;
	
	public ImageLoaderController(ArrayList<String> strlist, String caller){
		imload = new ImageLoader(strlist);
		infos = strlist;
		frame = imload.getFrame();
		listModel = imload.getlistModel();
		list = imload.getlist();
		openButton = imload.getopenButton();
		openButton.addActionListener(operationOpenListener);
		saveButton = imload.getsaveButton();
		saveButton.addActionListener(operationSaveListener);
		cancelButton = imload.getcancelButton();
		cancelButton.addActionListener(operationCancelListener);
		removeButton = imload.getremoveButton();
		removeButton.addActionListener(operationRemoveListener);
		this.caller = caller;
	}
	
	private void saveButton(File file, ArrayList<String> strlist, ArrayList<Image> imagelist) throws DocumentException, MalformedURLException, IOException{
		if(caller == "3DPrinter") cpdf_3DPrinter = new CreatePDF_3DPrinter(file, infos, imagelist);
		if(caller == "LaserCutting") cpdf_LaserCutting = new CreatePDF_LaserCutting(file, infos, imagelist);
	}
	
	private ActionListener operationCancelListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			frame.setVisible(false);
		}
	};
	
	private ActionListener operationRemoveListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			int index = list.getSelectedIndex();
            listModel.remove(index);

            int size = listModel.getSize();

            if (size == 0) {
                removeButton.setEnabled(false);
                saveButton.setEnabled(false);

            } else { //Select an index.
                if (index == listModel.getSize()) {
                    //removed item in last position
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
		}
	};
	
	private ActionListener operationOpenListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fchooser = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bnp");
			fchooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fchooser.setAcceptAllFileFilterUsed(false);
			fchooser.setFileFilter(filter);
			int returnVal = fchooser.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				File fichier =  fchooser.getSelectedFile();
				String filePath = fichier.getPath();
				imload.addToJList(filePath);
				removeButton.setEnabled(true);
				saveButton.setEnabled(true);
			}
		}
	};
	
	private ActionListener operationSaveListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

			try {	
				ArrayList<Image> images = new ArrayList<Image>();
				
				ListModel<?> items = list.getModel();
				for(int i=0;i<items.getSize();i++){
					Image image = Image.getInstance(items.getElementAt(i).toString());
					images.add(image);
				}
				
				JFileChooser fchooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Portable Document Format .pdf","pdf");
				fchooser.setFileFilter(filter);
				int returnVal = fchooser.showSaveDialog(null);
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					File fichier =  fchooser.getSelectedFile();
					String filePath = fichier.getPath();
					if(!filePath.toLowerCase().endsWith(".pdf"))
					{
						fichier = new File(filePath + ".pdf");
					}
					saveButton(fichier, infos, images);

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
}
