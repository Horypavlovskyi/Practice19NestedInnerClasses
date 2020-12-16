//2. Реализация вложенного статического класса
//        • Создать класс Game.
//        Описать поля (пометить final):
//        name (название игры),
//        ganre (жанр игры, например ACTION, SPORT, RACE. Можно оформить enumом),
//        type (тип носителя, например VIRTUAL, PHYSICAL. Можно оформить enumом прямо внутри класса Game),
//
//        • Создать приватный конструктор. В конструктор передать поля: name, ganre,
//        type.
//        • Создать getter-ы для полей.
//        • Создать вложенный (статический) класс GameDisk.
//        • Создать поле description (описание игры, пометить final)
//        • Создать поле Game data (final);
//
//        • В приватный конструктор передать поля: name, ganre, description.
//        • При вызове конструктора создавать экземпляр класса Game и
//        передавать в него параметры name, ganre и фиксированный type
//        соответствующий этому носителю.
//        • Также инициализировать поле с описанием.
//
//        • Создать геттеры
//        • Создать вложенный (статический) класс VirtualGame.
//        • Создать поле rating (рейтинг игры от 0 до 5)
//        • Создать поле Game data (final);

//        • В приватный конструктор передать поля: name, ganre.
//        • При вызове конструктора создавать экземпляр класса Game и
//        передавать в него параметры name, ganre и фиксированный type
//        соответствующий этому носителю.
//        • Создать необходимые геттеры/сеттеры


//        • В классе Game создать статический метод GameDisk getDisk(…){…} для
//        получения экземпляра класса (Паттерн static factory)
//        • В метод передать параметры name, ganre, description
//        • Внутри метода создать и вернуть экземпляр класса GameDisk
//        • Аналогично, в классе Game создать статический метод VirtualGame
//        getVirtualGame (…){…}
//        • В метод передать параметры name, ganre
//        • Внутри метода создать и вернуть экземпляр класса VirtualGame.
public class Game {
    private final String NAME;
    private Ganre ganre;
    private Type type;

    private Game(String name, Ganre ganre, Type type) {
        this.NAME = name;
        this.ganre = ganre;
        this.type = type;
    }


    public static GameDisk getDisk(String name, Ganre ganre, String description) {
        return new GameDisk(name, ganre, description);
    }

    public static VirtualGame getVirtualGame(String name, Ganre ganre) {
        return new VirtualGame(name, ganre);
    }

    public String getNAME() {
        return NAME;
    }

    public Ganre getGanre() {
        return ganre;
    }

    public Type getType() {
        return type;
    }

    public static class GameDisk {
        private final String DESCRIPTION;
        private final Game DATA;

        public GameDisk(String name, Ganre ganre, String description) {
            this.DESCRIPTION = description;
            DATA = new Game(name, ganre, Type.PHYSICAL);
        }

        public String getDESCRIPTION() {
            return DESCRIPTION;
        }

        public Game getDATA() {
            return DATA;
        }
    }

    public static class VirtualGame {
        private int rating = (int) (Math.random() * 5);
        private final Game data;

        public VirtualGame(String name, Ganre ganre) {
            data = new Game(name, ganre, Type.VIRTUAL);
        }

        public int getRating() {
            return rating;
        }

        public Game getData() {
            return data;
        }
    }
}
