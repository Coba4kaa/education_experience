package org.example;

import static org.mockito.Mockito.*;

import org.example.dataModel.Cat;
import org.example.interfaces.ICatDao;
import org.hibernate.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CatDaoTest {
    private ICatDao catDao;
    private Session session;

    @BeforeEach
    void setUp() {
        catDao = mock(CatDao.class);
        session = mock(Session.class);
        when(catDao.getSession()).thenReturn(session);
    }

    @Test
    void saveCatTest() {
        Cat newCat = new Cat("Bob", "british", "black");
        when(catDao.save(any(Cat.class))).thenReturn(newCat);

        Cat savedCat = catDao.save(newCat);

        assertEquals(newCat.getName(), savedCat.getName());

        verify(catDao, times(1)).save(newCat);
    }

    @Test
    void updateCatTest(){
        Cat cat = new Cat("Bib", "brit", "white");

        when(catDao.update(any(Cat.class))).thenAnswer(invocation -> {
            Cat updatedCat = invocation.getArgument(0);
            updatedCat.setName("Bob");
            return updatedCat;
        });

        Cat updatedCat = catDao.update(cat);

        assertEquals("Bob", updatedCat.getName());

        verify(catDao, times(1)).update(cat);
    }

    @Test
    void deleteCatTest(){
        Cat cat = new Cat("Bib", "brit", "white");
        when(catDao.findById(any(Long.class))).thenReturn(cat);

        catDao.delete(cat.getId());

        assertNull(catDao.findById(cat.getId()));

        verify(catDao, times(1)).delete(cat.getId());
    }

    @Test
    void findAllTest(){
        Cat cat1 = new Cat("Bib", "brit", "white");
        Cat cat2 = new Cat("Baobab", "amam", "black");
        List<Cat> cats = new ArrayList<>();
        cats.add(cat1);
        cats.add(cat2);
        when(catDao.findAll()).thenReturn(cats);

        List<Cat> returnedCats = catDao.findAll();

        assertEquals(cat1.getName(), returnedCats.get(0).getName());
        assertEquals(cat2.getName(), returnedCats.get(1).getName());

        verify(catDao, times(1)).findAll();
    }

    @Test
    void addFriendshipTest(){
        Cat cat1 = new Cat("Bib", "brit", "white");
        Cat cat2 = new Cat("Baobab", "amam", "black");

        catDao.addFriendship(cat1.getId(), cat2.getId());

        verify(catDao, times(1)).addFriendship(cat1.getId(), cat2.getId());
    }
}
