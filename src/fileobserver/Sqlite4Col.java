/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileobserver;

import java.util.Arrays;

/**
 *
 * @author hansone123
 */
enum Sqlite4ColumnType {
    STR , UTF8, UTF16LE, UTF16BE,
    BLOB, NULL, ZERO, ONE, INT,
    REAL, OTHER
    
}
public class Sqlite4Col {
    private String name;
    private byte[] value ;
    private Sqlite4ColumnType type ;
    
    public Sqlite4Col(){ }
    public void setName(String name) {
        this.name = name;
    }
    public void setValue(byte[] value) {
        this.value = value.clone();
    }

    /**
     *
     * @param type
     * enum Sqlite4ColumnType {
     *  KEY,UTF8STR,UTF16LE,UTF16BE,
     *  BLOB, NULL, ZERO, ONE, INT, REAL
     *}
     *
     */
    public void setType(Sqlite4ColumnType type) {
        this.type = type;
    }
    public byte[] getValue() {
        return this.value;
    }

    /**
     *
     * @return type
     * enum Sqlite4ColumnType {
     *  KEY,UTF8STR,UTF16LE,UTF16BE,
     *  BLOB, NULL, ZERO, ONE, INT, REAL
     *}
     */
    public Sqlite4ColumnType getType() {
        return this.type;
    }
    public void SetColumnTypeAndValue(HeaderOfKValue hdr, byte[] input) {
        
        this.setType(hdr.type);
        this.setValue(Arrays.copyOfRange(input, hdr.ofstOfValue, hdr.ofstOfValue + hdr.sizeOfValue-1));
        
    }
    
    public static void main(String args[]) {
        
    }
}
