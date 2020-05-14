package com.supermistmc.antibow.commands;

import lombok.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@Data
public abstract class AbstractSubCommand {
    private BaseCommand parentCommand;
    public abstract boolean canExecute(CommandSender sender, Command command, String label, String[] args);
    public abstract boolean executeCommand(CommandSender sender, Command command, String label, String[] args);
}
