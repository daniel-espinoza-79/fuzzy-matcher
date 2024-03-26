package com.intuit.fuzzymatcher;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.intuit.fuzzymatcher.component.MatchService;
import com.intuit.fuzzymatcher.domain.Document;
import com.intuit.fuzzymatcher.domain.Element;
import com.intuit.fuzzymatcher.domain.Match;
import static com.intuit.fuzzymatcher.domain.ElementType.ADDRESS;
import static com.intuit.fuzzymatcher.domain.ElementType.NAME;
import static com.intuit.fuzzymatcher.domain.ElementType.PATH;

public class Main {
    public static void main(String[] args) {
        String[][] input = {
            {"1", "Android/android-L-preview /AndroidManifest.xml"},
            {"2", "Android/android-L- preview/AndroidManfet.xml"}
    };

    
    List<Document> documentList = Arrays.asList(input).stream().map(contact -> {
        return new Document.Builder(contact[0])
                .addElement(new Element.Builder<String>().setValue(contact[1]).setType(PATH).createElement())
                .createDocument();
    }).collect(Collectors.toList());

        MatchService matchService = new MatchService();
    Map<String, List<Match<Document>>> result = matchService.applyMatchByDocId(documentList);
            
    result.entrySet().forEach(entry -> {
        entry.getValue().forEach(match -> {
            System.out.println("Data: " + match.getData() + " Matched With: " + match.getMatchedWith() + " Score: " + match.getScore().getResult());
        });
    });
}
}