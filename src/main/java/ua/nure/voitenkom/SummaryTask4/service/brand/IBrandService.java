package ua.nure.voitenkom.SummaryTask4.service.brand;

import ua.nure.voitenkom.SummaryTask4.db.entity.Brand;

import java.util.List;

public interface IBrandService {

    List<Brand> getAll();

    Brand selectById(int id);

    Brand selectByName(String name);

}
