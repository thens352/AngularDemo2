package com.ens.degerleme.deneme;

public class Dongu2 {
	public static void main(String args[]) {
		double Toplam = 0;
		int i = 1;
		while (i <= 1000)

		{
			Toplam = Toplam + (Math.pow(i, 4));
			i++;
		}
		System.out.println(Toplam);
	}
}