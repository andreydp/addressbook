package ua.dp.levelup.addressbook.dao.impl;


import ua.dp.levelup.addressbook.dao.DataProvider;

import java.io.File;
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
//        if (dataMap.containsKey(fileName))
//        {
//            return dataMap.get(fileName);
//        }
//        RandomAccessFile randomAccessFile = new RandomAccessFile(fileName, READ_WRITE_ACCESS);
//        dataMap.put(fileName, randomAccessFile);
//        return randomAccessFile;
        return dataMap.get(fileName);
    }

    @Override
    public void openConnection()
    {
        try
        {
            for (String fileName : files.toString().split(";"))
            {
                String path = directoryPath == null || directoryPath.isEmpty()
                        ? fileName
                        : directoryPath + File.separator + fileName;
                File file = new File(path);
                if (!file.exists()) file.createNewFile();
                dataMap.put(fileName, new RandomAccessFile(file, READ_WRITE_ACCESS));
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void closeConnection()
    {
        try
        {
            for (RandomAccessFile dataFile : dataMap.values())
            {
                dataFile.close();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void appendFile(final String fileName)
    {
        files.append(fileName + ";");
    }
}
