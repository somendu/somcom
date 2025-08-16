package j17.newapiexamples;

import jdk.incubator.vector.FloatVector;
import jdk.incubator.vector.VectorSpecies;

public class VectorApiExample {

    private static final VectorSpecies<Float> SPECIES = FloatVector.SPECIES_PREFERRED;

    private static void vectorMultiply(float[] a, float[] b, float[] result) {
        int length = a.length;
        int i = 0;

        // Process elements in chunks of the preferred vector size
        for (; i <= length - SPECIES.length(); i += SPECIES.length()) {
            var va = FloatVector.fromArray(SPECIES, a, i);
            var vb = FloatVector.fromArray(SPECIES, b, i);
            var vr = va.mul(vb);
            vr.intoArray(result, i);
        }

        // Handle remaining elements
        for (; i < length; i++) {
            result[i] = a[i] * b[i];
        }
    }

    public static void main(String[] args) {
        float[] a = {1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f};
        float[] b = {2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f};
        float[] result = new float[a.length];

        vectorMultiply(a, b, result);

        System.out.print("Result: ");
        for (float value : result) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
