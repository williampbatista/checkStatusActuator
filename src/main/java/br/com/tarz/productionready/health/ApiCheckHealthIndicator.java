package br.com.tarz.productionready.health;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@SuppressWarnings("deprecation")
@Component
public class ApiCheckHealthIndicator implements HealthIndicator {

	private final String URL_BRAND = "http://localhost:8081/enroll/brands";
	private final String URL_PRODUCT = "http://localhost:8081/enroll/products";
	private final String URL_BRAND_PRODUCT = "http://localhost:8081/enroll/brandproduct/";
	private final String URL_CUSTOMER = "http://localhost:8081/enroll/customer";
	private final String URL_CONTACT = "http://localhost:8081/enroll/contacts/";
	private final String URL_ADDRESS = "http://localhost:8081/enroll/addresses/";
	private final String URL_PLAN = "http://localhost:8081/enroll/plans/";
	private final String URL_AGREEMENT = "http://localhost:8081/enroll/agreements/";
	private final String URL_AGREEMENT_DETAIL = "http://localhost:8081/enroll/agreements-detail/";
	private final String URL_INSTALLMENT_TYPES = "http://localhost:8081/enroll/installment-types/";

	@Override
	public Health health() {
		try {
			return Health.up().withDetail("ENROLL STATUS", prepareUrls()).build();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Health.down().build();
	}

	protected int cheackGetEnroll(String url) throws ClientProtocolException, IOException {
		HttpGet request = new HttpGet(url);
		HttpResponse response = new DefaultHttpClient().execute(request);

		return response.getStatusLine().getStatusCode();

	}

	public HashMap<String, Object> prepareUrls() throws ClientProtocolException, IOException {
		HashMap<String, Object> map = new HashMap<String, Object>();
//		map.put("version", buildProperties.getVersion());
		map.put(URL_BRAND, "Status: " + cheackGetEnroll(URL_BRAND));
		map.put(URL_PRODUCT, "Status: " + cheackGetEnroll(URL_PRODUCT));
		map.put(URL_BRAND_PRODUCT, "Status: " + cheackGetEnroll(URL_BRAND_PRODUCT));
		map.put(URL_CUSTOMER, "Status: " + cheackGetEnroll(URL_CUSTOMER));
		map.put(URL_CONTACT, "Status: " + cheackGetEnroll(URL_CONTACT));
		map.put(URL_ADDRESS, "Status: " + cheackGetEnroll(URL_ADDRESS));
		map.put(URL_PLAN, "Status: " + cheackGetEnroll(URL_PLAN));
		map.put(URL_AGREEMENT, "Status: " + cheackGetEnroll(URL_AGREEMENT));
		map.put(URL_AGREEMENT_DETAIL, "Status: " + cheackGetEnroll(URL_AGREEMENT_DETAIL));
		map.put(URL_INSTALLMENT_TYPES, "Status: " + cheackGetEnroll(URL_INSTALLMENT_TYPES));
		return map;
	}

}
