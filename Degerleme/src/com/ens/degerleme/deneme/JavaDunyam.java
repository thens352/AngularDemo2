package com.ens.degerleme.deneme;

public class JavaDunyam

{
	public static void main(String[] args) {
		System.out.println("Java Dunayas�na ilk ad�m�m� att�m galiba...");
	}

	public void Deneme() {
		System.out.println("JavaDunyam i�inden bir ses verbana...");
	}

	public class Diger {
		public void main(String[] args) {
			JavaDunyam jd = new JavaDunyam();
			jd.Deneme();
		}
	}

}
