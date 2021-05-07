import java.util.ArrayList;
import java.lang.Math;
import java.util.Random;

public class Noise {

    private Random rd;
    private Math math;

    public Noise() {               

        this.rd = new Random();

    }

    public double findAverageRiverValue(int Xcoordinates, int Ycoordinates) {

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

    public double findAverageOfNearbyNumbers(int Xcoordinates, int Ycoordinates) {

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

                if (rd.nextFloat() < 0.5) {

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

                if (rd.nextFloat() < 0.5) {

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

    public void riverGeneration(int length, int width) {

        /*
        *   To generate a river with 1 and 0
        *   1 = River, 0 = Not a river
        *   Initially it checks if first "tile" should be river, it has a X% chance to be a river 
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
        */

        int riverValue = 0;
        int initialSizeOfRiver = 0;

        float modifier = 1.2f;     

        boolean firstRow = true;

        for (int y = 0; y < length; y++) {

            for (int x = 0; x < width; x++) {

                if (firstRow) {

                    /*
                    *   firstrow is to check where the river should start
                    *   firstRow has different modifiers than y > 0
                    *   if (firstrow) codeblock has to end with "continue;" to prevent it using the other algorhitm to define values.
                    */

                    if (riverValue == 1) {
                        
                        /*
                        *   if riverValue == 1 then it should be 1 til rng determines it should not be 1.
                        */

                        if (rd.nextFloat() > riverGenFormula(width, initialSizeOfRiver, x, modifier)) {

                            System.out.print("--" + riverGenFormula(width, initialSizeOfRiver, x, modifier));

                            riverValue = 0;

                        }

                    } else if (rd.nextFloat() >= 0.9) {

                        System.out.print("-");

                        /*
                        *   To determine the initial riverValue's value
                        *   The 
                        */                        

                        riverValue = 1;
                        initialSizeOfRiver++;

                    }

                }

                if (riverValue == 1 && !firstRow) {

                    if (rd.nextInt() <= 5) {

                    }

                } else {



                }

                System.out.print(riverValue + " ");

            }

            System.out.println(riverValue);
            firstRow = false;

        }

    }

    public double riverGenFormula(int width, int initialSizeOfRiver, int x, float modifier) {

        return (1 / initialSizeOfRiver * ((width - x) / width) + 0.5) * modifier;

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