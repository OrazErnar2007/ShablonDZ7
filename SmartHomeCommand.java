import java.util.*;

interface ICommand {
    void execute();
    void undo();
}

class Light {
    public void on() { System.out.println("Свет включен"); }
    public void off() { System.out.println("Свет выключен"); }
}

class Door {
    public void open() { System.out.println("Дверь открыта"); }
    public void close() { System.out.println("Дверь закрыта"); }
}

class Thermostat {
    private int temperature = 20;

    public void increase() {
        temperature++;
        System.out.println("Температура увеличена: " + temperature);
    }

    public void decrease() {
        temperature--;
        System.out.println("Температура уменьшена: " + temperature);
    }
}

class LightOnCommand implements ICommand {
    private Light light;
    public LightOnCommand(Light light) { this.light = light; }
    public void execute() { light.on(); }
    public void undo() { light.off(); }
}

class LightOffCommand implements ICommand {
    private Light light;
    public LightOffCommand(Light light) { this.light = light; }
    public void execute() { light.off(); }
    public void undo() { light.on(); }
}

class DoorOpenCommand implements ICommand {
    private Door door;
    public DoorOpenCommand(Door door) { this.door = door; }
    public void execute() { door.open(); }
    public void undo() { door.close(); }
}

class TemperatureUpCommand implements ICommand {
    private Thermostat thermostat;
    public TemperatureUpCommand(Thermostat thermostat) { this.thermostat = thermostat; }
    public void execute() { thermostat.increase(); }
    public void undo() { thermostat.decrease(); }
}

class RemoteControl {
    private Stack<ICommand> history = new Stack<>();

    public void press(ICommand command) {
        command.execute();
        history.push(command);
    }

    public void undo() {
        if(history.isEmpty()) {
            System.out.println("Нет команд для отмены!");
            return;
        }
        ICommand cmd = history.pop();
        cmd.undo();
    }
}

public class SmartHomeCommand {
    public static void main(String[] args) {

        Light light = new Light();
        Door door = new Door();
        Thermostat thermostat = new Thermostat();

        RemoteControl remote = new RemoteControl();

        remote.press(new LightOnCommand(light));
        remote.press(new DoorOpenCommand(door));
        remote.press(new TemperatureUpCommand(thermostat));

        System.out.println("=== Отмена ===");
        remote.undo();
        remote.undo();
    }
}
