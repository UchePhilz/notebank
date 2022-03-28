package com.notebank.utils;

import com.notebank.models.Notes;
import com.notebank.models.enums.NoteVisibility;
import java.text.*;
import java.util.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.text.DateFormatter;

/**
 *
 * I have a class (lib) of methods that performs actions that I will be using
 * multiple times
 *
 * @author Uche Powers
 */
public class NoteUtils {

    public static boolean isNumeric(String value) {
        return notEmpty(value) && value.matches("[0-9]+");
    }

    public static boolean isNonZero(String value) {
        return notEmpty(value) && value.matches("[1-9]+");
    }

    private boolean isDecimal(String value) {
        return value.matches("(\\+|-)?(([0-9])*(\\.[0-9]+)?)");
    }

    public static boolean isDouble(String value) {
        if (value.isEmpty()) {
            return true;
        }
        try {
            double c = Double.parseDouble(value);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean isNoSpecialCharacter(String value) {
        return value.matches("[a-zA-Z0-9]+");
    }

    public static boolean isEmail(String value) {
        return value.matches("[a-zA-Z0-9\\.\\-]+\\@[a-zA-Z0-9\\.\\-]+");
    }

    public static boolean isEmailRecent(String value) {
        return value.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
    }

    public boolean isString(String value) {
        return value.length() > 0;
    }

    private boolean isPasswd(String value) {
        return value.length() > 4;
    }

    private boolean equals(String str, String o) {
        return str.equals(o);
    }

    public static boolean isDate(String value) {
        return value.matches("^(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$");
    }

    public static boolean isDateTime(String dt) {
        try {
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dt);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

    public static boolean isTime(String value) {
        return value.matches("^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$");
    }

    public static boolean isTimeHM(String value) {
        return value.matches("^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$");
    }

    public static boolean isNumber(String str) {
        return notNull(str) && str.trim().matches("[0-9]+");
    }

    public static int stringToInt(String str) {
        return stringToInt(str, false);
    }

    public static long stringToLong(String str) {
        return stringToLong(str, false);
    }

    public static int stringToInt(String str, boolean reportErr) {
        try {
            return Integer.parseInt(str.trim());
        } catch (Exception e) {
            if (reportErr) {
                e.printStackTrace();
            }
            return 0;
        }
    }

    public static Long stringToLong(String str, boolean reportErr) {
        try {
            return Long.parseLong(str.trim());
        } catch (Exception e) {
            if (reportErr) {
                e.printStackTrace();
            }
            return 0l;
        }
    }

    public static double stringToDouble(String str) {
        return stringToDouble(str, true);
    }

    public static double stringToDouble(String str, boolean reportErr) {
        try {
            return Double.parseDouble(str.trim());
        } catch (Exception e) {
            if (reportErr) {
                e.printStackTrace();
            }
            return 0.0;
        }
    }

    public static boolean isHex(String str) {
        return notNull(str) && str.matches("[0-9A-Fa-f]+");
    }

    public static boolean notEmpty(String str) {
        return notNull(str) && str.length() > 0;
    }

    public static boolean notEmpty(List str) {
        return notNull(str) && str.size() > 0;
    }

    public static boolean notNull(Object ob) {
        return ob != null;
    }

    public static boolean inArray(String ob[], int n) {
        try {
            String str = ob[n];
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isMatch(String str, String str1) {
        return notEmpty(str) && str.equals(str1);
    }

    public static boolean isMinLength(String str, int len) {
        return notNull(str) && str.length() >= len;
    }

    public static boolean isPassword(String str, int len) {
        return notNull(str) && str.length() >= len && str.matches(".*[0-9].*");
    }

    public static boolean hasNumber(String str) {
        return notNull(str) && str.matches(".*[0-9].*");
    }

    public static boolean contains(String str, String[] strArr) {
        return notEmpty(str) && matches(str, strArr);
    }

    private static boolean matches(String str, String[] strArr) {
        for (String s : strArr) {
            if (s.equals(str)) {
                return true;
            }
        }
        return false;
    }

    private static String pad(String str) {
        if (str.length() > 8) {
            return str;
        }
        return str.concat("000000");
    }

    public static Predicate getPredicates(Integer id, Integer userId, String title, String body, String tag, String visibility,
            Integer page, Integer size, String orderColumn, String orderDirection,
            Root<Notes> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();
        if (NoteUtils.notNull(id)) {
            predicates.add(criteriaBuilder.equal(root.get("id"), id));
        }
        if (NoteUtils.notNull(userId)) {
            predicates.add(criteriaBuilder.equal(root.get("userId"), userId));
        }
        if (NoteUtils.notNull(visibility)) {
            predicates.add(criteriaBuilder.equal(root.get("visibility"), visibility));
        }
        if (NoteUtils.notEmpty(tag)) {
            predicates.add(criteriaBuilder.like(root.get("tag"), "%" + tag + "%"));
        }
        if (NoteUtils.notEmpty(title)) {
            predicates.add(criteriaBuilder.like(root.get("title"), "%" + title + "%"));
        }
        if (NoteUtils.notEmpty(body)) {
            predicates.add(criteriaBuilder.like(root.get("body"), "%" + body + "%"));
        }

        Predicate predicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        //System.out.println(new Gson().toJson(predicates.));
        return predicate;
    }

}
