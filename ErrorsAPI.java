package advisor;

import com.google.gson.JsonElement;

public class ErrorsAPI extends Exception {
    public ErrorsAPI(String  error_description) {
        super(error_description);
    }
}
