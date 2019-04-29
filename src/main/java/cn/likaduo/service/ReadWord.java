package cn.likaduo.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.springframework.stereotype.Service;

import cn.likaduo.common.Path;
import cn.likaduo.po.Word;




/**
 * @author CHL
 * 
 */
@Service
public class ReadWord {
	  
	  public  List<Word> ReadWords(String path) throws IOException {
         
			File file = new File(path);
			FileInputStream  fileInputStream = new FileInputStream(file);
			POIFSFileSystem poifsFileSystem = new POIFSFileSystem(fileInputStream);
	        HWPFDocument doc=new HWPFDocument(poifsFileSystem);
			Range range = doc.getRange();
			TableIterator it = new TableIterator(range);
			PicturesTable picturesTable = doc.getPicturesTable();
			  Table tb;
              TableRow row;
              TableCell td ;
			while (it.hasNext()) {
				
        		  tb = (Table) it.next();
        		  int rowNum = tb.numRows();
        		  
        		  for(int i =0 ;i<rowNum;i++){
        			
        			  row = tb.getRow(i);
        			  int cellNum = row.numCells();
        			  
        			  for(int j = 0 ; j< cellNum ;j++){
        				  
        				  td = row.getCell(j);
        				  int numCharacterRuns = td.numCharacterRuns();
        				  System.out.println(td.text().trim());
     	                   for(int k=0;k<numCharacterRuns;k++ ){
     	                	   CharacterRun cr = td.getCharacterRun(k);
     	                	   if (picturesTable.hasPicture(cr)) {
     	     						Picture pic = picturesTable.extractPicture(cr, true);
     	     						byte[] picbyte = pic.getContent();
     	     						String filePath = Path.PATH+ UUID.randomUUID() +".jpg";
     	     						FileOutputStream fos = new FileOutputStream(filePath);
     	     						fos.write(picbyte);
     	     						System.out.println(filePath);
     	                          
     	                  }
     	                	   
     	                   }
        				  
        			  }
        			  
        		  }
        		  
              }      
          
			return null;
	  
	  }
}
	 
	/*  public static void main(String[] args) throws IOException {
		  ReadWord readWord = new ReadWord();
		  List<Word> readWords = readWord.ReadWords("E://word//sss.doc");
		  
		  for (Word word : readWords) {
			  List<String> imageUrl = word.getImageUrl();
			  System.out.println( word.getConnect());
			 for (String str : imageUrl) {
				System.out.println(str);
			}
			  
			 
		}
		  
	}*/
	  
	  