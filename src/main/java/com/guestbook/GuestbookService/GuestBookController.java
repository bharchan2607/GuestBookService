package com.guestbook.GuestbookService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GuestBookController {

    GuestBookService service;

    public GuestBookController(GuestBookService service){
        this.service = service;
    }

    @PostMapping("/entry")
    @ResponseStatus(HttpStatus.CREATED)
    public Guest addEntry(@RequestBody Guest guest){
        return service.addEntry(guest);
    }

    @GetMapping("/entry")
    public List<Guest> viewEntries(){
        return service.viewEntries();
    }
}
