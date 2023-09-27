package ra.controller;

import ra.model.Singer;
import ra.model.Song;
import ra.service.SongService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SongController {
    public static void management(Scanner scanner, Song[] songs, Singer[] singers) {
        SongService songService = new SongService();

        boolean isLoop =  true;
        int choice = 0;

        while (isLoop) {
            System.out.println("**********************SONG-MANAGEMENT*************************");
            System.out.println("1. .Nhập vào số lượng bài hát cần thêm và \n " +
                    "nhập thông tin cần thêm mới (có validate dữ liệu nhập vào)");
            System.out.println("2. Hiển thị danh sách tất cả bài hát đã lưu trữ");
            System.out.println("3. Thay đổi thông tin bài hát theo mã id");
            System.out.println("4. Xóa bài hát theo mã id");
            System.out.println("5. Thoát");


            /******************SONG-FUNCTION********************/
            try{
                System.out.println("Xin mời chọn chức năng:");
                choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        songService.addSong(scanner, songs, singers);
                        break;
                    case 2:
                        songService.findAllSong(songs);
                        break;
                    case 3:
                        songService.updateSong(scanner, songs);
                        break;
                    case 4:
                        songService.deleteSong(scanner,songs);
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
