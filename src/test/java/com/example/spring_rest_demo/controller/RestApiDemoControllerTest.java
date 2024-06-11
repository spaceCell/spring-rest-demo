package com.example.spring_rest_demo.controller;

import com.example.spring_rest_demo.entity.Coffee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RestApiDemoControllerTest {

    private RestApiDemoController controller;

    @BeforeEach
    void setUp() {
        controller = new RestApiDemoController();
    }

    @Test
    void getCoffees() {
        Iterable<Coffee> coffees = controller.getCoffees();
        assertNotNull(coffees);
        assertTrue(((List<Coffee>) coffees).size() > 0);
    }

    @Test
    void getCoffeeById() {
        Optional<Coffee> coffee = controller.getCoffeeById("1");
        assertTrue(coffee.isPresent());
        assertEquals("1", coffee.get().getId());
    }

    @Test
    void postCoffee() {
        Coffee newCoffee = new Coffee("20", "Latte");
        Coffee addCoffee = controller.postCoffee(newCoffee);
        assertNotNull(addCoffee);
        assertEquals("Latte", addCoffee.getName());
    }

    @Test
    void putCoffee() {
        Coffee updatedCoffee = new Coffee("Latte");
        ResponseEntity<Coffee> response = controller.putCoffee("1", updatedCoffee);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Latte", response.getBody().getName());
    }

    @Test
    void deleteCoffee() {
        controller.deleteCoffee("1");
        Optional<Coffee> coffee = controller.getCoffeeById("1");
        assertFalse(coffee.isPresent());
    }
}
