

import java.util.Scanner;

public class Barang implements Penjualan{
	String namaBarang;
	Scanner input = new Scanner(System.in);

	
	@Override
	public String namaBarang() {
		System.out.print("Masukkan nama barang \t : ");
		namaBarang = input.nextLine();
		return this.namaBarang;
	}

	@Override
	public int hargaBarang() {
		return 0;
	}
	
	@Override
	public int noFaktur() {
		// TODO Auto-generated method stub
		return 0;
	}
	

	@Override
	public int subtotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double discount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double totalharga() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int jumlah() {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
