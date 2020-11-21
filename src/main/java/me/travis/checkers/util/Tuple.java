package me.travis.checkers.util;

/*
 * tuple class, holds 3 elements of any datatype
 * (like a list or array but easy to use and more efficient)
 */
public class Tuple<E1, E2, E3> {

    private final E1 element1;
    private final E2 element2;
    private final E3 element3;

    // creates a tuple
    public static <e1, e2, e3> Tuple<e1, e2, e3> create(e1 element1, e2 element2, e3 element3) {
        return new Tuple<>(element1, element2, element3);
    }

    public Tuple(E1 element1, E2 element2, E3 element3) {
        this.element1 = element1;
        this.element2 = element2;
        this.element3 = element3;
    }

    public E1 getElement1() {
        return this.element1;
    }

    public E2 getElement2() {
        return this.element2;
    }

    public E3 getElement3() {
        return this.element3;
    }

}