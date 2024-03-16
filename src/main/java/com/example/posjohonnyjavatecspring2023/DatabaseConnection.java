package com.example.posjohonnyjavatecspring2023;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckMenuItem;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseConnection {
    private int restaurantId, dRate, orderNumber;
    private final ObjectEmployee user = new ObjectEmployee();
    private final String jdbcUrl = "jdbc:mysql://127.0.0.1:3307/pos_db";
    private final String username = "root";
    private final String password = "1234";

    public int getRestaurantId() {
        return restaurantId;
    }

    public int getdRate() {
        return dRate;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public ObjectEmployee getEmployee() {
        System.out.println("USER NIGGA:" + user.getEmployeeLname());
        return user;
    }

    public boolean validateToken(String token) {
        String query = "SELECT token, restaurant_id, restaurant_Drates FROM token INNER JOIN restaurant ON token.token = ? AND restaurant.restaurant_token = ?";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, token);
            statement.setString(2, token);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    restaurantId = resultSet.getInt("restaurant_id");
                    dRate = resultSet.getInt("restaurant_Drates");
                    System.out.println("Restaurant ID: " + restaurantId + " drate: " + dRate);

                    return false;
                } else {
                    System.out.println("Token or restaurant not found for token: " + token);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    public boolean validateEmployee(String username, String password) {
        String query = "SELECT employee_username, employee_status,employee_password, employee_id, employee_lname, employee_fname FROM employee WHERE employee_username = ? AND employee_password = ? AND restaurant_id = ?";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, this.username, this.password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);
            statement.setInt(3, restaurantId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {


                    user.setEmployeeId(resultSet.getInt("employee_id"));
                    user.setEmployeeFname(resultSet.getString("employee_fname"));
                    user.setEmployeeLname(resultSet.getString("employee_lname"));
                    user.setEmployeeStatus(resultSet.getString("employee_status").charAt(0));
                    System.out.println((user.getEmployeeLname().isBlank() ? "no lname" : user.getEmployeeLname()) + " " + (user.getEmployeeFname().isBlank() ? "no fname" : user.getEmployeeFname()) + " " + user.getEmployeeId());

                    System.out.println(user.getEmployeeId() + " " + user.getEmployeeFname() + " "
                            + user.getEmployeeLname() + " " + user.getEmployeeFname());

                    return true;
                } else {
                    System.out.println("Employee not found for username and password");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void clockIn() {
        String querry = "Insert into labor (restaurant_id, employee_id) values (?,?)";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, this.username, this.password);
             PreparedStatement statement = connection.prepareStatement(querry)) {
            statement.setInt(1, restaurantId);
            statement.setInt(2, user.getEmployeeId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int clockIn(String username, String password) {
        String query = "SELECT employee_username, employee_password, employee_id, employee_lname, employee_fname FROM employee WHERE employee_username = ? AND employee_password = ? AND restaurant_id = ?";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, this.username, this.password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);
            statement.setInt(3, restaurantId);

            try (ResultSet resultSet = statement.executeQuery()) { // Use executeQuery() here
                if (resultSet.next()) {
                    int id = resultSet.getInt("employee_id");
                    String querry = "Insert into labor (restaurant_id, employee_id) values (?,?)";
                    try (PreparedStatement statement2 = connection.prepareStatement(querry)) {
                        statement2.setInt(1, restaurantId);
                        statement2.setInt(2, id);
                        statement2.executeUpdate(); // This is fine because it's an INSERT statement
                        System.out.print("Employee clocked in " + id);
                        return id;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Employee not found for username and password");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public void clockOut() {
        String querry = "Update labor set labor_end = CURRENT_TIMESTAMP where restaurant_id = ? and employee_id = ? and labor_end is null";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, this.username, this.password);
             PreparedStatement statement = connection.prepareStatement(querry)) {
            statement.setInt(1, restaurantId);
            statement.setInt(2, user.getEmployeeId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int clockOut(int employeeId){

        String querry = "Update labor set labor_end = CURRENT_TIMESTAMP where restaurant_id = ? and employee_id = ? and labor_end is null";

            try (Connection connection = DriverManager.getConnection(jdbcUrl, this.username, this.password);
                PreparedStatement statement = connection.prepareStatement(querry)) {
                statement.setInt(1, restaurantId);
                statement.setInt(2, employeeId);
                statement.executeUpdate();
                return employeeId;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return 0;
    }

    public ObservableList<ObjectFood> getAllFood() {
        String querry = "SELECT food.food_id, food.restaurant_id, food.food_name, food.food_price, food_category.food_category_id, food_category.food_category_category " +
                "FROM food " +
                "JOIN food_category ON food.food_id = food_category.food_id " +
                "WHERE restaurant_id = ? " +
                "GROUP BY food.food_id, food.restaurant_id, food.food_name, food.food_price, food_category.food_category_id, food_category.food_category_category " +
                "ORDER BY food.food_id ASC;";
        System.out.println(querry);
        String querry2 = "Select count(*) from food where restaurant_id = ?";
        int count = 0;
        try (Connection connection = DriverManager.getConnection(jdbcUrl, this.username, this.password);
             PreparedStatement statement = connection.prepareStatement(querry);
             PreparedStatement statement2 = connection.prepareStatement(querry2)) {
            statement.setInt(1, restaurantId);
            statement2.setInt(1, restaurantId);
            try (ResultSet resultSet = statement2.executeQuery()) {
                if (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                ObservableList<ObjectFood> food = FXCollections.observableArrayList();
                int i = 0;
                while (resultSet.next()) {
                    food.add(new ObjectFood(resultSet.getInt("food_id"),
                            resultSet.getString("food_name"), resultSet.getDouble("food_price"),
                            resultSet.getString("food_category_category").charAt(0),
                            getIngrediant(resultSet.getInt("food_id")),
                            "com/example/posjohonnyjavatecspring2023/Image Food/image-" + resultSet.getInt("food_id") + ".png"));
                    System.out.print(food.toString());
                    i++;
                }
                return food;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return FXCollections.observableArrayList();
    }

    public String[] getIngrediant(int food_id) {
        String querry = "Select ingrediant_name " +
                "from ingrediant_names join ingrediant " +
                "on ingrediant_names.ingrediant_names_id = ingrediant.ingrediant_names_id " +
                "where ingrediant.food_id = ?";
        String querry2 = "Select count(*) from ingrediant where food_id = ?";
        int count = 0;
        try (Connection connection = DriverManager.getConnection(jdbcUrl, this.username, this.password);
             PreparedStatement statement = connection.prepareStatement(querry);
             PreparedStatement statement2 = connection.prepareStatement(querry2)) {
            statement.setInt(1, food_id);
            statement2.setInt(1, food_id);
            try (ResultSet resultSet = statement2.executeQuery()) {
                if (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                String[] ingrediant = new String[count];
                int i = 0;
                while (resultSet.next()) {
                    ingrediant[i] = resultSet.getString("ingrediant_name");
                    i++;
                }
                System.out.println(ingrediant.toString());
                return ingrediant;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new String[0];
    }

    public void insertOrder(ObjectOrder order) {
        String querry = "Insert into orders (restaurant_id, employee_id, food_id, order_number) values (?,?,?,?)";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, this.username, this.password);
             PreparedStatement statement = connection.prepareStatement(querry)) {
            statement.setInt(1, restaurantId);
            statement.setInt(2, user.getEmployeeId());
            statement.setInt(3, order.getId());
            statement.setInt(4, orderNumber);
            statement.executeUpdate();
            insertOrderList(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertOrderList(ObjectOrder order) {
        String querry = "Insert into orderlist (food) values (?)";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, this.username, this.password);
             PreparedStatement statement = connection.prepareStatement(querry)) {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(order);
            statement.setString(1, jsonString);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ObservableList<ObjectOrderList> getAllOrderList() {
        String querry = "Select orderList_id, food from orderlist";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, this.username, this.password);
             PreparedStatement statement = connection.prepareStatement(querry);
             ResultSet resultSet = statement.executeQuery()) {
            ObservableList<ObjectOrderList> orderList = FXCollections.observableArrayList();
            while (resultSet.next()) {
                ObjectMapper objectMapper = new ObjectMapper();
                ObjectOrder order = objectMapper.readValue(resultSet.getString("food"), ObjectOrder.class);
                int id = resultSet.getInt("orderList_id");
                orderList.add(new ObjectOrderList(order, id));
            }
            return orderList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return FXCollections.observableArrayList();

    }

    public void deleteOrderList(int id) {
        String querry = "Delete from orderlist where orderList_id = ?";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, this.username, this.password);
             PreparedStatement statement = connection.prepareStatement(querry)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Deleted orderlist with id: " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CheckMenuItem[] getAllIngrediantNames(){
        String querry = "Select ingrediant_names_id, ingrediant_name from ingrediant_names";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, this.username, this.password);
             PreparedStatement statement = connection.prepareStatement(querry);
             ResultSet resultSet = statement.executeQuery()) {
            ArrayList<CheckMenuItem> ingrediantNames = new ArrayList<>();
            while (resultSet.next()) {
                CheckMenuItem menuItem = new CheckMenuItem(resultSet.getString("ingrediant_name"));
                menuItem.setId(resultSet.getInt("ingrediant_names_id") + "");
                ingrediantNames.add(menuItem);
            }
            return ingrediantNames.toArray(new CheckMenuItem[0]);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new CheckMenuItem[0];
    }

    public void addFood(String name, double price, ArrayList<String> ingrediants, ArrayList<String> categories) {
        String querry = "Insert into food (restaurant_id, food_name, food_price) values (?,?,?)";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, this.username, this.password);
             PreparedStatement statement = connection.prepareStatement(querry)) {
            statement.setInt(1, restaurantId);
            statement.setString(2, name);
            statement.setDouble(3, price);
            statement.executeUpdate();
            int id = getLastFoodId();
            addIngrediants(id, ingrediants);
            addCategories(id, categories);
            System.out.println("Added food with id: " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getLastFoodId() {
        String querry = "Select food_id from food where restaurant_id = ? order by food_id desc limit 1";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, this.username, this.password);
             PreparedStatement statement = connection.prepareStatement(querry)) {
            statement.setInt(1, restaurantId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("food_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void addIngrediants(int foodId, ArrayList<String> ingrediants) {
        String querry = "Insert into ingrediant (food_id, ingrediant_names_id) values (?,?)";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, this.username, this.password);
             PreparedStatement statement = connection.prepareStatement(querry)) {
            for (String ingrediant : ingrediants) {
                statement.setInt(1, foodId);
                statement.setInt(2, getIngrediantId(ingrediant));
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addCategories(int foodId, ArrayList<String> categories) {
        String querry = "Insert into food_category (food_id, food_category_category) values (?,?)";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, this.username, this.password);
             PreparedStatement statement = connection.prepareStatement(querry)) {
            for (String category : categories) {
                statement.setInt(1, foodId);
                statement.setInt(2, category.toLowerCase().charAt(0));
                statement.executeUpdate();
                System.out.println("Added to categorie");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public int getFoodId(String name){
        String querry = "Select food_id from food where restaurant_id = ? and food_name = ?";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, this.username, this.password);
             PreparedStatement statement = connection.prepareStatement(querry)) {
            statement.setInt(1, restaurantId);
            statement.setString(2, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("food_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getIngrediantId(String ingrediantName) {
        String querry = "Select ingrediant_names_id from ingrediant_names where ingrediant_name = ?";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, this.username, this.password);
             PreparedStatement statement = connection.prepareStatement(querry)) {
            statement.setString(1, ingrediantName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("ingrediant_names_id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void deleteFood(int id) {
        String querry = "Delete from food where food_id = ?";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, this.username, this.password);
             PreparedStatement statement = connection.prepareStatement(querry)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Deleted food with id: " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
