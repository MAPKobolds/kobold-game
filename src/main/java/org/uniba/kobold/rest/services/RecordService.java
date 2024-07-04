package org.uniba.kobold.rest.services;

import org.uniba.kobold.rest.CRUDInterface;
import org.uniba.kobold.rest.models.Record;
import org.uniba.kobold.rest.repositories.RecordRepository;

import java.util.List;

public class RecordService implements CRUDInterface<Record> {

    RecordRepository recordRepository = new RecordRepository();

    @Override
    public Record save(Record entity) {
        return recordRepository.save(entity);
    }

    @Override
    public int deleteById(int id) {
        return recordRepository.deleteById(id);
    }

    @Override
    public Record updateById(Record entity, int id) {
        return recordRepository.updateById(entity, id);
    }

    @Override
    public Record getById(int id) {
        return recordRepository.getById(id);
    }

    public List<Record> getBest(int limit) {
        if(limit <= 0) {
            throw new  Error("Invalid limit");
        }

        return recordRepository.getBestTime(limit);
    }
}
