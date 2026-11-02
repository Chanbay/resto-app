public class Menu {
    String nama;
    int harga;
    String kategori;
    boolean isB1G1;

    public Menu(String nama, int harga, String kategori, boolean isB1G1) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
        this.isB1G1 = isB1G1;
    }

    public static Menu[] daftarMenu = {
        new Menu("Nasi Goreng", 15000, "Makanan", false),
        new Menu("Sate Padang", 20000, "Makanan", false),
        new Menu("Soto Ayam", 18000, "Makanan", false),
        new Menu("Lontong Sayur", 10000, "Makanan", false),
        new Menu("Es Teh", 5000, "Minuman", true),
        new Menu("Alpukat Kocok", 15000, "Minuman", false),
        new Menu("Jus Mangga", 10000, "Minuman", false),
        new Menu("Air Mineral", 3500, "Minuman", false)
    };

    public static void ubahDataMenu() {
        while (true) {
            Main.tampilkanMenu(daftarMenu);

            System.out.println("\n======= UBAH DATA MENU =======");
            System.out.println("0. Kembali ke menu utama");
            System.out.print("Pilih nomor menu yang ingin diubah: ");

            String teksNomor = Main.input.nextLine();
            int nomor;

            try {
                nomor = Integer.parseInt(teksNomor);
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka.");
                continue;
            }

            if (nomor == 0) {
                return;
            }

            if (nomor < 1 || nomor > daftarMenu.length) {
                System.out.println("Nomor menu tidak valid.");
                continue;
            }

            Menu menu = daftarMenu[nomor - 1];

            while (true) {
                System.out.println("\nData menu saat ini:");
                System.out.println("Nama     : " + menu.nama);
                System.out.println("Harga    : Rp. " + menu.harga);
                System.out.println("Kategori : " + menu.kategori);
                System.out.println(
                    "Promo    : " + (menu.isB1G1 ? "B1G1" : "Tidak")
                );

                System.out.println("\nPilih data yang ingin diubah:");
                System.out.println("1. Nama menu");
                System.out.println("2. Harga menu");
                System.out.println("3. Kategori menu");
                System.out.println("4. Status promo B1G1");
                System.out.println("0. Kembali");
                System.out.print("Pilihan: ");

                String teksPilihan = Main.input.nextLine();
                int pilihan;

                try {
                    pilihan = Integer.parseInt(teksPilihan);
                } catch (NumberFormatException e) {
                    System.out.println("Input harus berupa angka.");
                    continue;
                }

                if (pilihan == 0) {
                    break;
                }

                switch (pilihan) {
                    case 1:
                        System.out.print("Masukkan nama baru: ");
                        String namaBaru = Main.input.nextLine().trim();

                        if (namaBaru.isEmpty()) {
                            System.out.println(
                                "Nama menu tidak boleh kosong."
                            );
                        } else {
                            menu.nama = namaBaru;
                            System.out.println(
                                "Nama menu berhasil diubah."
                            );
                        }
                        break;

                    case 2:
                        System.out.print("Masukkan harga baru: ");
                        String teksHarga = Main.input.nextLine();

                        try {
                            int hargaBaru = Integer.parseInt(teksHarga);

                            if (hargaBaru <= 0) {
                                System.out.println(
                                    "Harga harus lebih dari 0."
                                );
                            } else {
                                menu.harga = hargaBaru;
                                System.out.println(
                                    "Harga menu berhasil diubah."
                                );
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(
                                "Harga harus berupa angka yang valid."
                            );
                        }
                        break;

                    case 3:
                        System.out.println("Pilih kategori:");
                        System.out.println("1. Makanan");
                        System.out.println("2. Minuman");
                        System.out.print("Pilihan kategori: ");

                        String kategori = Main.input.nextLine();

                        switch (kategori) {
                            case "1":
                                menu.kategori = "Makanan";
                                System.out.println(
                                    "Kategori berhasil diubah."
                                );
                                break;

                            case "2":
                                menu.kategori = "Minuman";
                                System.out.println(
                                    "Kategori berhasil diubah."
                                );
                                break;

                            default:
                                System.out.println(
                                    "Kategori tidak valid."
                                );
                        }
                        break;

                    case 4:
                        menu.isB1G1 = !menu.isB1G1;

                        System.out.println(
                            "Status promo sekarang: "
                            + (menu.isB1G1 ? "B1G1" : "Tidak")
                        );
                        break;

                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            }
        }
    }
}