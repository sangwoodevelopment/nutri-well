package com.example.nutri_well.basket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
class BoardDAOImplTest {


//    @Test
//    @Disabled
//    public void test() {
//        dao.insert(new BasketDTO(5L, "title1", "content1"));
//        dao.insert(new BasketDTO(6L, "title2", "content2"));
//    }

//    @Test
//    @Disabled
//    public void readtest() {
//        System.out.println(dao.read("2"));
//    }
//
//    @Test
//    @Disabled
//    public void deletetest() {
//        dao.delete("2");
//    }
//
//    @Test
//    public void selecttest() {
//        System.out.println(dao.list());
//    }
}