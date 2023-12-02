package com.example.demo.minio.service;


import com.example.demo.minio.entity.FileInfo;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.Item;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MinioService implements IMinioService {
    private final MinioClient minioClient;
    private static final String BUCKET_NAME = "student";

    public MinioService(MinioClient minioClient) {
        this.minioClient = minioClient;
    }


    @Override
    public FileInfo uploadByFile(MultipartFile file, String filePath) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String objectName = filePath + "/" + file.getOriginalFilename();
        ObjectWriteResponse response = minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(BUCKET_NAME)
                        .object(objectName)
                        .contentType(file.getContentType())
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .build()
        );

        return FileInfo.builder()
                .name(file.getOriginalFilename())
                .size(file.getSize())
                .url(getUrlFile(response.object()))
                .build();
    }

    @Override
    public FileInfo uploadByLink(String link, String filePath) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        String fileName = StringUtils.substringAfterLast(link, "/");

        Path path = new File(fileName).toPath();
        String mimeType = Files.probeContentType(path);

        InputStream fileInputStream = null;
        int fileSize = 0;
        try {
            URLConnection urlConnection = new URL(link).openConnection();
            fileSize = urlConnection.getContentLength();
            fileInputStream = new URL(link).openStream();

        } catch (IOException e) {
            e.printStackTrace();
        }

        ObjectWriteResponse response = minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(BUCKET_NAME)
                        .object(filePath + "/" + fileName)
                        .contentType(mimeType)
                        .stream(fileInputStream, fileSize, -1)
                        .build()
        );

        return FileInfo.builder()
                .name(fileName)
                .size((long) fileSize)
                .url(getUrlFile(response.object()))
                .build();
    }

    @Override
    public List<FileInfo> getList() {
        Iterable<Result<Item>> results = minioClient.listObjects(
                ListObjectsArgs.builder().bucket(BUCKET_NAME).build());

        List<FileInfo> fileInfos = new ArrayList<>();

        results.forEach(
                value -> {
                    try {
                        Item item = value.get();

                        fileInfos.add(
                                FileInfo.builder()
                                        .name(item.objectName())
                                        .size(item.size())
                                        .url(
                                                getUrlFile(item.objectName())
                                        )
                                        .build()
                        );

                    } catch (ErrorResponseException | InsufficientDataException | InternalException |
                             InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException |
                             ServerException | XmlParserException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        return fileInfos;
    }

    private String getUrlFile(String object) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .object(object)
                        .bucket(BUCKET_NAME)
                      //  .expiry(2, TimeUnit.HOURS)
                        .method(
                                Method.GET
                        )
                        .build()
        );
    }

}