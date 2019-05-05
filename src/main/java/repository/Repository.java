package repository;

import java.util.Map;

public interface Repository<A> {

    A add(A object);

    boolean contains(int key);

    A get(int key);

    boolean remove(int key);

    Map<Integer, A> getAll();

}
