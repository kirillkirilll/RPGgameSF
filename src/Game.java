import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
    private static BufferedReader br;
    private static Character player = null;
    private static Battle battle = null;

    public static void main(String[] args) {
        br = new BufferedReader(new InputStreamReader(System.in));
        battle = new Battle();
        System.out.println("Введите имя персонажа:");
        try {
            command(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void command(String string) throws IOException {
        if (player == null) {
            player = new Hero(string, 100, 0, 20, 0, 20);
            System.out.printf("Спасти наш мир от нечисти вызвался %s!%n", player.getName());
            printNavigation();
        }
        switch (string) {
            case "1": {
                System.out.println("Торговец еще не приехал");
                command(br.readLine());
            }
            break;
            case "2": {
                commitFight();
            }
            break;
            case "3":
                System.exit(1);
                break;
            case "да":
                command("2");
                break;
            case "нет": {
                printNavigation();
                command(br.readLine());
            }
        }
        command(br.readLine());
    }

    private static void commitFight() {
        battle.fight(player, spawnMonster(), new Auxiliary() {
            @Override
            public void winFight() {
                System.out.printf("%s победил! Теперь у вас %d опыта и %d золота, а также осталось %d едениц здоровья.%n", player.getName(), player.getXp(), player.getGold(), player.getHealthPoints());
                System.out.println("Желаете продолжить поход или вернуться в город? (да/нет)");
                try {
                    command(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void loseFight() {

            }
        });
    }

    private static void printNavigation() {
        System.out.println("Куда вы хотите пойти?");
        System.out.println("1. К Торговцу");
        System.out.println("2. В темный лес");
        System.out.println("3. Выход");
    }

    private static Character spawnMonster() {
        int random = (int) (Math.random() * 10);
        if (random % 2 == 0) return new Goblin("Гоблин", 50, 20, 10, 100, 10
        );
        else return new Skeleton("Скелет", 25, 10, 20, 100, 20
        );
    }
}
