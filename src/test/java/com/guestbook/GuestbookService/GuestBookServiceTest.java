package com.guestbook.GuestbookService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GuestBookServiceTest {
    @Mock
    GuestBookRepository repository;
    @InjectMocks
    GuestBookService service;

    @Test
    public void addEntry(){
        Guest guest = new Guest("jack","Good");
        GuestEntity entity = new GuestEntity("jack", "Good");
        when(repository.save(entity)).thenReturn(entity);
        Guest actualGuest = service.addEntry(guest);
        verify(repository,times(1)).save(entity);
        assertEquals(guest.getName(), actualGuest.getName());
        assertEquals(guest.getComments(), actualGuest.getComments());

    }

    @Test
    public void viewEntries(){
        Guest guest = new Guest("jack","Good");
        Guest guest1 = new Guest("jill","Good");

        GuestEntity entity = new GuestEntity("jack", "Good");
        GuestEntity entity1 = new GuestEntity("jill", "Good");

        when(repository.findAll()).thenReturn(List.of(entity,entity1));
        List<Guest> actualGuest = service.viewEntries();
        verify(repository,times(1)).findAll();
        assertEquals(guest.getName(), actualGuest.get(0).getName());
        assertEquals(guest.getComments(), actualGuest.get(0).getComments());
        assertEquals(guest1.getName(), actualGuest.get(1).getName());
        assertEquals(guest1.getComments(), actualGuest.get(1).getComments());
    }
}
