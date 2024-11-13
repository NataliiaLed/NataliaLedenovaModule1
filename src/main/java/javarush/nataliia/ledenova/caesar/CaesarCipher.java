package javarush.nataliia.ledenova.caesar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaesarCipher implements CharTransformer {
    private final int shift;
    private final List<Character> alphabet;
    private final Map<Character, Integer> index = new HashMap<>();

    public CaesarCipher(String alphabetStr, int shift) {
        this.shift = shift;
        alphabet = new ArrayList<>();
        char[] charArray = alphabetStr.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            alphabet.add(ch);
            index.put(ch, i);
        }
    }

    @Override
    public char transform(char ch) {
        if (!index.containsKey(ch)) {
            return ch;
        }
        int length = alphabet.size();
        int currentPosition = index.get(ch);
        int targetPosition = (currentPosition + shift) % length;
        return alphabet.get(targetPosition);
    }
}
