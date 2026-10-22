package com.acme.field.parts;

public enum PartCategory {
    ELECTRICAL("Electrical"),
    PLUMBING("Plumbing"),
    HVAC("Heating and cooling"),
    FASTENERS("Fasteners"),
    OTHER("Other");

    private final String displayName;

    PartCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static PartCategory fromCode(String code) {
        for (PartCategory category : values()) {
            if (category.name().equalsIgnoreCase(code)) {
                return category;
            }
        }
        return OTHER;
    }
}
