package com.nullpointerexception.hackathon.app;

public class Calculations {




	public static double getWeight(double carDataArray, double rainDataArray,double tempDataArray,double carDataArraySum, double rainDataArraySum,double tempDataArraySum){

	double weighting = ((carDataArray/carDataArraySum)*100)+((rainDataArray/rainDataArraySum)*100)+((tempDataArray/tempDataArraySum)*100);

	return weighting;

	}


	public static double sum(double[] p) {
	    double sum = 0;  // sum of all the elements
	    for (int i=0; i<p.length; i++) {
        sum += p[i];
    }
	    return sum;
	}







}