package christmas.domain;

public enum Badge {
    STAR("별"),
    TREE("트리"),
    SANTA("산타"),
    NOTHING("없음");

    private final String name;

    Badge(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
