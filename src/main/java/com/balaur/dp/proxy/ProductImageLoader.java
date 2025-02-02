package com.balaur.dp.proxy;

public class ProductImageLoader implements ProductImage {
    private String filename;
    private RealProductImage realImage;

    public ProductImageLoader(String filename) {
        this.filename = filename;
    }

    @Override
    public void displayImage() {
        if (realImage == null) {
            realImage = new RealProductImage(filename);
        }
        realImage.displayImage();
    }
}
