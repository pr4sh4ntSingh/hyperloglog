package com.hyperloglog;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import com.utility.LinetToArray;

public class HLLSketch {
	
	static int numberOfBuckets=64;
	static int sketch_1_ip[]=new int [numberOfBuckets];
	static int sketch_2_job_signature[]=new int[numberOfBuckets];
	static int sketch_3_job_signature_by_clusterIds[][]=new int[100][numberOfBuckets];
	static int sketch_4_job_signature_by_month[][]=new int[12][numberOfBuckets];
	static int sketch_5_job_signature_by_geography[][]=new int[10][numberOfBuckets];
	static int sketch_6_ip_address_by_geograpgy[][]=new int[10][numberOfBuckets];
	
	static int p=6;
	
	public static void createSketches(String fileName) throws NumberFormatException, IOException {
		
		String inputFile = fileName;
		BufferedReader br = new BufferedReader(new FileReader(inputFile));
		String st;


		while ((st = br.readLine()) != null) {
			// System.out.println(st);
			String[] array = st.split("\t");
			String sourceIp=array[0];
			String clusterId=array[1];
			String job_signature=array[2];
			String date=array[3];
			String geo=array[4];
			
			// create sketch for query 1 i.e. distict ip
			HyperLogLog.hyperlogloForOneStream(sketch_1_ip, sourceIp,p);
			
			// create sketch for query 2 i.e. distinct job_signature
			HyperLogLog.hyperlogloForOneStream(sketch_2_job_signature, job_signature,p);
									
			// create sketch for query 3,4 for every clusterid create sketch of Job_signature
			int cluster_index=Integer.parseInt(clusterId);
			int [] HLL_for_cluster=sketch_3_job_signature_by_clusterIds[cluster_index];
			
			HyperLogLog.hyperlogloForOneStream(HLL_for_cluster, job_signature, p);
			
			
			
			//create sketch for query 5,6: for every month create sketch of Job_signature
			String month= date.split(":")[1];
			int month_index=Integer.parseInt(month)-1;
			
			int [] HLL_for_month=sketch_4_job_signature_by_month[month_index];
			
			HyperLogLog.hyperlogloForOneStream(HLL_for_month, job_signature, p);
			
			// create sketch for query 7,9: for every geography create sketch of Job_signature
			
			int geography_index=Integer.parseInt(geo);
			int [] HLL_for_geo= sketch_5_job_signature_by_geography[geography_index];
			
			HyperLogLog.hyperlogloForOneStream(HLL_for_geo, job_signature, p);

			// create sketch for query 8,10: for every geography create sketch of ip_address
			
			int [] HLL_for_geo_ip=sketch_6_ip_address_by_geograpgy[geography_index];
			
			HyperLogLog.hyperlogloForOneStream(HLL_for_geo_ip, sourceIp, p);
			
			
		}
		
//		System.out.println(Arrays.toString(sketch_1_ip));
//		System.out.println(Arrays.toString(sketch_2_job_signature));
		for(int i=0;i<9;i++) {
			System.out.println(Arrays.toString(sketch_6_ip_address_by_geograpgy[i]));
			System.out.println(HyperLogLog.calculateCardinality(sketch_6_ip_address_by_geograpgy[i]));
			
		}
		
		
		System.out.println(HyperLogLog.calculateCardinality(sketch_1_ip));
		System.out.println(HyperLogLog.calculateCardinality(sketch_2_job_signature));
		
		

		

		
			 
	}
}
