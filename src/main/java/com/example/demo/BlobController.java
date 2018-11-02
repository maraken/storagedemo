package com.example.demo;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.BlobListingDetails;
import com.microsoft.azure.storage.blob.BlobProperties;
import com.microsoft.azure.storage.blob.CloudBlob;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;
import com.microsoft.azure.storage.blob.ListBlobItem;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlobController {

  public static final String storageConnectionString =
      "DefaultEndpointsProtocol=http;" +
          "AccountName=devstoreaccount1;" +
          "AccountKey=Eby8vdM02xNOcqFlqUwJPLlmEtlCDXJ1OUzFT50uSRZ6IFsuFq2UVErCz4I6tq/K1SZFPTOtr/KBHBeksoGMGw==;" +
          "BlobEndpoint=http://127.0.0.1:10000/devstoreaccount1;";

  @GetMapping("/list")
  public ResponseEntity getBlobList(@RequestParam(value="prefix", defaultValue="") String prefix) {
    CloudStorageAccount storageAccount;
    CloudBlobClient client;
    CloudBlobContainer container;
    List<String> items = new ArrayList<>();
    try {
      storageAccount = CloudStorageAccount.parse(storageConnectionString);

      client = storageAccount.createCloudBlobClient();
      container = client.getContainerReference("quickstartcontainer");

      for(ListBlobItem blobItem : container.listBlobs(prefix, true, EnumSet.of(BlobListingDetails.METADATA), null, null)) {
        if(blobItem instanceof CloudBlob) {
          CloudBlob blob = (CloudBlob) blobItem;
          items.add(blob.getName());
        }
      }
      return ResponseEntity.ok(items);
    } catch (URISyntaxException | StorageException | InvalidKeyException e) {
      e.printStackTrace();
    }
    return null;
  }

  @GetMapping("/get")
  public void getBlobFile(HttpServletResponse response,@RequestParam(value="name") String name) {
    CloudStorageAccount storageAccount;
    CloudBlobClient client;
    CloudBlobContainer container;
    CloudBlockBlob blob;

    try {
      storageAccount = CloudStorageAccount.parse(storageConnectionString);

      client = storageAccount.createCloudBlobClient();
      container = client.getContainerReference("quickstartcontainer");
      blob = container.getBlockBlobReference(name);
      BlobProperties properties = blob.getProperties();
      byte[] buffer = null;
      ByteArrayOutputStream copyData = new ByteArrayOutputStream();

      blob.download(copyData);

      response.setContentType(properties.getContentType());
      response.setHeader("Content-Disposition", String.format("inline; filename=\"" + blob.getName() + "\""));
      //response.setContentLength((int) properties.getLength());

      byte[] file = copyData.toByteArray();
      InputStream inputStream = new ByteArrayInputStream( file );

      FileCopyUtils.copy(inputStream, response.getOutputStream());


    } catch (URISyntaxException | StorageException | InvalidKeyException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
