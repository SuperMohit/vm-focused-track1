package command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class School {
    // Command pattern, closure based factories for other behaviors
    // Higher order functions.
    // and, called Function composition.

    public static <E> Predicate<E> and(Predicate<E> first, Predicate<E> second) {
        return s -> first.test(s) && second.test(s);
    }

    public static Predicate<String> longStrings(/*final */int threshold) {
//        threshold ++; // captured variables (closured) must be final or effectively
        return s -> s.length() > threshold;  // closure -- takes a copy of the local variable
    }

    public static <E> List<E> select(Iterable<E> in, Predicate<? super E> ps) {
        List<E> out = new ArrayList<>();
        in.forEach(s -> {
            if (ps.test(s)) {
                out.add(s);
            }
        });
        return out;
    }

    public static void main(String[] args) {
        List<Student> roster = Arrays.asList(
                new Student("Fred", 3.0, "Math", "Physics"),
                new Student("Jim", 2.0, "Art"),
                new Student("Sheila", 3.8, "Math", "Physics", "Astronomy", "Quantum Mechanics")
        );
        roster.forEach(s -> System.out.println(s));
        System.out.println("--------- smart -----------");
        select(roster, s -> s.getGpa() > 3).forEach(s -> System.out.println(s));
        System.out.println("----------- long strings --------------");
        List<String> ls = Arrays.asList("Fred", "Jim", "Sheila");
//        Predicate<String> longStrings = s -> s.length() > 3;
        Predicate<String> longStrings = longStrings(3);
//        select(ls, longStrings).forEach(s -> System.out.println(s));
        select(ls, longStrings).forEach(System.out::println);

        Predicate<String> shortString = s -> s.length() < 6;
//        Predicate<String> midLength = s -> s.length() > 3 && s.length() < 6;
        System.out.println("----------- mid length strings --------------");
        select(ls, and(longStrings, shortString)).forEach(s -> System.out.println(s));
        select(ls, longStrings.and(shortString)).forEach(s -> System.out.println(s));

//        Function<String, Integer> fis = s -> {
//          if (s.length() > 3) {
//              return this.apply(s.substring(3));
//          } else return 0;
//        };

        Function<String, Integer> fis = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                if (s.length() > 3) {
                    return this.apply(s.substring(3));
                } else return 0;
            }
        };
    }
}
