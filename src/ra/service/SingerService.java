package ra.service;

import ra.MusicManagement;
import ra.model.Singer;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SingerService {
    private static int singerCount = 1;

    public void addSinger(Scanner scanner, Singer[] singers) {
        boolean isLoop = true;
        boolean iExist = false;
        int number = 0;

        try {
            System.out.println("Nhập vào số lượng ca sĩ cần thêm");
            number = Integer.parseInt(scanner.nextLine().trim());
        } catch (InputMismatchException e) {
            System.err.println("Nhập sai định dạng");
        }

        /**Input data*/
        for (int i = 0; i < number; i++) {
            /**Khởi tạo đối tượng*/
            Singer singer = new Singer();

            System.out.println("****************");
            /** Input singer id */
            int singerId = singerCount++;
            singer.setSingerId(singerId);

            /** Input singer name */
            String singerName = "";
            do {
                System.out.println("Xin nhập tên ca sĩ:");
                singerName = scanner.nextLine().trim();

                if (singerName.length() >= 1) {
                    singer.setSingerName(singerName);
                } else {
                    System.err.println("Tên ca sĩ không được để trống!");
                }
            } while (singerName.length() < 1);

            /** Input age */
            int age = 0;
            do {
                try {
                    System.out.println("Xin mời tuổi:");
                    age = Integer.parseInt(scanner.nextLine().trim());

                    if (age > 1) {
                        singer.setAge(age);
                    } else {
                        System.err.println("Tuổi ca sĩ phải lơn hơn 0");
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Nhập sai định dạng");
                }

            } while (age < 1);

            /** Input nationality */
            String nationality = "";
            do {
                System.out.println("Xin mời nhập quốc tịch:");
                nationality = scanner.nextLine().trim();

                if (!nationality.equals("")) {
                    singer.setNationality(nationality);
                } else {
                    System.err.println("Quốc tịch không được để trống");
                }
            } while (nationality.equals(""));

            /** Input gender */
            do {
                boolean isExit = false;

                try {
                    System.out.printf("Xin mời chọn giới tính (1. Nam - 2. Nữ):\n");
                    int choiceGender = Integer.parseInt(scanner.nextLine().trim());

                    switch (choiceGender) {
                        case 1:
                            singer.setGender(true);
                            isExit = true;
                            break;
                        case 2:
                            singer.setGender(false);
                            isExit = true;
                            break;
                        default:
                            System.err.printf("Lưa chọn \"d\" không có", choiceGender);
                    }

                    if (isExit) {
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Nhập định dạng không hợp lệ!");
                }
            } while (isLoop);


            /** Input genre */
            String genre = "";
            do {
                System.out.println("Xin mời nhập thể loại:");
                genre = scanner.nextLine().trim();

                if (genre.length() >= 1) {
                    singer.setGenre(genre);
                } else {
                    System.err.println("Thể loại không được bỏ trống");
                }
            } while (genre.length() < 1);

            /**Thêm ca sĩ vào danh sách*/
            singers[MusicManagement.singerIndex] = singer;
            MusicManagement.singerIndex++;
        }
    }

    /**
     * 2. name: findAllSinger
     **/
    public void findAllSinger(Singer[] singers) {
        Optional<Singer> optionalSinger = Optional.ofNullable(singers[0]);

        if (optionalSinger.isPresent()) {
            for (int i = 0; i < MusicManagement.singerIndex; i++) {
                singers[i].display();
            }
        } else {
            System.err.println("Danh sách ca sĩ chưa được lập");
        }
    }

    /**
     * name: updateSinger
     */
    public void updateSinger(Scanner scanner, Singer[] singers) {
        boolean isLoop = true;
        boolean isExist = false;
        int singerID = 0;
        int index = 0;

        /***/
        try {
            System.out.println("Xin mời nhập mã ca sĩ");
            singerID = Integer.parseInt(scanner.nextLine().trim());
        } catch (InputMismatchException e) {
            System.err.println("Nhập sai định dạng");
        }

        /**Check singer is exists by id  */
        isExist = false;
        for (int i = 0; i < MusicManagement.singerIndex; i++) {
            if (singers[i].getSingerId() == singerID) {
                isExist = true;
                index = i;
                break;
            }
        }

        /**Update singer*/
        if (!isExist) {
            System.err.printf("Mã ca sĩ \"%d\" không có trong danh sách\n", singerID);
        } else {

            /** Update singer name */
            System.out.println("Xin nhập tên ca sĩ:");
            String singerName = scanner.nextLine().trim();

            if (singerName != "") {
                singers[index].setSingerName(singerName);
            }


            /** Input age */
            System.out.println("Xin mời nhập tuổi:");
            String age = scanner.nextLine().trim();

            if (age != "") {
                if (Pattern.matches("^[0-9].*", age)) {
                    if (Integer.parseInt(age) >= 1) {
                        singers[index].setAge(Integer.parseInt(age));
                    } else {
                        System.err.println("Tuổi nhập phải lớn hơn 0.");
                    }
                } else {
                    System.err.printf("Tuổi %s không đúng định dạng\n", age);
                }
            }


            /** Update nationality */
            System.out.println("Xin mời nhập quốc tịch");
            String nationality = scanner.nextLine().trim();

            if (!nationality.equals("")) {
                singers[index].setNationality(nationality);
            }

            /** Update gender */
            System.out.printf("Xin mời chọn giới tính (1. Nam - 2. Nữ)\n" +
                    "Nếu không chọn sẽ giữ nguyên\n");
            String choiceGender = scanner.nextLine().trim();

            if (Pattern.matches("^[0-9]$", choiceGender)) {
                do {
                    boolean isExit = false;

                    System.out.printf("Xin mời chọn giới tính (1. Nam - 2. Nữ):\n");
                    int choice = Integer.parseInt(choiceGender);

                    switch (choice) {
                        case 1:
                            singers[index].setGender(true);
                            isExit = true;
                            break;
                        case 2:
                            singers[index].setGender(false);
                            isExit = true;
                            break;
                        default:
                            System.err.printf("Lưa chọn \"d\" không có", choiceGender);
                    }

                    if (isExit) {
                        break;
                    }

                } while (isLoop);

                /** Update genre */
                String genre = "";

                if(!genre.equals("")) {
                    singers[index].setGenre(genre);
                }
            }
        }
    }

    public void deleteSinger(Scanner scanner, Singer[] singers) {
        int index = 0;
        boolean isExist = false;

        while (true) {
            try{
                System.out.println("Xin mời nhập mã ca sĩ");
                int singerId = Integer.parseInt(scanner.nextLine().trim());

                /**Check Singer is exists by id*/
                for (int i = 0; i < MusicManagement.singerIndex; i++) {
                    if(singers[i].getSingerId() == singerId) {
                        index = i;
                        isExist = true;
                        break;
                    }
                }

                if(isExist) {
                    for (int i = index; i < MusicManagement.singerIndex-1 ; i++) {
                        singers[i] = singers[i+1];
                    }
                    singers[MusicManagement.singerIndex] = null;
                    MusicManagement.singerIndex--;
                    break;
                }else {
                    System.out.printf("Không tìm thấy ca sĩ có mã \"%s\"\n", singers);
                    break;
                }
            }catch (Exception e) {
                System.err.println("Nhập sai định dạng");
            }
        }
    }

    public void findSinger(Scanner scanner, Singer[] singers, int singeIndex) {

    }


    public void findSingerByNameOrGenre(Scanner scanner, Singer[] singers) {
        System.out.println("Xin mời nhập tên ca sĩ hoặc thể loại");
        String findValue = scanner.nextLine().trim();
        boolean isExist = false;

        for (int i = 0; i < MusicManagement.singerIndex; i++) {
            if(singers[i].getSingerName().contains(findValue) || singers[i].getGenre().contains(findValue)) {
                isExist = true;
                singers[i].display();
            }
        }

        if(!isExist) {
            System.err.printf("Không tìm thấy ca sĩ tên \"%s\" hoặc thể loại \"%s\"\n", findValue, findValue);
        }
    }



}
