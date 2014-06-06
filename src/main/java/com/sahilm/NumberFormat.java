package com.sahilm;

import com.ibm.icu.text.RuleBasedNumberFormat;

import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NumberFormat {
    private static final String MARATHI_SPELLOUT_RULES = loadSpelloutRules(NumberFormat.class.getResource("/MarathiSpelloutRules.txt"));

    private static String loadSpelloutRules(URL resource) {
        StringBuilder ret = new StringBuilder();
        try {
            for (String s : Files.readAllLines(Paths.get(resource.toURI()), Charset.forName("UTF-8"))) {
                ret.append(s);
            }
            return ret.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String spellOutInMarathi(BigDecimal number) {
        RuleBasedNumberFormat formatter = new RuleBasedNumberFormat(MARATHI_SPELLOUT_RULES);
        return formatter.format(number);
    }
}
