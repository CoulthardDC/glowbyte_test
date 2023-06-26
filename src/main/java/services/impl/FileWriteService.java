package services.impl;

import model.Column;
import services.WriteService;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileWriteService implements WriteService {
    private final String fileName;

    public FileWriteService(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void saveAll(List<Column> columns) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            for(Column column : columns) {
                bufferedWriter.write(column.toString());
                bufferedWriter.newLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
