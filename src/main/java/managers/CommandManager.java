package managers;

import commands.*;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    public final Map<String, ICommand> commands;
    //private final String[] commandHistory = new String[COMMAND_HISTORY_SIZE];
    SpaceMarineInputManager spaceMarineInputManager;
    CollectionManager collectionManager;
    FileManager fileManager;

    /**
     * Command manager constructor
     * Adds all commands that are available.
     * @param collectionManager Collection manager of command manager
     * @param spaceMarineInputManager Input manager of command manager
     * @param fileManager File manager of command manager
     */
    public CommandManager(CollectionManager collectionManager, SpaceMarineInputManager spaceMarineInputManager, FileManager fileManager) {
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
        this.spaceMarineInputManager = spaceMarineInputManager;
        commands = new HashMap<>();
        commands.put("help", new HelpCommand(this.commands));
        commands.put("info", new InfoCommand(this.collectionManager));
        commands.put("add", new AddElementCommand(this.spaceMarineInputManager, this.collectionManager));
        commands.put("show", new ShowCommand(this.collectionManager));
        commands.put("update", new UpdateByIdCommand(this.collectionManager, this.spaceMarineInputManager));
        commands.put("remove_by_id", new RemoveByIdCommand(this.collectionManager));
        commands.put("exit", new ExitCommand());
        commands.put("remove_at", new RemoveAtCommand(this.collectionManager));
        commands.put("add_if_max", new AddIfMaxCommand(this.spaceMarineInputManager, this.collectionManager));
        commands.put("remove_greater", new RemoveGreaterCommand(this.collectionManager, this.spaceMarineInputManager));
        commands.put("filter_by_chapter", new FilterByChapterCommand(this.collectionManager, this.spaceMarineInputManager));
        commands.put("print_unique_heart_count", new PrintUniqueHeartCountCommand(this.collectionManager));
        commands.put("filter_less_than_health", new FilterLessThanHealthCommand(this.collectionManager));
        commands.put("save", new SaveCommand(this.collectionManager, this.fileManager));
        commands.put("execute", new ExecuteScriptCommand(this.spaceMarineInputManager));
    }

    /**
     * Returns the Map of commands.
     *
     * @return returns Map of commands.
     */
    public Map<String, ICommand> getCommands() {
        return commands;
    }


}
