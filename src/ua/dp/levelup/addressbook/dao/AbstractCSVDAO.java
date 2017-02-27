package ua.dp.levelup.addressbook.dao;


import ua.dp.levelup.addressbook.dao.impl.FileDataProvider;

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
    public void create(final T t)
    {
        return;
    }

    public ArrayList<T> read()
    {
        ArrayList<T> list = new ArrayList<T>();
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
