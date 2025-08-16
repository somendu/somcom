package j17.newapiexamples;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

public class RandomGeneratorExample {

    public static void main(String[] args) {
        RandomGenerator randomGenerator = RandomGeneratorFactory.
                of("L64X256MixRandom")
                .create();

        int randomInt = randomGenerator.nextInt();
        System.out.println("Random Int : " + randomInt);
        randomGenerator.ints(10, 1, 100)
                .forEach(i -> System.out.print("Random Ints: " + i + " "));

        System.out.println("");
        RandomGeneratorFactory.all()
                .map(RandomGeneratorFactory::name)
                .forEach(System.out::println);
    }
}
