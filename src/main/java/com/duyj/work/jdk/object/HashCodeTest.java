package com.duyj.work.jdk.object;

import java.util.Arrays;

public class HashCodeTest {

	public static void main(String[] args) {
	}

    class TestClass {
        // 若类较复杂，应将hashCode缓存，以提高性能
        private int hash = 0;

        private int i;
        private boolean b;
        private char c; // 或byte short
        private long l;
        private float f;
        private double d;
        private int[] aa;
        private String s;

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o.getClass() == getClass())) {
                return false;
            }
            TestClass ot = (TestClass) o;
            return i == ot.i && b == ot.b && c == ot.c && l == ot.l && Float.compare(f, ot.f) == 0 && Double.compare(d, ot.d) == 0
                    && Arrays.equals(aa, ot.aa) && s.equals(ot.s);
        }

        @Override
        public int hashCode() {
            int result = hash;
            if (result == 0) {
                result = 31 * result + i;
                result = 31 * result + Boolean.hashCode(b);
                result = 31 * result + (int) c;
                result = 31 * result + Long.hashCode(l);
                result = 31 * result + Float.hashCode(f);
                result = 31 * result + Double.hashCode(d);
                result = 31 * result + Arrays.hashCode(aa);
                result = 31 * result + s.hashCode();
                hash = result;
            }
            return result;
        }

        @Override
        public String toString() {
            //略
            return s;
        }
    }

}
