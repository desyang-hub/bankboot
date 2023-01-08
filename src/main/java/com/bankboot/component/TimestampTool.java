package com.bankboot.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class TimestampTool {

    Timestamp timestamp;

    @Autowired
    public TimestampTool(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Timestamp setTime(Long time) {
        timestamp.setTime(time);
        return timestamp;
    }
}
