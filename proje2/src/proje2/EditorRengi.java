package proje2;

import java.util.Scanner;

//Kullanıcıdan alinan inputa göre switch case ile KirmiziRenkliEditor, 
//MaviRenkliEditor veya SiyahRenkliEditor nesnesi donduren renkSec static metodunu bulunduran class.
public class EditorRengi {

    public static RenkliEditor renkSec(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Metin Editörü İçin Menu Çubuğu Rengi Seçiniz: "
                + "\n(Kırmızı için '0', Siyah için '1', Mavi için '2' giriniz. Seçim yapılmadığında editor default renkte çalışır.)");
        String renkSecimi = scanner.nextLine();

        switch (renkSecimi){
            case "0":
                return new KirmiziRenkliEditor();

            case "1":
                return new SiyahRenkliEditor();

            case "2":
                return new MaviRenkliEditor();
        }

        return null;
    }
}
