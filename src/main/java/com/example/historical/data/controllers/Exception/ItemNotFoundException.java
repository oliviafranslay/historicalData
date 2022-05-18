package com.example.historical.data.controllers.Exception;

import java.text.MessageFormat;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(final int id) {
        super(MessageFormat.format("Could not find item with id: {0}", id));
    }
}
