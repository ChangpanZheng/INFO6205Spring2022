public class Quiz0219 {
    public static void main(String[] args) {

    }
}

class Person {
    String name;
    int id;
    int age;

    public Person() {
    }

    public Person(String name, int id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }
}

class Professor extends Person {
    public String giveQuiz(String quiz){
        System.out.println("The professor gives a quiz as follow : " + quiz);
        return quiz;
    }
}

class Student extends Person {
    public String takeQuiz(String quiz) {
        System.out.println("The student's answer to the quiz is : " + quiz);
        return quiz;
    }
}
