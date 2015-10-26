package com.ens.degerleme.deneme;

public class Program3 {
	public static void main(String[] args) {
		int dizi1[] = new int[10];
		for (int i = 0; i < dizi1.length; ++i) {
			dizi1[i] = i * 2;
			System.out.println((i + 1) + ". eleman=" + dizi1[i]);
		}
	}
}