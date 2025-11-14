package edu.utm.tmps.Lab3;

public class FilterDecorator extends ProfileDecorator {

    public FilterDecorator(IProfileService wrapee) {
        super(wrapee);
    }

    @Override
    public void addProfilePicture(String picture) {
        super.addProfilePicture(picture);
    }

    public void applyFilter(String filterName) {
        System.out.println("Applied filter: " + filterName);
    }
}
