package org.example;

import static org.mockito.Mockito.*;

import org.example.dataModel.Owner;
import org.example.interfaces.ICatDao;
import org.example.interfaces.IOwnerDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OwnerDaoTest {
    private IOwnerDao ownerDao;
    private ICatDao catDao;

    @BeforeEach
    void setUp() {
        ownerDao = mock(OwnerDao.class);
        catDao = mock(CatDao.class);
    }

    @Test
    void saveOwnerTest(){
        Owner owner = new Owner("Vitya");

        when(ownerDao.save(owner)).thenReturn(owner);

        Owner result = ownerDao.save(owner);

        assertNotNull(result);

        verify(ownerDao, times(1)).save(owner);
    }

    @Test
    void updateOwnerTest(){
        Owner owner = new Owner("Vitya");

        when(ownerDao.save(owner)).thenReturn(owner);
        when(ownerDao.update(owner)).thenReturn(owner);

        ownerDao.save(owner);
        owner.setName("Vasya");
        Owner result = ownerDao.update(owner);

        assertNotNull(result);

        verify(ownerDao, times(1)).save(owner);
        verify(ownerDao, times(1)).update(owner);
    }

    @Test
    void findAllTest(){
        Owner owner1 = new Owner("Roma");
        Owner owner2 = new Owner("Rama");

        List<Owner> owners = new ArrayList<>();
        owners.add(owner1);
        owners.add(owner2);

        when(ownerDao.findAll()).thenReturn(owners);

        List<Owner> result = ownerDao.findAll();

        assertFalse(result.isEmpty());

        verify(ownerDao, times(1)).findAll();
    }
}
