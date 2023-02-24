package com.email.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.security.MessageDigest;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.NoSuchAlgorithmException;
import java.time.Instant;

@Getter
@Setter
public class CommonPath {
    private String AttachedFilePath;
    private String fileName;

    private String getPath() {
        return this.AttachedFilePath + "\\" + this.fileName;
    }

    private String getRandomString(int str_length) {
        // choose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(str_length);

        for (int i = 0; i < str_length; i++) {
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }

    public MailAttachment copyAttachedFileToLocal(String from_path) throws IOException, NoSuchAlgorithmException {

        // get file extension
        String extension = "";
        int i = from_path.lastIndexOf('.');
        if (i > 0) extension = from_path.substring(i + 1);
        String new_file_name = this.generateFileName() + "." + extension;
        String to_path = this.getAttachedFilePath() + "\\" + new_file_name ;
        File file_from = new File(from_path);

        MailAttachment mailAttachment = new MailAttachment();
        mailAttachment.setFile_path(to_path);
        mailAttachment.setFile_ext(extension);
        mailAttachment.setFile_name(new_file_name);
        mailAttachment.setOld_file_name(file_from.getName());

        // create destination file
        File file_to = new File(to_path);
        file_to.createNewFile();
        InputStream is = null;
        OutputStream os = null;

        // try to copy data to new file
        try {
            is = new FileInputStream(file_from);
            os = new FileOutputStream(file_to);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            is.close();
            os.close();
        }
        return mailAttachment; // new location
    }

    public String generateFileName() throws NoSuchAlgorithmException {
        String timestamp = Instant.now().toString();
        String random_name = this.getRandomString(10);
        String md5 = DigestUtils.md5Hex(timestamp + random_name).toUpperCase();
        return md5;
    }

}
