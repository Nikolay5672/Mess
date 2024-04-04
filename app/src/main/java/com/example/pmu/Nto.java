package com.example.pmu;

public class Nto {

    private Long distance;
    private String name;
    private String imgPath;
    private String placePhoneNumber;
    private String urlMap;
    private String workingHours;

    // Default constructor required for Firebase
    public Nto() {
    }

    public Nto(Long distance,String name, String imgPath, String placePhoneNumber, String urlMap, String workingHours) {
        this.name = name;
        this.imgPath = imgPath;
        this.placePhoneNumber = placePhoneNumber;
        this.urlMap = urlMap;
        this.workingHours = workingHours;
        this.distance = distance;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getPlacePhoneNumber() {
        return placePhoneNumber;
    }

    public void setPlacePhoneNumber(String placePhoneNumber) {
        this.placePhoneNumber = placePhoneNumber;
    }

    public String getUrlMap() {
        return urlMap;
    }

    public void setUrlMap(String urlMap) {
        this.urlMap = urlMap;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }
}
