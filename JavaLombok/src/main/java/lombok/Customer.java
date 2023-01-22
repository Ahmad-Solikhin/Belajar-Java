package lombok;

//Memberikan Getter
@Getter

//Memberikan Setter
@Setter
//Membuat constructor kosong
@NoArgsConstructor

//membuat constructor semua parameter
@AllArgsConstructor

//Annotation equals dan hascode
@EqualsAndHashCode(exclude = {
        "name"
})
public class Customer {

    private String id;
    private String name;
}
