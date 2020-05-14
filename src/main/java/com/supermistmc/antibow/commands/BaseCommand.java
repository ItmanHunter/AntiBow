package com.supermistmc.antibow.commands;
import lombok.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

@Data
public class BaseCommand implements CommandExecutor {

    private JavaPlugin plugin;

    private List<AbstractSubCommand> subCommandList;

    public BaseCommand() {
        subCommandList = new ArrayList<>();
    }

    public void addSubCommand(AbstractSubCommand subCommand) {
        subCommand.setParentCommand(this);
        subCommandList.add(subCommand);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        for (AbstractSubCommand abstractSubCommand:subCommandList) {
            if (abstractSubCommand.canExecute(
                    sender,command,label,args
            )) {
                return abstractSubCommand.executeCommand(
                        sender,
                        command,
                        label,
                        args
                );
            }
        }
        return false;
    }
}