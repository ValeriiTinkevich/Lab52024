package commands;

import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import managers.Console;

/**
 * Command that show information about collection.
 */
public class InfoCommand extends AbstractCommand {
    CollectionManager collectionManager;

    /**
     * Info command constructor
     * @param collectionManager Collection manager for info command.
     */
    public InfoCommand(CollectionManager collectionManager) {
        super("info", "Displays information about Collection");
        this.collectionManager = collectionManager;
    }

    /**
     * Shows information about collection.
     * @param argument The argument passed to the command.
     * @return the response of right execution.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfArgumentsException();
            Console.println("Collection used is: " + collectionManager.getSpaceMarineCollection()
                    .getClass()
                    .toString()
                    .replace("class java.util.", ""));
            Console.println("Initialization time is: " + collectionManager.getCreationDate().toString());
            Console.println("Current size of collection is: " + collectionManager.getSize() + " elements");
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            Console.println(e.getMessage());
            return false;
        }
    }

}
