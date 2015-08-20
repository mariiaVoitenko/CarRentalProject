package ua.nure.voitenkom.SummaryTask4.service.majorityclass;

import ua.nure.voitenkom.SummaryTask4.db.entity.MajorityClass;
import java.util.List;

public interface IMajorityClassService {

    List<MajorityClass> getAll();

    MajorityClass selectById(int id);

    MajorityClass selectByName(String name);
}
