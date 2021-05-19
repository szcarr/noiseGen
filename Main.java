import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        //System.out.println(riverGenFormula(40, 4, 10, 1.0));
        //System.out.println(riverGenFormula(60, 20, 20, 1.0));

        printSeparatingLines();

        testRiverGenerationPrint();

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
        an.riverGeneration(20, 40, 8, 10);

    }

    public static double riverGenFormula(int sizeOfRiver, int targetSize) {

        /*
        *   targetSize is the ideal size of the width of the river
        *   sizeOfRiver is the current size of the width of the river
        */

        if (sizeOfRiver <= targetSize) {

            

        }

        double riverGrowthFactor = 1.0 / (sizeOfRiver * 1.0);

        return riverGrowthFactor + 0.875;

    }

    public static void printSeparatingLines() {

        System.out.println("\n" + "============================================================" + "\n");

    }

}