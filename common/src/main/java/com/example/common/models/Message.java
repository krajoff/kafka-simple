package com.example.common.models;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private String code;
    private String label;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(code, message.code) && Objects.equals(label, message.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, label);
    }
}
