package com.levelup.dao;

import com.levelup.entity.Entity;
import com.levelup.utils.XMLParser;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by java on 28.02.2017.
 */
public abstract class AbstractXMLDAO<T extends Entity> extends AbstractFileDAO<T>
{

    private static final Logger LOG = Logger.getLogger(AbstractXMLDAO.class.getName());

    private final String header;
    private final String footer;
    protected final XMLParser parser = new XMLParser();
    private final Class clazz;

    public AbstractXMLDAO(DataProvider fileDataProvider, String fileName, String header, String footer, Class clazz)
    {
        super(fileDataProvider, fileName);
        this.header = header;
        this.footer = footer;
        this.clazz = clazz;
    }

    protected abstract T parseEntity(final String str);

    public abstract String viewEntity(T entity);

    @Override
    public void create(final T t)
    {
        try
        {
            RandomAccessFile file = getDataFile();
            if ((t.getId() == null) || (t.getId() == 0L))
            {
                t.setId(getNextId());
            }
            if (file.length() == 0)
            {
                file.write((header + LINE_SEPARATOR).getBytes());
                file.write((viewEntity(t) + LINE_SEPARATOR).getBytes());
                file.write((footer).getBytes());
            } else
            {
                file.seek(file.length() - (LINE_SEPARATOR + footer).length());
                if (file.length() != 20) //Empty file with header and footer
                {
                    file.write(",".getBytes());
                }
                file.write(LINE_SEPARATOR.getBytes());
                file.write((viewEntity(t) + LINE_SEPARATOR).getBytes());
                file.write((footer).getBytes());
            }
        } catch (IOException ex)
        {
            LOG.log(Level.INFO, "create entity error", ex);
        }
    }

    @Override
    public ArrayList<T> read()
    {
        ArrayList<T> result = new ArrayList<>();
        try
        {
            RandomAccessFile file = getDataFile();
            file.seek(0);
            String str;

            int position = header.length() + LINE_SEPARATOR.length();
            file.seek(position);
            // read lines till the end of the stream

            StringBuilder builder = new StringBuilder();
            while ((str = file.readLine()) != null)
            {
                if (str.contains(String.format("</%S>", clazz.getSigners())))
                {
                    result.add(parseEntity(str));
                    builder = new StringBuilder();

                }
            }
        } catch (IOException e)
        {
            System.out.println("Error get info from file XML (Street)");
        }
        return result;
    }

    @Override
    public void update(final T t)
    {
        try
        {
            RandomAccessFile file = getDataFile();
            String buffer = "";
            file.seek(0);
            String str;
            int[] startAndEndOfStr = getStartAndEndOfStr(file, t);
            int start = startAndEndOfStr[0];
            int end = startAndEndOfStr[1];
            file.seek(end);
            while ((str = file.readLine()) != null)
            {
                buffer += str + LINE_SEPARATOR;
            }
            file.seek(start);
            String s = viewEntity(t);
            s += (end + LINE_SEPARATOR.length() + footer.length()) < file.length() ? "," + LINE_SEPARATOR : LINE_SEPARATOR;
            file.write(s.getBytes());
            file.write(buffer.getBytes());
            file.setLength(start + s.length() + buffer.length() - 1);
        } catch (IOException e)
        {
            System.out.println("Error get info from file JSON (Street)");
        }
    }

    @Override
    public void delete(final T t)
    {
        try
        {
            RandomAccessFile file = getDataFile();
            String buffer = "";
            file.seek(0);
            String str;
            int startAndEndOfStr[] = getStartAndEndOfStr(file, t);
            int start = startAndEndOfStr[0];
            int end = startAndEndOfStr[1];
            file.seek(end);
            int count = 0;
            while ((str = file.readLine()) != null)
            {
                if (str.startsWith(footer) && count == 0)
                {
                    if (file.length() - (end - start) == (header.length() + footer.length() + "\t".length()))
                    {
                        file.setLength(0); // It was the last entry, clear the file
                        return;
                    }
                    start = start - LINE_SEPARATOR.length() - ",".length();
                    buffer += LINE_SEPARATOR;
                }
                buffer += str + LINE_SEPARATOR;
                count++;
            }
            file.seek(start);
            file.write(buffer.getBytes());
            file.setLength(start + buffer.length() - LINE_SEPARATOR.length());
        } catch (IOException e)
        {
            System.out.println("Error get info from file JSON (Street)");
        }
    }

    @Override
    public T getOneById(final long id)
    {
        T t = null;
        String str;
        try
        {
            RandomAccessFile file = getDataFile();
            while ((str = file.readLine()) != null)
            {
                if (!str.equals(header) && !str.equals(footer))
                {
                    t = parseEntity(str);
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    protected long initMaxId()
    {
        long maxId = 0;
        try
        {
            RandomAccessFile file = getDataFile();
            file.seek(0);
            String str;
            while ((str = file.readLine()) != null)
            {
                if (!str.equals(header) && !str.equals(footer))
                {
                    long id = parseEntity(str).getId();
                    if (maxId < id) maxId = id;
                }
            }
        } catch (IOException e)
        {
            LOG.log(Level.INFO, "error during initialization id", e);
        }
        return maxId;
    }

    public int[] getStartAndEndOfStr(RandomAccessFile file, T t) throws IOException
    {
        int[] arr = new int[2];
        int start = 0;
        int end = 0;
        boolean found = false;
        String str;
        while ((str = file.readLine()) != null && !found)
        {
            if (!str.equals(header) && !str.equals(footer))
            {
                if (t.getId().equals(parseEntity(str).getId()))
                {
                    found = true;
                }
            }
            if (!found)
            {
                start += str.length() + LINE_SEPARATOR.length();
                arr[0] = start;
            } else
            {
                end = start + str.length() + LINE_SEPARATOR.length();
                arr[1] = end;
            }
        }
        return arr;
    }
}
