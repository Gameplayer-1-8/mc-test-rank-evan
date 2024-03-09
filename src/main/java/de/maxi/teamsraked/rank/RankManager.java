package de.maxi.teamsraked.rank;

import de.maxi.teamsraked.sql.RankDB;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;

public class RankManager {
    public static HashMap<String, Rank> rankCache = new HashMap<>();
    public static void applyRankToPlayer(Player p) {
        if(!rankCache.containsKey(p.getUniqueId().toString())) {
            Rank rank = RankDB.getRankOfPlayer(p.getUniqueId().toString());
            rankCache.put(p.getUniqueId().toString(), rank);
        }
        Bukkit.getOnlinePlayers().forEach((all) -> {
            Scoreboard sb = all.getScoreboard();
            Team t = sb.getTeam(rankCache.get(p.getUniqueId().toString()).getName());
            t.addPlayer(p);
        });
    }
    public static void updatePlayerRank(Player p, RankType rankType){
        Bukkit.getOnlinePlayers().forEach((all) -> {
            Scoreboard sb = all.getScoreboard();
            Team t = sb.getTeam(rankCache.get(p.getUniqueId().toString()).getName());
            t.removePlayer(p);
        });
        rankCache.remove(p.getUniqueId().toString());
        rankCache.put(p.getUniqueId().toString(), new Rank(rankType));
        Bukkit.getOnlinePlayers().forEach((all) -> {
            Scoreboard sb = all.getScoreboard();
            Team t = sb.getTeam(rankCache.get(p.getUniqueId().toString()).getName());
            t.addPlayer(p);
        });

    }
}
