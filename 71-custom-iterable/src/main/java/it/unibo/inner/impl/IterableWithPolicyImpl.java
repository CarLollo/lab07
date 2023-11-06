package it.unibo.inner.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

import it.unibo.inner.api.IterableWithPolicy;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{

    private T[] list;
    private Predicate<T> predicate;

    public IterableWithPolicyImpl(final T[] arg){
        this(arg, new Predicate<T>() {
            private final boolean a = true;

            public boolean test(final T t) {
                return a;
            }
        });
    }

    public IterableWithPolicyImpl(final T[] arg, final Predicate<T> p){
        this.list = arg;
        this.predicate = p;
    }

    public void setIterationPolicy(Predicate<T> filter) {
        this.predicate = filter;
    }
    
    public Iterator<T> iterator() {
        return this.new InnerIterator();
    }

    public String toString() {
        return "IterableWithPolicyImpl [list=" + Arrays.toString(list) + "]";
    }

    class InnerIterator implements Iterator<T>{
        
        private int index;

        public InnerIterator() {
            this.index = 0;
        }

        public boolean hasNext() {
            while (this.index < list.length)   {
                if (predicate.test(list[index])) {
                    return true;
                }
                index++;
            }

            return false;
        }

        public T next() {
            this.index++;
            return list[index-1];
        }
    }
}
