package tn.esprit.mariemnajahi.service;

import tn.esprit.mariemnajahi.exception.ResourceNotFoundException;

import java.time.LocalDate;

final class ServiceValidation {

    private ServiceValidation() {
    }

    static void requireEntity(Object entity, String name) {
        if (entity == null) {
            throw new IllegalArgumentException(name + " must not be null");
        }
    }

    static void requireNewId(Long id) {
        if (id != null) {
            throw new IllegalArgumentException("A new resource must not have an id");
        }
    }

    static void requireId(Long id, String resource) {
        if (id == null) {
            throw new ResourceNotFoundException(resource + " not found: null");
        }
    }

    static void requireNonBlank(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " must not be blank");
        }
    }

    static void requireDateRange(LocalDate start, LocalDate end, String resource) {
        if (start != null && end != null && end.isBefore(start)) {
            throw new IllegalArgumentException(resource + " end date must not be before its start date");
        }
    }
}
