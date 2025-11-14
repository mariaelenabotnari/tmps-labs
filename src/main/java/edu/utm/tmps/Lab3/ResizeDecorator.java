package edu.utm.tmps.Lab3;

public class ResizeDecorator extends ProfileDecorator {

    public ResizeDecorator(IProfileService wrapee) {
        super(wrapee);
    }

    @Override
    public void addProfilePicture(String picture) {
        super.addProfilePicture(picture);
    }

    public void resizeSmaller() {
        System.out.println("Resized picture to SMALLER size");
    }

    public void resizeBigger() {
        System.out.println("Resized picture to BIGGER size");
    }
}
