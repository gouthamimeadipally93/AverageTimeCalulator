package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class AverageTime {

	public static void main(String[] args) throws ParseException {
		String file_path = "";
		System.out.println("Please give the file path");

		try (Scanner sc = new Scanner(System.in)) {

			file_path = sc.next();

			File file = new File(file_path);

			// copying file data to ArrayList
			List<String> file_data = null;
			file_data = copyFileData(file);

			Map<String, String> map = new HashMap<String, String>();
			
			Map<String, Long> averages = new HashMap<String, Long>();
			
			for (String a : file_data) {
				String b[] = a.split(",");
				if (map.containsKey(b[0])) {
					String t1 = map.get(b[0]);
					map.put(b[0], b[2]);
					long diff = difference(t1, b[2]);
					averages.put(b[0], diff);

				} else {
					map.put(b[0], b[2]);
				}
			}

			long sum = 0;
			for (Entry<String, Long> s : averages.entrySet()) {
				sum = sum + s.getValue();
			}
			long avg = sum / averages.size();
			System.out.println("Average transaction time :"+avg+" seconds.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// method to copy file data to list
	private static List<String> copyFileData(File file) throws IOException {
		List<String> file_data = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;
		while ((str = br.readLine()) != null) {
			file_data.add(str);
		}
		return file_data;
	}

	// method to find difference
	private static long difference(String s1, String s2) throws ParseException {

		String t1 = s1.trim().replace("am", "").replace("pm", "");
		String t2 = s2.trim().replace("am", "").replace("pm", "");

		DateFormat format = new SimpleDateFormat("HH:mm");
		Date date1 = format.parse(t1);
		Date date2 = format.parse(t2);
		long diff=(date2.getTime() - date1.getTime())/1000;
		return diff;
	}

}
