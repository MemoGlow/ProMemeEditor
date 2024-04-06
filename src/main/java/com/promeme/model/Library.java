package com.promeme.model;

import java.io.File;
import java.util.ArrayList;

public class Library {
    private ArrayList<File> directories;

    public Library(){
        directories = new ArrayList<File>();
    }

    public ArrayList<File> getDirectories() {
        return directories;
    }

    public void setDirectories(ArrayList<File> directories) {
        this.directories = directories;
    }
}
