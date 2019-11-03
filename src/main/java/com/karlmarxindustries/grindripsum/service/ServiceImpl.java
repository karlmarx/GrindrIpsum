package com.karlmarxindustries.grindripsum.service;

import com.karlmarxindustries.grindripsum.dao.DaoImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ServiceImpl {
    @Autowired
    DaoImpl dao = new DaoImpl();
    private int wordCount = 0;
    private List<String> STRING_LIST = dao.getStringList();
    private static final String[] SEN_TERMS = new String[]{".",".",".",".",".",".",".",".","!","?"};
    Random random = new Random(System.currentTimeMillis());
    static int DEF_SEN_MAX_WORDS = 12;
    static int DEF_SEN_MIN_WORDS = 4;
    private static final String WORD_SEPARATOR = " ";
    private static final String PHRASE_SEPARATOR = ",";


    public String getRandomWord() {
        return STRING_LIST.get(random.nextInt(STRING_LIST.size()));
    }
    public String buildSentence() {
        return buildSentence(random.nextInt(DEF_SEN_MAX_WORDS - DEF_SEN_MIN_WORDS) + DEF_SEN_MIN_WORDS);
    }
    public String buildSentence(int wordNum) {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.capitalize(getRandomWord()));
        wordCount ++;
        for (int i=1; i < wordNum; i++) {
            sb.append(WORD_SEPARATOR);
            sb.append(getRandomWord());
            if (i < (wordNum - 2)) {
                if (random.nextInt(8) == 7) {
                    sb.append(PHRASE_SEPARATOR);
                }
            }
            wordCount ++;
        }
        sb.append(SEN_TERMS[random.nextInt(SEN_TERMS.length)]);
        sb.append(WORD_SEPARATOR);
        return sb.toString();
    }
    public String buildParagraph() {
        int sentenceNum = random.nextInt(2) + 4;
        StringBuilder sb = new StringBuilder ();
        for (int i=0; i < sentenceNum; i++) {
            sb.append(buildSentence());
        }
        sb.append("\n");
        return sb.toString();
    }
    public String buildParagraphs (int paragraphNum) {
        StringBuilder sb = new StringBuilder ();
        for (int i=0; i < paragraphNum; i++) {
            sb.append(buildParagraph());
        }
        return sb.toString();
    }
    public String generateNovel () {
        StringBuilder sb = new StringBuilder ();
        for (int i=0; wordCount < 50000; i++) {
            sb.append(buildParagraph());
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        ServiceImpl service = new ServiceImpl();
        String novel = service.generateNovel();
        System.out.println(novel);
    }
}