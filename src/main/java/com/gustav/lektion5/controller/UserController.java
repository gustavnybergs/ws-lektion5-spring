package com.gustav.lektion5.controller;

import com.gustav.lektion5.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    // Enkel in-memory-lista för att kunna testa DELETE.
    private final List<User> users = new ArrayList<>(List.of(
            new User(1, "User1", "x", true),
            new User(2, "User2", "x", true),
            new User(3, "User3", "x", true)
    ));

    // GET för att visa aktuell lista (hjälper vid test).
    @GetMapping
    public List<User> all() { return users; }

    // Tar bort user på id. Returnerar 200 med objektet om träff, annars 404.
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).id() == id) {
                var removed = users.remove(i);
                return ResponseEntity.ok(removed);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
