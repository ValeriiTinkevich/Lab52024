package commands;

import exceptions.WrongAmountOfArgumentsException;
import managers.Console;
import managers.SpaceMarineInputManager;

/**
 * Command for executing a script from a txt file.
 */
public class ExecuteScriptCommand extends AbstractCommand {
    SpaceMarineInputManager spaceMarineInputManager;
    Console console;

    /**
     * Execute command constructor
     * @param spaceMarineInputManager Input manager for execute command.
     */
    public ExecuteScriptCommand(SpaceMarineInputManager spaceMarineInputManager) {
        super("execute", "Executes a script from given file");
        this.spaceMarineInputManager = spaceMarineInputManager;
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    /**
     * Execute the script.
     *
     * @param argument The argument passed to the command.
     * @return the response of right execution.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfArgumentsException();
            spaceMarineInputManager.setScriptMode();
            console.scriptMode(argument);
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            Console.println(e.getMessage());
        }
        return false;
    }
}
