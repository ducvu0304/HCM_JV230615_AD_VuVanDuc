package ra.controller;

import ra.model.Singer;
import ra.service.SingerService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SingerController {

    public static void management(Scanner scanner, Singer[] singers) {
        SingerService singerService =  new SingerService();
        boolean isLoop =  true;
        int choice = 0;


        while (isLoop) {
            System.out.println("**********************SINGER-MANAGEMENT*************************");
            System.out.println("1. .Nhập vào số lượng ca sĩ cần thêm và \n " +
                    "nhập thông tin cần thêm mới (có validate dữ liệu nhập vào)");
            System.out.println("2. Hiển thị danh sách tất cả ca sĩ đã lưu trữ");
            System.out.println("3. Thay đổi thông tin ca sĩ theo mã id");
            System.out.println("4. Xóa ca sĩ theo mã id");
            System.out.println("5. Thoát");


            /******************SINGER-FUNCTION********************/
            try{
                System.out.println("Xin mời chọn chức năng:");
                choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        singerService.addSinger(scanner, singers);
                        break;
                    case 2:

                        singerService.findAllSinger(singers);
                        break;
                    case 3:
                        singerService.updateSinger(scanner, singers);
                        break;
                    case 4:
                        singerService.deleteSinger(scanner, singers);
                        break;
                    case 5:
                        isLoop = false;
                        break;
                    case 6:
                        System.err.printf("Chức năng \"%d\" không có\n", choice);
                }

            }catch (InputMismatchException e) {
                System.err.println("Nhập sai định dạng!");
            }
        }
    }
}
