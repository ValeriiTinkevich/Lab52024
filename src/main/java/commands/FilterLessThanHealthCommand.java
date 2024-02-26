package commands;

import collections.Chapter;
import collections.SpaceMarine;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import managers.Console;
import managers.SpaceMarineInputManager;

/**
 * Shows filtered collection by less health.
 */
public class FilterLessThanHealthCommand extends AbstractCommand{
    CollectionManager collectionManager;

    /**
     * Filter_less_than command constructor.
     * @param collectionManager Collection manager for filter_less_than command.
     */
    public FilterLessThanHealthCommand(CollectionManager collectionManager) {
        super("filter_less_than_health", "Prints all space marines with health less than input");
        this.collectionManager = collectionManager;
    }

    /**
     * Show filtered elements that have less health than value.
     *
     * @param argument The argument passed to the command.
     * @return the response of right execution.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfArgumentsException();
            Integer healthAsked = Integer.parseInt(argument);
            for(SpaceMarine spaceMarine: collectionManager.getSpaceMarineCollection()) {
                if (spaceMarine.getHealth() < healthAsked) {
                    Console.println(spaceMarine + "\n===============");
                }
            }
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            Console.printError(e.getMessage());
            return false;
        }
    }
}

