package services;

import model.Column;

import java.util.List;

public interface WriteService {
    void saveAll(List<Column> columns);
}
