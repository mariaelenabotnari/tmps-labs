package edu.utm.tmps.Lab3.domain.decorator;

import edu.utm.tmps.Lab3.domain.service.IProfilePictureService;

public class FilterDecorator extends ProfilePictureDecorator {

    public FilterDecorator(IProfilePictureService wrapee) {
        super(wrapee);
    }

    @Override
    public void addProfilePicture(String picture) {
        System.out.println("Checking picture quality...");
        super.addProfilePicture(picture);
        System.out.println("Applying default enhancement filter...");
    }

    public void applyFilter(String filterName) {
        System.out.println("Applied filter: " + filterName);
        setAppliedFilter(filterName);
    }

}
