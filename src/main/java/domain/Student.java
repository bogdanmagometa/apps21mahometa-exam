package domain;

import json.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    private static final int PASSED_THRESHOLD = 3;

    private final Tuple<String, Integer>[] exams;
    public Student(String name,
                   String surname,
                   Integer year,
                   Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = Arrays.copyOf(exams, exams.length);
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject studentJson = super.toJsonObject();

        List<JsonObject> examsList = new ArrayList<>();
        for (Tuple<String, Integer> exam: exams) {
            examsList.add(new JsonObject(
                    new JsonPair("course", new JsonString(exam.key)),
                    new JsonPair("mark", new JsonNumber(exam.value)),
                    new JsonPair("passed",
                            new JsonBoolean(exam.value >= PASSED_THRESHOLD))
                    ));
        }
        JsonObject[] examsArray = examsList.toArray(
                new JsonObject[examsList.size()]);
        studentJson.add(new JsonPair("exams", new JsonArray(examsArray)));
        return studentJson;
    }
}