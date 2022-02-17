package in.nareshit.raghu.entity;

//ctrl+shift+O
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="spec_tab")
public class Specialization {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //For MySQL
	//@GeneratedValue(strategy = GenerationType.SEQUENCE) //For Oracle
	@Column(name="spec_id_col")
	private Long specId;
	
	@Column(name="spec_code_col")
	private String specCode;
	
	@Column(name="spec_name_col")
	private String specName;
	
	@Column(name="spec_note_col")
	private String specNote;
}
