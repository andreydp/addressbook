package com.levelup.dao;

import com.levelup.dao.impl.FileDataProviderImpl;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by andrey on 27.02.17.
 */
public abstract class AbstractFileDAO<T> implements DAO<T> {

    private Long id;
    protected final FileDataProviderImpl fileDataProvider;
    private String fileName;

    public AbstractFileDAO(DataProvider fileDataProvider, String fileName) {
        this.fileDataProvider = (FileDataProviderImpl) fileDataProvider;
        this.fileName = fileName;
        this.fileDataProvider.appendFile(fileName);
    }

    public RandomAccessFile getDataFile() throws IOException {
        return fileDataProvider.getDataFile(fileName);
    }

    public String getFileName() {
        return fileName;
    }

    protected long getNextId() {
        if (null == id) id = initMaxId();
        return ++id;
    }

    protected abstract long initMaxId();
}
