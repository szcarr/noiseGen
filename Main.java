import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println(riverGenFormula(40, 4, 35, 1.3f));

        //testRiverGenerationPrint();

    }

    public static void testprimitiveGenerate() {

        Noise an = new Noise();

        ArrayList<Double> test = new ArrayList<>();

        for (int i = 0; i < an.primitiveGenerate().size(); i++) {

            //Double holderVariable = an.primitiveGenerate().get(i);

            //test.add(holderVariable);

        }

        for (int i = 0; i < test.size(); i++) {

            System.out.println(test.get(i));
            
        }

    }

    public static void testPrimitiveGeneratePrint() {

        Noise an = new Noise();

        an.primitiveGeneratePrint();

    }

    public static void testRiverGenerationPrint() {

        Noise an = new Noise();
        an.riverGeneration(20, 40);
        
    }

    public static double riverGenFormula(int width, int initialSizeOfRiver, int x, float modifier) {

        return (1 / initialSizeOfRiver * ((width - x) / width) + 0.5) * modifier;

    }

}