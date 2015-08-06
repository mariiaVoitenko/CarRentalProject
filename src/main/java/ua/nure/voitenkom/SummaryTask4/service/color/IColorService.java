package ua.nure.voitenkom.SummaryTask4.service.color;

import ua.nure.voitenkom.SummaryTask4.db.entity.Brand;
import ua.nure.voitenkom.SummaryTask4.db.entity.Color;

import java.util.List;

/**
 * Created by Maria on 06.08.2015.
 */
public interface IColorService {

    List<Color> getAll();

    Color selectById(int id);

    int selectByName(String name);

}
