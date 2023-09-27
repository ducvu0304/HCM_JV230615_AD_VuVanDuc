package ra.controller;

import ra.model.Singer;
import ra.model.Song;
import ra.service.SingerService;
import ra.service.SongService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SearchController {
    public static void management(Scanner scanner, Song[] songs, Singer[] singers) {

        SingerService singerService =  new SingerService();
        SongService songService = new SongService();

        boolean isLoop =  true;
        int choice = 0;

        while (isLoop) {
            System.out.println("**********************SEARCH-MANAGEMENT*************************");
            System.out.println("1. Tìm kiếm bài hát theo tên ca sĩ hoặc thể loại");
            System.out.println("2. Tìm kiếm ca sĩ theo tên hoặc thể loại");
            System.out.println("3. Hiển thị danh sách bài hát theo thứ tự tên tăng dần");
            System.out.println("4. Hiển thị 10 bài hát được đăng mới nhất");
            System.out.println("5. Thoát");


            /******************SEARCH-FUNCTION********************/
            try{
                System.out.println("Xin mời chọn chức năng:");
                choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        songService.findSongBySingerNameOrGenre(scanner, songs);
                        break;
                    case 2:
                        singerService.findSingerByNameOrGenre(scanner, singers);
                        break;
                    case 3:
                        songService.searchTenSong(scanner, songs);
                        break;
                    case 4:
                        songService.findSongByTopDate(scanner, songs);
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
