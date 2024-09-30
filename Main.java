
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Введите данные");
        Scanner scanner = new Scanner(System.in);        //вызываю сканер
        String exp = scanner.nextLine();             //cтрока example считывается с консоли при помощи метода nextLine
        char action;                                       //декларирую "действие"
        String[] data;                           //декларирую массив
        if (exp.contains(" + ")) {                          //деление строк по символам при помощи метода contains + - * /
            data = exp.split(" \\+ ");
            action = '+';
        } else if (exp.contains(" - ")) {
            data = exp.split(" - ");
            action = '-';
        } else if (exp.contains(" * ")) {
            data = exp.split(" \\* ");
            action = '*';
        } else if (exp.contains(" / ")){
            data = exp.split(" / ");
            action = '/';
        }
        else {
            throw new Exception("Некорректный знак действия");
        }
        if (!(data[0].startsWith("\"") || (data[0].endsWith("\"")))){ throw new Exception("Первым аргументом выражения, подаваемого на вход, должна быть строка");}

        if (action == '*' || action == '/') {                  //добавляю исключение которое позволит производить * и / только с данными типа int
            if (data[1].contains("\"") || !isNumeric(data[1])) throw new Exception("Строку можно умножать или делить только на число");
            int num = Integer.parseInt(data[1]);
            if (num < 1 || num > 10) {
                throw new Exception("Число должно быть в диапазоне от 1 до 10.");
            }
            }

        for (int i = 0; i < data.length; i++){                 //удаляю кавычки с каждого элемента массива при помощи метода replace
            data[i] = data[i].replace("\"", "");
        }
        if (data[0].length() > 10 || (data.length > 1 && data[1].length() > 10)) {
            throw new Exception("Калькулятор должен принимать на вход строки длинной не более 10 символов");
        }
        if (action == '+'){
            resInQuotes(data[0]+data[1]);
        } else if (action == '*') {
            int multiplier = Integer.parseInt(data[1]);            //конвертирую строку в число
            String result = "";   //создаю строчку с которой произвожу действие(вывожу нулевой элемент массива в количестве второго элемента массива)
            for (int i=0; i<multiplier; i++){
                result+=data[0];
            }
            resInQuotes(result);
        } else if (action == '-') {
            int index = data[0].indexOf(data[1]);   //добавляю метод, который считывает содержание второй строки в первой
            if(index == -1){                   //если вторая строка не содержится в первой, то вывожу первую строку
                resInQuotes(data[0]);
            }else{
                String result = data[0].substring(0, index);    // извлекаю из исходной строки подстроку начиная с индекса 0 до начала дублирования строкой 2
                result += data[0].substring(index+data[1].length()); // извлекаю подстроку из исходной строки начиная с конца дублируемого слова до конца исходной строчки
                resInQuotes(result);
            }
        }else {
            int newLen = data[0].length()/Integer.parseInt(data[1]); //
            String result = data[0].substring(0, newLen);
            resInQuotes(result);
        }
    }

    static void resInQuotes(String text){    //создаю метод, который позволяет выводить результат в кавычках и ставить "..." если результат больше 40 символов. дальше буду использовать его вместо sout
        if (text.length() > 40) {
            text = text.substring(0, 40) + "...";
        }
        System.out.println("\""+text+"\"");

}   static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}



