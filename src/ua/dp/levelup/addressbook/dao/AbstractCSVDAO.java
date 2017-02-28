package ua.dp.levelup.addressbook.dao;

import ua.dp.levelup.addressbook.dao.impl.FileDataProvider;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Created by andrey on 27.02.17.
 */
public abstract class AbstractCSVDAO<T> extends AbstractFileDAO<T>
{
    private final String HEADER_CSV;

    protected AbstractCSVDAO(final FileDataProvider fileDataProvider, String fileName, final String HEADER_CSV)
    {
        super(fileDataProvider, fileName);
        this.HEADER_CSV = HEADER_CSV;
    }

    @Override
    public void create(final T t)
    {
        try
        {
            RandomAccessFile file = this.getDataFile();
            if (file.length() == 0) file.writeBytes(HEADER_CSV);
            file.seek(file.length());
            file.writeBytes("\n");
            file.writeBytes(viewEntity(t));
        } catch (IOException e)
        {
            System.out.println("There was an error creating/reading from file = " + this.getFileName());
            e.printStackTrace();
        }
    }

    public ArrayList<T> read()
    {
        ArrayList<T> list = new ArrayList<>();
        RandomAccessFile file = null;
        try
        {
            file = getDataFile();
            file.seek(0);
            for (String line; (line = file.readLine()) != null; )
            {
                if (line.startsWith(this.HEADER_CSV)) continue;
                list.add(parseEntity(line));
            }
        } catch (IOException e)
        {
            System.out.println("There was an error reading from file = " + this.getFileName());
            e.printStackTrace();
        }
        return list;
    }

    public void update(final T t)
    {
        return;
    }

    public void delete(final T t) throws IOException
    {
        RandomAccessFile file = getDataFile();
    }

    public T getOneById(final long id)
    {
        return null;
    }

    public abstract String viewEntity(T t);

    protected abstract T parseEntity(final String str);

    public int[] getStartAndEndOfStr(RandomAccessFile file, T t) throws IOException
    {
        int[] result = new int[2];
        String entityStr = viewEntity(t);
        for (String line; (line = file.readLine()) != null; )
        {
            if (line.equals(entityStr))
            {
                result[0] = (int) file.getFilePointer() - line.length();
                result[1] = (int) file.getFilePointer();
            }
        }
        return result;
    }
}
