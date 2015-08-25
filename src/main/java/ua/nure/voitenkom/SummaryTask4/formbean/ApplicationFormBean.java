package ua.nure.voitenkom.SummaryTask4.formbean;

public class ApplicationFormBean {

    private String model;
    private String brandName;
    private String photoPath;
    private int carId;
    private int rentId;
    private int doorsCount;
    private int sitsCount;
    private int bigLuggageCount;
    private int smallLuggageCount;
    private boolean hasConditioner;
    private int checkSum;
    private String userName;
    private String passportNumber;
    private String startDate;
    private String endDate;
    private boolean isDriven;
    private String className;
    private String colorName;
    private int userId;

    public ApplicationFormBean(int userId, String userName, String startDate, int smallLuggageCount, int sitsCount, int rentId,
                               String photoPath, String model, String passportNumber, boolean isDriven,
                               boolean hasConditioner, String endDate, int doorsCount, String colorName, String className,
                               int checkSum, int carId, int bigLuggageCount, String brandName) {
        this.userName = userName;
        this.userId = userId;
        this.startDate = startDate;
        this.smallLuggageCount = smallLuggageCount;
        this.sitsCount = sitsCount;
        this.rentId = rentId;
        this.photoPath = photoPath;
        this.model = model;
        this.passportNumber = passportNumber;
        this.isDriven = isDriven;
        this.hasConditioner = hasConditioner;
        this.endDate = endDate;
        this.doorsCount = doorsCount;
        this.colorName = colorName;
        this.className = className;
        this.checkSum = checkSum;
        this.carId = carId;
        this.bigLuggageCount = bigLuggageCount;
        this.brandName = brandName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getBigLuggageCount() {
        return bigLuggageCount;
    }

    public void setBigLuggageCount(int bigLuggageCount) {
        this.bigLuggageCount = bigLuggageCount;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(int checkSum) {
        this.checkSum = checkSum;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public int getDoorsCount() {
        return doorsCount;
    }

    public void setDoorsCount(int doorsCount) {
        this.doorsCount = doorsCount;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isHasConditioner() {
        return hasConditioner;
    }

    public void setHasConditioner(boolean hasConditioner) {
        this.hasConditioner = hasConditioner;
    }

    public boolean isDriven() {
        return isDriven;
    }

    public void setIsDriven(boolean isDriven) {
        this.isDriven = isDriven;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public int getRentId() {
        return rentId;
    }

    public void setRentId(int rentId) {
        this.rentId = rentId;
    }

    public int getSitsCount() {
        return sitsCount;
    }

    public void setSitsCount(int sitsCount) {
        this.sitsCount = sitsCount;
    }

    public int getSmallLuggageCount() {
        return smallLuggageCount;
    }

    public void setSmallLuggageCount(int smallLuggageCount) {
        this.smallLuggageCount = smallLuggageCount;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
