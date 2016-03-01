/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileobserver;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

/**
 *
 * @author hansone123
 */
public class FileObserver {
    private String directoryPath;
    
    
    public boolean setValidDirectoryPath(String dirPath) {
        
        this.directoryPath = dirPath;    
        return this.dirIsExisted(this.directoryPath);
    }
    
    public boolean dirIsExisted(String dir) {
        
        File directory = new File(dir);
        if ((directory.exists()  && directory.isDirectory())) {
            System.out.println("FileObserver directory path set to: " + this.directoryPath);
            return true;
        }
        return false;
    }
    
    public String chooseFile() {
       
        if (this.dirIsEmpty())
            return null;
        
        File directory = new File(this.directoryPath);
        String[] allFiles = directory.list();        
        String result = allFiles[0];
        for (String file : allFiles) {
            if (result.compareTo(file) > 0)
                result = file;
        }
        return this.directoryPath + "//" + result;
    }
    public boolean dirIsEmpty() {
        File directory = new File(this.directoryPath);
        String[] allFiles = directory.list();
        return allFiles.length < 1;
    }
    public KVFile readAndRenderKVFile(String fileName) throws FileNotFoundException, IOException {
           
        BufferedInputStream file = new BufferedInputStream(new FileInputStream(fileName));
        byte[] buf = new byte[file.available()];
        file.read(buf, 0, file.available());
        KVFile kvfile = new KVFile(fileName, buf);   
        
        System.out.println("Open file: " + kvfile.getName());
        System.out.println("file size: " + kvfile.getSize());
        
        return kvfile;
    }
    public int pushToPhoenix(KVFile kvfile) {
        
        for (Sqlite4Record record:kvfile.toSqlite4Records()) {
            this.sendAQuery(record);
            
        }
        int SuccessedNumberOfRecord = 0;
        return SuccessedNumberOfRecord;
    }
    public boolean sendAQuery(Sqlite4Record record) {
        
        for (Sqlite4Col col : record.getColumns()) {
            col.show();
            Sqlite4Decoder decoder = new Sqlite4Decoder();
            System.out.println("  value: " + decoder.fromColToString(col));
        }
        return true;
    }
    public void keepWatchOnDirectoryAndDoEvent() {
        
        while(true) {
            String file = this.chooseFile();
            System.out.println("Choose file: " + file);
            try{
                KVFile kvfile = new KVFile(file);
                kvfile.readAndRenderKVFile(file);                
                this.pushToPhoenix(kvfile);
                this.deleteFile(file);
                
            } catch(IOException e) {
                System.out.println("Directory has no file.");
            }
            break;
        }
    }
//    public String observeDirectory() {
//        
//    }
    
    public void deleteFile(String fileName) {
        System.out.println("Delete " + fileName + ".");
    }
    
            
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        FileObserver fileObserver = new FileObserver();
        if( !fileObserver.setValidDirectoryPath("/tmp/KVoutput") )  {            
            return;
        }
        
        fileObserver.keepWatchOnDirectoryAndDoEvent();
            
//            try{
//                KVFile kvf = fileObserver.readAndRenderKVFile("/tmp/KVoutput/2016_02_24_19:08:13_1.kv");
//            }catch (FileNotFoundException e) {
//                
//            }catch (IOException e) {
//                
//            }
            
    }
    
}
