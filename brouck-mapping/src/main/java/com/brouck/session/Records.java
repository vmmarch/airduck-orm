package com.brouck.session;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * @author brouck
 * Create time 2022/4/1
 */
public class Records<E> implements List<E> {

    private final List<E> a = new ArrayList<>();

    /**
     * 保存所有记录
     */
    public void saveRecords() {

    }

    /**
     * 更新所有记录
     */
    public void updateRecords() {

    }

    /**
     * 删除所有记录
     */
    public void removeRecords() {

    }

    @Override
    public int size() {
        return a.size();
    }

    @Override
    public boolean isEmpty() {
        return a.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return a.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return a.iterator();
    }

    @Override
    public Object[] toArray() {
        return a.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return a;
    }

    @Override
    public boolean add(E e) {
        return a.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return a.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return a.containsAll(c);
    }

    @Override
    public void clear() {
        a.clear();
    }

    @Override
    public boolean equals(Object o) {
        return a.equals(o);
    }

    @Override
    public int hashCode() {
        return a.hashCode();
    }

    @Override
    public String toString() {
        return a.toString();
    }

    @Override
    public E get(int index) {
        return a.get(index);
    }

    @Override
    public E set(int index, E element) {
        return a.set(index, element);
    }

    @Override
    public void add(int index, E element) {
        a.add(index, element);
    }

    @Override
    public E remove(int index) {
        return a.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return a.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return a.lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return a.listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return a.listIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return a.subList(fromIndex, toIndex);
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        a.forEach(action);
    }

    @Override
    public Spliterator<E> spliterator() {
        return a.spliterator();
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        return a.removeIf(filter);
    }

    @Override
    public Stream<E> stream() {
        return a.stream();
    }

    @Override
    public Stream<E> parallelStream() {
        return a.parallelStream();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return a.addAll(index, c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return a.addAll(c);
    }


    @Override
    public boolean removeAll(Collection<?> c) {
        return a.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return a.retainAll(c);
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        a.replaceAll(operator);
    }

    @Override
    public void sort(Comparator<? super E> c) {
        a.sort(c);
    }

}
