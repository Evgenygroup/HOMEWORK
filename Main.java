import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        System.out.println("--------1----------");
        Address address1 = new Address("Nemetskaya Sloboda", 1);
        Address address2 = new Address("Wilmersdorfer Str", 24);
        Address address3 = new Address("Dvortsovaya Square", 1);
        Address address4 = new Address("Machnower Str", 14);

        Person p1 = new Person("Anna Mons", 16, address1);
        Person p2 = new Person("Koenigsegg", 35, address2);
        Person p3 = new Person("Peter the Great", 47, address3);
        Person p4 = new Person("John Smith", 16, address3);
        Person p5 = new Person("Ivanov", 47, address4);

        List<Person> list = Arrays.asList(p1, p2, p3, p4, p5);
        System.out.println(addressMoreSeventeen(list));

        System.out.println("--------2----------");
        System.out.println(withoutDuplicates());

        System.out.println("--------3----------");

        mapGetAge(list).forEach((k, v) -> System.out.println(k + "->" + v.stream()
                .map(Person::getName).collect(Collectors.toList())));


        System.out.println("--------4,5----------");

        Person1 p11 = new Person1("Ivanov");
        Person1 p12 = new Person1("Petrov");
        Person1 p13 = new Person1("Sidorov");

        BankAccount acc1 = new BankAccount("DE76370400440532013000", p11);
        BankAccount acc2 = new BankAccount("DE23760419444839053718", p13);
        BankAccount acc3 = new BankAccount("DE45370400440532013067", p12);
        BankAccount acc4 = new BankAccount("DE81760408741587013019", p11);
        BankAccount acc5 = new BankAccount("DE89970453440523013096", p13);

        List<BankAccount> list1 = Arrays.asList(acc1, acc2, acc3, acc4, acc5);
        mapPersonAsKeyAccountAsValue(list1).forEach((k, v) -> System.out.println(k + " -> " + v));

        System.out.println(getListofIbannsWithStars(list1));

        System.out.println("--------6----------");
        //6. method accepts a sentence and
        // returns number of words starting with a certain letter

        String string = "aaaaaaaa fffffff aa bbbb a bbb aaa ggggggggggg";

        System.out.println(numberOfWords(string, "a"));

        System.out.println("--------7----------");
        //7. check if the string is number - Character.isDigit() and Stream.allMatch().

        String str = "1459032";
        String str1 = "adf45";

        System.out.println(isDig(str));
        System.out.println(isDig(str1));

        System.out.println("--------8-9----------");
        //8-9
        BankAccount2 acc21 = new BankAccount2("DE76370400440532013000");
        BankAccount2 acc22 = new BankAccount2("DE23760419444839053718");
        BankAccount2 acc23 = new BankAccount2("DE45370400440532013067");
        BankAccount2 acc24 = new BankAccount2("DE81760408741587013019");
        BankAccount2 acc25 = new BankAccount2("DE89970453440523013096");

        List<BankAccount2> ba1 = Arrays.asList(acc21, acc22);
        List<BankAccount2> ba2 = Arrays.asList(acc23, acc24);
        List<BankAccount2> ba3 = Arrays.asList(acc25);

        Person2 personA = new Person2("Ivanov", ba2);
        Person2 personB = new Person2("Petrov", ba1);
        Person2 personC = new Person2("Sidorov", ba3);
        List<Person2> persons = Arrays.asList(personA, personB, personC);

        System.out.println(ibanWithStars(persons));

        System.out.println("------------10,11-------------");
        Person p6 = new Person("Anna Mons", 16, address1 = null);
        Person p7 = new Person("Koenigsegg", 35, address2 = null);
        Person p8 = new Person("Peter the Great", 47, address3 = null);
        Person p9 = new Person("John Smith", 16, address3 = null);
        Person p10 = new Person("Ivanov", 47, address4 = null);
        List<Person> list2 = Arrays.asList(p6, p7, p8, p9, p10);
        System.out.println("Total age of those older than 17 is " + ageAll(list2));
        System.out.println(legalAge(list2));

    }

    //Task1 addresses of those older than 17
    public static List<Address> addressMoreSeventeen(List<Person> input) {
        List<Address> addrMoreSeventeen = input
                .stream()
                .filter(p -> p.getAge() > 17)
                .map(Person::getAddress)
                .collect(Collectors.toList());
        return addrMoreSeventeen;

    }

    //2. List of names without duplicates
    public static Set<String> withoutDuplicates() {
        Stream<String> streamOfNames = Stream.of("Ivan", "Petr", "Ivan", "Semen", "Petr");
        Set<String> withoutDupl = streamOfNames.collect(Collectors.toSet());
        return withoutDupl;
    }

    //3. return map, where age is a key and list of persons is a value
    public static Map<Integer, List<Person>> mapGetAge(List<Person> input) {
        Map<Integer, List<Person>> map = input
                .stream()
                .collect(Collectors.groupingBy(Person::getAge));

        return map;

    }
    //4.return map with Person as a key  and list of his/her accounts as value

    public static Map<Person1, List<BankAccount>> mapPersonAsKeyAccountAsValue(List<BankAccount> input) {
        Map<Person1, List<BankAccount>> mapPersonAsKey = input
                .stream()
                .collect(Collectors.groupingBy(BankAccount::getOwner));
        return mapPersonAsKey;

    }

    //5.IBANNs list with * after third symbol.
    public static List<String> getListofIbannsWithStars(List<BankAccount> input) {
        List<String> listOfIbannsWithStars = input
                .stream()
                .map(a -> replaceWithStars(a.getIBANN()))
                .collect(Collectors.toList());
        return listOfIbannsWithStars;


    }

    public static String replaceWithStars(String input) {
        char[] chars = input.toCharArray();
        for (int i = 3; i < input.length(); i++)
            chars[i] = '*';
        return new String(chars);
    }


    //6. method accepts a sentence and
    // returns number of words starting with a certain letter
    public static long numberOfWords(String input, String w) {
        long count = Stream.of(input.split(" "))
                .filter(s -> s.startsWith("a"))
                .count();
        return count;
    }

    //7. check if the string is number - Character.isDigit() and Stream.allMatch().
    public static boolean isDig(String input) {

        boolean allMatch = input.chars().allMatch(Character::isDigit);
        return allMatch;
    }

    //8-9 returns bank accounts with stars (flatMap)
    public static List<String> ibanWithStars(List<Person2> persons) {
        List<String> accountsListWithStars = persons
                .stream()
                .flatMap(ba -> ba.getBankAccounts().stream())
                .map(ib -> ib.getIBANN())
                .map(iba -> replaceWithStars(iba))
                .collect(Collectors.toList());
        return accountsListWithStars;
    }


    //10.  total age of those older than 17
    public static int ageAll(List<Person> input) {
        Integer reduce = input
                .stream()
                .filter(s -> s.getAge() > 17)
                .map(Person::getAge)
                .reduce(0, (s1, s2) -> s1 + s2);
        return reduce;

    }
    //11.They are of legal age in Germany

    public static String legalAge(List<Person> input) {
        String legalAge = input
                .stream()
                .filter(s -> s.getAge() > 17)
                .map(Person::getName)
                .collect(Collectors.joining(" and ", "in Germany ",
                        " are of legal age."));
        return legalAge;


    }
}





