package ua.nure.voitenkom.SummaryTask4.formbean;

/**
 * Special class to show data from variable tables of database
 *
 * @author Mariia Voitenko
 */
public class HistoryFormBean {

    private String model;
    private String brandName;
    private String photoPath;
    private int rentId;
    private int doorsCount;
    private int sitsCount;
    private int bigLuggageCount;
    private int smallLuggageCount;
    private boolean hasConditioner;
    private int checkSum;
    private boolean isPayed;
    private String startDate;
    private String endDate;
    private boolean isDriven;
    private String className;
    private String colorName;
    private boolean isFinished;
    private boolean isApproved;
    private boolean isReturned;
    private String declineName;

    public HistoryFormBean(int bigLuggageCount, String brandName, int checkSum, String className, String colorName,
                           int doorsCount, boolean hasConditioner, String endDate, boolean isApproved, boolean isDriven,
                           boolean isFinished, boolean isPayed, boolean isReturned, String photoPath, String model,
                           int sitsCount, int rentId, int smallLuggageCount, String startDate, String declineName) {
        this.bigLuggageCount = bigLuggageCount;
        this.brandName = brandName;
        this.checkSum = checkSum;
        this.className = className;
        this.colorName = colorName;
        this.doorsCount = doorsCount;
        this.hasConditioner = hasConditioner;
        this.endDate = endDate;
        this.isApproved = isApproved;
        this.isDriven = isDriven;
        this.isFinished = isFinished;
        this.isPayed = isPayed;
        this.isReturned = isReturned;
        this.photoPath = photoPath;
        this.model = model;
        this.sitsCount = sitsCount;
        this.rentId = rentId;
        this.smallLuggageCount = smallLuggageCount;
        this.startDate = startDate;
        this.declineName = declineName;

    }

    public String getDeclineName() {
        return declineName;
    }

    public void setDeclineName(String declineName) {
        this.declineName = declineName;
    }

    public int getBigLuggageCount() {
        return bigLuggageCount;
    }

    public void setBigLuggageCount(int bigLuggageCount) {
        this.bigLuggageCount = bigLuggageCount;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getDoorsCount() {
        return doorsCount;
    }

    public void setDoorsCount(int doorsCount) {
        this.doorsCount = doorsCount;
    }

    public boolean isHasConditioner() {
        return hasConditioner;
    }

    public void setHasConditioner(boolean hasConditioner) {
        this.hasConditioner = hasConditioner;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public boolean isDriven() {
        return isDriven;
    }

    public void setIsDriven(boolean isDriven) {
        this.isDriven = isDriven;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    public boolean isPayed() {
        return isPayed;
    }

    public void setIsPayed(boolean isPayed) {
        this.isPayed = isPayed;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setIsReturned(boolean isReturned) {
        this.isReturned = isReturned;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HistoryFormBean{");
        sb.append("bigLuggageCount=").append(bigLuggageCount);
        sb.append(", model='").append(model).append('\'');
        sb.append(", brandName='").append(brandName).append('\'');
        sb.append(", photoPath='").append(photoPath).append('\'');
        sb.append(", rentId=").append(rentId);
        sb.append(", doorsCount=").append(doorsCount);
        sb.append(", sitsCount=").append(sitsCount);
        sb.append(", smallLuggageCount=").append(smallLuggageCount);
        sb.append(", hasConditioner=").append(hasConditioner);
        sb.append(", checkSum=").append(checkSum);
        sb.append(", isPayed=").append(isPayed);
        sb.append(", startDate='").append(startDate).append('\'');
        sb.append(", endDate='").append(endDate).append('\'');
        sb.append(", isDriven=").append(isDriven);
        sb.append(", className='").append(className).append('\'');
        sb.append(", colorName='").append(colorName).append('\'');
        sb.append(", isFinished=").append(isFinished);
        sb.append(", isApproved=").append(isApproved);
        sb.append(", isReturned=").append(isReturned);
        sb.append(", declineName='").append(declineName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
