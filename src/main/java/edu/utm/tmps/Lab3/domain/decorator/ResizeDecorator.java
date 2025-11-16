package edu.utm.tmps.Lab3.domain.decorator;

import edu.utm.tmps.Lab3.domain.service.IProfilePictureService;

public class ResizeDecorator extends ProfilePictureDecorator {

    public ResizeDecorator(IProfilePictureService wrapee) {
        super(wrapee);
    }

    @Override
    public void addProfilePicture(String picture) {
        System.out.println("Validating image size...");
        super.addProfilePicture(picture);
        System.out.println("Compressing image for faster loading...");
    }

    public void resizeSmaller() {
        System.out.println("Resized picture to SMALLER size");
        setImageSize("small");
    }

    public void resizeBigger() {
        System.out.println("Resized picture to BIGGER size");
        setImageSize("big");
    }
}
