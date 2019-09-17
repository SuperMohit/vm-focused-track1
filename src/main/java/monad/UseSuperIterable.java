package monad;

import command.Student;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;

@FunctionalInterface
interface ExFunction<A, B> {
    B apply(A a) throws Throwable;
}
public class UseSuperIterable {

    public static <A, B> Function<A, Optional<B>> wrap(ExFunction<A, B> op) {
        return a -> {
            try {
                return Optional.ofNullable(op.apply(a));
            } catch (Throwable t) {
                return Optional.empty();
            }
        };
    }
    public static void main(String[] args) {
        SuperIterable<Student> roster = new SuperIterable<>(Arrays.asList(
                new Student("Fred", 3.0, "Math", "Physics"),
                new Student("Jim", 2.0, "Art"),
                new Student("Sheila", 3.8, "Math", "Physics", "Astronomy", "Quantum Mechanics")
        ));

        roster.forEach(System.out::println);

        roster
                .filter(s -> s.getGpa() > 4)
                .map(s -> s.getName())
                .forEach(System.out::println);

        Map<String, String> names = new HashMap<>();
        names.put("Fred", "Jones");
        String firstname = "Fred";

        String lastName = names.get(firstname);
        if (lastName != null) {
            String message = "Dear " + lastName.toUpperCase();
            System.out.println(message);
        }

        System.out.println("----------------------");
        SuperIterable<Map<String, String>> bucket =
                new SuperIterable<>(Arrays.asList(names));

        bucket.map(m -> m.get(firstname))
                .map(s -> "Dear " + s.toUpperCase())
                .forEach(System.out::println);

        System.out.println("----------------------");
        Optional.of(names)
                .map(m -> m.get(firstname))
                .map(s -> "Dear " + s.toUpperCase())
                .ifPresent(System.out::println);

        System.out.println("----------------------");
        String filename = "data.txt";
        Optional.of(filename)
                .flatMap(wrap(n -> Files.readAllLines(Paths.get(n))))
                .ifPresent(l -> l.forEach(System.out::println));

    }
}
