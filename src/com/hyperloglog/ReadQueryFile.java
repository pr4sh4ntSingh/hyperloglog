package com.hyperloglog;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.utility.LinetToArray;

public class ReadQueryFile {

	public static void main(String args[]) throws IOException {

	}

	public static double evaluateQuery(String line) {

		int[] queryInput = LinetToArray.convertLineToArray(line);

		int queryType = queryInput[0];

		switch (queryType) {

		case 1:
			return getDistinctIPs();

		case 2:
			return getDistinctJobs();

		case 3: { // distinct job Sign. for give clusterId
			int clusterId = queryInput[1];
			return applyDistnictOperarion(HLLSketch.sketch_3_job_signature_by_clusterIds, clusterId);
		}
		case 4: { // distinct job sings for range of clusterids
			int l = queryInput[1];
			int r = queryInput[2];
			return applyRangeOperation(HLLSketch.sketch_3_job_signature_by_clusterIds, l, r);
		}
		case 5: { // distinct job sign for given month
			int month = queryInput[1]-1;
			return applyDistnictOperarion(HLLSketch.sketch_4_job_signature_by_month, month);

		}
		case 6: { // distinct job sign for given range of month
			int l = queryInput[1];
			int r = queryInput[2];
			return applyRangeOperation(HLLSketch.sketch_4_job_signature_by_month, l, r);
		}

		case 7: { // distinct job sign for given geography
			int geo = queryInput[1];
			return applyDistnictOperarion(HLLSketch.sketch_5_job_signature_by_geography, geo);

		}

		case 8: { // distinct ip address for given geography
			int geography = queryInput[1];
			return applyDistnictOperarion(HLLSketch.sketch_6_ip_address_by_geograpgy, geography);
		}
		case 9: { // distinct job sign for set of geographies
			int geographies[] = queryInput; // Spare first value . It's query Id.

			return applySetOperation(HLLSketch.sketch_5_job_signature_by_geography, geographies);
		}

		case 10: { // ip address for set of geographies

			int geographies[] = queryInput;

			return applySetOperation(HLLSketch.sketch_6_ip_address_by_geograpgy, geographies);
		}
		default: {
			return 1.00;
		}
		}

	}

	public static double getDistinctIPs() {
		double cardinality = HyperLogLog.calculateCardinality(HLLSketch.sketch_1_ip);
		return cardinality;

	}

	public static double getDistinctJobs() {
		double cardinality = HyperLogLog.calculateCardinality(HLLSketch.sketch_2_job_signature);
		return cardinality;
	}

	/*
	 * public static double getJobIdForCluster(int clusterid) { int[] sketch =
	 * HLLSketch.sketch_3_job_signature_by_clusterIds[clusterid]; double cardinality
	 * = HyperLogLog.calculateCardinality(sketch); return cardinality; }
	 */

	public static double applyDistnictOperarion(int HLLSketchArray[][], int index) {
		int[] sketch = HLLSketchArray[index];
		double cardinality = HyperLogLog.calculateCardinality(sketch);
		return cardinality;

	}

	public static double applyRangeOperation(int HLLSketchArray[][], int l, int r) {
		int[] unionSet = new int[HLLSketch.numberOfBuckets];
		for (int i = l; i <= r; i++) {
			unionSet = union(unionSet, HLLSketchArray[i]);
		}

		double cardinality = HyperLogLog.calculateCardinality(unionSet);
		return cardinality;
	}

	public static double applySetOperation(int HLLSketchArray[][], int[] set) {
		int[] unionSet = new int[HLLSketch.numberOfBuckets];
		for (int i = 1; i < set.length; i++) { // because set[0] is query id.
			int index=set[i];
			unionSet = union(unionSet, HLLSketchArray[index]);
		}
		double cardinality = HyperLogLog.calculateCardinality(unionSet);
		return cardinality;

	}

	/*
	 * public static double getJobIdforMonth(int month) { int[] sketch =
	 * HLLSketch.sketch_4_job_signature_by_month[month]; double cardinality =
	 * HyperLogLog.calculateCardinality(sketch); return cardinality;
	 * 
	 * }
	 */

	public static int[] union(int[] sketch1, int[] sketch2) {
		int union[] = new int[sketch1.length];
		for (int i = 0; i < sketch1.length; i++) {

			int max = sketch1[i] > sketch2[i] ? sketch1[i] : sketch2[i];
			union[i] = max;
		}

		return union;
	}

}
