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
}