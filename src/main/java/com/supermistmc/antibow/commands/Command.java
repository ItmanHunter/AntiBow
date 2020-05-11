package com.supermistmc.antibow.commands;

import org.bukkit.plugin.java.JavaPlugin;

public enum Command {
    Currency(AntiBowCommand.COMMAND,new AntiBowCommand());

    private String commandString;
    private AbstractCommand command;

    Command(String cmd, AbstractCommand abstractCommand) {
        this.commandString = cmd;
        this.command = abstractCommand;
    }

    public AbstractCommand getCommand() {
        return command;
    }

    public static void registerCommands(JavaPlugin plugin) {
        for(Command command: Command.values()) {
            command.getCommand().setPlugin(plugin);
            plugin.getCommand(command.getCommandString())
                    .setExecutor(command.getCommand());
        }
    }

    public String getCommandString() {
        return commandString;
    }
}
