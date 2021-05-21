import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        //System.out.println(riverGenFormula(40, 4, 10, 1.0));
        //System.out.println(riverGenFormula(60, 20, 10, 20));

        //printSeparatingLines();

        testRiverGenerationPrint(20, 40, 10, 10, 40);

        //default


    }

    public static void testtestetest() {

        for (int i = 0; i < 40; i++) {

            System.out.println(("--" + i + " ") + (1 - riverGenFormula(i, 40, 10, 20)));

        }

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

    public static void testRiverGenerationPrint(int length, int width, int targetSize, int offSetPercentage, int riverDeviationPercentage) {

        Noise an = new Noise();
        an.riverGeneration(50, 40, 10, 10, 20);

    }

    public static double riverGenFormula(int sizeOfRiver, int targetSize, int offSetPercentage, int acceptedRiverDeviationPercentage) {

        /*
        *   targetSize is the ideal size of the width of the river
        *   sizeOfRiver is the current size of the width of the river
        *
        *   offSetPercentage means the max value of riverGenFormula will be 1 - offSetPercentage when the sizeOfRiver is equal or lower than targetSize
        *   the larger offSetPercentage is the harder it will be for a river to generate while sizeOfRiver <= targetSize
        *   so if the offSetPercentage is 20 then max value of riverGenFormula is 0.8 while sizeOfRiver <= targetSize is true
        */

        double riverGrowthFactor;

        /*
        *   Converting whole numbers to decimals
        */

        double riverDeviation = acceptedRiverDeviationPercentage / 100;
        double offSetDecimalPercentage = offSetPercentage / 100;

        if (sizeOfRiver <= targetSize) {

            System.out.println("--nils help");

            /*
            *   To generate formula for when the river is less than the ideal size
            *   The "river" should have a high probability to generate when this if statement is true
            *   
            *   riverGrowthFactor
            */

            riverGrowthFactor = 1 - offSetDecimalPercentage / targetSize * sizeOfRiver / 100;

        } else if (targetSize / sizeOfRiver > 1 - riverDeviation) {

            System.out.println("--helpp");

            /*
            *   For when the sizeOfRiver is larger than the ideal size
            */

            riverGrowthFactor = targetSize / sizeOfRiver * (1 + riverDeviation);

        } else {

            System.out.println("--elias test");

            //eg trur eg e her

            /*
            *   The further the sizeOfRiver is from targetSize the less chance it has to generate a new tile with the value 1
            */

            riverGrowthFactor = targetSize / sizeOfRiver;

        }

        return riverGrowthFactor;

    }

    public static void printSeparatingLines() {

        System.out.println("\n" + "============================================================" + "\n");

    }

}