package commands;

import collections.SpaceMarine;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import managers.Console;
import managers.SpaceMarineInputManager;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * Command for adding element to collection if its value is more than max in the collection.
 */
public class AddIfMaxCommand extends AbstractCommand{
    SpaceMarineInputManager spaceMarineInputManager;
    CollectionManager collectionManager;

    /**
     * Add if max command constructor
     * @param spaceMarineInputManager Input manager for add_if_max command.
     * @param collectionManager Collection manager for add_if_max command.
     */
    public AddIfMaxCommand(SpaceMarineInputManager spaceMarineInputManager, CollectionManager collectionManager) {
        super("add_if_max", "adds Space Marine");
        this.spaceMarineInputManager = spaceMarineInputManager;
        this.collectionManager = collectionManager;
    }
    /**
     * Adds asked element if its value is more than collections max object.
     *
     * @param argument The argument passed to the command.
     * @return the response of right execution.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if(!argument.isEmpty()) throw new WrongAmountOfArgumentsException();
            SpaceMarine askedMarine = new SpaceMarine(
                    spaceMarineInputManager.setId(),
                    spaceMarineInputManager.askName(),
                    spaceMarineInputManager.askCoordinates(),
                    spaceMarineInputManager.askCreationDate(),
                    spaceMarineInputManager.askHealth(),
                    spaceMarineInputManager.askHeartCount(),
                    spaceMarineInputManager.askHeight(),
                    spaceMarineInputManager.askMeleeWeapon(),
                    spaceMarineInputManager.askChapter()
            );
            if (collectionManager.getSize() == 0) {
                collectionManager.addToCollection(askedMarine);
                Console.println("Space marine was added successfully");
                return true;
            }
            SpaceMarine maxSpaceMarine = collectionManager.getSpaceMarineCollection()
                    .stream()
                    .max(Comparator.comparing(SpaceMarine::getHeight))
                    .orElseThrow(NoSuchElementException::new);
            if(askedMarine.compareTo(maxSpaceMarine) > 0) {
                collectionManager.addToCollection(askedMarine);
                Console.println("Space marine added successfully");
            } else {
                Console.println("Height is not enough to add!");
            }
        } catch (WrongAmountOfArgumentsException e) {
            Console.println(e.getMessage());
            return false;
        } catch (IncorrectInputInScriptException ignore) {
            return false;
        }
        return false;

    }
}
