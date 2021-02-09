package com.guestbook.GuestbookService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuestBookService {

    GuestBookRepository repository;

    public GuestBookService(GuestBookRepository repository){
        this.repository = repository;
    }

    public Guest addEntry(Guest guest) {
        return mapToGuest(repository.save(new GuestEntity(guest.getName(), guest.getComments())));
    }

    private Guest mapToGuest(GuestEntity entity) {
        return new Guest(entity.getName(), entity.getComments());
    }

    public List<Guest> viewEntries() {
        return repository.findAll()
                .stream()
                .map(this::mapToGuest)
                .collect(Collectors.toList());
    }
}
