package jsges.nails.DTO.Organizacion;
import lombok.Data;

import java.util.Date;
@Data
public class ClienteDTO {

    private long id;
    private String razonSocial;
    private String letra;
    private String contacto;
    private String celular;
    private String mail;
    private Date fechaInicio;
    private Date fechaNacimiento;

    
}
