package org.javabasics.serviceTest;

import static org.junit.jupiter.api.Assertions.*;
import org.javabasics.service.Service;
import org.javabasics.model.Magazzino;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ServiceTest {

    private Service service;

    @BeforeEach
    public void setUp() {
        Service.resetInstance(); // Resetta lo stato del singleton prima di ogni test
        service = Service.getInstance();
    }

    @Test
    public void testSingleton() {
        Service anotherInstance = Service.getInstance();
        assertSame(service, anotherInstance);
    }

    @Test
    public void testLoadData() {
        Magazzino magazzino = Service.getMagazzino();
        assertNotNull(magazzino);
    }
}
