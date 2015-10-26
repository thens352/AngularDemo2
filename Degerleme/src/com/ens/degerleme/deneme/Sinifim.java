package com.ens.degerleme.deneme;

public class Sinifim {
	public int Deger1;
	public int Deger2;

	public Sinifim() {
		Deger1 = 0;
		Deger2 = 0;
	}

	public Sinifim(int d1, int d2) {
		Deger1 = d1;
		Deger2 = d2;
	}

	public void DegerAta(int d1, int d2) {
		Deger1 = d1;
		Deger2 = d2;
	}

	public void Yaz() {
		System.out.println("Deger1 :" + Deger1);
		System.out.println("Deger2 :" + Deger2);
	}
}