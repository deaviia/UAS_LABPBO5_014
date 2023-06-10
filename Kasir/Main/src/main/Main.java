/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

/**
 *
 * @author Asus
 */
import java.util.ArrayList;
import java.util.Scanner;

// Kelas Barang
class Barang {
    private String kode;
    private String nama;
    private double harga;

    public Barang(String kode, String nama, double harga) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
    }

    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }
}

// Kelas Kasir
class Kasir {
    private String nama;
    private ArrayList<Barang> daftarBarang;
    private ArrayList<Barang> keranjang;

    public Kasir(String nama) {
        this.nama = nama;
        this.daftarBarang = new ArrayList<>();
        this.keranjang = new ArrayList<>();
    }

    public void tambahBarang(Barang barang) {
        daftarBarang.add(barang);
    }

    public void hapusBarang(String kode) {
        Barang barang = null;
        for (Barang b : daftarBarang) {
            if (b.getKode().equals(kode)) {
                barang = b;
                break;
            }
        }
        if (barang != null) {
            daftarBarang.remove(barang);
            System.out.println("Barang dengan kode " + kode + " berhasil dihapus.");
        } else {
            System.out.println("Barang dengan kode " + kode + " tidak ditemukan.");
        }
    }

    public double hitungTotalHarga() {
        double totalHarga = 0;
        for (Barang barang : keranjang) {
            totalHarga += barang.getHarga();
        }
        return totalHarga;
    }

    public ArrayList<Barang> getDaftarBarang() {
        return daftarBarang;
    }

    public void tambahBarangKeranjang(Barang barang) {
        keranjang.add(barang);
    }

    public ArrayList<Barang> getKeranjang() {
        return keranjang;
    }

    public void hapusBarangKeranjang(String kode) {
        Barang barang = null;
        for (Barang b : keranjang) {
            if (b.getKode().equals(kode)) {
                barang = b;
                break;
            }
        }
        if (barang != null) {
            keranjang.remove(barang);
            System.out.println("Barang dengan kode " + kode + " berhasil dihapus dari keranjang.");
        } else {
            System.out.println("Barang dengan kode " + kode + " tidak ditemukan dalam keranjang.");
        }
    }

    public void resetKeranjang() {
        keranjang.clear();
        System.out.println("Keranjang berhasil direset.");
    }

    public void checkout(double jumlahBayar) {
        double totalHarga = hitungTotalHarga();
        double kembalian = jumlahBayar - totalHarga;

        System.out.println("Total harga belanja: Rp" + totalHarga);
        System.out.println("Jumlah bayar: Rp" + jumlahBayar);
        if (kembalian >= 0) {
            System.out.println("Kembalian: Rp" + kembalian);
        } else {
            System.out.println("Jumlah bayar tidak cukup.");
        }
        System.out.println("Terima kasih telah berbelanja!");
        resetKeranjang();
    }
}

public class Main {
    public static void main(String[] args) {
        // Membuat objek Kasir
        Kasir kasir = new Kasir("Dea Oktavia");

        // Menampilkan menu
        System.out.println("=== Selamat Datang di Toko Serba Serbi ===");
        System.out.println("|1. Sebagai Admin                        |");
        System.out.println("|2. Sebagai Pembeli                      |");
        System.out.println("|0. Keluar                               |");

        // Membaca input pengguna
        Scanner scanner = new Scanner(System.in);
        int pilihan = -1;
        while (pilihan != 0) {
            System.out.print("Masukkan pilihan: ");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    menuAdmin(kasir, scanner);
                    break;
                case 2:
                    menuPembeli(kasir, scanner);
                    break;
                case 0:
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }

        scanner.close();
    }

    // Metode untuk menu admin
    public static void menuAdmin(Kasir kasir, Scanner scanner) {
        System.out.println("");
        System.out.println("==== Menu Admin ====");
        System.out.println("1. Tambah Barang");
        System.out.println("2. Hapus Barang");
        System.out.println("3. Tampilkan Daftar Barang");
        System.out.println("0. Kembali");

        int pilihan = -1;
        while (pilihan != 0) {
            System.out.print("Masukkan pilihan: ");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    tambahBarang(kasir, scanner);
                    break;
                case 2:
                    hapusBarang(kasir, scanner);
                    break;
                case 3:
                    tampilkanDaftarBarang(kasir);
                    break;
                case 0:
                    System.out.println("Kembali ke Menu Utama.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }
    }

    // Metode untuk menu pembeli
    public static void menuPembeli(Kasir kasir, Scanner scanner) {
        System.out.println("");
        System.out.println("==== Menu Pembeli ====");
        System.out.println("1. Tampilkan Daftar Barang");
        System.out.println("2. Tambah Barang ke Keranjang");
        System.out.println("3. Tampilkan Keranjang");
        System.out.println("4. Hapus Barang dari Keranjang");
        System.out.println("5. Checkout");
        System.out.println("0. Kembali");

        int pilihan = -1;
        while (pilihan != 0) {
            System.out.print("Masukkan pilihan: ");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    tampilkanDaftarBarang(kasir);
                    break;
                case 2:
                    tambahBarangKeranjang(kasir, scanner);
                    break;
                case 3:
                    tampilkanKeranjang(kasir);
                    break;
                case 4:
                    hapusBarangKeranjang(kasir, scanner);
                    break;
                case 5:
                    checkout(kasir, scanner);
                    break;
                case 0:
                    System.out.println("Kembali ke Menu Utama.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }
    }

    // Metode untuk menambahkan barang
    public static void tambahBarang(Kasir kasir, Scanner scanner) {
        System.out.print("Masukkan kode barang: ");
        String kode = scanner.next();
        System.out.print("Masukkan nama barang: ");
        String nama = scanner.next();
        System.out.print("Masukkan harga barang: ");
        double harga = scanner.nextDouble();

        Barang barang = new Barang(kode, nama, harga);
        kasir.tambahBarang(barang);

        System.out.println("Barang berhasil ditambahkan.");
    }

    // Metode untuk menghapus barang
    public static void hapusBarang(Kasir kasir, Scanner scanner) {
        System.out.print("Masukkan kode barang yang akan dihapus: ");
        String kode = scanner.next();
        kasir.hapusBarang(kode);
    }

    // Metode untuk menampilkan daftar barang
    public static void tampilkanDaftarBarang(Kasir kasir) {
        System.out.println("Daftar Barang:");
        ArrayList<Barang> daftarBarang = kasir.getDaftarBarang();
        for (Barang barang : daftarBarang) {
            System.out.println(barang.getKode() + " - " + barang.getNama() + " - Rp" + barang.getHarga());
        }
    }

    // Metode untuk menambahkan barang ke keranjang
    public static void tambahBarangKeranjang(Kasir kasir, Scanner scanner) {
        System.out.print("Masukkan kode barang yang akan ditambahkan ke keranjang: ");
        String kode = scanner.next();

        Barang barang = null;
        ArrayList<Barang> daftarBarang = kasir.getDaftarBarang();
        for (Barang b : daftarBarang) {
            if (b.getKode().equals(kode)) {
                barang = b;
                break;
            }
        }

        if (barang != null) {
            kasir.tambahBarangKeranjang(barang);
            System.out.println("Barang berhasil ditambahkan ke keranjang.");
        } else {
            System.out.println("Barang dengan kode " + kode + " tidak ditemukan.");
        }
    }

    // Metode untuk menampilkan keranjang
    public static void tampilkanKeranjang(Kasir kasir) {
        System.out.println("Keranjang Belanja:");
        ArrayList<Barang> keranjang = kasir.getKeranjang();
        if (keranjang.isEmpty()) {
            System.out.println("Keranjang kosong.");
        } else {
            for (Barang barang : keranjang) {
                System.out.println(barang.getKode() + " - " + barang.getNama() + " - Rp" + barang.getHarga());
            }
        }
    }

    // Metode untuk menghapus barang dari keranjang
    public static void hapusBarangKeranjang(Kasir kasir, Scanner scanner) {
        System.out.print("Masukkan kode barang yang akan dihapus dari keranjang: ");
        String kode = scanner.next();
        kasir.hapusBarangKeranjang(kode);
    }

    // Metode untuk proses checkout
    public static void checkout(Kasir kasir, Scanner scanner) {
        double totalHarga = kasir.hitungTotalHarga();
        System.out.println("Total harga belanja: Rp" + totalHarga);
        System.out.print("Masukkan jumlah uang dibayar: ");
        double pembayaran = scanner.nextDouble();
        double kembalian = pembayaran - totalHarga;
        if (kembalian >= 0) {
            System.out.println("Kembalian: Rp" + kembalian);
        } else {
            System.out.println("Jumlah bayar tidak cukup.");
        }
        kasir.resetKeranjang();
        System.out.println("Terima kasih telah berbelanja di toko kami!");
    
        // Meminta pengguna untuk keluar atau tidak
        System.out.print("Apakah Anda ingin keluar? (y/n): ");
        String response = scanner.next();
        if (response.equalsIgnoreCase("y")) {
            System.exit(0); 
     }
    }
}
        

