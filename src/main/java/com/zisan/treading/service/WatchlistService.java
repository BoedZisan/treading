package com.zisan.treading.service;

import com.zisan.treading.modal.Coin;
import com.zisan.treading.modal.User;
import com.zisan.treading.modal.Watchlist;

public interface WatchlistService {

    Watchlist findUserWatchlist(Long userId) throws Exception;

    Watchlist createWatchlist(User user);

    Watchlist findById(Long id) throws Exception;

    Coin addItemToWatchlist(Coin coin, User user) throws Exception;
}

