package co.id.bcafinance.finalproject.configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
public class CloudinaryConfig {
    Cloudinary cloudinary;

//    @Value("${cloud_name}")
//    private String cloud_name;
//    @Value("${api_key}")
//    private String api_key;
//    @Value("${api_secret}")
//    private String api_secret;

    public CloudinaryConfig() {
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("cloud_name", "ddyeqyqmm");
        valuesMap.put("api_key", "948617724999625");
        valuesMap.put("api_secret", "klvJbe5kP8-AvCk5l6PpZ8WSDsY");
        cloudinary = new Cloudinary(valuesMap);
    }


    public Map upload(MultipartFile multipartFile) throws IOException {
        File file = convert(multipartFile);
        HashMap<Object, Object> options = new HashMap<>();
        options.put("folder", "barang");

        Map result = cloudinary.uploader().upload(file, options);
        if (!Files.deleteIfExists(file.toPath())) {
            throw new IOException("Failed to delete temporary file: " + file.getAbsolutePath());
        }
        return result;
    }

    public Map delete(String id) throws IOException {
        HashMap<Object, Object> options = new HashMap<>();
        options.put("folder", "barang");
        return cloudinary.uploader().destroy("barang/" + id, options);
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
}


