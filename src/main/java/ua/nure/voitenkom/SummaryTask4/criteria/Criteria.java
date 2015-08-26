package ua.nure.voitenkom.SummaryTask4.criteria;

/**
 * @author Mariia Voitenko
 *         <p>
 *         Class for building SQL query with parameters to sort cars on pages. Contains brand column as brand field in car table
 *         if brand sorting needed, brand id value to put in SQL, class column as classType field, class id value, sortType to
 *         sort records ascending or descending and sort column that can be either price or model. All parameters don't need to
 *         be set.
 */
public class Criteria {

    private String brand;
    private int brandValue;
    private String classType;
    private int classValue;
    private String sortType;
    private String sortColumn;

    public int getBrandValue() {
        return brandValue;
    }

    public void setBrandValue(int brandValue) {
        this.brandValue = brandValue;
    }

    public int getClassValue() {
        return classValue;
    }

    public void setClassValue(int classValue) {
        this.classValue = classValue;
    }

    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    @Override
    public String toString() {
        final StringBuilder sb;
        sb = new StringBuilder("Criteria{");
        sb.append("brand='").append(brand).append('\'');
        sb.append(", brandValue=").append(brandValue);
        sb.append(", classType='").append(classType).append('\'');
        sb.append(", classValue=").append(classValue);
        sb.append(", sortType='").append(sortType).append('\'');
        sb.append(", sortColumn='").append(sortColumn).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
