package ua.nure.voitenkom.SummaryTask4.db.entity;

/**
 * @author MariiaVoitenko
 */
public class Car extends Entity {

    private String model;
    private int price;
    private int doorsCount;
    private boolean hasConditioner;
    private int bigLuggageCount;
    private int smallLuggageCount;
    private int sitsCount;
    private String photoPath;
    private int classTypeId;
    private int colorId;
    private int statusId;
    private int brandId;

    public Car(int id, String model, int price, int doorsCount,
               boolean hasConditioner, int bigLuggageCount,
               int smallLuggageCount, int sitsCount,
               int classTypeId, int colorId, int statusId, int brandId,
               String photoPath
    ) {
        this.setId(id);
        this.model = model;
        this.price = price;
        this.doorsCount = doorsCount;
        this.hasConditioner = hasConditioner;
        this.bigLuggageCount = bigLuggageCount;
        this.smallLuggageCount = smallLuggageCount;
        this.sitsCount = sitsCount;
        this.photoPath = photoPath;
        this.colorId = colorId;
        this.classTypeId = classTypeId;
        this.statusId = statusId;
        this.brandId = brandId;
    }

    public Car() {

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Car{");
        sb.append("id='").append(this.getId()).append('\'');
        sb.append("model='").append(model).append('\'');
        sb.append(", price=").append(price);
        sb.append(", doorsCount=").append(doorsCount);
        sb.append(", hasConditioner=").append(hasConditioner);
        sb.append(", bigLuggageCount=").append(bigLuggageCount);
        sb.append(", smallLuggageCount=").append(smallLuggageCount);
        sb.append(", sitsCount=").append(sitsCount);
        sb.append(", photoPath='").append(photoPath).append('\'');
        sb.append(", classTypeId=").append(classTypeId);
        sb.append(", colorId=").append(colorId);
        sb.append(", statusId=").append(statusId);
        sb.append(", brandId=").append(brandId);

        sb.append('}');
        return sb.toString();
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

    public int getClassTypeId() {
        return classTypeId;
    }

    public void setClassTypeId(int classTypeId) {
        this.classTypeId = classTypeId;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
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

}
