package edu.utm.tmps.Lab3.domain.service;

public interface IProfilePictureService {
    void addProfilePicture(String picture);
    void setImageSize(String size);
    void setAppliedFilter(String filter);
    void displayProfile();
}
