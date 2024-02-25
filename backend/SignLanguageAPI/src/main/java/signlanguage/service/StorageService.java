package signlanguage.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final Storage storage;
    private final String bucketName = "sign-language-f4752.appspot.com";



    public Map<String, String> getASLNumber() {
        String folderASLNumber = "ASL number/";
        Map<String, String> filesWithNames = new LinkedHashMap<>();
        for (Blob blob : storage.list(bucketName, Storage.BlobListOption.prefix(folderASLNumber)).iterateAll()) {
            if (!blob.getName().equals(folderASLNumber)) {
                String fileName = blob.getName();
                String[] parts = fileName.split("/");
                String imageName = parts[parts.length - 1].replaceAll("\\..*$", "");
                filesWithNames.put(fileName, imageName);
            }
        }
        return filesWithNames;
    }
    public Map<String, String> getASLAlphabet() {
        String folderASLAlphabet = "ASL alphabet/";
        Map<String, String> filesWithNames = new LinkedHashMap<>();
        for (Blob blob : storage.list(bucketName, Storage.BlobListOption.prefix(folderASLAlphabet)).iterateAll()) {
            if (!blob.getName().equals(folderASLAlphabet)) {
                String fileName = blob.getName();
                String[] parts = fileName.split("/");
                String imageName = parts[parts.length - 1].replaceAll("\\..*$", "");
                filesWithNames.put(fileName, imageName);
            }
        }
        return filesWithNames;
    }
}
