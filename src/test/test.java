/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import FileObserver.FileObserver;
import FileObserver.Job.PrintFileNameJob;
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
        
        
        String dirWatched;
        String fileExtesion;
        if (args.length == 2) {
            dirWatched = args[0];
            fileExtesion = args[1];
        } else {
            dirWatched = "testFiles";
            fileExtesion = ".txt";
        }
        
        FileObserver fileObserver = new FileObserver();
        if( !fileObserver.setValidDirectoryPath(dirWatched) )  {            
                return;
        }
        fileObserver.setFileExtension(fileExtesion);      
        PrintFileNameJob job = new PrintFileNameJob();
        fileObserver.setJob(job);
        fileObserver.keepWatchOnDirectoryAndDoJob();
        
    }
}
