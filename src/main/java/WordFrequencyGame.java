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
        return java.util.Arrays.stream(words)
                .collect(java.util.stream.Collectors.groupingBy(word -> word))
                .entrySet()
                .stream()
                .map(entry -> new Input(entry.getKey(), entry.getValue().size()))
                .collect(java.util.stream.Collectors.toList());
    }


    private static Map<String, List<String>> groupSameWords(String[] words) {
        return java.util.Arrays.stream(words)
                .collect(java.util.stream.Collectors.groupingBy(word -> word));
    }



}
