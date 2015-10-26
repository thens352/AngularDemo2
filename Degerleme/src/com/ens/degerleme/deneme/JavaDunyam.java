package com.ens.degerleme.deneme;

public class JavaDunyam

{
	public static void main(String[] args) {
		System.out.println("Java Dunayasýna ilk adýmýmý attým galiba...");
	}

	public void Deneme() {
		System.out.println("JavaDunyam içinden bir ses verbana...");
	}

	public class Diger {
		public void main(String[] args) {
			JavaDunyam jd = new JavaDunyam();
			jd.Deneme();
		}
	}

}
