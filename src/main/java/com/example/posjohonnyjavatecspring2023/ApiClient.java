package com.example.posjohonnyjavatecspring2023;

import com.example.posjohonnyjavatecspring2023.DTO.EmployeeDto;
import com.example.posjohonnyjavatecspring2023.DTO.FoodDto;
import com.example.posjohonnyjavatecspring2023.Entity.OrdersEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
                List<FoodDto> foods = objectMapper.readValue(responseBody, new TypeReference<List<FoodDto>>() {
                });
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
                System.out.println(response.body().string() + "\n" + response.code() +
                        "\n" + response.toString());
                return false;
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean clockIn(EmployeeDto employeeDto) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("employeeUsername", employeeDto.getEmployeeUsername());
        jsonObject.put("employeePassword", employeeDto.getEmployeePassword());

        RequestBody body = RequestBody.create(jsonObject.toString(), MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url("http://localhost:8080/api/v1/labors/start")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("Clocked in");
                return true;
            } else {
                System.out.println("Failed to clock in: " + response);
                return false;
            }
        } catch (IOException e) {
            System.out.println("Error during HTTP call:");
            e.printStackTrace();
            return false;
        }
    }

    public boolean clockOut(EmployeeDto employeeDto) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("employeeUsername", employeeDto.getEmployeeUsername());
        jsonObject.put("employeePassword", employeeDto.getEmployeePassword());

        RequestBody body = RequestBody.create(jsonObject.toString(), MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url("http://localhost:8080/api/v1/labors/end")
                .put(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("Clocked out");
                return true;
            } else {
                System.out.println("Failed to clock out: " + response);
                return false;
            }
        } catch (IOException e) {
            System.out.println("Error during HTTP call:");
            e.printStackTrace();
            return false;

        }
    }

    public void endAllLabors(List<EmployeeDto> e){
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(e);
        } catch (JsonProcessingException jsonProcessingException) {
            jsonProcessingException.printStackTrace();
        }

        RequestBody body = RequestBody.create(json, MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url("http://localhost:8080/api/v1/labors/end/all")
                .put(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("Clocked out");
            } else {
                System.out.println("Failed to clock out: " + response);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public boolean createOrder(List<ObjectOrder> order){
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(order);
        } catch (JsonProcessingException jsonProcessingException) {
            jsonProcessingException.printStackTrace();
        }

        RequestBody body = RequestBody.create(json, MediaType.parse("application/json"));
        Request request = new Request.Builder()
                .url("http://localhost:8080/api/v1/orderss/insertAll")
                .post(body)
                .build();

        System.out.println("Request: " + request + "\n" + "Body: " + body + "\n" + "Json: " + json);

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("Order created");
                return true;
            } else {
                System.out.println("Failed to create order: " + response);
                return false;
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return false;
        }
    }


    public String getFoodNameById(Integer id){
        Request request = new Request.Builder()
                .url("http://localhost:8080/api/v1/foods/foodName/" + id)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
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

}

