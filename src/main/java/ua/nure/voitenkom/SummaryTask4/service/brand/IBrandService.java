package ua.nure.voitenkom.SummaryTask4.service.brand;

import ua.nure.voitenkom.SummaryTask4.db.entity.Brand;
import ua.nure.voitenkom.SummaryTask4.db.entity.SimpleEntity;

import java.util.List;

/**
 * Created by Maria on 05.08.2015.
 */
public interface IBrandService {

    List<Brand> getAll();

    Brand selectById(int id);

    int selectByName(String name);

}
