package com.levelup.dao.impl;

import com.levelup.dao.DataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by java on 24.02.2017.
 */
public class FileDataProviderImpl implements DataProvider {

    private static final String READ_WRITE_ACCESS = "rw";
    private Map<String, RandomAccessFile> dataMap = new HashMap<>();
    private StringBuilder files = new StringBuilder();
    private final String directoryPath;

    public FileDataProviderImpl() {
        this.directoryPath = "";
    }

    public FileDataProviderImpl(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public RandomAccessFile getDataFile(String fileName) throws FileNotFoundException {
        return dataMap.get(fileName);
    }


    @Override
    public void openConnection() {
        try {
            for (String fileName : files.toString().split(";")) {

                String path = directoryPath == null || directoryPath.isEmpty()
                        ? fileName
                        : directoryPath + File.separator + fileName;

                File file = new File(path);

                if (!file.exists()) file.createNewFile();
                dataMap.put(fileName, new RandomAccessFile(file, READ_WRITE_ACCESS));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeConnection() {
        try {
            for (RandomAccessFile dataFile : dataMap.values()) {
                dataFile.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendFile(final String fileName) {
        files.append(fileName + ";");
    }
}
