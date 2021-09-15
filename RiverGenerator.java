package generation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class RiverGenerator {

    private Random rd;

    public RiverGenerator() {               

        this.rd = new Random(); // can remove honestly

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


    public HashMap<String, Integer> randomRiverGeneration(int length, int width, int targetSize, int offSetPercentage, int riverDeviationPercentage) {

        /*
        *   To generate a river with 1 and 0
        *   1 = River, 0 = Not a river
        *   Initially it checks if first "tile" should be river, it has a Z% chance to be a river 
        *   Remaining tiles of X coordinate / width
        *   Example generation:
        *
        *   0 0 0 0 0 1 1 1 1 1 1 1 1 0 0 0 0 0 0
        *   0 0 0 0 1 1 1 1 1 1 1 1 0 0 0 0 0 0 0
        *   0 0 0 1 1 1 1 1 1 1 1 1 1 0 0 0 0 0 0
        *   0 0 0 0 1 1 1 1 1 1 1 1 1 0 0 0 0 0 0
        *   0 0 0 0 1 1 1 1 1 1 1 1 1 1 0 0 0 0 0
        *   0 0 0 0 0 1 1 1 1 1 1 1 1 1 1 0 0 0 0
        *   0 0 0 0 1 1 1 1 1 1 1 1 1 1 0 0 0 0 0
        *
        *   the greater riverDeviationPercentage is the more likely the river is to continue generating after sizeOfRiver > targetSize
        *
        *   Returns a hashmap
        */

        HashMap<String, Integer> riverGenMap = new HashMap<>();

        int riverValue = 0;
        int sizeOfRiver = 0;

        boolean firstRow = true;
        boolean canGenerate = true;

        for (int y = 0; y < length; y++) {

            /*
            *   The y value goes vertically
            *   
            *   Y
            *   Y
            *   Y
            */

            for (int x = 0; x < width; x++) {

                /*
                *   The x value goes horizontally
                *
                *   X X X
                */

                if (firstRow) {

                    /*
                    *   firstrow is to check where the river should start
                    *   firstRow has different modifiers than y > 0
                    */

                    if (riverValue == 1) {

                        /*
                        *   if riverValue == 1 then it should be 1 til rng determines it should not be 1.
                        */

                        //System.out.println(riverGenFormula(sizeOfRiver, targetSize, offSetPercentage, riverDeviationPercentage));

                        if (rd.nextDouble() > riverGenFormula(sizeOfRiver, targetSize, offSetPercentage, riverDeviationPercentage)) {

                            /*
                            *   This if statement is to change the riverValue to 0
                            *   Meaning it's the end of the width of the river
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

                    } else if (firstRiverValue(x, width) == 1 && canGenerate) { //Change formula for generating 1

                        /*
                        *   To determine the initial riverValue's value
                        */

                        riverValue = 1;
                        sizeOfRiver++;
                        canGenerate = false;

                    }

                }

                if (!firstRow) {

                    /*
                    *   !firstRow needs to be an if statement or else the riverValue's value is going to be determined wrongly
                    *
                    *   To determine non firstRow riverValue by getting the average value of nearby tiles
                    */

                    riverValue = determineRiverValue(riverGenMap, x, y, width, sizeOfRiver);

                    if (riverValue == 1) {

                        sizeOfRiver++;

                        /*
                        *   The river keeps expanding
                        */

                    }

                }

                riverGenMap.put(x + " " + y, riverValue);
                //System.out.print(riverValue + " ");

            }

            /*
            *   riverValue is z? (what?)
            */

            //System.out.println(riverValue);
            riverValue = 0;
            firstRow = false;

        }

        return riverGenMap;

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

            riverGrowthFactor = (targetSize * 1.0f) / sizeOfRiver * (1 + riverDeviation);

        } else {

            /*
            *   The further the sizeOfRiver is from targetSize the less chance it has to generate a new tile with the value 1
            */

            riverGrowthFactor = (targetSize * 1.0f) / sizeOfRiver;

        }

        return riverGrowthFactor;

    }

    public int determineRiverValue(HashMap<String, Integer> riverNoiseMap, int currentXCoordinates, int currentYCoordinates, int width, int sizeOfRiver) {

        /*
        * Number can only exist like this
        * Target Number = X
        *
        *   60 59 58
        *   58 X
        *   
        *   Need to scan around the Target Number for values to get average of nearby values to get a more accurate result of new
        *   The max amount of variables around the target is 4 when its first generated.
        *   Through the first X values it can only get the value previous to itsself
        */

        int intLeft;
        int intTopLeft;
        int intTopMiddle;
        int intTopRight;

        /*
        *   Variables to check where and if there is a river around the target
        *   Variables contain the riverValue of its relative location to the target Value 
        *
        *   O O O
        *   O X
        *
        *   X is the target value
        *   O are the relevant tiles to the target tile
        */

        try {

            if (currentXCoordinates == 0) {

                /*
                *   ! O O
                *   ! X
                *
                *   Left does not exist in the current position
                *   TopLeft does not exist in the current position
                */

                intLeft = -1;
                intTopLeft = -1;

                intTopMiddle = riverNoiseMap.get(currentXCoordinates + " " + (currentYCoordinates - 1));
                intTopRight = riverNoiseMap.get((currentXCoordinates + 1) + " " + (currentYCoordinates - 1));

            } else if (currentXCoordinates == (width - 1)) {

                /*
                *   O O !
                *   O X
                *
                *   TopRight does not exist in the current position
                */

                intTopRight = -1;

                intLeft = riverNoiseMap.get((currentXCoordinates - 1) + " " + currentYCoordinates);
                intTopLeft = riverNoiseMap.get((currentXCoordinates - 1) + " " + (currentYCoordinates - 1));
                intTopMiddle = riverNoiseMap.get(currentXCoordinates + " " + (currentYCoordinates - 1));

            } else {

                intLeft = riverNoiseMap.get((currentXCoordinates - 1) + " " + currentYCoordinates);
                intTopLeft = riverNoiseMap.get((currentXCoordinates - 1) + " " + (currentYCoordinates - 1));
                intTopMiddle = riverNoiseMap.get(currentXCoordinates + " " + (currentYCoordinates - 1));
                intTopRight = riverNoiseMap.get((currentXCoordinates + 1) + " " + (currentYCoordinates - 1));

            }

        } catch (Exception e) {

            return -1;

        }

        /*
        *   Converting integers to boolean
        */

        boolean left = (intLeft == 1);
        boolean topLeft = (intTopLeft == 1);
        boolean topMiddle = (intTopMiddle == 1);
        boolean topRight = (intTopRight == 1);

        int riverValue = 0;

        /*
        *   Algorithm to determine the river's value
        */

        if (topLeft && topMiddle && topRight) {

            /*
            *   1 1 1
            *   0 X
            *
            *   Must be at top to be calculated properly
            *
            */

            riverValue = 1;

        } else if (topLeft && topMiddle && 0.65 < rd.nextDouble()) { //can change now is 35% chance to generate

            /*
            *   1 1 0
            *   0 X
            */

            riverValue = 1;
            
        } else if (topMiddle && topRight && 0.65 < rd.nextDouble()) { //can change now is 35% chance to generate

            /*
            *   0 1 1
            *   0 X
            */

            riverValue = 1;
        
        } else if (topRight && 0.80 < rd.nextDouble()) {    //can change now is 20% chance to generate

            /*
            *   0 0 1
            *   0 X
            */

            riverValue = 1;

        } else if (left && 0.80 < rd.nextDouble()) { //can change now is 20% chance to generate

            /*
            *   0 0 0
            *   1 X
            */

            riverValue = 1;

        } else if (left && topLeft && 0.50 < rd.nextDouble()) { //can change now is 50% chance to generate

            /*
            *   1 0 0
            *   1 X
            */

            riverValue = 1;

        }

        return riverValue;

    }

    public double regulateWidthOfRiver(int currentXCoordinates, int width, int currentSizeOfRiver, int targetSize) {

        /*
        *   To determine and help keep the width of the river around and or close to the targetSize
        */

        double modifier = 0f;

        if (currentSizeOfRiver < targetSize * 0.5) {

            /*
            *   IF:
            *   currentSizeOfRiver = 5
            *   targetSize = 12
            *
            *   THEN:
            *   we want to give it a offset boost to help the river survive
            *   we give it a boost of 28%
            *
            *   EXAMPLE OF DYING RIVER:
            *   0 0 0 0 0 0 0 0 0 0 1 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
            *   0 0 0 0 0 0 0 0 0 0 0 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
            *   0 0 0 0 0 0 0 0 0 0 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
            *   0 0 0 0 0 0 0 0 0 0 0 1 1 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
            *   0 0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
            *   0 0 0 0 0 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
            *   0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
            *   0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
            */
            
            modifier = 1.28f;

        } else if (currentSizeOfRiver < targetSize * 0.75) {

            modifier = 1.11f;

        } else if (currentSizeOfRiver < targetSize * 0.92) {

            modifier = 1.02f;

        } else if (currentSizeOfRiver > targetSize * 1.08) {

            /*
            *   For when the river is becoming too big
            */

            modifier = 0.98f;

        }

        return modifier;

    }

    public int firstRiverValue(int currentXCoordinates, int width) {

        /*
        *   To determine the first "river" which is 1 in a river.
        *
        *   Now the river should generate more towards the "middle" of the river
        *
        *   Previous Method was if(this.rd.nextDouble() > 0.9)
        *   MEANING:
        *   The river had a 10 percent chance of generating for each "tile"
        */

        int riverValue;


        double toFloat = (currentXCoordinates * 1.0f / width);

        if (toFloat > this.rd.nextDouble()) {

            riverValue = 1;

        } else {

            riverValue = 0;

        }

        return riverValue;
        
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