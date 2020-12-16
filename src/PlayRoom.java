//3. Создать класс PlayRoom
//        • Создать main метод.
//        • Создать массив с играми на физ. носителях (4 игры). (пользуемся методом
//        getDisk)
//        • Создать массив с играми из виртуального магазина (4 игры). (пользуемся
//        методом getVirtualGame)
//        • Создать GameConsole.
//        4. Реализация анонимного класса (Comparator)
//        • В методе main, отсортировать массив с физическими дисками по жанру.
//        • В методе main, отсортировать массив с виртуальными играми по рейтингу.
//        Для сравнения примитивов можно воспользоваться методом :
//        Integer.compare(a.getRating(), b.getRating())

import java.util.Arrays;
import java.util.Comparator;

public class PlayRoom {
    public static void main(String[] args) {
        Game.GameDisk[] disks = {
                Game.getDisk("GTA", Ganre.ACTION, "super1"),
                Game.getDisk("CS:GO", Ganre.ACTION, "super2"),
                Game.getDisk("Need for Speed", Ganre.RACE, "super3"),
                Game.getDisk("FIFA 2020", Ganre.SPORT, "super4"),
        };
        Game.VirtualGame[] games = {
                Game.getVirtualGame("Game1", Ganre.SPORT),
                Game.getVirtualGame("Game2", Ganre.ACTION),
                Game.getVirtualGame("Game3", Ganre.SPORT),
                Game.getVirtualGame("Game4", Ganre.RACE),
        };
GameConsole gameConsole = new GameConsole(Brand.SONY,"QWE123321");

//        gameConsole.getSecondGamepad().PowerOff();
//        System.out.println(gameConsole.getSecondGamepad().getConnectedNumber());

gameConsole.loadGame(disks[2].getDATA());
int count = 0;
while (count < 16) {
    gameConsole.playGame();
    count++;
}


Arrays.sort(disks, new Comparator<Game.GameDisk>() {
    @Override
    public int compare(Game.GameDisk o1, Game.GameDisk o2) {
        return o1.getDATA().getGanre().compareTo(o2.getDATA().getGanre());
    }
});
Arrays.sort(games, new Comparator<Game.VirtualGame>() {
    @Override
    public int compare(Game.VirtualGame o1, Game.VirtualGame o2) {
        return Integer.compare(o1.getRating(), o2.getRating());
    }
});
//        for (Game.GameDisk disk : disks) {
//            System.out.println(disk.getData().getGanre());
//
//        }
//        for (Game.VirtualGame game : games) {
//            System.out.println(game.getRating());
//        }

    }

    }

