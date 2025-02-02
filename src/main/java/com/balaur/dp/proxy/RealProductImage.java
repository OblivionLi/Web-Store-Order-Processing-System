package com.balaur.dp.proxy;

public class RealProductImage implements ProductImage {
    private String filename;

    public RealProductImage(String filename) {
        this.filename = filename;
        loadImageFromDisk(); // simulate loading the image
    }

    private void loadImageFromDisk() {
        System.out.println("Loading image: " + filename);
        // in real app, you would actually load the image data here
    }

    @Override
    public void displayImage() {
        System.out.println("Displaying image: " + filename);
    }
}
