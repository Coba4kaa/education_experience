package org.example;

import org.example.dataModel.ModelConfig;
import org.example.dataModel.Cat;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static junit.framework.Assert.assertEquals;

public class SpringTest {
    @Test
    public void testCatCreation() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ModelConfig.class);

        Cat cat = context.getBean(Cat.class);
        cat.setName("Cat");

        assertEquals("Cat", cat.getName());
    }
}
