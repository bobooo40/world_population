package com.napier.worldpopulation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Testing
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void choiceTestNull()
    {
        app.countries(1);
    }


}