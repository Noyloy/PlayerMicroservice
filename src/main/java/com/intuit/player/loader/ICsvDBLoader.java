package com.intuit.player.loader;

public interface ICsvDBLoader {
    String getCSVPath();
    int getCSVContentRowStartIndex();
    String getCSVDBName();
    boolean load(String[] csvRow);
}
