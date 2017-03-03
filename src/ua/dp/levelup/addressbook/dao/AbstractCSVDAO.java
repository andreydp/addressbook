package ua.dp.levelup.addressbook.dao;

import ua.dp.levelup.addressbook.dao.impl.FileDataProvider;
import ua.dp.levelup.addressbook.entity.Entity;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 * Created by andrey on 27.02.17.
 */
public abstract class AbstractCSVDAO<T extends Entity> extends AbstractFileDAO<T>
{
    private final String HEADER_CSV;
    private long maxId;

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
                if (line.startsWith(HEADER_CSV)) continue;
                list.add(parseEntity(line));
            }
        } catch (IOException e)
        {
            System.out.println("There was an error reading from file = " + this.getFileName());
            e.printStackTrace();
        }
        return list;
    }

    public long getMaxId() throws IOException
    {
        if (maxId == 0)
        {
            RandomAccessFile file = getDataFile();
            file.seek(HEADER_CSV.length() + 1);
            for (String line; (line = file.readLine()) != null; )
            {
                long currentId = parseEntity(line).getId();
                maxId = currentId > maxId ? currentId : maxId;
            }
        }
        return maxId++;
    }

    public void update(final T t)
    {
        try
        {
            RandomAccessFile file = getDataFile();
            file.seek(HEADER_CSV.length() + 1);
            for (String line; (line = file.readLine()) != null; )
            {
                if (line.startsWith(t.getId().toString() + ";"))
                {
                    StringBuilder sb = new StringBuilder();
                    int length = line.length() + 1;
                    String updatedLine = viewEntity(t);
                    int offset = updatedLine.length() - line.length();
                    sb.append(updatedLine).append("\n");
                    long writePos = file.getFilePointer() - length;
                    while ((line = file.readLine()) != null)
                    {
                        sb.append(line).append("\n");
                    }
                    file.seek(writePos);
                    file.write(sb.toString().getBytes());
                    if (offset < 0)
                    {
                        file.setLength(file.length() + offset);
                    }
                    break;
                }
            }
        } catch (IOException e)
        {
            System.out.println("There was an error updating record in file!");
            e.printStackTrace();
        }
    }

    public void delete(final T t)
    {
        try
        {
            RandomAccessFile file = getDataFile();
            file.seek(HEADER_CSV.length());
            for (String line; (line = file.readLine()) != null; )
            {

                if (line.startsWith(t.getId().toString() + ";"))
                {
                    //                    byte[] buffer = new byte[4096];
                    //                    long length = line.length() + 1;
                    //                    int read;
                    //                    while ((read = file.read(buffer)) > -1)
                    //                    {
                    //                        file.seek(file.getFilePointer() - read - length);
                    //                        file.write(buffer, 0, read);
                    //                        file.seek(file.getFilePointer() + length);
                    //                    }
                    //                    file.setLength(file.length() - length);
                    //                    break;

                    StringBuilder sb = new StringBuilder();
                    int length = line.length() + 1;
                    long writePos = file.getFilePointer() - length;
                    while ((line = file.readLine()) != null)
                    {
                        sb.append(line).append("\n");
                    }
                    file.seek(writePos);
                    file.write(sb.toString().getBytes());
                    file.setLength(file.length() - length);
                    break;
                }
            }

        } catch (IOException e)
        {
            System.out.printf("There was an error deleting from cxv file!");
            e.printStackTrace();
        }
    }

    public T getOneById(final long id)
    {
        try
        {
            RandomAccessFile file = getDataFile();
            file.seek(HEADER_CSV.length() + 1);
            for (String line; (line = file.readLine()) != null; )
            {
                if (line.startsWith(id + ";"))
                {
                    return parseEntity(line);
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public abstract String viewEntity(T t);

    protected abstract T parseEntity(final String str);

    public long[] getStartAndEndOfStr(RandomAccessFile file, T t)
    {
        long[] result = new long[2];
        try
        {
            file.seek(HEADER_CSV.length());
            for (String line; (line = file.readLine()) != null; )
            {
                if (line.startsWith(t.getId() + ";"))
                {
                    result[0] = file.getFilePointer() - line.length();
                    result[1] = file.getFilePointer();
                    break;
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
