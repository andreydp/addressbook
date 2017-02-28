package ua.dp.levelup.addressbook.dao;

import ua.dp.levelup.addressbook.dao.impl.FileDataProvider;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by andrey on 27.02.17.
 */
public abstract class AbstractFileDAO<T> implements DAO<T>
{
    private Long id;
    protected final FileDataProvider fileDataProvider;
    private String fileName;

    public AbstractFileDAO(FileDataProvider fileDataProvider, String fileName)
    {
        this.fileDataProvider = fileDataProvider;
        this.fileName = fileName;
        fileDataProvider.appendFile(fileName);
    }

    public RandomAccessFile getDataFile() throws IOException
    {
        return fileDataProvider.getDataFile(fileName);
    }

    public Long getId()
    {
        return id;
    }

    public String getFileName()
    {
        return fileName;
    }
}
