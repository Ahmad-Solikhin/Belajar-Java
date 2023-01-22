package lombok;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Manager extends Employee{

    private Integer totalEmployee;
}
