package managers;

import commands.ICommand;
import exceptions.ScriptRecursionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class that allows interaction with command line.
 */
public class Console {
    public static final String PS1 = "$ ";
    public static final String PS2 = "> ";

    private final CommandManager commandManager;
    private final Scanner userScanner;
    private final SpaceMarineInputManager spaceMarineInputManager;
    private final List<String> scriptStack = new ArrayList<>();

    /**
     * Console constructor
     * @param commandManager Command manager for console.
     * @param userScanner User scanner for console.
     * @param spaceMarineInputManager Input manager for console.
     */

    public Console(CommandManager commandManager, Scanner userScanner, SpaceMarineInputManager spaceMarineInputManager) {
        this.commandManager = commandManager;
        this.userScanner = userScanner;
        this.spaceMarineInputManager = spaceMarineInputManager;
    }

    /**
     * Print method.
     * @param obj Object to print
     */
    public static void print(Object obj) {
        System.out.print(obj);
    }
    /**
     * Print from new line method.
     * @param obj Object to print
     */
    public static void println(Object obj) {
        System.out.println(obj);
    }
    /**
     * Print error method.
     * @param obj Object to print
     */
    public static void printError(Object obj) {
        Console.println("Error: " + obj);
    }

    /**
     * Interactive mode setter.
     * Interactive mode is a console mode that constantly listens for command input.
     */
    public void interactiveMode() {
        while (userScanner.hasNext()) {
            Console.print(PS2);
            try {
                String[] userCommand;
                userCommand = (userScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                ICommand command = commandManager.commands.get(userCommand[0]);
                command.execute(userCommand[1]);
            } catch (NullPointerException e) {
                Console.printError("No such command!");
            }
        }

    }

    /**
     * Script mode setter.
     * Script mode is a console mode that executes command from script.
     * Script needs to contain 1 command every new line. No special characters.
     * @param argument Script file
     * @return Response of right execution of script.
     */
    public int scriptMode(String argument) {
        String[] userCommand;
        scriptStack.add(argument);
        try (Scanner scriptScanner = new Scanner(new File(argument))) {
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();
            Scanner tmpScanner = spaceMarineInputManager.getUserScanner();
            spaceMarineInputManager.setUserScanner(scriptScanner);
            spaceMarineInputManager.setScriptMode();
            do {
                userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                while (scriptScanner.hasNextLine() && userCommand[0].isEmpty()) {
                    userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                }
                Console.println(Console.PS1 + String.join(" ", userCommand));
                if (userCommand[0].equals("execute_script")) {
                    for (String script : scriptStack) {
                        if (userCommand[1].equals(script)) throw new ScriptRecursionException();
                    }
                }
                ICommand command = commandManager.commands.get(userCommand[0]);
                command.execute(userCommand[1]);
            } while (scriptScanner.hasNextLine());
            spaceMarineInputManager.setUserScanner(tmpScanner);
            spaceMarineInputManager.setUserMode();
            if (!(userCommand[0].equals("execute_script") && !userCommand[1].isEmpty()))
                Console.println("Check script for correct input data!");
            return 0;
        } catch (FileNotFoundException exception) {
            Console.printError("File was not found!");
        } catch (NoSuchElementException exception) {
            Console.printError("Script file is empty!");
        } catch (ScriptRecursionException exception) {
            Console.printError("Scripts can't be recursive!");
        } catch (IllegalStateException exception) {
            Console.printError("Unexpected error!");
            System.exit(0);
        } finally {
            scriptStack.remove(scriptStack.size() - 1);
        }
        return 1;
    }

}
