package ra.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Singer {

    private int singerId;
    private String singerName;
    private int age;
    private String nationality;
    private boolean gender;
    private String genre;

    /**
     * Contructor
     */
    public Singer() {
    }

    /**
     * Getter / Setter
     */
    public int getSingerId() {
        return singerId;
    }

    public void setSingerId(int singerId) {
        this.singerId = singerId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * name: inputData(Scanner scanner)
     *
     * @param: scanner
     * function: input data
     * date: 22/09/2023
     */
    public void inputData(Scanner scanner, Singer[] employees, int empCurIndex) {
        boolean isLoop = true;
        boolean iExist = false;

//        /** Input singer id */
//        this.singerId = count++;

        /** Input singer name */
        do {
            System.out.println("Xin nhập tên ca sĩ:");
            this.singerName = scanner.nextLine().trim();

            if (singerName.length() < 1) {
                System.err.println("Tên ca sĩ không được để trống!");
            }
        }while (singerName.length() < 1);

        /** Input age */
        do{
            try{
                System.out.println("Enter BirthYear:");
                this.age  = Integer.parseInt(scanner.nextLine().trim());

                if(this.age < 1){
                    System.err.println("Tuổi ca sĩ phải lơn hơn 0");
                }
            }catch (InputMismatchException e) {
                System.err.println("Nhập sai định dạng");
            }

        }while (this.age < 1);


        /** Input nationality */
        do{
            System.out.println("Enter Phone:");
            this.nationality = scanner.nextLine().trim();

            if (this.nationality.equals("")) {
                System.err.println("Quốc tịch không được để trống");
            }
        }while (this.nationality.equals(""));


        /** Input gender */
        do{
            iExist = false;

            try {
                System.out.printf("Xin mời chọn giới tính (1. Nam - 2. Nữ):\n");
                int choiceGender = Integer.parseInt(scanner.nextLine().trim());

                switch (choiceGender) {
                    case 1:
                        this.gender = true;
                        iExist = true;
                        break;
                    case 2:
                        this.gender = false;
                        iExist = true;
                        break;
                    default:
                        System.err.printf("Lưa chọn \"d\" không có", choiceGender);
                }

                if(iExist) {
                    break;
                }
            }catch (InputMismatchException e){
                System.err.println("Nhập định dạng không hợp lệ!");
            }
        }while (isLoop);


        /** Input genre */
        do{
            System.out.println("Xin mời nhập thể loại:");
            this.genre = scanner.nextLine().trim();

            if (this.genre.length() < 1){
                System.err.println("Thể loại không được bỏ trống");
            }

        }while (this.genre.length() < 1);

    }

    /**
     * name: updateData(Scanner scanner)
     *
     * @param: scanner
     * function: update employee information
     * date: 22/09/2023
     */
//    public void updateData(Scanner scanner, Singer[] employees, int currentIndex) {
//        boolean isLoop = true;
//        boolean isExist = false;
//
//
//        /** Update EmployeeName */
//        while (isLoop) {
//            System.out.println("Enter updateEmployeeName:");
//            String updateEmployeeName = scanner.nextLine().trim();
//
//            if(updateEmployeeName == "") {
//                break;
//            }
//
//            if(updateEmployeeName.length() >= 10 && updateEmployeeName.length() <= 50) {
//                this.empName = updateEmployeeName;
//            }else {
//                System.err.print("The %s must be from 10 to 50 character!\n");
//            }
//        }
//
//        /** Update BirthYear */
//        while (isLoop) {
//            int currentYear = LocalDate.now().getYear();
//
//            System.out.println("Enter updateBirthYear:");
//            String updateBirthYear = scanner.nextLine().trim();
//
//            if(updateBirthYear ==  "") {
//                break;
//            }
//
//            if(Pattern.matches("\\d{4}", updateBirthYear) && Integer.parseInt(updateBirthYear) < currentYear) {
//                this.birthYear = updateBirthYear;
//                break;
//            }else {
//                System.err.printf("The %s is in invalid year format!\n", updateBirthYear);
//            }
//        }
//
//        /** Update Phone */
//        while (isLoop) {
//            System.out.println("Enter updatePhone:");
//            String updatePhone = scanner.nextLine().trim();
//
//            if(updatePhone == "") {
//                break;
//            }
//
//            if(Pattern.matches("^[0][1-9][0-9]{8}", updatePhone)) {
//                this.phone = updatePhone;
//                break;
//            }else {
//                System.err.printf("The %s is in invalid phone number format!\n", updatePhone);
//            }
//        }
//
//        /** Update Email */
//        while (isLoop) {
//            System.out.println("Enter updateEmail:");
//            String updateEmail = scanner.nextLine().trim();
//
//            if(updateEmail == "") {
//                break;
//            }
//
//            if(Pattern.matches("^\\w.*@\\w.*\\.\\w.*$", updateEmail)) {
//                this.email = updateEmail;
//            }else {
//                System.err.printf("The %s is in invalid email format!\n", updateEmail);
//            }
//        }
//
//        /** Update EmployeeStatus */
//        while (isLoop) {
//            System.out.printf("Enter choice EmployeeStatus:\n" +
//                             "0. Active - 1. Resigned - 2. On leave\n");
//            String updateStatus =  scanner.nextLine().trim();
//
//            if(updateStatus == "") {
//                break;
//            }
//
//            if(Pattern.matches("[012]",updateStatus)) {
//                this.empStatus = Integer.parseInt(updateStatus);
//            }else {
//                System.err.printf("The EmployeeStatus \"%s\" is incorrect", updateStatus);
//            }
//        }
//    }

    /**
     * name: display().
     * @param: none.
     * function: show employee information
     * date: 22/09/2023
     */
    public void display() {
        System.out.printf("*****Thông tin Ca sĩ %s *****\n", singerName);
        System.out.printf("Mã ca sĩ: %d - Tên ca sĩ: %s\n" +
                          "tuổi: %d - Quốc tịch: %s\n" +
                          "Giới tính: %s - Thể loại: %s\n",
                          singerId, singerName, age, nationality, (gender? "Nam" : "Nữ"), genre);
        System.out.printf("\n");
    }
}
