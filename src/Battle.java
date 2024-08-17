public class Battle {
    public void fight(Character hero, Character monster, Auxiliary auxiliary) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int turn = 1;
                boolean isFight = false;

                while(!isFight) {
                    System.out.println("Ход " + turn);
                    if (turn % 2 != 0) {
                        isFight = hit(hero, monster, auxiliary);
                        turn++;
                    } else {
                        isFight = hit(monster, hero, auxiliary);
                        turn++;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private Boolean hit(Character defender, Character attacker, Auxiliary auxiliary) {
        int attack = attacker.attack();
        int defenderHealth = defender.getHealthPoints() - attack;
        if (attack != 0) {
            System.out.println(String.format("%s Нанес удар в %d единиц!", attacker.getName(), attack));
            System.out.println(String.format("У %s осталось %d единиц здоровья...", defender.getName(), defenderHealth));
        } else {
            System.out.println(String.format("%s промахнулся!", attacker.getName()));
        }
        if (defenderHealth <= 0 && defender instanceof Hero) {
            System.out.println("Вы пали в бою...");
            auxiliary.loseFight();
            return true;
        } else if(defenderHealth <= 0) {
            System.out.println(String.format("Враг повержен! Вы получаете %d опыт и %d золота", defender.getXp(), defender.getGold()));
            attacker.setXp(attacker.getXp() + defender.getXp());
            attacker.setGold(attacker.getGold() + defender.getGold());
            auxiliary.winFight();
            return true;
        } else {
            defender.setHealthPoints(defenderHealth);
            return false;
        }
    }

    /*public void hit(Character attacker, Character defender) {
        int attack = attacker.attack();
        int defenderHP = defender.getHealthPoint();
        if (attack != 0) {
            System.out.printf("%s нанес удар с силой в %d единиц", attacker.getName(), attack);
            if (defenderHP - attack <= 0) {
                System.out.printf("%s одержал поражение" + defender.getName());
                //Дописать
            }
            defender.setHealthPoint(defenderHP - attack);
        } else {
            System.out.printf("%s промахнулся", attacker.getName());
        }
    }*/
}

