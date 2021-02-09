package com.guestbook.GuestbookService;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestBookRepository extends JpaRepository<GuestEntity, Long> {
}
