package com.example.demo;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
import com.microsoft.azure.storage.blob.BlobRequestOptions;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.util.HashMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {


  public static void main(String[] args) {
//    try {
//
//      File sourceFile = File.createTempFile("sampleFile", ".txt");
//      System.out.println("Creating a sample file at: " + sourceFile.toString());
//      Writer output = new BufferedWriter(new FileWriter(sourceFile));
//      output.write("Hello Azure!");
//      output.close();
//
//      CloudBlockBlob blob = container.getBlockBlobReference("test/" + sourceFile.getName());
//      blob.getProperties().setContentType(Files.probeContentType(sourceFile.toPath()));
//
//      HashMap<String, String> metadata = new HashMap<>();
//      metadata.put("test", "test value");
//      blob.setMetadata(metadata);
//      blob.uploadFromFile(sourceFile.getAbsolutePath());
//
//    } catch (URISyntaxException e) {
//      e.printStackTrace();
//    } catch (InvalidKeyException e) {
//      e.printStackTrace();
//    } catch (StorageException e) {
//      e.printStackTrace();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }

    SpringApplication.run(DemoApplication.class, args);
  }
}
