package com.promeme.controller;

import com.promeme.model.Library;
import com.promeme.view.LibraryView;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryController {
    LibraryView libraryView;
    Library library;

    public LibraryController(LibraryView libraryView) {
        library = new Library();
        this.libraryView = libraryView;
    }

    public void loadData() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("library.dat"));
        library.setDirectories((ArrayList<File>) ois.readObject());
    }
    public void writeData() throws IOException {
        ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream("library.dat"));
        ous.writeObject(library.getDirectories());
    }
    public void addDirectory(File file) throws IOException {
        library.getDirectories().add(file);
        writeData();
    }
    public LibraryView getLibraryView() {
        return libraryView;
    }

    public void setLibraryView(LibraryView libraryView) {
        this.libraryView = libraryView;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }
}
