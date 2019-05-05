package service;

import java.util.Optional;

public interface Service<A> {

    Optional<A> get(int key);

}
