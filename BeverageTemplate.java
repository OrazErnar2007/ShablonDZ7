import java.util.Scanner;

abstract class Beverage {

    public final void prepare() {
        boilWater();
        brew();
        pourInCup();
        if(customerWantsCondiments())
            addCondiments();
        System.out.println("Напиток готов!\n");
    }

    protected void boilWater() {
        System.out.println("Кипятим воду");
    }

    protected void pourInCup() {
        System.out.println("Наливаем в чашку");
    }

    protected abstract void brew();
    protected abstract void addCondiments();

    protected boolean customerWantsCondiments() {
        return true;
    }
}

class Tea extends Beverage {
    protected void brew() {
        System.out.println("Завариваем чай");
    }

    protected void addCondiments() {
        System.out.println("Добавляем лимон");
    }
}

class Coffee extends Beverage {

    protected void brew() {
        System.out.println("Завариваем кофе");
    }

    protected void addCondiments() {
        System.out.println("Добавляем сахар и молоко");
    }

    protected boolean customerWantsCondiments() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Добавить сахар и молоко? (yes/no): ");
        String answer = sc.nextLine();
        return answer.equalsIgnoreCase("yes");
    }
}

public class BeverageTemplate {
    public static void main(String[] args) {

        Beverage tea = new Tea();
        tea.prepare();

        Beverage coffee = new Coffee();
        coffee.prepare();
    }
}
