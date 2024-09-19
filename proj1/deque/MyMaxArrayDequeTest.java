package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;

/** Tests for MaxArrayDeque. */
public class MyMaxArrayDequeTest {

    /** Comparator for int. */
    private static class IntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return a - b;
        }
    }

    public static Comparator<Integer> getIntComparator() {
        return new IntComparator();
    }

    /** Tests max() in an int array. */
    @Test
    public void intArrayMaxTest() {
        Comparator<Integer> intComparator = getIntComparator();
        MaxArrayDeque<Integer> ad1 = new MaxArrayDeque<>(intComparator);
        for (int i = 0; i < 100; i = i + 2) {
            ad1.addLast(i);
        }
        ad1.printDeque();

        int maxItem = ad1.max();
        assertEquals(98, maxItem);
    }


    /** Tests nested comparator using a Dog class. */
    public class Dog {
        private String name;
        private int age;
        private float weight;

        public Dog(String n, int a, float w) {
            this.name = n;
            this.age = a;
            this.weight = w;
        }
    }

    /** Comparator for Dog.name. */
    private static class DogNameComparator implements Comparator<Dog> {
        @Override
        public int compare(Dog d1, Dog d2) {
            return d1.name.compareTo(d2.name);
        }
    }

    public static Comparator<Dog> getDogNameComparator() {
        return new DogNameComparator();
    }

    /** Comparator for Dog.age. */
    private static class DogAgeComparator implements Comparator<Dog> {
        @Override
        public int compare(Dog d1, Dog d2) {
            return Integer.compare(d1.age, d2.age);
        }
    }

    public static Comparator<Dog> getDogAgeComparator() {
        return new DogAgeComparator();
    }

    /** Comparator for Dog.weight. */
    private static class DogWeightComparator implements Comparator<Dog> {
        @Override
        public int compare(Dog d1, Dog d2) {
            return Float.compare(d1.weight, d2.weight);
        }
    }

    public static Comparator<Dog> getDogWeightComparator() {
        return new DogWeightComparator();
    }

    /** Tests max() and max(Comparator) using a dog array. */
    @Test
    public void dogArrayMaxTest() {
        // Sets up a dog array with DogNameComparator as the default comparator
        Dog dog1 = new Dog("apple", 20, 10.1f);
        Dog dog2 = new Dog("box", 1, 13.9f);
        Dog dog3 = new Dog("happy", 11, 6.5f);

        Comparator<Dog> dogNameComparator = getDogNameComparator();
        MaxArrayDeque<Dog> dogArray = new MaxArrayDeque<>(dogNameComparator);
        dogArray.addLast(dog1);
        dogArray.addLast(dog2);
        dogArray.addLast(dog3);

        // Tests DogNameComparator
        // In String.compareTo(), "z" is bigger than "a"
        assertEquals(dog3, dogArray.max());

        // Tests DogAgeComparator
        Comparator<Dog> dogAgeComparator = getDogAgeComparator();
        assertEquals(dog1, dogArray.max(dogAgeComparator));

        // Tests DogWeightComparator
        Comparator<Dog> dogWeightComparator = getDogWeightComparator();
        assertEquals(dog2, dogArray.max(dogWeightComparator));
    }

}
