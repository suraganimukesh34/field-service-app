package com.acme.field.parts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartRepository {

    private static final PartRepository INSTANCE = new PartRepository();

    private final Map<String, Part> parts = new HashMap<>();
    private final PartStockNotifier notifier = new PartStockNotifier();

    private PartRepository() {
    }

    public static PartRepository getInstance() {
        return INSTANCE;
    }

    public PartStockNotifier getNotifier() {
        return notifier;
    }

    public void add(Part part) {
        parts.put(part.getSku(), part);
        notifier.check(part);
    }

    public Part find(String sku) {
        return parts.get(sku);
    }

    public List<Part> inCategory(PartCategory category) {
        List<Part> result = new ArrayList<>();
        for (Part part : parts.values()) {
            if (part.getCategory() == category) {
                result.add(part);
            }
        }
        return result;
    }

    public boolean takeFromStock(String sku, int amount) {
        Part part = parts.get(sku);
        if (part == null || part.getQuantity() < amount) {
            return false;
        }
        part.setQuantity(part.getQuantity() - amount);
        notifier.check(part);
        return true;
    }

    public double stockValue() {
        double total = 0;
        for (Part part : parts.values()) {
            total += part.getQuantity() * part.getUnitPrice();
        }
        return total;
    }
}
