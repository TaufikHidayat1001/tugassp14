

import java.util.Scanner;

public class Transaksi  extends Barang implements Penjualan {
	int jumlah;
	int nofaktur;
	int subtotal;
	double discount;
	double totalharga;
	int hargabarang;
	Scanner input = new Scanner(System.in);


	@Override
	public int hargaBarang() {
		System.out.print("Masukkan harga barang \t : ");
		hargabarang = input.nextInt();
		return this.hargabarang;
	}
	
	@Override
	public int noFaktur() {
		System.out.print("Masukkan nomor faktur \t : ");
		nofaktur = input.nextInt();
		return this.nofaktur;
	}
	
	@Override
	public int jumlah() {
		System.out.print("Masukkan jumlah barang \t : ");
		jumlah = input.nextInt();
		return this.jumlah;
	}
	
	@Override
	public int subtotal() {
		subtotal = hargabarang*jumlah;
		return this.subtotal;
	}

	@Override
	public double discount() {
		if (subtotal > 0) {
			if (subtotal > 10000 & subtotal <40000) {
				discount = (3*subtotal)/100;
			}
			else if (subtotal >40000 & subtotal <60000) {
				discount = (4*subtotal)/100;
			}
			else if (subtotal >60000 & subtotal <75000) {
				discount = (6*subtotal)/100;
			}
			else if (subtotal >75 & subtotal <100000) {
				discount = (7*subtotal)/100;
			}
			else if (subtotal >100000 ) {
				discount = (10*subtotal)/100;
			}
		}
		else {
			System.out.println("Masukkan Harga dan jumlah yang benar!!");
		}
		return this.discount;
	}

	@Override
	public double totalharga() {
		totalharga = subtotal - discount ;
		return this.totalharga;
	}
	
}
