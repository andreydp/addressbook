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
public abstract class AbstractXMLDAO<T extends Entity> extends AbstractFileDAO<T> {

    private static final Logger LOG = Logger.getLogger(AbstractXMLDAO.class.getName());

    private final String header;
    private final String footer;
    protected final XMLParser parser = new XMLParser();
    private final Class clazz;

    public AbstractXMLDAO(DataProvider fileDataProvider, String fileName, String header, String footer, Class clazz) {
        super(fileDataProvider, fileName);
        this.header = header;
        this.footer = footer;
        this.clazz = clazz;
    }

    protected abstract T parseEntity(final String str);

    public abstract String viewEntity(T entity);

    @Override
    public void create(final T t) {
        try {
            RandomAccessFile file = getDataFile();
            if ((t.getId() == null) || (t.getId() == 0L)) {
                t.setId(getNextId());
            }
            if (file.length() == 0) {
                file.write((header + "\r\n").getBytes());
                file.write((viewEntity(t) + "\r\n").getBytes());
                file.write((footer).getBytes());
            } else {
                file.seek(file.length() - ("\r\n" + footer).length());
                file.write(",\r\n".getBytes());
                file.write((viewEntity(t) + "\r\n").getBytes());
                file.write((footer).getBytes());
            }
        } catch (IOException ex) {
            LOG.log(Level.INFO, "create entity error", ex);
        }
    }

    @Override
    public ArrayList<T> read() {
        ArrayList<T> result = new ArrayList();
        try {
            RandomAccessFile file = getDataFile();
            file.seek(0);
            String str;

            int position = header.length() + 2;
            file.seek(position);
            // read lines till the end of the stream
            StringBuilder builder = new StringBuilder();
            while ((str = file.readLine()) != null) {
                if(str.contains(String.format("</%s>", clazz.getSimpleName()))) {
                    result.add(parseEntity(builder.toString()));
                    builder = new StringBuilder();
                }
                builder.append(str).append("\r\n");
            }
        } catch (IOException e) {
            System.out.println("Error get info from file JSON (Street)");
        }
        return result;
    }

    @Override
    public void update(final T t) {
        try {
            RandomAccessFile file = getDataFile();
            String buffer = "";
            file.seek(0);
            String str;
            int[] startAndEndOfStr = getStartAndEndOfStr(file, t);
            int start = startAndEndOfStr[0];
            int end = startAndEndOfStr[1];
            file.seek(end);
            while ((str = file.readLine()) != null) {
                buffer += str + "\n";
            }
            file.seek(start);
            String s = viewEntity(t);
            s += (end + 1) < file.length() ? "\n" : "\n";
            file.write(s.getBytes());
            file.write(buffer.getBytes());
            file.setLength(start + s.length() + buffer.length() - 1);
        } catch (IOException e) {
            System.out.println("Error get info from file JSON (Street)");
        }
    }

    @Override
    public void delete(final T t) {
        try {
            RandomAccessFile file = getDataFile();
            String buffer = "";
            file.seek(0);
            String str;
            int startAndEndOfStr[] = getStartAndEndOfStr(file, t);
            int start = startAndEndOfStr[0];
            int end = startAndEndOfStr[1];
            file.seek(end);
            while ((str = file.readLine()) != null) {
                buffer += str + "\n";
            }
            file.seek(start);
            file.write(buffer.getBytes());
            file.setLength(start + buffer.length() - 1);
        } catch (IOException e) {
            System.out.println("Error get info from file JSON (Street)");
        }
    }

    @Override
    public T getOneById(final long id) {
        T t = null;
        String str;
        try {
            RandomAccessFile file = getDataFile();
            while ((str = file.readLine()) != null) {
                if (str.contains("" + id)) {
                    t = parseEntity(str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    protected long initMaxId() {
        long maxId = 0;
        try {
            RandomAccessFile file = getDataFile();
            file.seek(0);
            String str = "";
            while ((str = file.readLine()) != null) {
                if (!str.contains("id")) {
                    long id = Long.parseLong(str.split(";")[0]);
                    if (maxId < id) maxId = id;
                }
            }
        } catch (IOException e) {
            LOG.log(Level.INFO, "error during initialization id", e);
        }
        return maxId;
    }

    public int[] getStartAndEndOfStr(RandomAccessFile file, T t) throws IOException {
        int[] arr = new int[2];
        int start = 0;
        int end = 0;
        boolean found = false;
        String str = "";
        while ((str = file.readLine()) != null && !found) {
            if (str.startsWith("" + t.getId() + ";")) {
                found = true;
            }
            if (!found) {
                start += str.length() + 1;
                arr[0] = start;
            } else {
                end = start + str.length() + 1;
                arr[1] = end;
            }
        } return arr;
    }
}
