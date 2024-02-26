package commands;

import exceptions.WrongAmountOfArgumentsException;
import managers.Console;

import java.util.Map;

/**
 * Command that shows usage and description of all available commands.
 */
public class HelpCommand extends AbstractCommand {
    Map<String, ICommand> commands;

    /**
     * Help command constructor.
     * @param commands
     */
    public HelpCommand(Map<String, ICommand> commands) {
        super("help", "Displays help on available commands");
        this.commands = commands;
    }

    /**
     * Show list of usage and description of all available commands.
     * @param argument The argument passed to the command.
     * @return the response of right execution.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfArgumentsException();
            for (String name : commands.keySet()) {
                String value = commands.get(name).toString();
                Console.println(value);
            }


        } catch (WrongAmountOfArgumentsException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;

    }
}
