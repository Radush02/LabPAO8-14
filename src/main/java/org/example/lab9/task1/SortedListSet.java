package org.example.lab9.task1;

import java.util.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class SortedListSet<T extends Comparable<T>> implements SortedSet<T> {
    private final LinkedList<T> list = new LinkedList<>();
    private Comparator<? super T> comparator;

    @Override
    public Comparator<? super T> comparator() {
        return comparator;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean add(T o) {
        if (list.contains(o)) return false;
        if (comparator != null) list.add(o);
        else list.add(o);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return list.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public T first() {
        return list.getFirst();
    }

    @Override
    public T last() {
        return list.getLast();
    }

    @Override
    public SortedSet<T> subSet(T from, T to) {
        SortedListSet<T> subSet = new SortedListSet<T>(comparator);
        for (T item : list) {
            if (compare(item, from) >= 0 && compare(item, to) < 0) {
                subSet.add(item);
            }
        }
        return subSet;
    }

    @Override
    public SortedSet<T> headSet(T to) {
        SortedListSet<T> headSet = new SortedListSet<T>(comparator);
        for (T item : list) {
            if (compare(item, to) < 0) {
                headSet.add(item);
            }
        }
        return headSet;
    }

    @Override
    public SortedSet<T> tailSet(T from) {
        SortedListSet<T> tailSet = new SortedListSet<T>(comparator);
        for (T item : list) {
            if (compare(item, from) >= 0) {
                tailSet.add(item);
            }
        }
        return tailSet;
    }

    private int compare(T item1, T item2) {
        if (comparator != null) {
            return comparator.compare(item1, item2);
        } else {
            return item1.compareTo(item2);
        }
    }
}
