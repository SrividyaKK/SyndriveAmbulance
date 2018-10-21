package universe.sk.syndriveambulance.Common;

import universe.sk.syndriveambulance.Remote.IGoogleAPI;
import universe.sk.syndriveambulance.Remote.RetrofitClient;

public class Common {
    public static final String baseURL = "https://maps.googleapis.com";
    public static IGoogleAPI getGoogleAPI() {
        return RetrofitClient.getClient(baseURL).create(IGoogleAPI.class);
    }
}