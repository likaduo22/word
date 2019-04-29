package cn.test.po;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.UUID;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import cn.likaduo.common.Path;


/**
 * @author CHL
 * 
 */
public class FileRead {
		
	@SuppressWarnings("resource")
	public static String getSpecifyDataForDoc() throws Exception {
			

				File file = new File("E:/word/aaa.doc");
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
	        		               
	     	                   for(int k=0;k<numCharacterRuns;k++ ){
	     	                	   CharacterRun cr = td.getCharacterRun(k);
	     	                	   System.out.println(cr);
	     	                	   if (picturesTable.hasPicture(cr)) {
	     	     						Picture pic = picturesTable.extractPicture(cr, true);
	     	     						byte[] picbyte = pic.getContent();
	     	     						String filePath = Path.PATH+ UUID.randomUUID() +".jpg";
	     	     						FileOutputStream fos = new FileOutputStream(filePath);
	     	     						fos.write(picbyte);
	     	     						
	     	                          
	     	                  }
	     	                	   
	     	                   }
	        				  
	        			  }
	        			  
	        		  }
	        		  
	              }
		
				return "121";
			}
	}
