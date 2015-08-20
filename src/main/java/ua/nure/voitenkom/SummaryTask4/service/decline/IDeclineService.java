package ua.nure.voitenkom.SummaryTask4.service.decline;

import ua.nure.voitenkom.SummaryTask4.db.entity.Decline;
import java.util.List;

public interface IDeclineService {

    Decline selectById(int id);

    List<Decline> getAll();
}
