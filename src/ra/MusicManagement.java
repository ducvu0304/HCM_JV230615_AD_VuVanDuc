package ra;

import ra.controller.SearchController;
import ra.controller.SingerController;
import ra.controller.SongController;
import ra.model.Singer;
import ra.model.Song;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MusicManagement {

    public static int singerIndex = 0;
    public static int songIndex = 0;
    public static Song[] songs = new Song[50];
    public static Singer[] singers =  new Singer[50];

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        boolean isLoop = true;
        int choice = 0;

        while (isLoop) {
            System.out.println("************************MUSIC-MANAGEMENT*************************");
            System.out.println("1. Quản lý ca sĩ");
            System.out.println("2. Quản lý bài hát");
            System.out.println("3. Tìm kiếm bài hát");
            System.out.println("4. Thoát");

            try{
                System.out.println("Xin mời chọn chức năng:");
                choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        SingerController.management(scanner, singers);
                        break;
                    case 2:
                        SongController.management(scanner, songs, singers);
                        break;
                    case 3:
                        SearchController.management(scanner, songs, singers);
                        break;
                    case 4:
                        scanner.close();
                        System.exit(0);
                    default:
                        System.err.printf("Chức năng \"%d\" không có", choice);
                }


            }catch (InputMismatchException e){
                System.err.println("Nhập sai định dạng");
            }
        }
    }
}
