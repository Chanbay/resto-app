import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static class Pesanan {
        Menu menu;
        int porsi;

        public Pesanan(Menu menu, int porsi) {
            this.menu = menu;
            this.porsi = porsi;
        }
    }

    public static void delay(int milidetik) {
        try {
            Thread.sleep(milidetik);
        } catch (InterruptedException e) {
            System.out.println("Proses terinterupsi.");
        }
    }

    public static void tampilkanResi(
        Pesanan[] daftarPesanan,
        int jumlahMaksimal,
        int subTotal,
        int tax,
        int service,
        int potonganDiscount,
        int potonganB1G1,
        int totalPembayaran
    ) {

        System.out.println();
        System.out.println("========================================");
        System.out.println("              RESI PESANAN");
        System.out.println("========================================");

        for (int i = 0; i < jumlahMaksimal; i++) {

            Pesanan pesanan = daftarPesanan[i];

            if (pesanan == null) {
                break;
            }

            int totalItem = pesanan.menu.harga * pesanan.porsi;

            System.out.println(
                (i + 1) + ". " +
                pesanan.menu.nama +
                " x" + pesanan.porsi +
                " = Rp." + totalItem
            );
        }

        System.out.println("----------------------------------------");
        System.out.println("Subtotal       : Rp." + subTotal);
        System.out.println("Pajak (10%)    : Rp." + tax);
        System.out.println("Service        : Rp." + service);

        if (potonganB1G1 > 0) {
            System.out.println("Diskon B1G1    : -Rp." + potonganB1G1);
        }

        if (potonganDiscount > 0) {
            System.out.println("Diskon 10%     : -Rp." + potonganDiscount);
        }

        System.out.println("----------------------------------------");
        System.out.println("TOTAL BAYAR    : Rp." + totalPembayaran);
        System.out.println("========================================");
        System.out.println("          Terima kasih!");
        System.out.println("========================================");
    }   

    public static void tampilkanMenu(Menu[] daftarMenu) {
        System.out.println("\n========== MENU RESTORAN ==========");

        String[] kategori = {"Makanan", "Minuman"};

        int nomorMenu = 1;

        for (String jenis : kategori) {

            System.out.println("\n======= " + jenis.toUpperCase() + " =======");

            for (int i = 0; i < daftarMenu.length; i++) {

                if (daftarMenu[i].kategori.equalsIgnoreCase(jenis)) {

                    System.out.println(
                        nomorMenu + ". " +
                        daftarMenu[i].nama +
                        " - Rp. " + daftarMenu[i].harga +
                        (daftarMenu[i].isB1G1 ? " [B1G1]" : "")
                    );
                    nomorMenu++;
                }
            }
        }

        System.out.println("===================================");

    }

    public static Menu pilihMenu(int nomor) {
        String[] kategori = {"Makanan", "Minuman"};

        int nomorMenu = 1;

        for (String jenis : kategori) {

            for (Menu menu : Menu.daftarMenu) {

                if (menu.kategori.equalsIgnoreCase(jenis)) {

                    if (nomorMenu == nomor) {
                        return menu;
                    }

                    nomorMenu++;
                }
            }
        }

        return null;
    }

    public static void prosesPembayaran(Pesanan[] daftarPesanan, int jumlahMaksimal) {
        int nomorPesanan = 0;
        int subTotal = 0;
        int service = 20000;
        int potonganDiscount = 0;
        int potonganB1G1 = 0;
        boolean b1g1ItemFound = false;

        Pesanan b1g1Item = null;

        while (true) {
            if (nomorPesanan == jumlahMaksimal) {
                break;
            }

            Pesanan currentPesanan = daftarPesanan[nomorPesanan];            

            if (currentPesanan == null) {
                break;
            }

            subTotal += currentPesanan.menu.harga * currentPesanan.porsi;

            if (currentPesanan.menu.isB1G1 && !b1g1ItemFound) {
                b1g1ItemFound = true;
                b1g1Item = currentPesanan;

            }

            nomorPesanan++;
        }

        int tax = subTotal * 10 / 100;
        int total = subTotal + tax + service;

        if (total > 50000 && b1g1ItemFound) {
            int jumlahGratis = b1g1Item.porsi / 2;
            potonganB1G1 = b1g1Item.menu.harga * jumlahGratis;  
        }

        if (total > 100000) {
            potonganDiscount = total * 10 / 100;
        }


        int totalPembayaran = total - potonganDiscount - potonganB1G1; 

        tampilkanResi(
            daftarPesanan,
            jumlahMaksimal,
            subTotal,
            tax,
            service,
            potonganDiscount,
            potonganB1G1,
            totalPembayaran
        );

    }

    public static void prosesPemesanan(Pesanan[] daftarPesanan, int jumlahMaksimal) {
        int nomorPesanan = 0;

        while (nomorPesanan < jumlahMaksimal) {
            delay(1000);

            tampilkanMenu(Menu.daftarMenu);
            System.out.println("Masukkan nomor menu yang ingin dipesan (" + nomorPesanan + "/4). Tekan 0 untuk menyelesaikan pemesanan.");

            System.out.print("Nomor menu:");
            String teksNomor = input.nextLine();

            int inputNomor;

            try {
                inputNomor = Integer.parseInt(teksNomor);
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka yang valid. Lakukan pemesanan ulang");
                continue;
            }
            
            if (inputNomor == 0) {
                break;
            }

            Menu menu = pilihMenu(inputNomor);

            if (menu == null) {
                System.out.println(inputNomor + " bukan angka yang valid, lakukan pemesanan ulang");
                continue;
            }

            System.out.println(menu.nama + " telah dipilih");



            int jumlahPorsi = 0;

            while (true) {
                System.out.println("Masukkan jumlah porsi yang ingin dipesan");
                System.out.print("Jumlah porsi:");
                String teksPorsi = input.nextLine();

                try {
                    jumlahPorsi = Integer.parseInt(teksPorsi);
                } catch (NumberFormatException e) {
                    System.out.println("Input harus berupa angka yang valid.");
                    continue;
                }

                if (jumlahPorsi <= 0) {
                    System.out.println("Porsi harus lebih dari 0");
                    continue;
                }
                break;
            }

            daftarPesanan[nomorPesanan] = new Pesanan(menu, jumlahPorsi);
            System.out.println("Pesanan " + daftarPesanan[nomorPesanan].menu.nama + " sebanyak " + daftarPesanan[nomorPesanan].porsi + " porsi berhasil ditambahkan" );
            nomorPesanan++;

            if (nomorPesanan == jumlahMaksimal) {
                System.out.println("Anda sudah mencapai batas pemesanan (" + nomorPesanan + "/"+ jumlahMaksimal +")");
            }
        }
        if (nomorPesanan == 0) {
            System.out.println("\nTidak ada pesanan yang dibuat.");
            System.out.println("Kembali ke menu utama...");
            return;
        }
    }

    public static void main(String[] args) {

        System.out.println("Program restoran dimulai");

        while (true) {
            System.out.println("\n================================");
            System.out.println("          MENU UTAMA");
            System.out.println("================================");
            System.out.println("1. Proses Pemesanan");
            System.out.println("2. Ubah Data Daftar Menu");
            System.out.println("0. Keluar");
            System.out.println("================================");
            System.out.print("Pilih menu: ");

            String teksPilihan = input.nextLine();

            int pilihan;

            try {
                pilihan = Integer.parseInt(teksPilihan);
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka.");
                continue;
            }

            switch (pilihan) {
                case 1:
                    int jumlahMaksimal = 4;

                    Pesanan[] daftarPesanan =
                        new Pesanan[jumlahMaksimal];

                    prosesPemesanan(
                        daftarPesanan,
                        jumlahMaksimal
                    );

                    if (daftarPesanan[0] != null) {
                        prosesPembayaran(daftarPesanan, jumlahMaksimal);
                    }
                    break;

                case 2:
                    Menu.ubahDataMenu();
                    break;

                case 0:
                    System.out.println(
                        "Terima kasih telah menggunakan program restoran."
                    );

                    input.close();
                    return;

                default:
                    System.out.println(
                        "Pilihan tidak valid. Silakan coba lagi."
                    );
            }
        }
    }
}