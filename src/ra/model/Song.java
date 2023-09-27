package ra.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Song {
    private String songId;
    private String songName;
    private String descriptions;
    private Singer singer;
    private String songWriter;
    private Date createdDate;
    private boolean songStatus;

    public Song() {
    }

    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public String getSongWriter() {
        return songWriter;
    }

    public void setSongWriter(String songWriter) {
        this.songWriter = songWriter;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isSongStatus() {
        return songStatus;
    }

    public void setSongStatus(boolean songStatus) {
        this.songStatus = songStatus;
    }

    /**
     * name: inputData(Scanner scanner)
     *
     * @param: scanner
     * function: input data
     * date: 22/09/2023
     */
    public void inputData(Scanner scanner, Song[] songs, int songIndex, Singer[] singers, int singerIndex) {
        boolean isLoop = true;
        boolean iExist = false;


        /** Input song id */
        while (isLoop) {
            iExist = false;

            System.out.println("XIn mời nhập mã bài hát:");
            String songId = scanner.nextLine().trim();

            if (Pattern.matches("^S\\w{3}$", this.songId)) {
                for (int i = 0; i < songIndex; i++) {
                    if (songs[i].getSongName().equals(songId)) {
                        iExist = true;
                        System.err.printf("Mã bài hat \"%S\" đã tồn tại!\n", this.songId);
                        break;
                    }
                }
            } else {
                System.err.printf("Tên bài hát \"%s\" phải là 4 ký từ và bắt đầu là ký tự \"S\"\n", this.songId);
            }

            if (!iExist) {
                break;
            }
        }


        /** Input song name */
        do {
            System.out.println("Xin nhập tên bài hát:");
            this.songName = scanner.nextLine().trim();

            if (songName.length() < 1) {
                System.err.println("Tên bài hát không được để trống!");
            }
        } while (songName.length() < 1);

        /** Input descriptions */
        System.out.println("Xin mời nhập mô tả bài hát:");
        this.descriptions = scanner.nextLine().trim();


//        /** Input singer */
//        while (isLoop) {
//            iExist = false;
//
//            try {
//                System.out.println("Xin mời nhập mã ca sĩ");
//                int singerId = Integer.parseInt(scanner.nextLine().trim());
//
//                if (singerId >= 1 && singerId <= singerIndex) {
//                    iExist = true;
//                    this.singer = singers[singerId - 1];
//                    break;
//                }
//
//                if (!iExist) {
//                    System.err.printf("Mã ca sĩ \"%s\" không có trong danh sách. Xin mời nhập lại\n", singerId);
//                }
//
//            } catch (InputMismatchException e) {
//                System.err.println("Nhập sai định dạng");
//            }
//        }

        /** Input songwriter */
        do {
            iExist = false;

            try {
                System.out.println("Xin mời nhập người sáng tác:");
                this.songWriter = scanner.nextLine().trim();


                if (this.songWriter.length() < 1) {
                    System.err.println("Người sáng tác không để trống");
                }

            } catch (InputMismatchException e) {
                System.err.println("Nhập sai định dạng");
            }
        } while (this.songWriter.length() < 1);


        /** Input date */
        Calendar calendar = Calendar.getInstance();
        this.createdDate = calendar.getTime();

        /** Input gender */
        do{
            boolean isExit = false;

            try {
                System.out.printf("Xin mời chọn trạng thai bài hát (1. Phổ biến - 2. Không phổ biên):\n");
                int choiceStatus = Integer.parseInt(scanner.nextLine().trim());

                switch (choiceStatus) {
                    case 1:
                        this.songStatus = true;
                        isExit = true;
                        break;
                    case 2:
                        this.songStatus = false;
                        isExit = true;
                        break;
                    default:
                        System.err.printf("Lưa chọn \"d\" không có", choiceStatus);
                }

                if(isExit) {
                    break;
                }

            }catch (InputMismatchException e){
                System.err.println("Nhập định dạng không hợp lệ!");
            }
        }while (isLoop);
    }

    /**Display data*/
    public void display() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        System.out.printf("*****Thông tin bài hát \"%s\" *****\n", songName);
        System.out.printf("Mã bài hát: %s - Tên bài hát: %s\n" +
                        "Mô tả: %s \n" +
                        "Tên ca sĩ: %s\n" +
                        "Tên người sáng tác: %s\n" +
                        "Ngày tạo: %s \n" +
                        "Trạng thái: %s\n",
                songId, songName, descriptions, singer.getSingerName(),
                songWriter, simpleDateFormat.format(createdDate), (songStatus ? "Phổ biến" : "Không phổ biến"));
    }
}


