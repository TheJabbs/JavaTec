package com.example.posjohonnyjavatecspring2023;

import com.example.posjohonnyjavatecspring2023.DTO.FoodDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import static com.example.posjohonnyjavatecspring2023.TempStorage.employee;

public class ApiClient {
    private OkHttpClient client;

    public ApiClient() {
        this.client = new OkHttpClient();
    }

    public List<FoodDto> getAllFood(Integer id) {
        OkHttpClient client = new OkHttpClient();  // Assume client is initialized here, could be moved to a shared area of your class if reused
        Request request = new Request.Builder()
                .url("http://localhost:8080/api/v1/foods/fetchAll/" + id)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                ObjectMapper objectMapper = new ObjectMapper();
                // Assuming ObjectFood is correctly annotated to be parsed by Jackson
                List<FoodDto> foods = objectMapper.readValue(responseBody, new TypeReference<List<FoodDto>>() {});
                System.out.println("Fetched data: " + foods);
                return foods;
            } else {
                System.out.println("Failed to fetch data: " + response);
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error during HTTP call:");
            e.printStackTrace();
            return null;
        }
    }


    public boolean auth(String username, String password) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("employeeUsername", username);
        jsonObject.put("employeePassword", password);

        RequestBody body = RequestBody.create(jsonObject.toString(), MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url("http://localhost:8080/api/v1/employees/authenticate")
                .post(body)
                .build();


        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                //Parse the response JSON into an objectUser
                JSONObject jsonResponse = new JSONObject(response.body().string());
                employee.setEmployeeId(jsonResponse.getInt("employeeId"));
                employee.setEmployeeFname(jsonResponse.getString("employeeFname"));
                employee.setEmployeeLname(jsonResponse.getString("employeeLname"));
                employee.setEmployeeStatus(jsonResponse.getString("employeeStatus"));
                employee.setEmployeeUsername(jsonResponse.getString("employeeUsername"));
                employee.setEmployeePassword(jsonResponse.getString("employeePassword"));
                employee.setRestaurantId(jsonResponse.getInt("restaurantId"));



                return employee.getEmployeeId() != 0;
            } else {
                System.out.println(response.body().string() + "\n" + response.code()+
                 "\n" + response.toString());
                return false;
            }
        }catch (JSONException | IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public void clockIn() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("employeeUsername", employee.getEmployeeUsername());
        jsonObject.put("employeePassword", employee.getEmployeePassword());

        RequestBody body = RequestBody.create(jsonObject.toString(), MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url("http://localhost:8080/api/v1/labors/start")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("Clocked in");
            } else {
                System.out.println("Failed to clock in: " + response);
            }
        } catch (IOException e) {
            System.out.println("Error during HTTP call:");
            e.printStackTrace();
        }
    }

    public void clockOut() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("employeeUsername", employee.getEmployeeUsername());
        jsonObject.put("employeePassword", employee.getEmployeePassword());

        RequestBody body = RequestBody.create(jsonObject.toString(), MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url("http://localhost:8080/api/v1/labors/end")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("Clocked out");
            } else {
                System.out.println("Failed to clock out: " + response);
            }
        } catch (IOException e) {
            System.out.println("Error during HTTP call:");
            e.printStackTrace();
        }
    }
}

