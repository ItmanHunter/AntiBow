package com.supermistmc.antibow.commands.anitbow;

import com.supermistmc.antibow.commands.BaseCommand;
import com.supermistmc.antibow.commands.anitbow.subcommands.*;

public class AntiBowCommand extends BaseCommand {

    public final static String COMMAND = "antiBow";

    public AntiBowCommand() {
        addSubCommand(new AntiBowSubCreateRegion());
        addSubCommand(new AntiBowSubDeleteRegion());
        addSubCommand(new AntiBowSubHelp());
        addSubCommand(new AntiBowSubListRegions());
        addSubCommand(new AntiBowSubReloadPlugin());
        addSubCommand(new AntiBowSubSetFirstPosition());
        addSubCommand(new AntiBowSubSetSecondPosition());
    }
}
