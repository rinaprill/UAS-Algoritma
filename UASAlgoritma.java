/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.uasalgoritma;

/**
 *
 * @author LENOVO
 */
import java.util.ArrayList;
import java.util.Scanner;

class Transaksi {
    String jenis;
    String keterangan;
    double jumlah;

    public Transaksi(String jenis, String keterangan, double jumlah) {
        this.jenis = jenis;
        this.keterangan = keterangan;
        this.jumlah = jumlah;
    }
}

public class UASAlgoritma {
    static ArrayList<Transaksi> transaksi = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void tampilkanMenu() {
        System.out.println("\n=== SISTEM CATATAN KEUANGAN PRODUKSI ===");
        System.out.println("1. Tambah Transaksi");
        System.out.println("2. Lihat Laporan Keuangan");
        System.out.println("3. Keluar");
    }

    public static void tambahTransaksi() {
        System.out.print("Masukkan jenis transaksi (Pendapatan/Pengeluaran): ");
        String jenis = scanner.nextLine().trim().toLowerCase();
        
        if (!jenis.equals("pendapatan") && !jenis.equals("pengeluaran")) {
            System.out.println("Jenis transaksi tidak valid! Harap masukkan 'Pendapatan' atau 'Pengeluaran'.");
            return;
        }

        System.out.print("Masukkan keterangan transaksi: ");
        String keterangan = scanner.nextLine().trim();
        
        System.out.print("Masukkan jumlah uang: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Input tidak valid! Masukkan angka yang benar.");
            scanner.next();
        }
        double jumlah = scanner.nextDouble();
        scanner.nextLine(); 

        if (jenis.equals("pengeluaran")) {
            jumlah = -Math.abs(jumlah); 
        }

        transaksi.add(new Transaksi(jenis.substring(0, 1).toUpperCase() + jenis.substring(1), keterangan, jumlah));
        System.out.println("Transaksi berhasil ditambahkan!");
    }

    public static void laporanKeuangan() {
        if (transaksi.isEmpty()) {
            System.out.println("\nBelum ada transaksi.");
            return;
        }

        double totalPendapatan = 0;
        double totalPengeluaran = 0;

        System.out.println("\n=== LAPORAN KEUANGAN ===");
        for (Transaksi t : transaksi) {
            System.out.printf("%s: %s - Rp%,.2f\n", t.jenis, t.keterangan, Math.abs(t.jumlah));
            if (t.jenis.equals("Pendapatan")) {
                totalPendapatan += t.jumlah;
            } else {
                totalPengeluaran += Math.abs(t.jumlah);
            }
        }

        double saldo = totalPendapatan - totalPengeluaran;
        System.out.printf("\nTotal Pendapatan: Rp%,.2f\n", totalPendapatan);
        System.out.printf("Total Pengeluaran: Rp%,.2f\n", totalPengeluaran);
        System.out.printf("Saldo Akhir: Rp%,.2f\n", saldo);

        if (saldo > 0) {
            System.out.println("Keuangan dalam kondisi surplus.");
        } else if (saldo < 0) {
            System.out.println("Keuangan dalam kondisi defisit!");
        } else {
            System.out.println("Keuangan dalam kondisi seimbang.");
        }
    }

    public static void main(String[] args) {
        while (true) {
            tampilkanMenu();
            System.out.print("Pilih menu: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Input tidak valid! Masukkan angka 1, 2, atau 3.");
                scanner.next();
                continue;
            }

            int pilihan = scanner.nextInt();
            scanner.nextLine(); 

            switch (pilihan) {
                case 1 -> tambahTransaksi();
                case 2 -> laporanKeuangan();
                case 3 -> {
                    System.out.println("Terima kasih telah menggunakan sistem catatan keuangan produksi!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Pilihan tidak valid, coba lagi!");
            }
        }
    }
}