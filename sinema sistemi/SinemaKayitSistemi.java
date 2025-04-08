import java.util.Scanner;

public class SinemaKayitSistemi {

    // Film bilgileri
    static String[] filmAdlari = new String[10];
    static int[] filmSureleri = new int[10];
    static String[] filmTurleri = new String[10];
    static int filmSayisi = 0;

    // Müşteri bilgileri
    static String[] musteriAdlari = new String[20];
    static String[] musteriEmailleri = new String[20];
    static int musteriSayisi = 0;

    // Bilet bilgileri
    static int[][] biletKayitlari = new int[20][10]; // [musteriIndex][filmIndex] = 1 ise bilet var

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int secim;

        do {
            System.out.println("\n--- Sinema Müşteri Kayıt Sistemi ---");
            System.out.println("1. Film Tanımla");
            System.out.println("2. Müşteri Kaydı");
            System.out.println("3. Bilet Kaydı");
            System.out.println("4. Kayıtları Görüntüle");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");
            secim = scanner.nextInt();
            scanner.nextLine(); // Dummy nextLine

            switch (secim) {
                case 1:
                    filmTanimla(scanner);
                    break;
                case 2:
                    musteriKaydet(scanner);
                    break;
                case 3:
                    biletKaydet(scanner);
                    break;
                case 4:
                    kayitlariGoster();
                    break;
                case 0:
                    System.out.println("Programdan çıkılıyor...");
                    break;
                default:
                    System.out.println("Geçersiz seçim!");
            }
        } while (secim != 0);

        scanner.close();
    }

    static void filmTanimla(Scanner scanner) {
        if (filmSayisi >= 10) {
            System.out.println("Maksimum film sayısına ulaşıldı!");
            return;
        }

        System.out.print("Film adı: ");
        String ad = scanner.nextLine();

        System.out.print("Film süresi (dk): ");
        int sure = scanner.nextInt();
        scanner.nextLine(); // dummy

        System.out.print("Film türü: ");
        String tur = scanner.nextLine();

        filmAdlari[filmSayisi] = ad;
        filmSureleri[filmSayisi] = sure;
        filmTurleri[filmSayisi] = tur;
        filmSayisi++;

        System.out.println("Film başarıyla tanımlandı.");
    }

    static void musteriKaydet(Scanner scanner) {
        if (musteriSayisi >= 20) {
            System.out.println("Maksimum müşteri sayısına ulaşıldı!");
            return;
        }

        System.out.print("Müşteri adı: ");
        String ad = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        musteriAdlari[musteriSayisi] = ad;
        musteriEmailleri[musteriSayisi] = email;
        musteriSayisi++;

        System.out.println("Müşteri başarıyla kaydedildi.");
    }

    static void biletKaydet(Scanner scanner) {
        if (musteriSayisi == 0 || filmSayisi == 0) {
            System.out.println("Önce müşteri ve film tanımlanmalıdır.");
            return;
        }

        System.out.println("--- Müşteriler ---");
        for (int i = 0; i < musteriSayisi; i++) {
            System.out.println(i + ". " + musteriAdlari[i] + " (" + musteriEmailleri[i] + ")");
        }

        System.out.print("Müşteri numarası: ");
        int musteriIndex = scanner.nextInt();
        scanner.nextLine();

        if (musteriIndex < 0 || musteriIndex >= musteriSayisi) {
            System.out.println("Geçersiz müşteri numarası!");
            return;
        }

        System.out.println("--- Filmler ---");
        for (int i = 0; i < filmSayisi; i++) {
            System.out.println(i + ". " + filmAdlari[i] + " (" + filmTurleri[i] + ", " + filmSureleri[i] + "dk)");
        }

        System.out.print("Film numarası: ");
        int filmIndex = scanner.nextInt();
        scanner.nextLine();

        if (filmIndex < 0 || filmIndex >= filmSayisi) {
            System.out.println("Geçersiz film numarası!");
            return;
        }

        if (biletKayitlari[musteriIndex][filmIndex] == 1) {
            System.out.println("Bu müşteri bu film için zaten bilet almış.");
        } else {
            biletKayitlari[musteriIndex][filmIndex] = 1;
            System.out.println("Bilet kaydı başarıyla yapıldı.");
        }
    }

    static void kayitlariGoster() {
        System.out.println("--- Filmler ---");
        for (int i = 0; i < filmSayisi; i++) {
            System.out.println(i + ". " + filmAdlari[i] + " (" + filmTurleri[i] + ", " + filmSureleri[i] + "dk)");
        }

        System.out.println("--- Müşteriler ve Biletleri ---");
        for (int i = 0; i < musteriSayisi; i++) {
            System.out.print(musteriAdlari[i] + " (" + musteriEmailleri[i] + "): ");
            boolean biletVar = false;
            for (int j = 0; j < filmSayisi; j++) {
                if (biletKayitlari[i][j] == 1) {
                    System.out.print(filmAdlari[j] + " | ");
                    biletVar = true;
                }
            }
            if (!biletVar) {
                System.out.print("Henüz bilet alınmamış.");
            }
            System.out.println();
        }
    }
}
