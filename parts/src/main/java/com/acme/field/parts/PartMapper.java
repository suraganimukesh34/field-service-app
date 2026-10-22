package com.acme.field.parts;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class PartMapper {

    private PartMapper() {
    }

    public static Part fromRow(Map<String, Object> row) {
        String sku = (String) row.get("sku");
        String name = row.get("name") != null ? (String) row.get("name") : sku;
        PartCategory category = PartCategory.fromCode((String) row.get("category"));
        int quantity = row.get("quantity") != null ? ((Number) row.get("quantity")).intValue() : 0;
        double price = row.get("price") != null ? ((Number) row.get("price")).doubleValue() : 0.0;
        return new Part(sku, name, category, quantity, price);
    }

    public static List<Part> fromRows(List<Map<String, Object>> rows) {
        List<Part> parts = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            parts.add(fromRow(row));
        }
        return parts;
    }

    public static String describe(Part part) {
        return part.getName() + " (" + part.getCategory().getDisplayName() + ") x" + part.getQuantity();
    }
}
