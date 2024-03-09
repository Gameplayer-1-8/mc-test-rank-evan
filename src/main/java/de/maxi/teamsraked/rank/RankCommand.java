package de.maxi.teamsraked.rank;

import de.maxi.teamsraked.sql.RankDB;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public class RankCommand implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender cs, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player p = (Player) cs;
        if(args.length == 2){
            String playerName = args[0];
            String rankName = args[1];
            OfflinePlayer target = Bukkit.getOfflinePlayer(playerName);
            if(!RankDB.isPlayerInDB(target.getUniqueId().toString())){
                p.sendMessage("§a[§cRank§a] §r§cSpieler ist weder online, noch konnte er in derDatenbank gefunden werden!");
                return false;
            }
            System.out.println(Arrays.stream(RankType.values()).toList());
            System.out.println(rankName.toUpperCase());
            if(RankType.valueOf(rankName) == null){
                p.sendMessage(rankName);
                p.sendMessage("§a[§cRank§a] §r§cDieser Rank existiert nicht!");
                return false;
            }
            RankType rt = RankType.valueOf(rankName);
            RankDB.updatePlayerRank(target.getUniqueId().toString(), target.getName(), rt);
            if(target.isOnline()) {
                RankManager.updatePlayerRank((Player) target, rt);
                p.sendMessage("§a[§cRank§a] §r§aRank wurde geändert!");
                return true;
            }else{
                p.sendMessage("§a[§cRank§a] §r§aRang wurde geändert, von einem Spieler, der nicht online ist!");
                return true;
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender cs, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        return null;
    }
}
