package com.javarush.test.level33.lesson15.big01.strategies;

/**
 * Created by Sergey on 04.03.2017.
 */
public class FileStorageStrategy implements StorageStrategy
{
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    private long bucketSizeLimit = 10000;
    private int size;

    private int hash(Long k)
    {
        int h = k.hashCode();

        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    private static int indexFor(int hash, int length)
    {
        return hash & (length - 1);
    }

    private Entry getEntry(Long key)
    {
        int hash = hash(key);
        for (Entry e = table[indexFor(hash, table.length)].getEntry(); e != null; e = e.next)
        {
            Long k;
            if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
                return e;
        }
        return null;
    }

    private void resize(int newCapacity)
    {
        int MAXIMUM_CAPACITY = 1 << 30;

        FileBucket[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == MAXIMUM_CAPACITY)
        {
            return;
        }

        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
        table = newTable;
    }

    void transfer(FileBucket[] newTable)
    {
        for (int i = 0; i < table.length; i++)
        {
            if (table[i] == null) continue;
            Entry entry = table[i].getEntry();
            while (entry != null)
            {
                Entry next = entry.next;
                int newIndex = indexFor(entry.hash, newTable.length);
                if (newTable[newIndex] == null)
                {
                    entry.next = null;
                    newTable[newIndex] = new FileBucket();
                } else
                {
                    entry.next = newTable[newIndex].getEntry();
                }
                newTable[newIndex].putEntry(entry);
                entry = next;
            }
            table[i].remove();
        }
    }

    void addEntry(int hash, Long key, String value, int bucketIndex)
    {
        Entry entry = table[bucketIndex].getEntry();
        table[bucketIndex].putEntry(new Entry(hash, key, value, entry));
        size++;
        if (table[bucketIndex].getFileSize() > bucketSizeLimit) resize(2 * table.length);
    }

    private void createEntry(int hash, Long key, String value, int bucketIndex)
    {
        table[bucketIndex] = new FileBucket();
        table[bucketIndex].putEntry(new Entry(hash, key, value, null));
        size++;
    }

    @Override
    public boolean containsKey(Long key)
    {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value)
    {
        if (value == null)
            return false;
        for (FileBucket fb : table)
        {
            if (fb == null)
                continue;
            for (Entry e = fb.getEntry(); e != null; e = e.next)
                if (value.equals(e.getValue()))
                    return true;
        }

        return false;
    }

    @Override
    public void put(Long key, String value)
    {
        if (key == null)
            return;
        int hash = hash(key);
        int i = indexFor(hash, table.length);
        if (table[i] != null)
        {
            for (Entry e = table[i].getEntry(); e != null; e = e.next)
            {
                Long k;
                if (e.hash == hash && ((k = e.key) == key || key.equals(k)))
                {
                    e.value = value;
                    return;
                }
            }
            addEntry(hash, key, value, i);
        } else
        {
            createEntry(hash, key, value, i);
        }
    }

    @Override
    public Long getKey(String value)
    {
        if (value == null)
            return null;
        for (FileBucket fb : table)
        {
            if (fb == null)
                continue;
            for (Entry e = fb.getEntry(); e != null; e = e.next)
                if (value.equals(e.getValue()))
                    return e.getKey();
        }

        return null;
    }

    @Override
    public String getValue(Long key)
    {
        Entry entry = getEntry(key);
        return null == entry ? null : entry.getValue();
    }

    public long getBucketSizeLimit()
    {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit)
    {
        this.bucketSizeLimit = bucketSizeLimit;
    }
}
