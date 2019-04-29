package cn.test.word;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;

import cn.test.po.Word;



/**
 * @author CHL
 * 
 */
public class ReadWord {
	
	  public  List<Word> ReadWords(String path) throws IOException {
          List<Word> list = new ArrayList<Word>();
          List<String> imgList = new ArrayList<String>();
          InputStream is = new FileInputStream(path);  
		  //WordExtractor extractor = new WordExtractor(is);
		  HWPFDocument doc=new HWPFDocument(is); 
		  int length=doc.characterLength();
		  PicturesTable pTable=doc.getPicturesTable();
		  Word word =null;
		 for (int i=0;i<length;i++){
		         Range range=new Range(i, i+1,doc);
		         CharacterRun cr=range.getCharacterRun(0);
		         word = new Word();
		         if(pTable.hasPicture(cr)){
		          Picture pic=pTable.extractPicture(cr, false);
		          String afileName=pic.suggestFullFileName();
		          OutputStream out=new FileOutputStream(new File("E://word//"+afileName));
		          pic.writeImageContent(out);
		          imgList.add("E://word//"+afileName);
		        }
		         word.setImageUrl(imgList);
		       }
		 Range range = doc.getRange();
		 TableIterator tableIter = new TableIterator(range);   
		 Table table;  
		 TableRow row;   
		 TableCell cell;   
		 while (tableIter.hasNext()) {   
			 table = tableIter.next();    
			 
			 int rowNum = table.numRows();
			 String str = "";
			 for (int j=0; j<rowNum; j++) {      
				 row = table.getRow(j);      
				 int cellNum = row.numCells();     
				 for (int k=0; k<cellNum; k++) {        
					 cell = row.getCell(k);
					 str +=cell.text().trim();
				 }
			 }word.setConnect(str);
		 }
		 list.add(word);
		return list;
	  
	  }
	 
	 /* public static void main(String[] args) throws IOException {
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
	  
	  

}