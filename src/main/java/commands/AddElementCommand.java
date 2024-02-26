package commands;

import collections.SpaceMarine;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import managers.Console;
import managers.SpaceMarineInputManager;

/**
 * Command for adding an element to collection from user input.
 */
public class AddElementCommand extends AbstractCommand{
    private final SpaceMarineInputManager spaceMarineInputManager;
    private final CollectionManager collectionManager;

    /**
     * Add command constructor.
     * @param spaceMarineInputManager Input manager for add command.
     * @param collectionManager Collection manager for add command.
     */
    public AddElementCommand(SpaceMarineInputManager spaceMarineInputManager, CollectionManager collectionManager) {
        super("add", "Adds and element to collection");
        this.spaceMarineInputManager = spaceMarineInputManager;
        this.collectionManager = collectionManager;
    }

    /**
     * Adds element from user input.
     *
     * @param argument The argument passed to the command.
     * @return the response of right execution.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfArgumentsException();
            collectionManager.addToCollection(new SpaceMarine(
                    spaceMarineInputManager.setId(),
                    spaceMarineInputManager.askName(),
                    spaceMarineInputManager.askCoordinates(),
                    spaceMarineInputManager.askCreationDate(),
                    spaceMarineInputManager.askHealth(),
                    spaceMarineInputManager.askHeartCount(),
                    spaceMarineInputManager.askHeight(),
                    spaceMarineInputManager.askMeleeWeapon(),
                    spaceMarineInputManager.askChapter()
            ));
            Console.println("Space marine was added successfully!");
            return true;
        } catch (WrongAmountOfArgumentsException e) {
            Console.println(e.getMessage());
            return false;
        } catch (IncorrectInputInScriptException e) {
        }
        return false;
    }
}
