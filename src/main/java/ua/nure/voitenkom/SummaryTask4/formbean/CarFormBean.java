package ua.nure.voitenkom.SummaryTask4.formbean;

import ua.nure.voitenkom.SummaryTask4.db.entity.Entity;

public class CarFormBean extends Entity {

    private String model;
    private int price;
    private int doorsCount;
    private boolean hasConditioner;
    private int bigLuggageCount;
    private int smallLuggageCount;
    private int sitsCount;
    private String photoPath;
    private String brandName;
    private String colorName;
    private String className;
    private String statusName;

    public CarFormBean(int id, String model, int price, int doorsCount, boolean hasConditioner, int bigLuggageCount,
                       int smallLuggageCount, int sitsCount, String brandName, String colorName, String className,
                       String statusName, String photoPath) {
        this.setId(id);
        this.model = model;
        this.price = price;
        this.doorsCount = doorsCount;
        this.hasConditioner = hasConditioner;
        this.bigLuggageCount = bigLuggageCount;
        this.smallLuggageCount = smallLuggageCount;
        this.sitsCount = sitsCount;
        this.photoPath = photoPath;
        this.className = className;
        this.brandName = brandName;
        this.colorName = colorName;
        this.statusName = statusName;
    }

    public CarFormBean(String model, int price, int doorsCount,  boolean hasConditioner, int bigLuggageCount,
                       int smallLuggageCount, int sitsCount, String brandName, String colorName,
                       String className, String statusName) {
        this.model = model;
        this.price = price;
        this.doorsCount = doorsCount;
        this.hasConditioner = hasConditioner;
        this.bigLuggageCount = bigLuggageCount;
        this.smallLuggageCount = smallLuggageCount;
        this.sitsCount = sitsCount;
        this.className = className;
        this.brandName = brandName;
        this.colorName = colorName;
        this.statusName = statusName;
    }

    public CarFormBean() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSmallLuggageCount() {
        return smallLuggageCount;
    }

    public void setSmallLuggageCount(int smallLuggageCount) {
        this.smallLuggageCount = smallLuggageCount;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public int getSitsCount() {
        return sitsCount;
    }

    public void setSitsCount(int sitsCount) {
        this.sitsCount = sitsCount;
    }

    public int getBigLuggageCount() {
        return bigLuggageCount;
    }

    public void setBigLuggageCount(int bigLuggageCount) {
        this.bigLuggageCount = bigLuggageCount;
    }

    public boolean isHasConditioner() {
        return hasConditioner;
    }

    public void setHasConditioner(boolean hasConditioner) {
        this.hasConditioner = hasConditioner;
    }

    public int getDoorsCount() {
        return doorsCount;
    }

    public void setDoorsCount(int doorsCount) {
        this.doorsCount = doorsCount;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CarFormBean{");
        sb.append("model='").append(model).append('\'');
        sb.append(", price=").append(price);
        sb.append(", doorsCount=").append(doorsCount);
        sb.append(", hasConditioner=").append(hasConditioner);
        sb.append(", bigLuggageCount=").append(bigLuggageCount);
        sb.append(", smallLuggageCount=").append(smallLuggageCount);
        sb.append(", sitsCount=").append(sitsCount);
        sb.append(", photoPath='").append(photoPath).append('\'');
        sb.append(", brandName='").append(brandName).append('\'');
        sb.append(", colorName='").append(colorName).append('\'');
        sb.append(", className='").append(className).append('\'');
        sb.append(", statusName='").append(statusName).append('\'');
        sb.append('}');
        return sb.toString();
    }

}