package ua.dp.levelup.addressbook.dao.impl;


import ua.dp.levelup.addressbook.dao.DataProvider;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by java on 24.02.2017.
 */
public class FileDataProvider implements DataProvider
{
    private static final String READ_WRITE_ACCESS = "rw";
    private final String directoryPath;
    private Map<String, RandomAccessFile> dataMap = new HashMap<>();
    private StringBuilder files = new StringBuilder();

    public FileDataProvider(String directoryPath)
    {
        this.directoryPath = directoryPath;
    }

    public RandomAccessFile getDataFile(String fileName) throws IOException
    {
        if (dataMap.containsKey(fileName))
        {
            return dataMap.get(fileName);
        }
//        File file = new File(directoryPath + File.pathSeparator + fileName);
//        file.createNewFile();
        RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, READ_WRITE_ACCESS);
        dataMap.put(fileName, randomAccessFile);
        return randomAccessFile;
    }

    @Override
    public void openConnection()
    {

    }

    @Override
    public void closeConnection()
    {

    }

    public void appendFile(final String fileName)
    {
        files.append(fileName + ";");
    }
}
