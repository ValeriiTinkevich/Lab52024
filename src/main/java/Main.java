import commands.ExecuteScriptCommand;
import managers.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner userScanner = new Scanner(System.in);
        String fileName = System.getenv("VAR1");
        if (fileName == null) fileName = "";
        //String fileName = "spaceMarine.csv";
        String splitString = ";";
        FileManager fileManager = new FileManager(fileName, splitString);

        CollectionManager collectionManager = new CollectionManager();
        SpaceMarineInputManager spaceMarineInputManager = new SpaceMarineInputManager(collectionManager, userScanner);
        CommandManager cm = new CommandManager(collectionManager, spaceMarineInputManager, fileManager);

        Console cs = new Console(cm, userScanner, spaceMarineInputManager);
        Console.println("Using env filename \"" + System.getenv("VAR1") + "\"" );
        collectionManager.setSpaceMarineCollection(fileManager.readCollection());
        ExecuteScriptCommand command = (ExecuteScriptCommand) cm.getCommands().get("execute");
        command.setConsole(cs);
        cs.interactiveMode();

    }
}
