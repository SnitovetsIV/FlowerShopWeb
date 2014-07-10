package by.snitovets.flowershopweb.logic;

import by.snitovets.flowershopweb.exception.CommandException;
import by.snitovets.flowershopweb.logic.command.ChangeLanguageCommand;
import by.snitovets.flowershopweb.logic.command.ICommand;
import by.snitovets.flowershopweb.logic.command.NoCommand;
import by.snitovets.flowershopweb.logic.command.ShowFlowerCompositionCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by Илья on 05.07.2014.
 */
public class CommandFactory {

    public static final String PARAM_NAME_COMMAND = "command";
    public static final String NO_COMMAND_NAME = "noCommand";
    public static final String SHOW_FLOWER_COMPOSITION_COMMAND_NAME = "showFloralComposition";
    public static final String CHANGE_LANGUAGE_COMMAND_NAME = "changeLanguage";
    private final HashMap<String, ICommand> allCommands;

    private CommandFactory() {
        allCommands = new HashMap<>();
        allCommands.put(NO_COMMAND_NAME, new NoCommand());
        allCommands.put(SHOW_FLOWER_COMPOSITION_COMMAND_NAME, new ShowFlowerCompositionCommand());
        allCommands.put(CHANGE_LANGUAGE_COMMAND_NAME, new ChangeLanguageCommand());
    }

    public static CommandFactory getInstance() {
        return LazyRequestHelperHolder.singletonInstance;
    }

    public ICommand getCommand(HttpServletRequest request) throws CommandException {
        String action = request.getParameter(PARAM_NAME_COMMAND);
        ICommand command = allCommands.get(action);
        if (null == command) {
            command = allCommands.get(NO_COMMAND_NAME);
        }
        return command;
    }

    private static class LazyRequestHelperHolder {
        public static CommandFactory singletonInstance = new CommandFactory();
    }

}
