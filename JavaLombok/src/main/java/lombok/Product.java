package lombok;

@Data
@ToString(exclude = {
        "price"
})
@AllArgsConstructor
@RequiredArgsConstructor
public class Product {

    private final String id;
    private String name;
    private Long price;
}
