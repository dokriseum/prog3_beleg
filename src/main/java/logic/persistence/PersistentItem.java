package logic.persistence;

import logic.BusinessLogic;

import java.io.*;
import java.util.Collection;
import java.util.LinkedList;

public class PersistentItem {
    private int id;
    private double aDouble;
    private char aChar;

    public static void save(String filename, Collection<BusinessLogic> items) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(filename))) {
            for (BusinessLogic i : items) {
                //dos.write;//4 bytes
                //dos.writeDouble(i.aDouble);//8 bytes
                //dos.writeChar(i.aChar);//2 bytes
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Collection<PersistentItem> load(String filename) {
        LinkedList<PersistentItem> r = new LinkedList<>();
        try (DataInputStream dis = new DataInputStream(new FileInputStream(filename))) {
            while (dis.available() >= 14) {
                PersistentItem i = new PersistentItem();
                i.id = dis.readInt();
                i.aDouble = dis.readDouble();
                i.aChar = dis.readChar();
                r.add(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return r;
    }

    public static PersistentItem semiRandomAccessLoad(String filename, int id) {
        try (RandomAccessFile ras = new RandomAccessFile(filename, "r")) {
            ras.seek(0);
            while (ras.getFilePointer() <= ras.length() - 14) {
                int cid = ras.readInt();
                if (id == cid) {
                    PersistentItem i = new PersistentItem();
                    i.id = cid;
                    i.aDouble = ras.readDouble();
                    i.aChar = ras.readChar();
                    return i;
                }
                ras.skipBytes(10);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PersistentItem randomAccessLoad(String filename, int id) {
        try (RandomAccessFile ras = new RandomAccessFile(filename, "r")) {
            ras.seek((id - 1) * 14);
            PersistentItem i = new PersistentItem();
            i.id = ras.readInt();
            i.aDouble = ras.readDouble();
            i.aChar = ras.readChar();
            return i;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
