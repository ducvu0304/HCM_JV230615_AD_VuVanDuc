package ra.service;

import ra.MusicManagement;
import ra.model.Singer;
import ra.model.Song;

import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SongService {

    public void addSong(Scanner scanner, Song[] songs, Singer[] singers) {
        boolean isLoop = true;
        boolean iExist = false;
        int number = 0;

        try {
            System.out.println("Nhập vào số lượng bài hát cần thêm");
            number = Integer.parseInt(scanner.nextLine().trim());
        } catch (InputMismatchException e) {
            System.err.println("Nhập sai định dạng");
        }

        /**Input data*/
        for (int i = 0; i < number; i++) {
            /**Khởi tạo đối tượng*/
            Song song = new Song();

            System.out.println("****************");
            /** Input song id */
            while (isLoop) {
                iExist = false;

                System.out.println("XIn mời nhập mã bài hát:");
                String songId = scanner.nextLine().trim();

                if (Pattern.matches("^S\\w{3}$", songId)) {
                    for (int j = 0; j < MusicManagement.songIndex; j++) {
                        if (songs[i].getSongName().equals(songId)) {
                            iExist = true;
                            System.err.printf("Mã bài hat \"%S\" đã tồn tại!\n", songId);
                            break;
                        }
                    }
                } else {
                    iExist = true;
                    System.err.printf("Tên bài hát \"%s\" phải là 4 ký từ và bắt đầu là ký tự \"S\"\n", songId);
                }

                if (!iExist) {
                    song.setSongId(songId);
                    break;
                }
            }

            /** Input song name */
            String songName = "";
            do {
                System.out.println("Xin nhập tên bài hát:");
                songName = scanner.nextLine().trim();

                if (songName.length() >= 1) {
                    song.setSongName(songName);
                    break;
                } else {
                    System.err.println("Tên ca sĩ không được để trống!");
                }
            } while (songName.length() < 1);

            /** Input descriptions */
            System.out.println("Xin mời nhập mô tả bài hát:");
            String descriptions = scanner.nextLine().trim();
            song.setDescriptions(descriptions);

            /** Input singer */
            while (isLoop) {
                iExist = false;

                try {
                    System.out.println("Xin mời nhập mã ca sĩ");
                    int singerId = Integer.parseInt(scanner.nextLine().trim());

                    if (singerId >= 1 && singerId <= MusicManagement.singerIndex) {
                        iExist = true;
                        song.setSinger(singers[singerId - 1]);
                        break;
                    }

                    if (!iExist) {
                        System.err.printf("Mã ca sĩ \"%s\" không có trong danh sách. Xin mời nhập lại\n", singerId);
                    }

                } catch (InputMismatchException e) {
                    System.err.println("Nhập sai định dạng");
                }
            }

            /** Input songwriter */
            String songWriter = "";
            do {
                try {
                    System.out.println("Xin mời nhập người sáng tác:");
                    songWriter = scanner.nextLine().trim();

                    if (songWriter.length() >= 1) {
                        song.setSongWriter(songWriter);
                        break;
                    }else {
                        System.err.println("Người sáng tác không để trống");
                    }

                } catch (InputMismatchException e) {
                    System.err.println("Nhập sai định dạng");
                }
            } while (songWriter.length() < 1);

            /** Input date */
            Calendar calendar = Calendar.getInstance();
            song.setCreatedDate(calendar.getTime());

            /** Input song status */
            do {
                boolean isExit = false;

                try {
                    System.out.printf("Xin mời chọn trạng thai bài hát (1. Phổ biến - 2. Không phổ biên):\n");
                    int choiceStatus = Integer.parseInt(scanner.nextLine().trim());

                    switch (choiceStatus) {
                        case 1:
                            song.setSongStatus(true);
                            isExit = true;
                            break;
                        case 2:
                            song.setSongStatus(false);
                            isExit = true;
                            break;
                        default:
                            System.err.printf("Lưa chọn \"d\" không có", choiceStatus);
                    }

                    if (isExit) {
                        break;
                    }

                } catch (InputMismatchException e) {
                    System.err.println("Nhập định dạng không hợp lệ!");
                }
            } while (isLoop);


            /**Thêm ca sĩ vào danh sách*/
            songs[MusicManagement.songIndex] = song;
            MusicManagement.songIndex++;
        }
    }

    public void findAllSong(Song[] songs) {
        Optional<Song> optionalSinger = Optional.ofNullable(songs[0]);

        if (optionalSinger.isPresent()) {
            for (int i = 0; i < MusicManagement.songIndex; i++) {
                songs[i].display();
            }
        } else {
            System.err.println("Danh sách ca sĩ chưa được lập");
        }
    }

    public void updateSong(Scanner scanner, Song[] songs) {
        boolean isLoop = true;
        int index = 0;

        /***/
        System.out.println("Xin mời nhập mã bài hát");
        String songId = scanner.nextLine().trim();

        /**Check singer is exists by id  */
        boolean isExist = false;

        for (int i = 0; i < MusicManagement.songIndex; i++) {
            if (songs[i].getSongId().equals(songId)) {
                isExist = true;
                index = i;
                break;
            }
        }

        /**Update singer*/
        if (!isExist) {
            System.err.printf("Mã bài hát \"%d\" không có trong danh sách\n", songId);
        } else {
            /** Update song name */
            System.out.println("Xin nhập tên bài hát:");
            String songName = scanner.nextLine().trim();

            if (songName != "") {
                songs[index].setSongName(songName);
            }

            /** Input description */
            System.out.println("Xin mời nhập tuổi:");
            String description = scanner.nextLine().trim();

            if (description != "") {
                songs[index].setDescriptions(description);
            }


            /** Update songwriter */
            System.out.println("Xin mời nhập quốc tịch");
            String songWriter = scanner.nextLine().trim();

            if (!songWriter.equals("")) {
                songs[index].setSongWriter(songWriter);
            }

            /** Update song status */
            System.out.printf("Xin mời chọn trang thái (1. Phổ biến - 2. Không phổ biến )\n" +
                    "Nếu không chọn sẽ giữ nguyên\n");
            String choiceStatus = scanner.nextLine().trim();

            if (Pattern.matches("^[0-9]$", choiceStatus)) {
                do {
                    boolean isExit = false;

                    System.out.printf("Xin mời chọn giới tính (1. Nam - 2. Nữ):\n");
                    int choice = Integer.parseInt(choiceStatus);

                    switch (choice) {
                        case 1:
                            songs[index].setSongStatus(true);
                            isExit = true;
                            break;
                        case 2:
                            songs[index].setSongStatus(false);
                            isExit = true;
                            break;
                        default:
                            System.err.printf("Lưa chọn \"d\" không có", choiceStatus);
                    }

                    if (isExit) {
                        break;
                    }

                } while (isLoop);
            }
        }
    }

    public void deleteSong(Scanner scanner, Song[] songs) {
        int index = 0;
        boolean isExist = false;

        System.out.println("Xin mời nhập mã ca sĩ");
        String songID = scanner.nextLine().trim();

        /**Check Singer is exists by id*/
        for (int i = 0; i < MusicManagement.songIndex; i++) {
            if (songs[i].getSongId().equals(songID)) {
                index = i;
                isExist = true;
                break;
            }
        }

        if (isExist) {
            for (int i = index; i < MusicManagement.singerIndex - 1; i++) {
                songs[i] = songs[i + 1];
            }
            songs[MusicManagement.songIndex] = null;
            MusicManagement.songIndex--;
        } else {
            System.out.printf("Không tìm thấy bài hát có mã có mã \"%s\"\n", songID);
        }

    }


    public void findSongBySingerNameOrGenre(Scanner scanner, Song[] songs) {
        System.out.println("Xin mời nhập tên cá sĩ hoặc thể loại");
        String findValue = scanner.nextLine().trim();
        boolean isExist = false;

        for (int i = 0; i < MusicManagement.songIndex ; i++) {
            if(songs[i].getSinger().getSingerName().contains(findValue) || songs[i].getSinger().getGenre().contains(findValue)) {
                isExist = true;
                songs[i].getSinger().display();
                break;
            }
        }

        if(!isExist){
            System.err.printf("Không tìm thấy bài hát của ca sĩ \"%s\" hoặc thê loại \"%s\"\n", findValue, findValue);
        }
    }

    public void searchTenSong(Scanner scanner, Song[] songs) {
        for (int i = 0; i < MusicManagement.songIndex -1 ; i++) {
            for (int j = 0; j < MusicManagement.songIndex; j++) {
                if(songs[i].getSongName().charAt(0) > songs[i].getSongName().charAt(0)){
                    Song temp = songs[i];
                    songs[i] = songs[j];
                    songs[j] = temp;
                }
            }
        }
    }

    public void findSongByTopDate(Scanner scanner, Song[] songs) {
        for (int i = 0; i < MusicManagement.songIndex -1 ; i++) {
            for (int j = 0; j < MusicManagement.songIndex; j++) {
                if(songs[i].getCreatedDate().getTime() < songs[i].getCreatedDate().getTime()){
                    Song temp = songs[i];
                    songs[i] = songs[j];
                    songs[j] = temp;
                }
            }
        }
    }


}
