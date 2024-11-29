package kz.kuanysh.bot.parsing;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HMSTRPriceParser {
    public static String getHMSTRPrice(){
        try {
            String url = "https://www.binance.com/ru-KZ/price/hamster-kombat/KZT";

            Document doc = Jsoup.connect(url).get();

            // Находим элемент, который содержит курс токена (например, по классу или ID)
            Elements priceElement = doc.select("div.css-1bwgsh3"); // Замените на правильный селектор
            Element firstPriceElement = priceElement.get(0); // Получаем первый элемент
            String priceText = firstPriceElement.text(); // Получаем текст внутри тега

            return priceText;
//            // Извлекаем и выводим курс
//            if (priceElement != null) {
//                String price = priceElement.text();
//                System.out.println("Курс токена: " + price);
//            } else {
//                System.out.println("Элемент с курсом не найден");
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "типо валюта";
    }
}
