package com.example.nutri_well.dao;

import com.example.nutri_well.entity.BookMark;

public interface BookMarkDAO {
    BookMark update(BookMark bookMark);
    BookMark findByFoodIdAndUserId(Long foodId, Long userId);

    int updatePreferredState(Long id, boolean preferredState);
    int updateExcludedState(Long id, boolean excludedState);


}
