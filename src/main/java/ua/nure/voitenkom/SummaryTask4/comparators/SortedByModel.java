package ua.nure.voitenkom.SummaryTask4.comparators;


import ua.nure.voitenkom.SummaryTask4.formbean.CarFormBean;

import java.util.Comparator;

public class SortedByModel implements Comparator<CarFormBean> {

    @Override
    public int compare(CarFormBean o1, CarFormBean o2) {
        String model1 = o1.getModel();
        String model2 = o2.getModel();
        return model1.compareTo(model2);
    }

}
