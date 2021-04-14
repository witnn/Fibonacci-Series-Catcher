// Fibonacci series catcher ; Author : witnn

package com.company;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Main {
    public static void main(String[] args)
    {
        // Fib. serisine uyan sayıların bulunduğu liste.
        ArrayList<Integer> fibonacciDizisineUyanSayilar = new ArrayList<>();

        // Sayı üretme
        System.out.println("Üretilen Sayılar");
        int[] sayilar = new int[400];
        for(int i = 0; i < sayilar.length; i++)
        {
            // Sayılar 2 ile 6 basamak arasında rastgele bir sayıya atanır.
            sayilar[i] = rastgeleSayi(10,999999);

            // Atanan sayı print edilir
            System.out.println(sayilar[i]);

            // Atanan sayının fibonacci dizisine uygun olup olmadığı kontrol edilir.
            if(isContainsFibonacci(sayilar[i]))
            {
                fibonacciDizisineUyanSayilar.add(sayilar[i]);
            }
        }

        System.out.println("Fibonacci serisine uyan sayılar : "+ fibonacciDizisineUyanSayilar);

        // Eğer isterseniz kendi girdiğiniz bir sayının Fibonacci dizisine uyup uymadığı deneyebilirsiniz.
        // System.out.println(isContainsFibonacci( SAYIYI BURAYA GİRİN ));

    }
    // Giren sayının fibonacci dizisine uygun olup olmadığını kontrol eden metot.
    static boolean isContainsFibonacci(int number)
    {
        int gelenSayi = number;

        // giren sayıyı rakamlarına ayırma
        ArrayList<Integer> bolunmusSayi = rakam(gelenSayi);

        // Rakamlarına ayrılmış fibonacci dizisinin oluştuğu kısım
        ArrayList<Integer> fibSerisi = fibBolme(fib(29));

        // sayımızın ilk hanesinin fibonacci dizisinde bulunduğu index değerleri
        ArrayList<Integer> indexDegerleri = IndexBul(fibSerisi, bolunmusSayi.get(0));

        // index değerlerinden birisi eğer fib serisi boyutu ile sayımızın hane miktarının
        // farkından büyükse o index değeri geçersiz sayılır "Hatanın önüne geçer"
        for(int i = 0; i < indexDegerleri.size(); i++){
            if(indexDegerleri.get(i) > fibSerisi.size() - bolunmusSayi.size()){
                indexDegerleri.remove(i);
            }
        }

        // tüm index değerleri için dönen döngü ve sayının uygunluğunun kontrolü
        boolean isContainsFib = false;
        for(int i = 0; i < indexDegerleri.size(); i ++)
        {
            // eğer index değeri + n .nci rakam bizim sayımızın n. rakamına eşitse
            // eşit rakam sayısı bir artar, tüm rakamlar eşit çıkarsa o sayı fibonacci
            // serisine uygun bir sayıdır.
            int equalCount = 0;
            for(int j = 1; j < bolunmusSayi.size() ; j ++)
            {
                if(fibSerisi.get(indexDegerleri.get(i) + j) == bolunmusSayi.get(j)){
                    equalCount += 1;
                }
            }
            if(equalCount == bolunmusSayi.size() - 1)
            {
                isContainsFib = true;
                break;
            }
        }
        if(isContainsFib){
           return true;
        }
        else{
           return false;
        }
    }
    // bir ArrayList'te girilen sayının varsa hangi indexlerde olduğunu gösterir
    static ArrayList<Integer> IndexBul(ArrayList<Integer> arrayList, int number)
    {
        ArrayList<Integer> indexValues = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++)
        {
            // girilen arraylistin i. elemanı girilen sayıya eşitse i sayısı index belirtmiş olur.
            if(arrayList.get(i) == number)
            {
                indexValues.add(i);
            }
        }
        return indexValues;
    }
    // Fibonacci Arraylistindeki birden fazla basamaklı sayıları
    // tek basamaklı hale getirip yeniden bir ArrayList içerisinde toplama.
    static ArrayList<Integer> fibBolme(ArrayList<Integer> fib)
    {
        ArrayList<Integer> bolunmusFibonacci = new ArrayList<Integer>();
        for(int i = 0; i < fib.size(); i++)
        {
            // Fibonacci listesindeki i. elemanın rakamlarına arılmış hali "elemanlar"
            // isimli listede tutulur.
            ArrayList<Integer> elemanlar = rakam(fib.get(i));

            // elemanlar listesindeki değerler bolunmusFibonacci listesine eklenir.
            for (int j = 0; j < elemanlar.size(); j++)
            {
                bolunmusFibonacci.add(elemanlar.get(j));
            }
        }
        return bolunmusFibonacci;
    }
    // birden fazla basamaklı sayıları tek basamaklı şekilde bölüp Arraylist içinde toplama.
    static ArrayList<Integer> rakam(int number)
    {
        ArrayList<Integer> stack = new ArrayList<>();
        int num = number;
        // Sayının önce 10 a bölümünden kalanı ile son basamağındaki değer bulunup listeye eklenir.
        // sonra sayı 10 a bölünür ve bir basamak küçülmüş olur. bu durum kendini tekrarlar.
        while(num >= 1)
        {
            stack.add(num % 10);
            num = num / 10;
        }
        // listeyi tersine sıralama
        Collections.reverse(stack);
        return stack;
    }
    // Fibonacci serisi oluşturucu.
    static ArrayList<Integer> fib(int max)
    {
        // fibonacci serisi elemanları Arraylist içinde tutulur.
        ArrayList<Integer> fibonacci = new ArrayList<Integer>();
        // Döngü ile yapılan basit bir fibonacci serisi oluşturucu.
        fibonacci.add(1);
        int a = 0;
        int b = 1;
        int c;
        for(int i = 0; i < max; i ++)
        {
            c = a + b;
            fibonacci.add(c);
            a = b;
            b = c;
        }
        return fibonacci;
    }
    // Rastgele sayı oluşturucu
    static int rastgeleSayi(int min, int max)
    {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
