package com.example.nutri_well.dao;

import com.example.nutri_well.entity.BookMark;
import com.example.nutri_well.repository.BookMarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookMarkDAOImpl implements BookMarkDAO{
    private final BookMarkRepository bookMarkRepository;

    @Override
    public BookMark update(BookMark bookMark) {
        return bookMarkRepository.save(bookMark);
    }

    @Override
    public BookMark findByFoodIdAndUserId(Long foodId, Long userId) {
        return bookMarkRepository.findByFoodIdAndUser_UserId(foodId, userId);
    }

    @Override
    public int updatePreferredState(Long id, boolean preferredState) {
        return bookMarkRepository.updatePreferredState(id,preferredState);
    }

    @Override
    public int updateExcludedState(Long id, boolean excludedState) {
        return bookMarkRepository.updateExcludedState(id,excludedState);
    }
}
