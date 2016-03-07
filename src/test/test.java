/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import FileObserver.FileObserver;
import java.io.IOException;

/**
 *
 * @author hansone123
 */
public class test {
        /**
     * @param args the command line arguments
     * args[0]: is the file extension we watch
     */
    public static void main(String[] args) throws IOException {
        
        FileObserver fileObserver = new FileObserver();
        if( !fileObserver.setValidDirectoryPath("/tmp/") )  {            
            return;
        }
        
        if (args.length >0) {
            String fileExtesion = args[0];
            fileObserver.setFileExtension(fileExtesion);            
        }
        
        fileObserver.setFileExtension("");
        fileObserver.keepWatchOnDirectoryAndDoJob();
        
    }
}
