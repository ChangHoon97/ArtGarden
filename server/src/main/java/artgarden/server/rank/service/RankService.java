package artgarden.server.rank.service;

import artgarden.server.rank.entity.WeeklyRank;

import java.time.LocalDate;

public interface RankService {
    public WeeklyRank findByRankDate(LocalDate rankDate);
}
