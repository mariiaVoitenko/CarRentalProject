package ua.nure.voitenkom.SummaryTask4.comparators;

import ua.nure.voitenkom.SummaryTask4.formbean.CarFormBean;

import java.util.Comparator;

public class SortedByPrice implements Comparator<CarFormBean> {

    @Override
    public int compare(CarFormBean o1, CarFormBean o2) {
        int price1 = o1.getPrice();
        int price2 = o2.getPrice();
        if (price1 > price2) {
            return 1;
        } else if (price1 < price2) {
            return -1;
        } else {
            return 0;
        }
    }

}
