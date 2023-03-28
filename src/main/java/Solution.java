import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;



public class Solution {
    class Student {
        int id;
        String name;
        double cgpa;

        public Student(int id, String name, double cgpa) {
            this.id = id;
            this.name = name;
            this.cgpa = cgpa;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getCgpa() {
            return cgpa;
        }
    }

    class Priorities {
        public List<Student> getStudents(List<String> events) {
            Comparator<Student> comparator = Comparator.comparing(Student::getCgpa).reversed()
                    .thenComparing(Student::getName)
                    .thenComparing(Student::getId);
            PriorityQueue<Student> queue = new PriorityQueue<>(comparator);
            List<Student> students = new ArrayList<>();
            for (String event : events) {
                String[] eventArr = event.split("\\s");
                if (eventArr[0].equalsIgnoreCase("enter")) {
                    Student student = new Student(Integer.parseInt(eventArr[3]), eventArr[1], Double.parseDouble(eventArr[2]));
                    queue.add(student);
                } else if (eventArr[0].equalsIgnoreCase("served")) {
                    if (queue.size() != 0) {
                        queue.poll();
                    }
                }
            }
            while (!queue.isEmpty()) {
                students.add(queue.poll());
            }
            return students;
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            System.out.println(sb);
        } catch (NoSuchAlgorithmException ex) {
            System.out.println(ex.getMessage());
        }
    }
}