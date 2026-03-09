package com.sourcetrail.name;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NameHierarchy {
    private final List<NameElement> m_elements = new ArrayList<>();
    private char m_separator = '.';

    public NameHierarchy() {
    }

    public NameHierarchy(String name) {
        m_elements.add(new NameElement(name));
    }

    public NameHierarchy(NameElement name) {
        m_elements.add(name);
    }

    public NameHierarchy(List<NameElement> names) {
        m_elements.addAll(names);
    }

    public void setSeparator(char separator) {
        m_separator = separator;
    }

    public void push(NameElement element) {
        m_elements.add(element);
    }

    public void pop() {
        if (!m_elements.isEmpty()) {
            m_elements.removeLast();
        }
    }

    public Optional<NameElement> peek() {
        if (!m_elements.isEmpty()) {
            return Optional.of(m_elements.getLast());
        }
        return Optional.empty();
    }

    public String serialize() {
        StringBuilder serialized = new StringBuilder(m_separator + "\tm");

        for (int i = 0; i < m_elements.size(); i++) {
            if (i != 0) {
                serialized.append("\tn");
            }

            serialized.append(m_elements.get(i).serialize());
        }

        return serialized.toString();
    }
}
