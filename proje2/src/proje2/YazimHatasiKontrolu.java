package proje2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


//“words.txt” dosyasinin okundugu, bu dosyadaki kelimelere gore iterator kullanilarak 
//metindeki hatalarin belirlenip duzeltildigi ve bu hatalara göre aciklama metinlerinin 
//olusturuldugu metodlari iceren class.
public class YazimHatasiKontrolu {

    private final String duzeltilenKelimeler;
    private final String duzeltilenYazi;

    public YazimHatasiKontrolu(String kontrolEdilecekYazi) {

        String[] temp = hataDuzelt(kontrolEdilecekYazi);

        duzeltilenYazi = temp[0];
        duzeltilenKelimeler = temp[1];
    }

    public String getDuzeltilenKelimeler() {
        return duzeltilenKelimeler;
    }

    public String getDuzeltilenYazi() {
        return duzeltilenYazi;
    }

    public ArrayList<String> wordsListYapan() {
        ArrayList<String> list = new ArrayList<>();
        //words.txt okunup string tipinde arrayliste atanir
        try {
            File file = new File("words.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                list.add(line);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Dosya Bulunamadı.");
            e.printStackTrace();
        }

        return list;
    }

    //hata kontrolunde harf yeri degisiminde kullanilan metod
    public String swap(String str, int i, int j)
    {
        /*parametre str harf degisimi yapilacak kelimeyi gosterir
        parametre i yeri degistirilecek ilk harfi gosterir
        parametre j yeri degistirilecek ikinci harfi gosterir
        */
        int tempor=0;

        char[] ch = str.toCharArray();
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
        //LinkedList olusturuluyor ve ici harf yerleri degistirilmis stringin harfleriyle dolduruluyor
        LinkedList<Character> LL=new LinkedList<>();
        while(true){
            try {
                LL.add(ch[tempor]);
            } catch ( IndexOutOfBoundsException e ) {
                break;
            }
            tempor++;
        }
        //iterator kullanilarak LinkedList stringe donusturuluyor
        Iterator<Character> charIterator=LL.iterator();
        StringBuilder sb=new StringBuilder();
        while (charIterator.hasNext()){
            sb.append(charIterator.next());
        }
        return sb.toString();
    }

    /*Stringdeki hatalari duzelten ve returnnumbera gore
    0 ise duzeltilmis stringe
    1 ise yazilan textte tespit edilen tum hatali kelimeleri veren arrayliste
    2 ise bu yanlislar icinde bize projede verilen parametreye gore duzeltilebilen hatalari veren arraylist
    baska herhangi bisey ise de duzeltilebilen hatalarin duzeltilmis halini veren arrrayliste
    ulastiran metod
    */
    public String[] hataDuzelt(String duzeltilecekString){

        ArrayList<String>wordsList = wordsListYapan();

        LinkedList<String>duzelenYanlis = new LinkedList<>();
        LinkedList<String>duzelmisYanlis = new LinkedList<>();
        LinkedList<String>duzelmeyenYanlis = new LinkedList<>();

        String duzelmis = duzeltilecekString;

        //Bosluklari ayirma
        String[] bosluklariAtilmisArr=duzeltilecekString.split(" ");
        LinkedList<String> hataKontrolLinkedList=new LinkedList<>();

        //Virgulleri ayirma(Iterator ile)
        LinkedList<String> VirgulLL=new LinkedList<>(Arrays.asList(bosluklariAtilmisArr));
        Iterator<String> VirgulIt=VirgulLL.iterator();
        while(VirgulIt.hasNext()) {
            String element= VirgulIt.next();
            String[] araListe = element.split(",");
            hataKontrolLinkedList.addAll(Arrays.asList(araListe));
        }

        String[] virgulleriAtilmisArr= new String[hataKontrolLinkedList.size()];
        virgulleriAtilmisArr=hataKontrolLinkedList.toArray(virgulleriAtilmisArr);
        hataKontrolLinkedList.clear();

        //Noktalari ayirma(Iterator ile)
        LinkedList<String> NoktaLL=new LinkedList<>(Arrays.asList(virgulleriAtilmisArr));
        Iterator<String> NoktaIt=NoktaLL.iterator();
        while (NoktaIt.hasNext()) {
            String item= NoktaIt.next();
            String[] araListe = item.split("\\.");
            Collections.addAll(hataKontrolLinkedList, araListe);
        }

        String[] noktalariAtilmisArr=new String[hataKontrolLinkedList.size()];
        noktalariAtilmisArr=hataKontrolLinkedList.toArray(noktalariAtilmisArr);
        hataKontrolLinkedList.clear();

        //Noktali virgulleri ayirma(Iteratorler ile)
        LinkedList<String> NVirgulLL=new LinkedList<>(Arrays.asList(noktalariAtilmisArr));
        Iterator<String> NVirgulIt=NVirgulLL.iterator();
        while(NVirgulIt.hasNext()) {
            String value= NVirgulIt.next();
            String[] araListe = value.split(";");

            LinkedList<String> midList=new LinkedList<>(Arrays.asList(araListe));
            Iterator<String> midListIt= midList.iterator();
            while (midListIt.hasNext()) {
                /*yanyana iki tane ayrilacak elemandan var ise bos eleman eklenir onlardan kurtulmak icin
                bu if kullanildi*/
                String s=midListIt.next();
                if (s.isEmpty()) {
                    continue;
                }
                hataKontrolLinkedList.add(s);
            }
        }

        int buyukHarf;

        //(Iteratorler ile)Kelime hatası kontrol etme ve hata duzeltme
        Iterator<String> hataKontrolIt=hataKontrolLinkedList.iterator();

        while(hataKontrolIt.hasNext()){
            //Kelimenin buyuk harfle baslama kontrolu yapilir
            String simdikiKelime=hataKontrolIt.next();
            if (Character.isUpperCase(simdikiKelime.charAt(0))){
                buyukHarf=1;
            }
            else{
                buyukHarf=0;
            }
            //butun kelimenin kucukharfe donusturulmus kopyasi olusturulur
            String kelimeTemp=simdikiKelime.toLowerCase();

            if(wordsList.contains(kelimeTemp)){
                //eger bu if saglanirsa kelime dogru yazilmis
                continue;
            }
            //eger bu if saglanirsa kelimenin icinde numara vardir
            else if(kelimeTemp.contains("0") || kelimeTemp.contains("1") || kelimeTemp.contains("2") ||
                    kelimeTemp.contains("3") || kelimeTemp.contains("4") || kelimeTemp.contains("5") ||
                    kelimeTemp.contains("6") || kelimeTemp.contains("7") || kelimeTemp.contains("8") || kelimeTemp.contains("9")){
                //kelime sadece numaradan olusmuyorsa try catch yapisi bunu tespit eder ve kelimeyi duzeltilemeyecek hatali kelime listesine ekler.
                try {
                    double d = Double.parseDouble(kelimeTemp);
                } catch (NumberFormatException nfe) {
                    duzelmeyenYanlis.add(simdikiKelime);
                }
                continue;
            }
            else{
                //eger bu else saglanirsa kelime yanlis yazilmistir

                //swapTemp initialize edilir
                String swapTemp = null;
                //swapTemp kelimesini olustururken her seferinde farkli bir harf degisimi olmasi icin gerekli dongu
                char[] tempHarf=kelimeTemp.toCharArray();
                LinkedList<Character> tempLL=new LinkedList<>();
                int tempint=0;
                while(true){
                    try {
                        tempLL.add(tempHarf[tempint]);
                    } catch ( IndexOutOfBoundsException e ) {
                        break;
                    }
                    tempint++;
                }
                Iterator<Character> characterIterator= tempLL.iterator();
                int m=0;
                while (characterIterator.hasNext()){
                    characterIterator.next();
                    if(!characterIterator.hasNext()){
                        break;
                    }
                    //swapTemp kelimesine kullanicinin yazdigi kelimenin harf yeri degismis halinin atanmasi
                    swapTemp=swap(kelimeTemp,m,m+1);
                    //swapTemp kelimesi words.txt icinde varmi kontrolu
                    if(wordsList.contains(swapTemp)){
                        //duzeltilebilecek kelimeleri gosteren listeye alim
                        duzelenYanlis.add(simdikiKelime);
                        //yanlis yazilmis kelimenin bas harfinin buyuklugune gore dogru haliyle degistirilmesi
                        if(buyukHarf==1){
                            char character=Character.toUpperCase(swapTemp.charAt(0));
                            String charToStringTemp=String.valueOf(character);
                            simdikiKelime=charToStringTemp+swapTemp.substring(1);
                        }
                        else{
                            simdikiKelime=swapTemp;
                        }
                        /*yanlis kelimenin duzgun halinin duzgun kelimelerin oldugu listeye alimi
                        (hatali kelimeyle karsilastirilmasini gostermek icin)*/
                        duzelmisYanlis.add(simdikiKelime);
                        //duzelmis hali bulununca while donusunu durdurmak icin break kullanildi
                        break;
                    }
                    m++;
                }
                //duzeltilmeyecek hatali kelime kontrol ve duzeltilmeyecek hatali listeye alim
                if(!wordsList.contains(swapTemp)&&!wordsList.contains(simdikiKelime)){
                    duzelmeyenYanlis.add(simdikiKelime);
                }

            }
        }
        /*hatali kelimelerin replace araciligiyla duzeltildigi ve bize duzelmis halini string olarak veren daha sonra
        da string buffera kullanicinin karsisina cikacak mesaji dolduran dongu
        */
        Iterator<String> duzelenIt=duzelenYanlis.iterator();
        Iterator<String> duzelmisIt=duzelmisYanlis.iterator();
        StringBuilder sb1=new StringBuilder();
        sb1.append("Düzeltilen Hatalı Kelimeler: \n");
        while(duzelenIt.hasNext()){
            String simdikiDuzelen=duzelenIt.next();
            String simdikiDuzelmis=duzelmisIt.next();
            duzelmis=duzelmis.replace(simdikiDuzelen,simdikiDuzelmis);

            sb1.append(simdikiDuzelen);
            sb1.append(" -> ");
            sb1.append(simdikiDuzelmis);
            sb1.append("\n");
        }
        //String bufferin devaminin dolumu(while dahil)
        sb1.append("\nDüzeltilemeyen Hatalı Kelimeler: \n");

        Iterator<String> duzelmeyenIt=duzelmeyenYanlis.iterator();
        while (duzelmeyenIt.hasNext()){
            String s=duzelmeyenIt.next();
            sb1.append(s);
            sb1.append("\n");
        }

        //metodun geri dongurecegi icerik ataniyor.
        String[] returnArray = new String[2];

        returnArray[0] = duzelmis;
        returnArray[1] = sb1.toString();

        return returnArray;
    }
}
