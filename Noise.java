import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Noise {

    private Random rd;

    public Noise() {               

        this.rd = new Random();

    }

    public double findAverageRiverValue(int currentXCoordinates, int currentYCoordinates) {

        /*
        * Number can only exist like this
        * Target Number = X
        *
        *   60 59 58
        *   58 X
        *   
        *   Need to scan around the Target Number for values to get average of nearby values to get a more accurate result of new
        *   The max amount of variables around the Target Value = 4
        *   Through the first X values it can only get the value previous to itsself
        */

        try {

            while (true) {

                break;

            }

        } catch (Exception e) {



        }

        return -1;

    }

    public double findAverageOfNearbyNumbers(int currentXCoordinates, int currentYCoordinates) {

        /*
        * Number can only exist like this
        * Target Number = X
        *
        *   60 59 58
        *   58 X
        *   
        *   Need to scan around the Target Number for values to get average of nearby values to get a more accurate result of new
        *   The max amount of variables around the Target Value = 4
        *   Through the first 256 values it can only get the value previous to itsself
        */

        try {

            while (true) {



                break;

            }

        } catch (Exception e) {



        }

        return -1;

    }

    public ArrayList primitiveGenerate() { 

        /*
        *   Generates numbers for a noise map, but does not assert values to array
        *   This function needs to be called before any of the other reference functions
        *   This function creates a square-like map
        */

        int targetLength = 255;

        double firstNumber = rd.nextDouble() * 100; //can change v 

        double currentValue = firstNumber; //can change ^

        ArrayList<Double> primitiveCoords = new ArrayList<>();
        ArrayList<Double> holder = new ArrayList<>();

        for (int x = 0; x < targetLength; x++) {

            holder.clear();

            for (int y = 0; y < targetLength; y++) {

                if (rd.nextDouble() < 0.5) {

                    currentValue++;

                } else {

                    currentValue--;

                }

                primitiveCoords.add(currentValue);

            }

            primitiveCoords.add(currentValue);

        }

        return primitiveCoords;

    }

    public void primitiveGeneratePrint() { 

        /*
        *   Generates numbers for a noise map, but does not assert values to array
        *   The numbers generated are integers between -100 to 100
        *   This function needs to be called before any of the other reference functions
        *   This function creates a square-like map
        */

        int targetLength = 40;
        int maxValueOfNode = 100;

        int currentValue = rd.nextInt(maxValueOfNode);

        ArrayList<Integer> primitiveCoords = new ArrayList<>();
        ArrayList<Integer> holder = new ArrayList<>();

        for (int x = 0; x < targetLength; x++) {

            holder.clear();

            for (int y = 0; y < targetLength; y++) {

                if (rd.nextDouble() < 0.5) {

                    currentValue++;

                } else {

                    currentValue--;

                }

                if (currentValue > 100) {

                    currentValue = 100;

                } else if (currentValue < 0) {

                    currentValue = 0;

                }

                System.out.print(currentValue + " ");

            }

            System.out.println(currentValue);

        }

    }

    public void riverGeneration(int length, int width, int targetSize, int offSetPercentage, int riverDeviationPercentage) {

        /*
        *   To generate a river with 1 and 0
        *   1 = River, 0 = Not a river
        *   Initially it checks if first "tile" should be river, it has a Z% chance to be a river 
        *   Remaining tiles of X coordinate / width
        *   Example generation:
        *
        *   0 0 0 0 0 1 1 1 1 1 1 1 1 0 0 0 0 0 0
        *   0 0 0 0 1 1 1 1 1 1 1 1 0 0 0 0 0 0 0
        *   0 0 0 0 1 1 1 1 1 1 1 1 1 0 0 0 0 0 0
        *   0 0 0 0 1 1 1 1 1 1 1 1 1 0 0 0 0 0 0
        *   0 0 0 0 1 1 1 1 1 1 1 1 1 1 0 0 0 0 0
        *   0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 0 0 0 0
        *   0 0 0 0 1 1 1 1 1 1 1 1 1 1 0 0 0 0 0
        *
        *   the greater riverDeviationPercentage is the more likely the river is to continue generating after sizeOfRiver > targetSize
        */

        int riverValue = 0;
        int sizeOfRiver = 0;

        boolean firstRow = true;
        boolean canGenerate = true;

        for (int y = 0; y < length; y++) {

            /*
            *   The y value goes vertically
            */

            for (int x = 0; x < width; x++) {

                /*
                *   The x value goes horizontally
                */

                if (firstRow) {

                    // DONE for now
                    /*
                    *   firstrow is to check where the river should start
                    *   firstRow has different modifiers than y > 0
                    *   if (firstrow) codeblock has to end with "continue;" to prevent it using the other algorhitm to define values.
                    */

                    if (riverValue == 1) {

                        /*
                        *   if riverValue == 1 then it should be 1 til rng determines it should not be 1.
                        */

                        //System.out.println(riverGenFormula(sizeOfRiver, targetSize, offSetPercentage, riverDeviationPercentage));

                        if (rd.nextDouble() > riverGenFormula(sizeOfRiver, targetSize, offSetPercentage, riverDeviationPercentage)) {

                            // eg e her

                            /*
                            *   This if statement is to change the riverValue to 0
                            *   Meaning it's the end of the river
                            *   This is randomly determined
                            *
                            *   initialSizeOfRiver is reset when the river ends
                            */

                            //System.out.print("--" + (riverGenFormula(sizeOfRiver, targetSize, offSetPercentage, riverDeviationPercentage)));
                            //System.out.print("!" + initialSizeOfRiver + " "); remove later

                            riverValue = 0;
                            sizeOfRiver = 0;

                        } else {

                            /*
                            *   The river keeps expanding
                            */

                            sizeOfRiver++;

                        }

                    } else if (rd.nextDouble() >= 0.9 && canGenerate) { //Change formula for generating 1

                        System.out.print("-");

                        /*
                        *   To determine the initial riverValue's value
                        *   The 
                        */

                        riverValue = 1;
                        sizeOfRiver++;
                        canGenerate = false;

                    }

                }

                if (riverValue == 1 && !firstRow) {

                    /*
                    *   To determine non firstRow riverValue by getting the average value of nearby tiles
                    */

                    

                } else {



                }

                System.out.print(riverValue + " ");

            }

            /*
            *   riverValue is z?
            */

            System.out.println(riverValue);
            riverValue = 0;
            firstRow = false;

        }

    }

    public double riverGenFormula(int sizeOfRiver, int targetSize, int offSetPercentage, int acceptedRiverDeviationPercentage) {

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

            /*
            *   To generate formula for when the river is less than the ideal size
            *   The "river" should have a high probability to generate when this if statement is true
            *   
            *   riverGrowthFactor
            */

            riverGrowthFactor = 1 - offSetDecimalPercentage / targetSize * sizeOfRiver / 100;

        } else if ((targetSize / sizeOfRiver) > 1 - riverDeviation) {

            /*
            *   For when the sizeOfRiver is larger than the ideal size
            */

            riverGrowthFactor = (targetSize * 1.0) / (sizeOfRiver * 1.0) * ((1 * 1.0) + (riverDeviation * 1.0));

        } else {

            /*
            *   The further the sizeOfRiver is from targetSize the less chance it has to generate a new tile with the value 1
            */

            riverGrowthFactor = (targetSize * 1.0) / (sizeOfRiver * 1.0);

        }

        return riverGrowthFactor;

    }

    public void worldGeneration() {

        /*
        *   A function to call all other gens, this is how the "world" generates
        *   Terrain gen comes first to set the foundation of the world
        *   Then mountains/hills should be generated
        *   Then rivers, rivers can generate through the mountains
        */

    }

}