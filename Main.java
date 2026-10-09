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
        System.out.println("======= MENU RESTORAN =======");
        System.out.println("       --- MAKANAN ---");
        System.out.println("1. " + daftarMenu[0].nama +  " - Rp. " + daftarMenu[0].harga);
        System.out.println("2. " + daftarMenu[1].nama +  " - Rp. " + daftarMenu[1].harga);
        System.out.println("3. " + daftarMenu[2].nama +  " - Rp. " + daftarMenu[2].harga);
        System.out.println("4. " + daftarMenu[3].nama +  " - Rp. " + daftarMenu[3].harga);
        System.out.println("       --- MINUMAN ---");
        System.out.println("5. " + daftarMenu[4].nama +  " - Rp. " + daftarMenu[4].harga);
        System.out.println("6. " + daftarMenu[5].nama +  " - Rp. " + daftarMenu[5].harga);
        System.out.println("7. " + daftarMenu[6].nama +  " - Rp. " + daftarMenu[6].harga);
        System.out.println("8. " + daftarMenu[7].nama +  " - Rp. " + daftarMenu[7].harga);

    }

    public static Menu pilihMenu(int nomor) {
        switch (nomor) {
            case 1:
                return Menu.daftarMenu[0];
            case 2:
                return Menu.daftarMenu[1];
            case 3:
                return Menu.daftarMenu[2];
            case 4:
                return Menu.daftarMenu[3];
            case 5:
                return Menu.daftarMenu[4];
            case 6:
                return Menu.daftarMenu[5];
            case 7:
                return Menu.daftarMenu[6];
            case 8:
                return Menu.daftarMenu[7];
            default:
                return null;
        }
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
    }

    public static void main(String[] args) {

        System.out.println("Program restoran dimulai");

        int jumlahMaksimal = 4;

        Pesanan[] daftarPesanan = new Pesanan[jumlahMaksimal];

        prosesPemesanan(daftarPesanan, jumlahMaksimal);
        
        prosesPembayaran(daftarPesanan, jumlahMaksimal);
    }
}