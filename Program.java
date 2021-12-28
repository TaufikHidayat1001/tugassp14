

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Program {

	static Scanner scanner;
	static String DB_URL = "jdbc:mysql://localhost:3306/transaksi_belanja";
    static String USERNAME = "root";
    static String PASSWORD = "";
    
    static Connection connection;
    
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
        scanner = new Scanner(System.in);
        Integer option = 0;
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            
            do {
                System.out.println(">>> MENU PILIHAN");
                System.out.println(" 1. Lihat Data");
                System.out.println(" 2. Tambah Data");
                System.out.println(" 3. Edit Data");
                System.out.println(" 4. Hapus Data");
                System.out.println(" 0. Keluar");
                System.out.print("\nPilihan Anda (1/2/3/4/0)? ");
                option = Integer.parseInt(scanner.nextLine());
                
                switch (option) {
                    case 1:
                    tampilkan_data();
                    break;
                    case 2:
                    save();
                    break;
                    case 3:
                    update();
                    break;
                    case 4:
                    hapus();
                    case 0:
                    break;
                    default:
                    System.out.println("Input tidak valid");
                }
                tunggu();
                
            } while (option != 0);
            
            connection.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Ada Kesalahan : Driver tidak ditemukan");
        }
        
	}
		
	public static void tampilkan_data() throws SQLException {
		System.out.println(" >> TAMPILKAN DATA");
		
		try {
			Statement statement = connection.createStatement();
	        String sql = "SELECT * FROM `data_transaksi`";
	        
	        ResultSet result = statement.executeQuery(sql);
	        
	        while(result.next()){
	            System.out.println("\nNo Faktur : " + result.getInt("no_faktur"));
	            System.out.println("Nama Barang : " + result.getString("nama_barang"));
	            System.out.println("Harga Barang : " + result.getInt("harga_barang"));
	            System.out.println("Jumlah : " + result.getInt("jumlah"));
	            System.out.println("Sub Total : " + result.getInt("sub_total"));
	            System.out.println("Diskon : " + result.getInt("diskon"));
	            System.out.println("Total Harga : " + result.getInt("total_harga"));
	        }
		}catch(SQLException e){
			System.out.println("Terjadi kesalahan : " + e.getMessage());
		}
		
	    
    }
	
	public static void save() throws SQLException {
		System.out.println(" >> TAMBAH DATA");
		Barang brg = new Barang();
		Transaksi trk = new Transaksi();
		
		Integer noFaktur = trk.noFaktur();
		String namaBarang = brg.namaBarang();
		Integer hargaBarang = trk.hargaBarang();
		Integer jumlah = trk.jumlah();
		Integer subTotal = trk.subtotal();
		Double diskon = trk.discount();
		Double totalHarga = trk.totalharga();
		
		try {
			String sql = "INSERT INTO `data_transaksi`(`no_faktur`, `nama_barang`, `harga_barang`, `jumlah`, `total_harga`) VALUES  ('"+noFaktur+"','"+namaBarang+"','"+hargaBarang+"','"+jumlah+"','"+subTotal+"','"+diskon+"','"+totalHarga+"')";
        
            Statement statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("\nBerhasil input data!");
        } catch (SQLException e) {
            System.out.println("\nTerjadi kesalahan input data!" + e.getMessage());
        }
	    
    }
	
	public static void update() throws SQLException {
		System.out.println(" >> EDIT DATA");
		
		Barang brg = new Barang();
		Transaksi trk = new Transaksi();
		
		try {
			tampilkan_data();
			System.out.print("\nNo Faktur data yang akan diedit ? ");
            Integer no_faktur = Integer.parseInt(scanner.nextLine());
            
            String sql = "SELECT `no_faktur`, `nama_barang`, `harga_barang`, `jumlah`, `total_harga` FROM `data_transaksi` " + no_faktur;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            
            if(rs.next()){
                
                System.out.print("Nama Barang ["+rs.getString("nama_barang")+"]: ");
                String namaBarang = brg.namaBarang();
                
                System.out.print("Harga Barang ["+rs.getInt("harga_barang")+"]: ");
                Integer hargaBarang = trk.hargaBarang();
                
                System.out.print("Jumlah ["+rs.getInt("jumlah")+"]: ");
                Integer jumlah = trk.jumlah();
                
//                System.out.print("Sub Total ["+rs.getInt("sub_total")+"]: ");
                Integer subTotal = trk.subtotal();
                
//                System.out.print("Diskon ["+rs.getInt("diskon")+"]: ");
                Double diskon = trk.discount();
                
//                System.out.print("Total Harga ["+rs.getInt("total_harga")+"]: ");
                Double totalHarga = trk.totalharga();
                
                sql = "UPDATE `data_transaksi` SET ` " +
                        "nama_barang = '" + namaBarang + "', " +
                        "harga_barang = '" + hargaBarang + "', " +
                        "jumlah = '" + jumlah + "', " +
                        "sub_total = '" + subTotal + "', " +
                        "diskon = '" + diskon + "', " +
                        "total_harga = " + totalHarga + " " +
                        "WHERE no_faktur = " + no_faktur;
                
                if(statement.executeUpdate(sql) > 0){
                    System.out.println("Berhasil memperbaharui data transaksi");
                }
            }
            statement.close();
		
		}catch (SQLException e) {
			System.out.println("Terjadi kesalahan dalam mengedit data");
            System.out.println(e.getMessage());
		}
	}
	
	public static void hapus() throws SQLException{
        System.out.println(" >> HAPUS KOLEKSI");
        
        try{
            tampilkan_data();
            
            System.out.print("\nNo Faktur Data yang akan dihapus ? ");
            Integer no_faktur = Integer.parseInt(scanner.nextLine());
            
            String sql = "DELETE FROM `data_transaksi` WHERE 0 "+ no_faktur;
            Statement statement = connection.createStatement();
            
            if(statement.executeUpdate(sql) > 0){
                System.out.println("\nBerhasil menghapus data transaksi");
            }
            
        }catch(SQLException e){
            System.out.println("Terjadi kesalahan dalam menghapus data");
        }
           
    }
	
	public static void tunggu(){
        System.out.print("\n\nTekan Enter untuk melanjutkan");
        scanner.nextLine();
    }
	
	
	
}
