package View.Forms;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class AbstractForm {
	
	protected ArrayList<Component> componentlist = new ArrayList<Component>();
	protected JFrame frame;
	
	public void addComponent(JFrame frame, Component comp, GridBagConstraints c){
		frame.add(comp,c);
		componentlist.add(comp);
	}
	
	private JPanel createTextedGridedPanel(int rows, int cols){
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(rows,cols));
		return panel;
	}
	
	protected void addMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu infoMenu = new JMenu("Info");
		
		JMenuItem aboutItem = new JMenuItem("About");
		aboutItem.addActionListener(aboutlistener);
		infoMenu.add(aboutItem);
		
		JMenuItem licenseItem = new JMenuItem("License");
		licenseItem.addActionListener(licenselistener);
		infoMenu.add(licenseItem);
		
		menuBar.add(infoMenu);
	}
	
	ActionListener licenselistener = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			double width = screenSize.getWidth();
			double height = screenSize.getHeight();
			
			JFrame licenseframe = new JFrame("License");
			licenseframe.setResizable(false);
			JPanel panel = createTextedGridedPanel(8, 0);
			licenseframe.add(panel);
			panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			panel.add(new JLabel("This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as "));
			panel.add(new JLabel("published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version."));
			panel.add(new JLabel(""));
			panel.add(new JLabel("This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS "));
			panel.add(new JLabel("FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details."));
			panel.add(new JLabel(""));
			panel.add(new JLabel("You should have received a copy of the GNU General Public License along with this program. If not, see "));
			
			JLabel gnu_label1 = new JLabel("http://www.gnu.org/licenses/");
			gnu_label1.addMouseListener(hyperlinklistener);
			panel.add(gnu_label1);
			
			licenseframe.setVisible(true);
			licenseframe.pack();
			licenseframe.setLocation((((int)width/2)-(licenseframe.getWidth()/2)), (((int)height/2)-(licenseframe.getHeight()/2)));
		}
	};
	
	ActionListener aboutlistener = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {

			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			double width = screenSize.getWidth();
			double height = screenSize.getHeight();
			
			JFrame aboutframe = new JFrame("About");
			aboutframe.setResizable(false);
			JPanel panel = createTextedGridedPanel(9, 0);
			aboutframe.add(panel);
			panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			panel.add(new JLabel("Authors: Alexandre Péchereau and Romain Di Vozzo"));
			panel.add(new JLabel("Contents: Romain Di Vozzo"));
			panel.add(new JLabel("Coding and Design: Alexandre Péchereau"));
			panel.add(new JLabel(""));
			panel.add(new JLabel("July 2015 beta version"));
			panel.add(new JLabel("Université Paris-Sud/Fablab Digiscope/INRIA"));
			panel.add(new JLabel(""));
			
			JLabel github_label1 = new JLabel("https://github.com/AlexandrePechereau/FabDocMaker");
			github_label1.addMouseListener(hyperlinklistener);
			panel.add(github_label1);
			
			JLabel github_label2 = new JLabel("http://github.com/fablabdigiscope/FabDocMaker");
			github_label2.addMouseListener(hyperlinklistener);
			panel.add(github_label2);
			
			aboutframe.setVisible(true);
			aboutframe.pack();
			aboutframe.setLocation((((int)width/2)-(aboutframe.getWidth()/2)), (((int)height/2)-(aboutframe.getHeight()/2)));
		}
		
	};
	
	MouseListener hyperlinklistener = new MouseListener(){

		@Override
		public void mouseClicked(MouseEvent e) {
            JLabel label=(JLabel)e.getSource();
            String plainText = label.getText().replaceAll("<[^>]*>", "");
            try {
                Desktop.getDesktop().browse(new URI(plainText));
            } catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }

        public void mouseEntered(MouseEvent e) {
            JLabel label=(JLabel)e.getSource();
            String plainText = label.getText().replaceAll("<[^>]*>", "");
            String urlText="<html><u>"+plainText+"</u></html>";
            label.setText(urlText);
        }

        public void mouseExited(MouseEvent e) {
            JLabel label=(JLabel)e.getSource();
            String plainText = label.getText().replaceAll("<[^>]*>", "");
            String urlText="<html>"+plainText+"</html>";
            label.setText(urlText);
        }

		public void mousePressed(MouseEvent e) {
			
		}

		public void mouseReleased(MouseEvent e) {
			
		}
		
	};
	
	public abstract JLabel createLabel(String text_title);
	public abstract JTextField createTextField();
	public abstract JPanel createTextedGridedPanel(String title, int rows, int cols);
}
