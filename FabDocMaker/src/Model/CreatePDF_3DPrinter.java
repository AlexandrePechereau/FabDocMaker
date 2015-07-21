package Model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@SuppressWarnings("unused")
public class CreatePDF_3DPrinter {

	private  static File result;
	private Document MyPDF;
	private PdfWriter MyWriter;
	
	public CreatePDF_3DPrinter(File file, ArrayList<String> infos, ArrayList<Image> imagelist) throws DocumentException, MalformedURLException, IOException{
		result = file;
		MyPDF = new Document(PageSize.A4, 50, 50, 80, 80);
		MyWriter = PdfWriter.getInstance(MyPDF,new FileOutputStream(result));
		MyWriter.setInitialLeading(160);
		MyPDF.open();
		Font font = new Font(Font.FontFamily.TIMES_ROMAN, 40, Font.NORMAL);
		Paragraph pg = new Paragraph();
		pg.setAlignment(Element.ALIGN_CENTER);
		pg.setSpacingAfter(150);
		pg.setFont(font);
		pg.add(infos.get(0));	
		pg.setLeading(2,1);
		MyPDF.add(pg);
		Image image = Image.getInstance(loadImage("/logo.png"), null);
		image.setAbsolutePosition((MyPDF.getPageSize().getWidth()/2) - (image.getWidth()/2), 580f);
		if(infos.get(0).length()<25){
			image.setAbsolutePosition((MyPDF.getPageSize().getWidth()/2) - (image.getWidth()/2), 620f);
		}
        MyPDF.add(image);
		MyPDF.add(createFrontPageTable(infos));
		
		if(imagelist!=null){
			tilingImages(imagelist);
		}
		MyPDF.close();
	}
	
	public BufferedImage loadImage(String fileName){

	    BufferedImage buff = null;
	    try {
	        buff = ImageIO.read(getClass().getResource(fileName));
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	        return null;
	    }
	    return buff;

	}
	
	private void tilingImages( ArrayList<Image> images) throws DocumentException {
		
		if(images.isEmpty()) return;
		MyPDF.newPage();
		float imagewidthmax, imageheightmax;
		if(images.size() < 3){
			
			imagewidthmax = 400;
			imageheightmax = 300;
			
			for(int i = 0 ; i<images.size() ; i++){
				
				Image image = images.get(i);
				float scaler = reshapeImage(image, imagewidthmax, imageheightmax);
				float x = (MyPDF.getPageSize().getWidth()/2) - ((image.getWidth()*scaler)/2);
				float y = ((MyPDF.getPageSize().getHeight())/4) - ((image.getHeight()*scaler)/2);
				
				if(i==0){
					image.setAbsolutePosition(x, y+(MyPDF.getPageSize().getHeight()/2));
				}
				else{
					image.setAbsolutePosition(x, y);
				}
				MyPDF.add(image);
			}
			return;
		}
		else{
			imagewidthmax = 260;
			imageheightmax = 300;
			
			for(int i = 0 ; i < 4 ; i++){
				
				if(images.isEmpty()) break;
				Image image = images.remove(0);
				float scaler = reshapeImage(image, imagewidthmax, imageheightmax);
				float x = (MyPDF.getPageSize().getWidth()/4) - ((image.getWidth()*scaler)/2);
				float y = (MyPDF.getPageSize().getHeight()/4) - ((image.getHeight()*scaler)/2);
				
				if(i==0){
					image.setAbsolutePosition(x, y+(MyPDF.getPageSize().getHeight()/2));
				}
				if(i==1){
					image.setAbsolutePosition(x+(MyPDF.getPageSize().getWidth()/2), y+(MyPDF.getPageSize().getHeight()/2));
				}
				if(i==2){
					image.setAbsolutePosition(x, y);
				}
				if(i==3){
					image.setAbsolutePosition(x+(MyPDF.getPageSize().getWidth()/2), y);
				}
				MyPDF.add(image);
			}
			tilingImages(images);
	
			return;
		}
	}
	
	private float reshapeImage(Image image, float imagewidthmax, float imageheightmax){

		float imageheight = image.getHeight();
		float imagewidth = image.getWidth();
		float scaler;
		if((imageheightmax>imageheight)&&(imageheightmax>imagewidth)) return 1;
		if(imageheight < imagewidth){
			
			scaler = imagewidthmax/image.getWidth()*100;	
			image.scalePercent(scaler);
		}
		else {
			scaler = imageheightmax/image.getHeight()*100;
			image.scalePercent(scaler);
		}
		return scaler/100;
	}
	
	private PdfPTable createFrontPageTable(ArrayList<String> infos) {

		PdfPTable table = new PdfPTable(2);

        PdfPCell cell,cell1,cell2,cell3,cell4,cell5,cell6,cell7,cell8,cell9,cell10,cell11,cell12,cell13,cell14,cell15,cell16,cell17,cell18,cell19,cell20,cell21,cell22;
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);


       
        cell = new PdfPCell(new Phrase("Machine Brand :"));
        cell1 = new PdfPCell(new Phrase("Machine version :"));
        cell2 = new PdfPCell(new Phrase("Filament/Material you used :"));
        cell3 = new PdfPCell(new Phrase("Filament Brand :"));
        cell4 = new PdfPCell(new Phrase("Filament diameter :"));
        cell5 = new PdfPCell(new Phrase("Object Layer Height :"));
        cell6 = new PdfPCell(new Phrase("Object InFill :"));
        cell7 = new PdfPCell(new Phrase("Object Print Speed :"));
        cell8 = new PdfPCell(new Phrase("Print Temperature :"));
        cell9 = new PdfPCell(new Phrase("Support Type :"));
        cell10 = new PdfPCell(new Phrase("Platform Adhesion Type :"));
        cell11 = new PdfPCell(new Phrase("Duration of the print :"));
        cell12 = new PdfPCell(new Phrase("Length of the filament :"));
        cell13 = new PdfPCell(new Phrase("Weight of the print :"));
        cell14 = new PdfPCell(new Phrase("Cost of the print :"));
        cell15 = new PdfPCell(new Phrase("Software you used to convert your .STL 3D object into a G-Code file :"));
        cell16 = new PdfPCell(new Phrase("Software you used to draw this 3D object :"));
        cell17 = new PdfPCell(new Phrase("Software you used to analyze and repair this 3D object :"));
        cell18 = new PdfPCell(new Phrase("Files were designed by You :"));
        cell19 = new PdfPCell(new Phrase("Files were downloaded from :"));
        cell20 = new PdfPCell(new Phrase("Files were downloaded and modified by you :"));
        cell21 = new PdfPCell(new Phrase("Your Name :"));
        cell22 = new PdfPCell(new Phrase("Are you a member of :"));

        table.addCell(cell);
        table.addCell(infos.get(1));
        table.addCell(cell1);
        table.addCell(infos.get(2));
        table.addCell(cell2);
        table.addCell(infos.get(3));
        table.addCell(cell3);
        table.addCell(infos.get(4));
        table.addCell(cell4);
        table.addCell(infos.get(5));
        table.addCell(cell5);
        table.addCell(infos.get(6));
        table.addCell(cell6);
        table.addCell(infos.get(7));
        table.addCell(cell7);
        table.addCell(infos.get(8));
        table.addCell(cell8);
        table.addCell(infos.get(9));
        table.addCell(cell9);
        table.addCell(infos.get(10));
        table.addCell(cell10);
        table.addCell(infos.get(11));
        table.addCell(cell11);
        table.addCell(infos.get(12));
        table.addCell(cell12);
        table.addCell(infos.get(13));
        table.addCell(cell13);
        table.addCell(infos.get(14));
        table.addCell(cell14);
        table.addCell(infos.get(15));
        table.addCell(cell15);
        table.addCell(infos.get(16));
        table.addCell(cell16);
        table.addCell(infos.get(17));
        table.addCell(cell17);
        table.addCell(infos.get(18));
        table.addCell(cell18);
        table.addCell(infos.get(19));
        table.addCell(cell19);
        table.addCell(infos.get(20));
        table.addCell(cell20);
        table.addCell(infos.get(21));
        table.addCell(cell21);
        table.addCell(infos.get(22));
        table.addCell(cell22);
        table.addCell(infos.get(23));
        return table;
    }
	
}

