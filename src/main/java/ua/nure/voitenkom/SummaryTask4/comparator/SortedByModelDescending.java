package ua.nure.voitenkom.SummaryTask4.comparator;

import ua.nure.voitenkom.SummaryTask4.formbean.CarFormBean;

import java.util.Comparator;

public class SortedByModelDescending implements Comparator<CarFormBean> {

    @Override
    public int compare(CarFormBean o1, CarFormBean o2) {
        String model1 = o1.getModel();
        String model2 = o2.getModel();
        return model2.compareTo(model1);
    }

}