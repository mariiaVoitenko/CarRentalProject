package ua.nure.voitenkom.SummaryTask4.service.color;

import ua.nure.voitenkom.SummaryTask4.db.entity.Brand;
import ua.nure.voitenkom.SummaryTask4.db.entity.Color;

import java.util.List;

public interface IColorService {

    List<Color> getAll();

    Color selectById(int id);

    int selectByName(String name);

}
