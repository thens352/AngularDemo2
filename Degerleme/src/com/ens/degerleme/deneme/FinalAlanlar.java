package com.ens.degerleme.deneme;

public class FinalAlanlar {
	static double deger2;

	public static void main(String[] args) {
		int deger1 = 7;
		deger2 = 0.125;
		int deger3 = (int) (Math.random() * 10);

		Yaz(deger1, deger2, deger3);

		deger2 = 100;
		deger1 = 8;
		deger3 = (int) (Math.random() * 5);
		Yaz(deger1, deger2, deger3);
	}

	static void Yaz(int a, double b, int c) {
		System.out.println("Deger1=" + a);
		System.out.println("Deger2=" + b);
		System.out.println("Deger3=" + c);
	}

}