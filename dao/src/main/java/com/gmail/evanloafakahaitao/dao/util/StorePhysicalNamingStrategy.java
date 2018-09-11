package com.gmail.evanloafakahaitao.dao.util;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class StorePhysicalNamingStrategy implements PhysicalNamingStrategy {

    @Override
    public Identifier toPhysicalCatalogName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return identifier;
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return identifier;
    }

    @Override
    public Identifier toPhysicalTableName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return getIdentifier(identifier, jdbcEnvironment, "T_");
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return identifier;
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier identifier, JdbcEnvironment jdbcEnvironment) {
        return getIdentifier(identifier, jdbcEnvironment, "F_");
    }

    private Identifier getIdentifier(Identifier identifier, JdbcEnvironment jdbcEnvironment, String prefix) {
        final List<String> parts = splitAndReplace(identifier.getText());
        return jdbcEnvironment.getIdentifierHelper().toIdentifier(
                join(parts, prefix),
                identifier.isQuoted()
        );
    }

    private List<String> splitAndReplace(String name) {
        List<String> result = new LinkedList<>();
        for (String part : splitByCharacterTypeCamelCase(name)) {
            if (part == null || part.trim().isEmpty()) {
                continue;
            }
            result.add(part.toUpperCase(Locale.ROOT));
        }
        return result;
    }

    private String join(List<String> parts, String prefix) {
        boolean firstPass = true;
        String separator = "";
        StringBuilder joined = new StringBuilder();
        joined.append(prefix);
        for (String part : parts) {
            joined.append(separator).append(part);
            if (firstPass) {
                firstPass = false;
                separator = "_";
            }
        }
        return joined.toString();
    }

    private String[] splitByCharacterType(String str, boolean camelCase) {
        if (str == null) {
            return null;
        } else if (str.isEmpty()) {
            return ArrayUtils.EMPTY_STRING_ARRAY;
        } else {
            char[] c = str.toCharArray();
            List<String> list = new ArrayList<>();
            int tokenStart = 0;
            int currentType = Character.getType(c[tokenStart]);

            for(int pos = tokenStart + 1; pos < c.length; ++pos) {
                int type = Character.getType(c[pos]);
                if (type != currentType && type != Character.CONNECTOR_PUNCTUATION) {
                    if (camelCase && type == 2 && currentType == 1) {
                        int newTokenStart = pos - 1;
                        if (newTokenStart != tokenStart) {
                            list.add(new String(c, tokenStart, newTokenStart - tokenStart));
                            tokenStart = newTokenStart;
                        }
                    } else {
                        list.add(new String(c, tokenStart, pos - tokenStart));
                        tokenStart = pos;
                    }

                    currentType = type;
                }
            }

            list.add(new String(c, tokenStart, c.length - tokenStart));
            return list.toArray(new String[list.size()]);
        }
    }

    private String[] splitByCharacterTypeCamelCase(String str) {
        return splitByCharacterType(str, true);
    }
}
