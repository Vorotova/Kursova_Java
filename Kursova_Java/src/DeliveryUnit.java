import java.util.Locale;

interface DeliveryUnitStrategy {
    int toDays(int value);
}

class DaysUnit implements DeliveryUnitStrategy {
    public int toDays(int value) { return value; }
}

class MonthsUnit implements DeliveryUnitStrategy {
    public int toDays(int value) { return value * 30; }
}

class YearsUnit implements DeliveryUnitStrategy {
    public int toDays(int value) { return value * 365; }
}

class DeliveryUnitResolver {
    static DeliveryUnitStrategy resolve(String unitRaw) {
        String unit = unitRaw.toLowerCase(Locale.ROOT);
        if (unit.startsWith("день")) return new DaysUnit();
        if (unit.startsWith("місяц")) return new MonthsUnit();
        if (unit.startsWith("рік") || unit.startsWith("рок")) return new YearsUnit();
        return new DaysUnit();
    }
}


