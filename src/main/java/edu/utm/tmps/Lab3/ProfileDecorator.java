package edu.utm.tmps.Lab3;

public abstract class ProfileDecorator implements IProfileService {
    private IProfileService wrapee;

    public ProfileDecorator(IProfileService wrapee) {
        this.wrapee = wrapee;
    }

    @Override
    public void addProfilePicture(String picture) {
        wrapee.addProfilePicture(picture);
    }

    @Override
    public void displayProfile() {
        wrapee.displayProfile();
    }
}
