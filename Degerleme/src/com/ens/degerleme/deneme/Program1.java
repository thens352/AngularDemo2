package com.ens.degerleme.deneme;

public class Program1 {
	public static void main(String args[]) {
		Sinifim sf1 = new Sinifim(10, 20);
		System.out.println("Sf1 nesnesi i�in");
		sf1.Yaz();
		Sinifim sf2 = new Sinifim();
		System.out.println("Sf2 nesnesi i�in");
		sf2.Yaz();
		System.out.println("sf1 nesnesi, Sf2 nesnesine aktar�l�yor");
		sf2 = sf1;
		sf2.Yaz();

		sf1.DegerAta(1, 2);
		sf1.Yaz();
		sf2.Yaz();
	}
}