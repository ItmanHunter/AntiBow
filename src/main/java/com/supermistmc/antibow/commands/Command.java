package com.supermistmc.antibow.commands;

import com.supermistmc.antibow.commands.anitbow.AntiBowCommand;
import org.bukkit.plugin.java.JavaPlugin;

public enum Command {
    Currency(AntiBowCommand.COMMAND,new AntiBowCommand());

    private String commandString;
    private BaseCommand command;

    Command(String cmd, BaseCommand abstractCommand) {
        this.commandString = cmd;
        this.command = abstractCommand;
    }

    public BaseCommand getCommand() {
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
