package monad;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E> {
    private Iterable<E> self;

    public SuperIterable(Iterable<E> target) {
        self = target;
    }

    public SuperIterable<E> filter(Predicate<? super E> pred) {
        List<E> out = new ArrayList<>();
        for (E e : self) {
            if (pred.test(e)) out.add(e);
        }
        return new SuperIterable(out);
    }

    // map is the "key" operation of a "Functor"
    public <F> SuperIterable<F> map(Function<? super E, F> op) {
        List<F> out = new ArrayList<>();
        for (E e : self) {
            F f = op.apply(e);
            if (f != null) out.add(f);
        }
        return new SuperIterable(out);
    }

    public Iterator<E> iterator() {
        return self.iterator();
    }
}
