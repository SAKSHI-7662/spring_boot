import java.util.*;

class Student {
    String name;
    int studentClass;
    String batch;
    Map<String, Integer> scores;

    Student(String name, int studentClass, String batch, Map<String, Integer> scores) {
        this.name = name;
        this.studentClass = studentClass;
        this.batch = batch;
        this.scores = scores;
    }
}

public class StudentAnalytics {

    public static String getGrade(int score) {
        if (score >= 90 && score < 100) return "A";
        else if (score >= 80 && score < 90) return "B";
        else if (score >= 70 && score < 80) return "C";
        else if (score >= 60 && score < 70) return "D";
        else if (score >= 50 && score < 60) return "E";
        else return "F";
    }

    

    public static double calculatePercentage(Student student) {
        int total = 0;
        for (int score : student.scores.values()) {
            total += score;
        }
        return (double) total / student.scores.size();
    }

    public static Map<Integer, List<Double>> calculateClassAverages(List<Student> students) {
        Map<Integer, List<Double>> classMap = new HashMap<>();
        for (Student student : students) {
            double percent = calculatePercentage(student);
            classMap.putIfAbsent(student.studentClass, new ArrayList<>());
            classMap.get(student.studentClass).add(percent);
        }
        return classMap;
    }

    public static double calculateSchoolAverage(List<Student> students) {
        double total = 0;
        int count = 0;
        for (Student student : students) {
            total += calculatePercentage(student);
            count++;
        }
        return total / count;
    }

    public static Map<String, List<Integer>> calculateSubjectWiseAverage(List<Student> students) {
        Map<String, List<Integer>> subjectMap = new HashMap<>();
        for (Student student : students) {
            for (Map.Entry<String, Integer> entry : student.scores.entrySet()) {
                subjectMap.putIfAbsent(entry.getKey(), new ArrayList<>());
                subjectMap.get(entry.getKey()).add(entry.getValue());
            }
        }
        return subjectMap;
    }

    public static void showStudentDetails(List<Student> students) {
        for (Student student : students) {
            System.out.println("Name: " + student.name);
            System.out.println("Class: " + student.studentClass);
            System.out.println("Batch: " + student.batch);
            System.out.println("Scores:");
            for (Map.Entry<String, Integer> entry : student.scores.entrySet()) {
                System.out.println("  Subject: " + entry.getKey() +
                                   ", Score: " + entry.getValue() +
                                   ", Grade: " + getGrade(entry.getValue()));
            }
            System.out.println("---------------------------");
        }
    }

    public static void main(String[] args) {
        // Sample data
        List<Student> students = new ArrayList<>();

        Map<String, Integer> scores1 = Map.of(
            "Physics", 45, "Biology", 90, "Math", 56, "Chemistry", 47, "Computer Science", 70);
        students.add(new Student("John Doe", 12, "2018", scores1));

        Map<String, Integer> scores2 = Map.of(
            "Physics", 76, "Biology", 45, "Math", 60, "Chemistry", 88, "Physical Education", 36);
        students.add(new Student("John Blake", 11, "2018", scores2));

        Map<String, Integer> scores3 = Map.of(
            "Physics", 55, "Biology", 99, "Math", 45, "Chemistry", 87, "Computer Science", 77);
        students.add(new Student("Joseph Marino", 10, "2018", scores3));

        Map<String, Integer> scores4 = Map.of(
            "Physics", 90, "Biology", 70, "Math", 60, "Chemistry", 49, "Computer Science", 91);
        students.add(new Student("Jason Bourne", 11, "2019", scores4));

        // 1. Student Percentage
        System.out.println("Student Percentages:");
        for (Student student : students) {
            System.out.printf("%s: %.2f%%\n", student.name, calculatePercentage(student));
        }

        // 2. Class Averages
        System.out.println("\nClass Averages:");
        Map<Integer, List<Double>> classAverages = calculateClassAverages(students);
        for (Map.Entry<Integer, List<Double>> entry : classAverages.entrySet()) {
            double avg = entry.getValue().stream().mapToDouble(Double::doubleValue).average().orElse(0);
            System.out.printf("Class %d: %.2f%%\n", entry.getKey(), avg);
        }

        // 3. School Overall Percentage
        System.out.printf("\nSchool Overall Percentage: %.2f%%\n", calculateSchoolAverage(students));

        // 4. Subject-wise Averages
        System.out.println("\nSubject-wise Averages:");
        Map<String, List<Integer>> subjectMap = calculateSubjectWiseAverage(students);
        for (Map.Entry<String, List<Integer>> entry : subjectMap.entrySet()) {
            double avg = entry.getValue().stream().mapToInt(Integer::intValue).average().orElse(0);
            System.out.printf("%s: %.2f\n", entry.getKey(), avg);
        }

        // 5. Student Detailed Info
        System.out.println("\nStudent Details with Grades:");
        showStudentDetails(students);
    }
}