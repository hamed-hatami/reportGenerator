package ir.dotin.core.utils;


import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;

public class RESTfulClientUtil implements Serializable {

    public String callJobService(String serviceName, String parameters) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(Configuration.getProperty("job.service.url") + serviceName);

            postRequest.setEntity(new StringEntity(parameters));
            HttpResponse response = client.execute(postRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            String result = "";
            String output = "";
            while ((output = br.readLine()) != null) {
                result += output;
            }

            client.getConnectionManager().shutdown();
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String callBPMService(String serviceName, String parameters) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(Configuration.getProperty("bpm.service.url") + serviceName);

            postRequest.setEntity(new StringEntity(parameters));
            HttpResponse response = client.execute(postRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            String result = "";
            String output = "";
            while ((output = br.readLine()) != null) {
                result += output;
            }

            client.getConnectionManager().shutdown();
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String callBRMService(String serviceName, String parameters) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(Configuration.getProperty("brm.service.url") + serviceName);

            postRequest.setEntity(new StringEntity(parameters));
            HttpResponse response = client.execute(postRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            String result = "";
            String output = "";
            while ((output = br.readLine()) != null) {
                result += output;
            }

            client.getConnectionManager().shutdown();
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String restFullService(String url, String serviceName, String jsonString) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url + serviceName);
            postRequest.setHeader("Content-type", "application/json");
            postRequest.setEntity(new StringEntity(jsonString, "UTF-8"));
            HttpResponse response = client.execute(postRequest);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            return IOUtils.toString(response.getEntity().getContent(), "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String restFullServiceString(String url, String serviceName, String value) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url + serviceName);
            postRequest.setHeader("Content-type", "TEXT/PLAIN");
            postRequest.setEntity(new StringEntity(value, "UTF-8"));
            HttpResponse response = client.execute(postRequest);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            return IOUtils.toString(response.getEntity().getContent(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String restFullServiceStringAsJson(String url, String serviceName, String value, String contentType) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url + serviceName);
            postRequest.setHeader("Content-type", contentType);
            postRequest.setEntity(new StringEntity(value, "UTF-8"));
            HttpResponse response = client.execute(postRequest);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }

            return IOUtils.toString(response.getEntity().getContent(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public String restFullService(String url, String serviceName) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost postRequest = new HttpPost(url + serviceName);
            postRequest.setHeader("Content-type", "application/json; charset=UTF-8");
            HttpResponse response = client.execute(postRequest);
            if (response.getStatusLine().getStatusCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatusLine().getStatusCode());
            }
            return IOUtils.toString(response.getEntity().getContent(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}