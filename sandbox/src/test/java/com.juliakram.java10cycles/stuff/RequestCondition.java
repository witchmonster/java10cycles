package com.juliakram.java10cycles.stuff;

import java.util.Map;

/**
 * Created by jkramr on 2/21/17.
 */
public class RequestCondition {
    private String name;
    private String param;
    private TaxifySimpleRule<String[]> rule;

    public RequestCondition(String name, String param, TaxifySimpleRule<String[]> rule) {
        this.name = name;
        this.param = param;
        this.rule = rule;
    }

    public boolean matches(Map<String, String[]> requestParamMap) {
        String[] values = requestParamMap.get(param);

        if (values != null && values.length > 0) {
            return rule.matches(values);
        }

        return false;
    }

    public String getName() {
        return name;
    }

    public String getParam() {
        return param;
    }

    public interface TaxifySimpleRule<T> { // this is functional interface (has only one implementable method)
        boolean matches(T actual);
    }

    public static void main(String[] args) {
        new RequestCondition(
                "user_not_found",
                "phone",
                actual -> {
                    for (String a : actual) {
                        if (a.equals("+3725555522")) return true;
                    }

                    return false;
                });
    }

    private static class StringUtils {
    }
}
