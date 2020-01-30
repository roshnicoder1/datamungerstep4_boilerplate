package com.stackroute.datamunger.reader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import com.stackroute.datamunger.query.DataTypeDefinitions;
import com.stackroute.datamunger.query.Header;
public class CsvQueryProcessor extends QueryProcessingEngine {
    String fileName;
    File file;
    // Parameterized constructor to initialize filename
    public CsvQueryProcessor(String fileName) throws FileNotFoundException{
       		this.fileName= fileName;
       		this.file = new File(this.fileName);
       		new FileReader(fileName);
//    		if(file == null)
//    		{
//    		System.out.println("Hi");
    //
    }
    /*
     * Implementation of getHeader() method. We will have to extract the headers
     * from the first line of the file.
     * Note: Return type of the method will be Header
     */
    @Override
    public Header getHeader() throws IOException {
    	Header h = null;
    	try {
    	if(file != null) {
    	FileReader fr=new FileReader(file);
    	BufferedReader br=new BufferedReader(fr);
    	String txt=br.readLine();
        String[] arr=txt.split(",");
        if(arr.length!=-1) 
            h=new Header(arr);
           	}else {
           	}
    	}catch(FileNotFoundException f) {
    		System.out.println("Exception");
    	}
        return h;
    }
    /**
     * getDataRow() method will be used in the upcoming assignments
     */
//  
//  @Override
//  public void getDataRow() {
//
//  
//  }
//  
    /*
     * Implementation of getColumnType() method. To find out the data types, we will
     * read the first line from the file and extract the field values from it. If a
     * specific field value can be converted to Integer, the data type of that field
     * will contain "java.lang.Integer", otherwise if it can be converted to Double,
     * then the data type of that field will contain "java.lang.Double", otherwise,
     * the field is to be treated as String. 
     * Note: Return Type of the method will be DataTypeDefinitions
     */
    @Override
    public DataTypeDefinitions getColumnType() throws IOException{
    int i=0;
        try {
        FileReader fr=new FileReader(file);
        BufferedReader br=new BufferedReader(fr);
        String col=br.readLine();
        int len=col.split(",").length;
        col=br.readLine();
        String[] arr1=col.split(",");
        String arr[] = new String[len];
     
        	for(String s:arr1) {
                try {
                	if(s.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$"))
                	{
                		arr[i]="java.util.Date";
                		i++;
                		
                	}
                	else if(s.matches("-?\\d+"))
                	{
                        arr[i]="java.lang.Integer";
                        i++;
                		
                	}
                	else
                	{
                		 arr[i]="java.lang.String";
                		 i++;
                		
                	}
                    
                }
                catch(Exception e) {            
                   
                    }
                
                }
            for(int j=0;j<len;j++)
            {
                if(arr[j]==null) {
                arr[j]="java.lang.Object";
                }
            }
                
        DataTypeDefinitions d=new DataTypeDefinitions(arr);
        fr.close();
        br.close();
        return d;
        }
        catch(Exception ie) {
        DataTypeDefinitions d=new DataTypeDefinitions(new String[0]);
        return d;
        }
    }
    @Override
    public void getDataRow() {
        // TODO Auto-generated method stub
    }
}