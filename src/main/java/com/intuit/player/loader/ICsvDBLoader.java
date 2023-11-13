package com.intuit.player.loader;

public interface ICsvDBLoader {
    public String getCSVPath();
    public int getCSVContentRowStartIndex();
    public String getCSVDBName();
    public boolean load(String[] csvRow);
}
