package com.nullpointerexception.hackathon.app;

public class DataWeighter {

    public static double[] weight(double[] carFreq, double[] temp, double[] rain){
        double[] carDataArray = carFreq; //Cars per day
        double[] rainDataArray = rain; // average rain for month
        double[] tempDataArray= temp; // average temp data array


        int length = carDataArray.length;
        double carDataArraySum = Calculations.sum(carDataArray);
        double rainDataArraySum = Calculations.sum(rainDataArray);
        double tempDataArraySum = Calculations.sum(tempDataArray);


        double[] weightedValues = new double[length];

        for(int i=0; i<length; i++){

            weightedValues[i] = Calculations.getWeight(carDataArray[i],rainDataArray[i],tempDataArray[i],carDataArraySum,rainDataArraySum,tempDataArraySum);
            System.out.println(weightedValues[i]);
        }
        return weightedValues;
	}

    public static double randomWithRange(int min, int max)
    {
        double range = (max - min) + 1;
        return (double)(Math.random() * range) + min;
    }

}