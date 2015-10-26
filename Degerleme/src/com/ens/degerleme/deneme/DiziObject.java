package com.ens.degerleme.deneme;


public class DiziObject {
	public static void main(String[] args) {
		
		Integer eleman1 = new Integer(456);
		String eleman2 = new String("Ben bir stringim...");
		Double eleman3 = new Double(45.45687);
		Boolean eleman4 = new Boolean(true);
		Object[] dizi = { eleman1, eleman2, eleman3, eleman4 };
		for (int i = 0; i < dizi.length; ++i) {
			System.out.println((i + 1) + ". eleman=" + dizi[i]);
		}
	}
}
