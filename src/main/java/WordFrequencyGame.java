import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    public static final String ANY_SPACE_SEPARATOR = "\\s+";

    public String getResult(String inputStr) {


        if (inputStr.split(ANY_SPACE_SEPARATOR).length == 1) {
            return inputStr + " 1";

        } else {

            try {

                //split the input string with 1 to n pieces of spaces
                String[] words = inputStr.split(ANY_SPACE_SEPARATOR);

                List<Input> frequencies = countFrequency(words);

                frequencies.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (Input w : frequencies) {
                    String s = w.getValue() + " " + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }

    private List<Input> countFrequency(String[] words) {
        Map<String, List<String>> map = groupSameWords(words);
        List<Input> list = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            Input input = new Input(entry.getKey(), entry.getValue().size());
            list.add(input);
        }
        return list;
    }

    private static Map<String, List<String>> groupSameWords(String[] words) {
        List<String> frequencies = new ArrayList<>();
        for (String s : words) {
            frequencies.add(s);
        }
        //get the map for the next step of sizing the same word
        Map<String, List<String>> map = new HashMap<>();
        for (String input1 : frequencies) {
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(input1)) {
                ArrayList arr = new ArrayList<>();
                arr.add(input1);
                map.put(input1, arr);
            } else {
                map.get(input1).add(input1);
            }
        }
        return map;
    }


}
