package com.passGenerator.PassGenarator.Pessoa;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(classes = { CPFTest.class })
@ExtendWith(SpringExtension.class)
public class CPFTest {

    @Test
    void isValidCPF() {
        assertTrue(ValidaCPF.isCPF("10525900632"));
        assertTrue(ValidaCPF.isCPF("105.259.006-32"));
    }

    @Test
    void isValidEmailAddress() {
        assertTrue(ValidaEmail.isValidEmailAddress("brunobrandao147@gmail.com"));
    }

    @Test
    void isinValidEmailAddress() {
        assertFalse(ValidaEmail.isValidEmailAddress("dumb.com"));
    }
}
