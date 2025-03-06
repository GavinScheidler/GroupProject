//Dane Iwema

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class MET09 {
    private final int number;

    public MET09(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MET09)) {
            return false;
        }
        MET09 cc = (MET09) o;
        return cc.number == number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    public static void main(String[] args) {
        Map<MET09, String> m = new HashMap<>();
        m.put(new MET09(100), "****-****-****-1111"); // Masked sensitive data
        System.out.println(m.get(new MET09(100))); // Should return "****-****-****-1111"
    }
}
