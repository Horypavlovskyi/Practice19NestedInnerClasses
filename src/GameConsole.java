//1. Реализация внутреннего класса
//        • Создать класс GameConsole.
//        Описать поля:
//        brand (название производителя, например Sony, Microsoft. Можно оформить
//enum-ом),
//        model (название модели, например XBOX 360, PS4 PRO),
//        serial (серийный номер приставки, например XC123QeWR),
//        firstGamepad (объект для первого джойстика, который будет реализован как
//        внутренний класс),
//        secondGamepad (объект для второго джойстика),
//        isOn (флаг состояния. True – вкл, false - выкл)
//
//        • Создать внутренний (нестатический) класс Gamepad.
//        Описать поля:
//        brand (название производителя, например Sony, Microsoft).
//        consoleSerial (серийный номер консоли, к которой подключен джойстик),
//        connectedNumber (порядковый номер джойстика),
//        color (цвет джойстика, можно оформить enum-ом),
//        chargeLevel (значение процента заряда, по умолчанию поставить 100.0)
//        isOn (флаг состояния. True – вкл, false - выкл).
//
//        • Создать конструктор для класса GameConsole. Передать в него 2 параметра
//        (brand, serial)
//        • Создать конструктор для класса Gamepad. Передать в него параметр (brand и
//        connectedNumber), а полю consoleSerial присвоить значение серийного
//        номера приставки.
//        • Внутри конструктора создать и присвоить 2 джойстика (firstGamepad,
//        secondGamepad). Причем brand можно передать уже существующие для самой
//        консоли, а connectedNumber фиксированными значениями 1 и 2.
//        • Для полей которые не должны меняться (определите их сами :) ), применить
//        модификатор final, и создать геттеры.
//        • Для остальных полей создать геттеры и сеттеры.

public class GameConsole extends MyException implements Powered {
    private final Brand brand;
    private String model;
    private final String serial;
    private final Gamepad firstgamepad;
    private final Gamepad secondgamepad;
    private boolean isOn;
    private Game activeGame;
    private int waitingCounter;

    public GameConsole(Brand brand, String serial) {
        this.brand = brand;
        this.serial = serial;
        firstgamepad = new Gamepad(brand, 1);
        secondgamepad = new Gamepad(brand, 2);
    }

    public Brand getBrand() {
        return brand;
    }

    public String getSerial() {
        return serial;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Gamepad getFirstgamepad() {
        return firstgamepad;
    }

    public Gamepad getSecondgamepad() {
        return secondgamepad;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    @Override
    public void powerOn() {
        isOn = true;
    }

    @Override
    public void PowerOff() {
        isOn = false;
    }

    public void loadGame (Game game) {
        System.out.println("Игра загружается " + game.getNAME());
        activeGame = game;
    }


    public void playGame() {
        if (!isOn) {
            System.out.println("Играем в " + activeGame.getNAME() + "\nЗаряд 1-го джостика: " + firstgamepad.chargeLevel + " %" + "\nЗаряд 2-го джостика: " + secondgamepad.chargeLevel + " %");
            Gamepad[] gamepads = {firstgamepad, secondgamepad};
            for (Gamepad gamepad : gamepads) {
                if (gamepad.chargeLevel == 0.0) {
                    gamepad.PowerOff();
                    System.out.println("Game Over");
                }
                else if (!gamepad.isOn) {
                    gamepad.chargeLevel -= 10;
                    System.out.println("Остаток зарядки: " + gamepad.connectedNumber + " " + gamepad.chargeLevel);
                }
            }
        }
        checkStatus();
    }

    private void checkStatus() {
        if (firstgamepad.chargeLevel < 10 & secondgamepad.chargeLevel < 10) {
            System.out.println("Подключите джостик " + ++waitingCounter);
            try {
                if (waitingCounter > 5) throw new MyException();
                PowerOff();
            } catch (MyException e) {
                System.out.println("Приставка завершает работу из-за отсутсвия активности");
            }
        } else {
            System.out.println(waitingCounter = 0);
        }
    }

    public class Gamepad implements Powered {
        private final Brand brand;
        private final String consoleSerial;
        private int connectedNumber;
        private Color color;
        private double chargeLevel = 100.0;
        private boolean isOn;

        public Gamepad(Brand brand, int connectedNumber) {
            this.brand = brand;
            this.connectedNumber = connectedNumber;
            this.consoleSerial = serial;
        }

        public Brand getBrand() {
            return brand;
        }

        public String getConsoleSerial() {
            return consoleSerial;
        }

        public int getConnectedNumber() {
            return connectedNumber;
        }

        public void setConnectedNumber(int connectedNumber) {
            this.connectedNumber = connectedNumber;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public double getChargeLevel() {
            return chargeLevel;
        }

        public void setChargeLevel(double chargeLevel) {
            this.chargeLevel = chargeLevel;
        }

        public boolean isOn() {
            return isOn;
        }

        public void setOn(boolean on) {
            isOn = on;
        }

        @Override
        public void powerOn() {
            isOn = true;
            GameConsole.this.isOn = true;
            if (firstgamepad.isOn)
                secondgamepad.connectedNumber = 2;
            else {
                secondgamepad.connectedNumber = 1;
            }
        }

        @Override
        public void PowerOff() {
            isOn = false;
            if(!firstgamepad.isOn) {
                secondgamepad.connectedNumber = 1;
            }
        }
    }

}
