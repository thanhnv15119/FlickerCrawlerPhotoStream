package thanhnv;

import thanhnv.utils.ImageIOUtils;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        //args 0 is url of photo stream
        //args 1 is folder of photos that you want to save
        FlickerCrawler flickerCrawler = new FlickerCrawler();
        flickerCrawler.initDriver();
        flickerCrawler.crawlPhotoLinkDownload(args[0]);
        List<String> listPhoto =  flickerCrawler.getListPhoto();
        for (String url: listPhoto) {
            String fileName = url.replaceAll(".+/","");
            ImageIOUtils.saveImageByURL(url, args[1]);
            System.out.println(fileName + "was save");
        }
        System.out.println("WE DOWNLOAD " + listPhoto.size() + "photos");
    }
}
