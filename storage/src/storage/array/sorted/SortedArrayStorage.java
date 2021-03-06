package storage.array.sorted;

import model.Resume;
import storage.array.ArrayStorage;
import storage.exception.ResumeNotFoundException;

import java.util.Arrays;

public class SortedArrayStorage extends ArrayStorage {

    public SortedArrayStorage(int size) {
        super(size);
    }

    public SortedArrayStorage() {
    }

    @Override
    protected Integer getKey(String uuid) {
        Resume resume = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, currentSize, resume);
    }


    @Override
    protected void store(Resume r, Integer index) {
        int storeIndex = -index - 1;
        System.arraycopy(storage, storeIndex, storage, storeIndex + 1, currentSize - storeIndex);
        storage[storeIndex] = r;
    }

    @Override
    protected void erase(int index) {
        int erasedIndex = currentSize - index - 1;
        if (erasedIndex > 0) {
            System.arraycopy(storage, index + 1, storage, index, erasedIndex);
        }
    }
}
