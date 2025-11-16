package edu.utm.tmps.Lab3.domain.decorator;

import edu.utm.tmps.Lab3.domain.service.IProfilePictureService;

public abstract class ProfilePictureDecorator implements IProfilePictureService {
    protected IProfilePictureService wrapee;

    public ProfilePictureDecorator(IProfilePictureService wrapee) {
        this.wrapee = wrapee;
    }

    @Override
    public void addProfilePicture(String picture) {
        wrapee.addProfilePicture(picture);
    }

    @Override
    public void setImageSize(String size) {
        wrapee.setImageSize(size);
    }

    @Override
    public void setAppliedFilter(String filter) {
        wrapee.setAppliedFilter(filter);
    }

    @Override
    public void displayProfile() {
        wrapee.displayProfile();
    }
}
