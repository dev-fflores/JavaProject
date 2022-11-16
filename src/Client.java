import java.util.ArrayList;
import java.util.List;

public class Client {
    //must be private to getter and setter can control the out put of the data

    private String numberPhone;
    private String name;
    private int age;
    private String gender;
    private String typeClient;

    private List<Product> shopping;

    public Client() {
    }

    public Client(String numberPhone, String name, int age, String gender, String typeClient) {
        super();
        this.numberPhone = numberPhone;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.typeClient = typeClient;
        this.shopping = new ArrayList<>();
    }


    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTypeClient() {
        return typeClient;
    }

    public void setTypeClient(String typeClient) {
        this.typeClient = typeClient;
    }

    @Override
    public String toString() {
        return "Client [Numphone=" + numberPhone + ", name=" + name + ", age=" + age + ", gender=" + gender
                + ", typeClient=" + typeClient + "]";
    }

    public List<Product> getShopping() {
        return shopping;
    }

    public void setShopping(List<Product> shopping) {
        this.shopping = shopping;
    }
    public void addProductToShopping(Product product){
        this.shopping.add(product);
    }
}
