package tdd.greeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Greeter {

    String greet(List<String> names) {
        String greeting = "Hello, ";
        String uppercaseGreeting = "HELLO, ";
        String uppercaseGreetingAfterLowercase = " AND HELLO ";

        List<String> namesList = separateAllNamesInListAndGetRidOfEscapeChars(names);

        if(isNameListEmpty(namesList)) {
            greeting = greeting.concat("my friend.");

        } else if (areAllNamesUpperCase(namesList)) {
            uppercaseGreeting = uppercaseGreeting.concat(buildUppercaseGreeting(namesList));
            return uppercaseGreeting;

        } else if (areAllNamesLowerCase(namesList)) {
            greeting = greeting.concat(buildLowercaseGreeting(namesList));

        } else {
            List<String> lowercaseNames = getLowercaseNames(namesList);
            List<String> uppercaseNames = getUppercaseNames(namesList);
            greeting = greeting.concat(buildLowercaseGreeting(lowercaseNames));
            uppercaseGreetingAfterLowercase = uppercaseGreetingAfterLowercase.concat(buildUppercaseGreeting(uppercaseNames));
            greeting = greeting.concat(uppercaseGreetingAfterLowercase);
        }

        return greeting;
    }

    private boolean isNameListEmpty(List<String> nameList) {
        return nameList.size() == 1 && nameList.get(0).isEmpty() || nameList.size() == 0;
    }

    private List<String> separateAllNamesInListAndGetRidOfEscapeChars(List<String> nameList) {
        List<String> finalNamesList = new ArrayList<>();
        for (String name : nameList) {
            String nameWithoutEscapeChars = name.replaceAll("([\"])", "");

            if (nameWithoutEscapeChars.indexOf(',') >= 0) {
                String[] separateNames = nameWithoutEscapeChars.split("\\s*,\\s*");
                finalNamesList.addAll(Arrays.asList(separateNames));
            } else {
                finalNamesList.add(nameWithoutEscapeChars);
            }
        }
        return finalNamesList;
    }

    private String buildUppercaseGreeting(List<String> nameList) {
        String uppercaseGreeting = "";
        for (String name : nameList) {
            if (nameList.indexOf(name) == nameList.size()-1) {
                uppercaseGreeting = uppercaseGreeting.concat(name + "!");
            } else if (nameList.indexOf(name) == nameList.size()-2) {
                uppercaseGreeting = uppercaseGreeting.concat(name + " AND ");
            } else {
                uppercaseGreeting = uppercaseGreeting.concat(name + ", ");
            }
        }
        return uppercaseGreeting;
    }

    private String buildLowercaseGreeting(List<String> nameList) {
        String lowercaseGreeting = "";
        for (String name : nameList) {
            if (nameList.indexOf(name) == nameList.size()-1) {
                lowercaseGreeting = lowercaseGreeting.concat(name + ".");
            }  else if (nameList.indexOf(name) == nameList.size()-2) {
                lowercaseGreeting = lowercaseGreeting.concat(name + " and ");
            } else {
                lowercaseGreeting = lowercaseGreeting.concat(name + ", ");
            }
        }
        return lowercaseGreeting;
    }

    private List<String> getLowercaseNames(List<String> nameList) {
        List<String> lowercaseNames = new ArrayList<>();
        for (String name : nameList) {
            if (!isNameUpperCase(name)) {
                lowercaseNames.add(name);
            }
        }
        return lowercaseNames;
    }

    private List<String> getUppercaseNames(List<String> nameList) {
        List<String> uppercaseNames = new ArrayList<>();
        for (String name : nameList) {
            if (isNameUpperCase(name)) {
                uppercaseNames.add(name);
            }
        }
        return uppercaseNames;
    }

    private boolean isNameUpperCase(String name){
        for (int i = 0; i < name.length(); i++)
        {
            if (Character.isLowerCase(name.charAt(i))){
                return false;
            }
        }
        return true;
    }

    private boolean areAllNamesUpperCase(List<String> nameList){
        int count = 0;
        for (String name : nameList) {
            if (isNameUpperCase(name)) {
                count++;
            }
        }
        return count == nameList.size();
    }

    private boolean areAllNamesLowerCase(List<String> nameList){
        int count = 0;
        for (String name : nameList) {
            if (!isNameUpperCase(name)) {
                count++;
            }
        }
        return count == nameList.size();
    }
}
