package com.ens.degerleme.deneme;

public class Dongu {
	public static void main(String args[]) {
		double Toplam = 0;
		for (int i = 1; i <= 1000; i++) {
			Toplam = Toplam + (Math.pow(i,4));
		}
		System.out.println(Toplam);
	}
}