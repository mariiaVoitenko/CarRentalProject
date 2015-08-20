package ua.nure.voitenkom.SummaryTask4.service.status;

import ua.nure.voitenkom.SummaryTask4.db.entity.Status;

import java.util.List;

public interface IStatusService {

    List<Status> getAll();

    Status selectById(int id);

    Status selectByName(String name);
}
