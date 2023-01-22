package lombok;

@Getter

//Bikin constructor yang akan membuat static methodnya
@NoArgsConstructor(staticName = "createEmpty")
@AllArgsConstructor(staticName = "create")

//Annotation ToString
@ToString(exclude = {
        "password"
})
public class Login {

    @Setter(value = AccessLevel.PROTECTED)
    private String username;

    @Setter(value = AccessLevel.PROTECTED)
    private String password;

}
