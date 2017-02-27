package ua.dp.levelup.addressbook.dao;


import ua.dp.levelup.addressbook.dao.impl.FileDataProvider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Created by andrey on 27.02.17.
 */
public abstract class AbstractCSVDAO<T> extends AbstractFileDAO<T>
{
    private final String HEADER_CSV;

    protected AbstractCSVDAO(final FileDataProvider fileDataProvider,

                             String fileName, final String HEADER_CSV)
    {

        super(fileDataProvider, fileName);

        this.HEADER_CSV = HEADER_CSV;
    }

    @Override
    public void create(final T t) throws FileNotFoundException
    {
        this.getDataFile();
    }

    public ArrayList<T> read() throws IOException
    {
        ArrayList<T> list = new ArrayList<T>();
        RandomAccessFile file = this.getDataFile();

        String line;
        while ((line = file.readLine()) != null && !line.startsWith(HEADER_CSV))
        {

        }


        return list;
    }

    public void update(final T t)
    {
        return;
    }

    public void delete(final T t)
    {
        return;
    }

    public T getOneById(final long id)
    {
        return null;
    }

    public abstract String viewEntity(T t);

    protected abstract T parseEntity(final String str);

    public int[] getStartAndEndOfStr(RandomAccessFile file, T t)
    {
        int[] result = null;
        return result;
    }
}
