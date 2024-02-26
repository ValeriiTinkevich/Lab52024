package commands;

import collections.SpaceMarine;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfArgumentsException;
import managers.CollectionManager;
import managers.Console;
import managers.SpaceMarineInputManager;

import java.util.ArrayList;

/**
 * Command that removes all elements greater than inputted.
 */
public class RemoveGreaterCommand extends AbstractCommand{
    CollectionManager collectionManager;
    SpaceMarineInputManager spaceMarineInputManager;

    /**
     * Remove_greater command constructor.
     * @param collectionManager Collection manager for remove_greater command.
     * @param spaceMarineInputManager Input manager for remove_greater command.
     */
    public RemoveGreaterCommand(CollectionManager collectionManager, SpaceMarineInputManager spaceMarineInputManager) {
        super("remove_greater", "removes all elements greater than input");
        this.collectionManager  = collectionManager;
        this.spaceMarineInputManager = spaceMarineInputManager;
    }
    /**
     * Removes all elements greater than inputted.
     * @param argument The argument passed to the command.
     * @return the response of right execution.
     */
    @Override
    public boolean execute(String argument) {
        try {
            if(!argument.isEmpty()) throw new WrongAmountOfArgumentsException();
            int counter = 0;
            SpaceMarine greaterSpaceMarine = new SpaceMarine(
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
            ArrayList<SpaceMarine> tempList = new ArrayList<>();
            for (SpaceMarine spaceMarine: collectionManager.getSpaceMarineCollection()) {
                if(spaceMarine.compareTo(greaterSpaceMarine) > 0) {
                    tempList.add(spaceMarine);
                    counter++;
                }
            }
            collectionManager.getSpaceMarineCollection().removeAll(tempList);
            Console.println("Removed "+ counter + " Space marines" );
            return true;
        } catch (IncorrectInputInScriptException ignored) {
        } catch (WrongAmountOfArgumentsException e) {
            Console.println(e.getMessage());
            return false;
        }
        return false;
    }
}
